package hr.alphacloud.server.service.company;


import hr.alphacloud.server.model.command.account.CompanyFilter;
import hr.alphacloud.server.model.command.account.CompanySaveCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.account.CompanyDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;


public interface CompanyService {
    ApiBaseDTO<CompanyDTO> findCompany(ApiBaseCommand<CompanyFilter> command);

    ApiBasePageDTO<CompanyDTO> findAll(ApiBasePageCommand<CompanyFilter> command);

    ApiBaseDTO<Boolean> delete(ApiBaseCommand<CompanyFilter> command);

    ApiBaseDTO<CompanyDTO> save(ApiBaseCommand<CompanySaveCommand> command);
}
