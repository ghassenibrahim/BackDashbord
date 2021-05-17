package hr.alphacloud.server.service.customer_supplier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.customer_supplier.CustomerSupplierFilterCommand;
import hr.alphacloud.server.model.command.customer_supplier.base.AbstractCustomerSupplierSaveCommand;
import hr.alphacloud.server.model.dto.CustomerSupplierDTO;
import hr.alphacloud.server.model.dto.ReportDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.settings.*;
import hr.alphacloud.server.model.entity.reporting.base.AbstractCustomerSupplier;
import hr.alphacloud.server.model.entity.reporting.base.CustomerSupplierProperties;
import hr.alphacloud.server.repository.ReportRepository;
import hr.alphacloud.server.repository.base.BaseReportRepository;
import hr.alphacloud.server.util.AbstractReportUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Abstract service implementation for Customer, Supplier, Customer advance, Supplier advance, Given loans and
 * Received loans, receives Entity and its respective Repository
 *
 * @param <T> Repository
 * @param <R> Entity
 */
public abstract class AbstractCustomerSupplierServiceImpl<T extends AbstractCustomerSupplier,
        R extends BaseReportRepository<T>> implements CustomerSupplierService<T> {

    protected final ReportRepository reportRepository;
    protected final AbstractReportUtil abstractReportUtil;
    protected final R repository;

    public AbstractCustomerSupplierServiceImpl(ReportRepository reportRepository,
                                               AbstractReportUtil abstractReportUtil, R repository) {
        this.reportRepository = reportRepository;
        this.abstractReportUtil = abstractReportUtil;
        this.repository = repository;
    }

    /**
     * Filters and pages entities
     *
     * @param command - filter command
     * @param entity  - {@link AbstractCustomerSupplier} subclass
     * @return - ApiBasePageDTO
     */
    @Override
    @Transactional
    public ApiBaseDTO<List<CustomerSupplierDTO>> filter(ApiBasePageCommand<CustomerSupplierFilterCommand> command, Class<T> entity) {
        List<T> customerList = this.repository.findAllByReportId(command.getCommand().getReportId());
        List<CustomerSupplierDTO> customerSupplierDTOS = new ArrayList<>();
        customerList.forEach(item -> {
            customerSupplierDTOS.add(CustomerSupplierDTO.of(item));
        });

        return ApiBaseDTO.generateSuccessResponse(customerSupplierDTOS);
    }

    /**
     * Finds all <T>Entities, assign properties to them, populates the settings and data for return.
     */
    @Override
    @Transactional
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<CustomerSupplierFilterCommand>
                                                                     command, Class<T> entity) {
        List<T> objectsList = this.repository.findAllByReportId(
                command.getCommand().getReportId());
        List<ReportTableDataDTO> reportTableDataListDTO = new ArrayList<>();

        objectsList.forEach(object -> {
            List<CustomerSupplierProperties> customerSupplierProperties = object.getCustomerSupplierProperties();

            if (customerSupplierProperties != null) {
                try {
                    String json = new ObjectMapper().writeValueAsString(customerSupplierProperties);
                    customerSupplierProperties = new ObjectMapper().readValue(json, new TypeReference<>() {
                    });
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }

                List<List<String>> finalList = new ArrayList<>();
                customerSupplierProperties.forEach(item -> {
                    finalList.add(item.toList());
                });
                ReportTableDataDTO reportTableDataDTO = new ReportTableDataDTO();
                reportTableDataDTO.setProperties(CustomerSupplierProperties.getPropertyList());
                reportTableDataDTO.setSpendingLocation(SpendingLocationDTO.of(object.getSpendingLocation()));
                reportTableDataDTO.setPremises(PremisesDTO.fromEntity(object.getPremises()));
                reportTableDataDTO.setSectorType(SectorTypeDTO.fromEntity(object.getSectorType()));
                reportTableDataDTO.setAccountBook(AccountBookDTO.of(object.getAccountBook()));
                reportTableDataDTO.setReport(ReportDTO.of(object.getReport()));
                reportTableDataDTO.setImportSettings(ImportSettingsDTO.of(object.getImportSettings()));
                reportTableDataDTO.setId(object.getId());
                reportTableDataDTO.setData(finalList);
                reportTableDataDTO.setImportType(object.getImportType());
                reportTableDataListDTO.add(reportTableDataDTO);
            }
        });

        return ApiBaseDTO.generateSuccessResponse(reportTableDataListDTO);
    }

    /**
     * Saves or updates entity
     *
     * @param saveCommand - save command
     * @return CustomerSupplierDTO
     */
    @Override
    @Transactional
    public <S extends AbstractCustomerSupplierSaveCommand<T>> ApiBaseDTO<Boolean> save(ApiBaseCommand<S> saveCommand) {
        Optional<T> optionalCustomerClass = repository.findById(saveCommand.getCommand().getId());
        if (optionalCustomerClass.isPresent()) {
            T customerClass = saveCommand.getCommand().convertToEntity();
            customerClass.setImportSettings(optionalCustomerClass.get().getImportSettings());
            this.repository.save(customerClass);
        } else {
            final T customerClass = saveCommand.getCommand().convertToEntity();
            this.repository.save(customerClass);
        }

        return ApiBaseDTO.generateSuccessResponse(true);
    }

    /**
     * Deletes entity
     *
     * @param deleteCommand - delete command
     * @return - true if entity is successfully deleted
     */
    @Override
    @Transactional
    public ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> deleteCommand) {
        //TODO remove if delete happens only from report service
        return null;
    }
}
