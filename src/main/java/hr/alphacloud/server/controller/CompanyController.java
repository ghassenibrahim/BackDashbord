package hr.alphacloud.server.controller;

import hr.alphacloud.server.model.command.account.CompanyFilter;
import hr.alphacloud.server.model.command.account.CompanySaveCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.account.CompanyDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.service.company.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/company")
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/find-company")
    public ApiBaseDTO<CompanyDTO> findCompany(@Valid @RequestBody ApiBaseCommand<CompanyFilter> command) {
        return companyService.findCompany(command);
    }

    @PostMapping("/find-all")
    public ApiBasePageDTO<CompanyDTO> findAll(@Valid @RequestBody ApiBasePageCommand<CompanyFilter> command) {
        return companyService.findAll(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@Valid @RequestBody ApiBaseCommand<CompanyFilter> command) {
        return companyService.delete(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<CompanyDTO> save(@Valid @RequestBody ApiBaseCommand<CompanySaveCommand> command) {
        return companyService.save(command);
    }
}
