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
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;

public interface ReportService {

    ApiBaseDTO<ReportDTO> findReport(ApiBaseCommand<ReportFilterCommand> command);

    ApiBasePageDTO<ReportDTO> filterReport(ApiBasePageCommand<ReportFilterCommand> command);

    ApiBaseDTO<ReportDTO> saveReport(ApiBaseCommand<ReportSaveCommand> command);

    ApiBaseDTO<Boolean> deleteReport(ApiBaseCommand<DeleteCommand> command);

    ApiBaseDTO<String> getLastImportDate(ApiBaseCommand<Long> command);

    ApiBaseDTO<LinkedList<LinkedList<String>>> processCsv(ApiBaseCommand<ProcessCsvCommand> command,
                                                          MultipartFile file);

    String getLastImportDate(Long id);

}
