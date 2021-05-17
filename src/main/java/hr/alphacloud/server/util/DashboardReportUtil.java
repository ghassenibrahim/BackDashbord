package hr.alphacloud.server.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.dto.dashboard.ChartsDTO;
import hr.alphacloud.server.model.entity.reporting.*;
import hr.alphacloud.server.model.entity.reporting.base.CustomerSupplierProperties;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.*;
import hr.alphacloud.server.model.enums.report.ExcelType;
import hr.alphacloud.server.repository.*;
import hr.alphacloud.server.repository.customer_supplier.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class DashboardReportUtil {
    private static SalesRecapRepository salesRecapRepository;
    private static BruttoBalanceRepository bruttoBalanceRepository;
    private static PurchaseRecapRepository purchaseRecapRepository;
    private static SupplyAnalyticsRepository supplyAnalyticsRepository;
    private static ReceivableMaturityRepository receivableMaturityRepository;
    private static CustomerRepository customerRepository;
    private static SupplierRepository supplierRepository;
    private static CustomerAdvancesRepository customerAdvancesRepository;
    private static SupplierAdvanceRepository supplierAdvanceRepository;
    private static LoanGivenRepository loanGivenRepository;
    private static LoanReceivedRepository loanReceivedRepository;

    public DashboardReportUtil(SalesRecapRepository salesRecapRepository,
                               BruttoBalanceRepository bruttoBalanceRepository,
                               PurchaseRecapRepository purchaseRecapRepository,
                               SupplyAnalyticsRepository supplyAnalyticsRepository,
                               ReceivableMaturityRepository receivableMaturityRepository,
                               CustomerRepository customerRepository,
                               SupplierRepository supplierRepository,
                               CustomerAdvancesRepository customerAdvancesRepository,
                               SupplierAdvanceRepository supplierAdvanceRepository,
                               LoanGivenRepository loanGivenRepository,
                               LoanReceivedRepository loanReceivedRepository) {
        DashboardReportUtil.salesRecapRepository = salesRecapRepository;
        DashboardReportUtil.bruttoBalanceRepository = bruttoBalanceRepository;
        DashboardReportUtil.purchaseRecapRepository = purchaseRecapRepository;
        DashboardReportUtil.supplyAnalyticsRepository = supplyAnalyticsRepository;
        DashboardReportUtil.receivableMaturityRepository = receivableMaturityRepository;
        DashboardReportUtil.customerRepository = customerRepository;
        DashboardReportUtil.supplierRepository = supplierRepository;
        DashboardReportUtil.customerAdvancesRepository = customerAdvancesRepository;
        DashboardReportUtil.supplierAdvanceRepository = supplierAdvanceRepository;
        DashboardReportUtil.loanGivenRepository = loanGivenRepository;
        DashboardReportUtil.loanReceivedRepository = loanReceivedRepository;
    }

    /**
     * Fetch data from DB. Extract JSON data based on parameters received.
     *
     * @param excelType-   table required from DB.
     * @param chartColumn- column from a table
     * @param chartRow-    column from a table
     * @param chartValue-  column from a table
     */
    public static List<ChartsDTO> fetchData(ExcelType excelType,
                                            String chartColumn,
                                            String chartRow,
                                            String chartValue) {
        List<ChartsDTO> chartsDTO = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        switch (excelType) {
            case BRUTO_BALANCE:
                List<BruttoBalance> bruttoBalanceList;
                bruttoBalanceList = bruttoBalanceRepository.findAllByReportId((long) 4);
                bruttoBalanceList.forEach(item -> {
                    List<BruttoBalanceProperties> bruttoBalanceProperties = objectMapper.convertValue(item.getBruttoBalanceProperties(),
                            new TypeReference<>() {
                            });
                    for (BruttoBalanceProperties bruttoBalanceProperty : bruttoBalanceProperties) {
                        Field[] fields = bruttoBalanceProperty.getClass().getDeclaredFields();
                        chartsDTO.add(extractData(fields, chartColumn, chartRow, chartValue, bruttoBalanceProperty));
                    }
                });
                break;
            case SALES_RECAP:
                List<SalesRecap> salesRecapArrayList;
                salesRecapArrayList = salesRecapRepository.findAllByReportId((long) 3);
                salesRecapArrayList.forEach(item -> {
                    List<SalesRecapProperties> salesRecapPropertiesList = objectMapper.convertValue(item.getSalesRecapProperties(),
                            new TypeReference<>() {
                            });
                    for (SalesRecapProperties salesRecapProperties : salesRecapPropertiesList) {
                        Field[] fields = salesRecapProperties.getClass().getDeclaredFields();
                        chartsDTO.add(extractData(fields, chartColumn, chartRow, chartValue, salesRecapProperties));
                    }
                });
                break;
            case PURCHASE_RECAP:
                List<PurchaseRecap> purchaseRecapList;
                purchaseRecapList = purchaseRecapRepository.findAllByReportId((long) 3);
                purchaseRecapList.forEach(item -> {
                    List<PurchaseRecapProperties> purchaseRecapPropertiesList = objectMapper.convertValue(item.getPurchaseRecapProperties(),
                            new TypeReference<>() {
                            });
                    for (PurchaseRecapProperties purchaseRecapProperties : purchaseRecapPropertiesList) {
                        Field[] fields = purchaseRecapProperties.getClass().getDeclaredFields();
                        chartsDTO.add(extractData(fields, chartColumn, chartRow, chartValue, purchaseRecapProperties));
                    }
                });
                break;
            case SUPPLY_ANALYTIC:
                List<SupplyAnalytics> supplyAnalyticsList;
                supplyAnalyticsList = supplyAnalyticsRepository.findAllByReportId((long) 3);
                supplyAnalyticsList.forEach(item -> {
                    List<SupplyAnalyticsProperties> supplyAnalyticsPropertiesList = objectMapper.convertValue(item.getSupplyAnalyticsProperties(),
                            new TypeReference<>() {
                            });
                    for (SupplyAnalyticsProperties supplyAnalyticsProperties : supplyAnalyticsPropertiesList) {
                        Field[] fields = supplyAnalyticsProperties.getClass().getDeclaredFields();
                        chartsDTO.add(extractData(fields, chartColumn, chartRow, chartValue, supplyAnalyticsProperties));
                    }
                });
                break;
            case RECEIVABLE_MATURITY:
                List<ReceivableMaturity> receivableMaturityList;
                receivableMaturityList = receivableMaturityRepository.findAllByReportId((long) 3);
                receivableMaturityList.forEach(item -> {
                    List<ReceivableMaturityProperties> receivableMaturityPropertiesList = objectMapper.convertValue(item.getReceivableMaturityProperties(),
                            new TypeReference<>() {
                            });
                    for (ReceivableMaturityProperties receivableMaturityProperties : receivableMaturityPropertiesList) {
                        Field[] fields = receivableMaturityProperties.getClass().getDeclaredFields();
                        chartsDTO.add(extractData(fields, chartColumn, chartRow, chartValue, receivableMaturityProperties));
                    }
                });
                break;
            case CUSTOMER:
                List<Customer> customerList;
                customerList = customerRepository.findAllByReportId((long) 3);
                customerList.forEach(item -> {
                    List<CustomerSupplierProperties> customerSupplierProperties = item.getCustomerSupplierProperties();
                    extractCustomerData(objectMapper, customerSupplierProperties, chartsDTO, chartColumn, chartRow, chartValue);
                });
                break;
            case SUPPLIER:
                List<Supplier> supplierList;
                supplierList = supplierRepository.findAllByReportId((long) 3);
                supplierList.forEach(item -> {
                    List<CustomerSupplierProperties> customerSupplierProperties = item.getCustomerSupplierProperties();
                    extractCustomerData(objectMapper, customerSupplierProperties, chartsDTO, chartColumn, chartRow, chartValue);
                });
                break;
            case CUSTOMER_ADVANCE:
                List<CustomerAdvance> customerAdvanceList;
                customerAdvanceList = customerAdvancesRepository.findAllByReportId((long) 3);
                customerAdvanceList.forEach(item -> {
                    List<CustomerSupplierProperties> customerSupplierProperties = item.getCustomerSupplierProperties();
                    extractCustomerData(objectMapper, customerSupplierProperties, chartsDTO, chartColumn, chartRow, chartValue);
                });
                break;
            case SUPPLIER_ADVANCE:
                List<SupplierAdvance> supplierAdvanceList;
                supplierAdvanceList = supplierAdvanceRepository.findAllByReportId((long) 3);
                supplierAdvanceList.forEach(item -> {
                    List<CustomerSupplierProperties> customerSupplierProperties = item.getCustomerSupplierProperties();
                    extractCustomerData(objectMapper, customerSupplierProperties, chartsDTO, chartColumn, chartRow, chartValue);
                });
                break;
            case GIVER_LOANS:
                List<LoanGiven> loanGivenList;
                loanGivenList = loanGivenRepository.findAllByReportId((long) 3);
                loanGivenList.forEach(item -> {
                    List<CustomerSupplierProperties> customerSupplierProperties = item.getCustomerSupplierProperties();
                    extractCustomerData(objectMapper, customerSupplierProperties, chartsDTO, chartColumn, chartRow, chartValue);
                });
                break;
            case RECEIVED_LOANS:
                List<LoanReceived> loanReceivedList;
                loanReceivedList = loanReceivedRepository.findAllByReportId((long) 3);
                loanReceivedList.forEach(item -> {
                    List<CustomerSupplierProperties> customerSupplierProperties = item.getCustomerSupplierProperties();
                    extractCustomerData(objectMapper, customerSupplierProperties, chartsDTO, chartColumn, chartRow, chartValue);
                });
                break;
        }
        return chartsDTO;

    }

    /**
     * Get the data from object, based on parameters
     */
    public static ChartsDTO extractData(Field[] fields,
                                        String chartColumn,
                                        String chartRow,
                                        String chartValue,
                                        Object properties) {
        ChartsDTO chart = new ChartsDTO();
        for (Field field : fields) {
            if (field.getName().equals(chartColumn) || field.getName().equals(chartValue)
                    || field.getName().equals(chartRow)) {
                try {
                    field.setAccessible(true);
                    Object object = field.get(properties);
                    if (field.getName().equals(chartColumn)) {
                        chart.setChartColumn(object.toString());
                    } else if (field.getName().equals(chartRow)) {
                        chart.setChartRow(object.toString());
                    } else if (field.getName().equals(chartValue)) {
                        chart.setChartValue(object.toString());
                    }
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return chart;
    }

    /**
     * customers, suppliers, loans use the same properties class
     */
    public static void extractCustomerData(ObjectMapper objectMapper,
                                           List<CustomerSupplierProperties> customerSupplierProperties,
                                           List<ChartsDTO> chartsDTO,
                                           String chartColumn,
                                           String chartRow,
                                           String chartValue) {
        List<CustomerSupplierProperties> receivableMaturityPropertiesList = objectMapper.convertValue(customerSupplierProperties,
                new TypeReference<>() {
                });
        for (CustomerSupplierProperties property : receivableMaturityPropertiesList) {
            Field[] fields = property.getClass().getDeclaredFields();
            chartsDTO.add(extractData(fields, chartColumn, chartRow, chartValue, property));
        }
    }

}
