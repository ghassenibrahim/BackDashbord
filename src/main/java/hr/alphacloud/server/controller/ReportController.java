package hr.alphacloud.server.controller;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.command.*;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.ReportDTO;
import hr.alphacloud.server.model.dto.ReportSettingsDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.service.ReportDataService;
import hr.alphacloud.server.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.LinkedList;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/report")
public class ReportController {

    private final ReportService reportService;
    private final ReportDataService reportDataService;


    @PostMapping("/find-report")
    public ApiBaseDTO<ReportDTO> findReport(@RequestBody ApiBaseCommand<ReportFilterCommand> command) {
        return reportService.findReport(command);
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<ReportDTO> filter(@RequestBody ApiBasePageCommand<ReportFilterCommand> command) {
        return reportService.filterReport(command);
    }

    @PostMapping("/import-date")
    public ApiBaseDTO<String> getLastImportDate(@RequestBody ApiBaseCommand<Long> command) {
        return reportService.getLastImportDate(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<ReportDTO> saveReport(@RequestBody ApiBaseCommand<ReportSaveCommand> command) {
        return reportService.saveReport(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> deleteReport(@RequestBody ApiBaseCommand<DeleteCommand> command) {
        return reportService.deleteReport(command);
    }

    @SneakyThrows
    @PostMapping(path = "/process-csv")
    public ApiBaseDTO<LinkedList<LinkedList<String>>> processCsv(
            @RequestPart String cmd,
            @RequestPart MultipartFile file) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JavaType type = mapper.getTypeFactory().constructParametricType(
                ApiBaseCommand.class, ProcessCsvCommand.class);

        ApiBaseCommand<ProcessCsvCommand> command = mapper.readValue(cmd, type);
        return reportService.processCsv(command, file);
    }

    @PostMapping("/data/filter")
    public ApiBaseDTO<ReportSettingsDataDTO> getReportSettingsData(@Valid @RequestBody ApiBasePageCommand<ReportDataFilterCommand> command) {
        return this.reportDataService.getReportSettingsData(command);
    }

}
