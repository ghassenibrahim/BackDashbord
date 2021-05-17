package hr.alphacloud.server.service;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.ProcessCsvCommand;
import hr.alphacloud.server.model.command.ReportFilterCommand;
import hr.alphacloud.server.model.command.ReportSaveCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.ReportDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.entity.reporting.Report;
import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import hr.alphacloud.server.model.entity.reporting.settings.ImportSettings;
import hr.alphacloud.server.repository.ReportRepository;
import hr.alphacloud.server.repository.settings.AccountBookRepository;
import hr.alphacloud.server.service.settings.positions.PositionsServiceImpl;
import hr.alphacloud.server.specification.ReportSpecification;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final AccountBookRepository accountBookRepository;
    private final PositionsServiceImpl positionsService;

    public ReportServiceImpl(ReportRepository reportRepository, AccountBookRepository accountBookRepository, PositionsServiceImpl positionsService) {
        this.reportRepository = reportRepository;
        this.accountBookRepository = accountBookRepository;
        this.positionsService = positionsService;
    }

    @Override
    @Transactional
    public ApiBaseDTO<ReportDTO> findReport(ApiBaseCommand<ReportFilterCommand> command) {
        final Optional<Report> reportOptional = this.reportRepository
                .findById(command.getCommand().getId());
        return reportOptional.map(report -> ApiBaseDTO.generateSuccessResponse(ReportDTO.of(report)))
                .orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

    @Override
    @Transactional
    public ApiBasePageDTO<ReportDTO> filterReport(ApiBasePageCommand<ReportFilterCommand> command) {
        final Page<Report> reportPage = this.reportRepository
                .findAll(ReportSpecification.getReportFilterSpecification(command.getCommand()),
                        command.getPaginationAndSorting().generatePagingAndSortingRequest());
        return ApiBasePageDTO.generateSuccessResponse(ReportDTO.of(reportPage.getContent()),
                PageDTO.ofPage(reportPage));
    }

    @Override
    public ApiBaseDTO<String> getLastImportDate(ApiBaseCommand<Long> command) {
        return ApiBaseDTO.generateSuccessResponse(this.getLastImportDate(command.getCommand()));
    }

    @Transactional(readOnly = true)
    public String getLastImportDate(Long id) {
        final Optional<Report> reportOptional = this.reportRepository.findFirstByCompanyIdOrderByAddedOnAsc(id);
        return reportOptional.map(report -> report.getAddedOn().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).orElse("");
    }

    @Override
    @Transactional
    public ApiBaseDTO<ReportDTO> saveReport(ApiBaseCommand<ReportSaveCommand> command) {
        List<AccountBook> accountBook = this.accountBookRepository.findAllByCompanyId(command.getCommand().getCompanyId());
        List<AccountBook> accountBookToSave = new ArrayList<>();
        Report report = command.getCommand().convertToEntity();
        report.getBruttoBalanceList().forEach(balance -> balance.getBruttoBalanceProperties().forEach(property -> {
            if (accountBook.size() == 0) {
                if (property.getKonto() != null) {
                    AccountBook tempAccount = new AccountBook();
                    tempAccount.setCompanyId(command.getCommand().getCompanyId());
                    tempAccount.setName(property.getKontoName());
                    tempAccount.setCode(property.getKonto());
                    accountBookToSave.add(tempAccount);
                }
            } else {
                accountBook.forEach(konto -> {
                    if (!property.getKonto().equals(konto.getCode()) && property.getKonto() != null) {
                        accountBookToSave.forEach(kontoToSave -> {
                            if (!property.getKonto().equals(kontoToSave.getCode())) {
                                AccountBook tempAccount = new AccountBook();
                                tempAccount.setCompanyId(command.getCommand().getCompanyId());
                                tempAccount.setName(property.getKontoName());
                                tempAccount.setCode(property.getKonto());
                                accountBookToSave.add(tempAccount);
                            }
                        });
                    }
                });
            }

        }));
        this.accountBookRepository.saveAll(accountBookToSave);
        this.positionsService.fetchAllDistinctKontos(command.getCommand().getCompanyId());
        return ApiBaseDTO.generateSuccessResponse(ReportDTO.of(this.reportRepository.save(report)));
    }

    @Override
    public ApiBaseDTO<Boolean> deleteReport(ApiBaseCommand<DeleteCommand> command) {
        Optional<Report> reportOptional = this.reportRepository
                .findByIdAndCompanyId(command.getCommand().getId(), command.getCommand().getCompanyId());

        return reportOptional.map(report -> {
            this.reportRepository.delete(report);
            return ApiBaseDTO.generateSuccessResponse(true);
        }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

    @Override
    public ApiBaseDTO<LinkedList<LinkedList<String>>> processCsv(ApiBaseCommand<ProcessCsvCommand> command,
                                                                 MultipartFile file) {
        if (file == null) {
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.CSV_FILE_NULL);
        }
        ImportSettings settings = command.getCommand().getImportSettings();
        try {
            LinkedList<LinkedList<String>> data = this.prepareList(file);
            for (int i = settings.getSkipFirstRowAmount(); i > 0; i--) {
                data.removeFirst();
            }
            for (int i = settings.getSkipLastRowAmount(); i > 0; i--) {
                data.removeLast();
            }
            for (int i = settings.getSkipFirstColumnAmount(); i > 0; i--) {
                data.forEach(LinkedList::removeFirst);
            }
            for (LinkedList<String> row : data) {
                if (row.size() < settings.getMinimumAcceptedColumnLength()) {
                    return ApiBaseDTO.generateErrorResponse(ErrorInfo.CSV_NOT_ENOUGH_COLUMNS);
                }
            }
            return ApiBaseDTO.generateSuccessResponse(data);
        } catch (IOException ex) {
            System.out.println("fail");
        }
        return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
    }

    private LinkedList<LinkedList<String>> prepareList(MultipartFile file) throws IOException {
        LinkedList<LinkedList<String>> data = new LinkedList<>();
        InputStreamReader streamReader = new InputStreamReader(file.getInputStream());
        Iterable<CSVRecord> records = CSVParser.parse(streamReader, CSVFormat.DEFAULT);
        for (CSVRecord record : records) {
            LinkedList<String> row = new LinkedList<>();
            for (String s : record) {
                row.add(s);

            }
            data.add(row);
        }
        return data;
    }

}
