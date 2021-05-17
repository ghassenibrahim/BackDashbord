package hr.alphacloud.server.service.settings.account_book;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.account_book.AccountBookFilterCommand;
import hr.alphacloud.server.model.command.settings.account_book.AccountBookSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.dto.settings.AccountBookDTO;
import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import hr.alphacloud.server.model.specifications.SharedSpecification;
import hr.alphacloud.server.repository.settings.AccountBookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountBookSettingsServiceImpl implements AccountBookSettingsService {

    private final AccountBookRepository accountBookRepository;

    public AccountBookSettingsServiceImpl(AccountBookRepository accountBookRepository) {
        this.accountBookRepository = accountBookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ApiBasePageDTO<AccountBookDTO> filter(ApiBasePageCommand<AccountBookFilterCommand> command) {
        return fetchAccountBooks(command);
    }

    public ApiBaseDTO<List<AccountBookDTO>> filterList(ApiBaseCommand<AccountBookFilterCommand> command) {
        List<AccountBook> accountBookList = this.accountBookRepository.findAllByCompanyId(command.getCommand().getCompanyId());
        return ApiBaseDTO.generateSuccessResponse(AccountBookDTO.of(accountBookList));
    }

    /**
     * TODO 3x similar filters, improve if possible
     * Filter list for report edit component
     */
    @Override
    public List<AccountBookDTO> filterList(Long companyId, Pageable pageable) {
        Specification<AccountBook> specification = SharedSpecification.getNumericValueEqual(AccountBook.class,
                companyId, AccountBook.COMPANY_ID);

        final Page<AccountBook> sectors = this.accountBookRepository.findAll(specification, pageable);
        return AccountBookDTO.of(sectors.getContent());
    }

    /**
     * Filters account books
     *
     * @param command - filter command
     * @return Paged account book DTO-s
     */
    private ApiBasePageDTO<AccountBookDTO> fetchAccountBooks(ApiBasePageCommand<AccountBookFilterCommand> command) {
        final Pageable pageable = command.getPaginationAndSorting().generatePagingAndSortingRequest();
        Specification<AccountBook> specification = SharedSpecification.getNumericValueEqual(AccountBook.class, command.getCommand().getCompanyId(), AccountBook.COMPANY_ID)
                .and(SharedSpecification.getNumericValueEqual(AccountBook.class, command.getCommand().getCode(), AccountBook.CODE))
                .and(SharedSpecification.getStringValueEquals(AccountBook.class, command.getCommand().getName(), AccountBook.NAME));

        final Page<AccountBook> accountBookPage = this.accountBookRepository.findAll(specification, pageable);
        return ApiBasePageDTO.generateSuccessResponse(AccountBookDTO.of(accountBookPage.getContent()),
                PageDTO.ofPage(accountBookPage));
    }

    @Override
    public ApiBaseDTO<AccountBookDTO> save(ApiBaseCommand<AccountBookSaveCommand> command) {
        AccountBook accountBook;
        if (command.getCommand().getId() != null) {
            Optional<AccountBook> accountBookOptional = this.accountBookRepository
                    .findByIdAndCompanyId(command.getCommand().getId(), command.getCommand().getCompanyId());

            if (accountBookOptional.isPresent()) {
                accountBook = accountBookOptional.get();
                command.getCommand().updateEntity(accountBook);
            } else {
                return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
            }
        } else {
            accountBook = command.getCommand().convertToEntity();
        }
        this.accountBookRepository.save(accountBook);
        return ApiBaseDTO.generateSuccessResponse(AccountBookDTO.of(accountBook));
    }

    @Override
    public ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command) {
        final Optional<AccountBook> accountBookOptional = this.accountBookRepository
                .findByIdAndCompanyId(command.getCommand().getId(), command.getCommand().getCompanyId());

        return accountBookOptional.map(accountBook -> {
            this.accountBookRepository.delete(accountBook);

            return ApiBaseDTO.generateSuccessResponse(true);
        }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

}