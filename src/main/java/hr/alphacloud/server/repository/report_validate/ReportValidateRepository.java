package hr.alphacloud.server.repository.report_validate;

import hr.alphacloud.server.model.dto.report_validate.base_control.BaseControl;
import hr.alphacloud.server.model.dto.report_validate.bruto_balance.BrutoBalanceValidate;
import hr.alphacloud.server.model.dto.report_validate.bruto_customer.BrutoCustomerValidate;
import hr.alphacloud.server.model.dto.report_validate.bruto_sales_recap.BrutoSalesRepValidate;
import hr.alphacloud.server.model.dto.report_validate.bruto_supply.BrutoSupplyValidate;
import hr.alphacloud.server.model.dto.report_validate.quantity_control.QuantityControl;
import hr.alphacloud.server.model.dto.report_validate.sales_recap_supply.SalesRepSupplyValidate;
import hr.alphacloud.server.model.entity.reporting.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportValidateRepository extends JpaRepository<Report, Long> {


    @Query(value = "SELECT bruto.konto as brutoKonto, bruto.kontoName as brutoName, (bruto.totalValue * -1) as brutoValue," +
            "SUM((supplier.totalValue) * -1)as importValue " +
            "FROM " +
            "(SELECT report_id, AB.code as supplierKonto," +
            "SUM((y.x ->'balance')\\:\\:decimal) as totalValue " +
            "from supplier " +
            "join account_book AB on AB.id = supplier.account_book_id," +
            "LATERAL (SELECT jsonb_array_elements(supplier.customer_supplier_properties) x) y " +
            "group by report_id, supplierKonto ) as supplier," +
            "(SELECT report_id, (y.x ->'finishedBalance')\\:\\:decimal as totalValue," +
            "(y.x ->'kontoName')\\:\\:text as kontoName," +
            "(y.x ->'konto')\\:\\:decimal as konto " +
            "from brutto_balance as bruto," +
            "LATERAL (SELECT jsonb_array_elements(bruto.brutto_balance_properties) x) y " +
            "group by report_id, totalValue, kontoName, konto " +
            "order by konto ) as bruto " +
            "where bruto.konto = supplierKonto " +
            "and bruto.report_id = :reportId " +
            "and supplier.report_id = :reportId " +
            "group by bruto.konto, bruto.kontoName, bruto.totalValue " +
            "order by konto", nativeQuery = true)
    List<BaseControl> validateObligationsToSuppliersControl(@Param("reportId") Long reportId);

    @Query(value = "SELECT bruto.konto as brutoKonto, bruto.kontoName as brutoName, bruto.totalValue as brutoValue," +
            "customer.totalValue as importValue " +
            "FROM " +
            "(SELECT report_id, AB.code as customerKonto," +
            "SUM((y.x ->'balance')\\:\\:decimal) as totalValue " +
            "from customer " +
            "join account_book AB on AB.id = customer.account_book_id," +
            "LATERAL (SELECT jsonb_array_elements(customer.customer_supplier_properties) x) y " +
            "group by report_id, customerKonto ) as customer," +
            "(SELECT report_id, (y.x ->'finishedBalance')\\:\\:decimal as totalValue," +
            "(y.x ->'kontoName')\\:\\:text as kontoName," +
            "(y.x ->'konto')\\:\\:decimal as konto " +
            "from brutto_balance as bruto," +
            "LATERAL (SELECT jsonb_array_elements(bruto.brutto_balance_properties) x) y " +
            "group by report_id, totalValue, kontoName, konto order by konto ) as bruto " +
            "where bruto.konto = customerKonto " +
            "and bruto.report_id = :reportId " +
            "and customer.report_id = :reportId", nativeQuery = true)
    List<BaseControl> validateCustomerReceivablesControl(@Param("reportId") Long reportId);

    @Query(value = "SELECT bruto.konto as brutoKonto, bruto.kontoName as brutoName, bruto.totalValue as brutoValue," +
            "supply.totalValue as importValue " +
            "FROM " +
            "(SELECT report_id, AB.code as supplyKonto," +
            "SUM((y.x ->'exitValue')\\:\\:decimal) as totalValue " +
            "from supply_analytics " +
            "join account_book AB on AB.id = supply_analytics.account_book_id," +
            "LATERAL (SELECT jsonb_array_elements(supply_analytics.supply_analytics_properties) x) y " +
            "group by report_id, supplyKonto ) as supply," +
            "(SELECT report_id, (y.x ->'finishedBalance')\\:\\:decimal as totalValue," +
            "(y.x ->'kontoName')\\:\\:text as kontoName," +
            "(y.x ->'konto')\\:\\:decimal as konto " +
            "from brutto_balance as bruto," +
            "LATERAL (SELECT jsonb_array_elements(bruto.brutto_balance_properties) x) y " +
            "group by report_id, totalValue, kontoName, konto order by konto ) as bruto " +
            "where bruto.konto = supplykonto " +
            "and bruto.report_id = :reportId " +
            "and supply.report_id = :reportId", nativeQuery = true)
    List<BaseControl> validateExpenditureControl(@Param("reportId") Long reportId);

    @Query(value = "SELECT finish.konto as kontoCode, finish.konto_name as kontoName," +
            "COALESCE(supplyVP.quantity, 0) as quantitySupplyVP, COALESCE(supplyMP.quantity, 0) as quantitySupplyMP," +
            "COALESCE(salesReturns.quantity,0) as quantitySalesReturn, COALESCE(salesMP.quantity,0) as quantitySalesMP, COALESCE(salesVP.quantity,0) as quantitySalesVP," +
            "COALESCE((salesMP.quantity + salesVP.quantity + (salesReturns.quantity * -1) - salesMP.quantity - salesVP.quantity),0) as quantityDifference " +
            "from " +
            "(SELECT DISTINCT naming.report_id, naming.konto, naming.konto_name " +
            "from " +
            "(SELECT report_id, id, import_type, (y.x ->'konto')\\:\\:decimal as konto, (y.x ->'kontoName')\\:\\:text as konto_name " +
            "from sales_recap," +
            "LATERAL (SELECT jsonb_array_elements(sales_recap.sales_recap_properties) x) y " +
            "group by report_id, id, konto, konto_name, import_type " +
            "UNION " +
            "SELECT report_id, id, import_type, (y.x ->'konto')\\:\\:decimal as konto, (y.x ->'kontoName')\\:\\:text as konto_name " +
            "from supply_analytics," +
            "LATERAL (SELECT jsonb_array_elements(supply_analytics.supply_analytics_properties) x) y " +
            "group by report_id, id, konto, konto_name, import_type ) as naming order by konto )as finish " +
            "left join " +
            "(SELECT report_id, (y.x ->'quantity')\\:\\:decimal as quantity, (y.x ->'konto')\\:\\:decimal as konto " +
            "from sales_recap," +
            "LATERAL (SELECT jsonb_array_elements(sales_recap.sales_recap_properties) x) y " +
            "WHERE sales_recap.import_type='Home' " +
            "group by report_id, import_type, quantity, konto ) salesMP on COALESCE(salesMP.konto, 0)=finish.konto " +
            "and salesMP.report_id = :reportId " +
            "left join " +
            "(SELECT report_id, (y.x ->'quantity')\\:\\:decimal as quantity, (y.x ->'konto')\\:\\:decimal as konto " +
            "from sales_recap," +
            "LATERAL (SELECT jsonb_array_elements(sales_recap.sales_recap_properties) x) y " +
            "WHERE sales_recap.import_type='Away' " +
            "group by report_id, import_type, quantity, konto ) salesVP on COALESCE(salesVP.konto, null) = finish.konto " +
            "and salesVP.report_id = :reportId " +
            "left join " +
            "(SELECT report_id, (y.x ->'quantity')\\:\\:decimal as quantity, (y.x ->'konto')\\:\\:decimal as konto " +
            "from sales_recap," +
            "LATERAL (SELECT jsonb_array_elements(sales_recap.sales_recap_properties) x) y " +
            "WHERE sales_recap.import_type='Returns' " +
            "group by report_id, import_type, quantity, konto ) salesReturns on COALESCE(salesReturns.konto, null) = finish.konto " +
            "and salesReturns.report_id = :reportId " +
            "left join " +
            "(SELECT report_id, (y.x ->'exiting')\\:\\:decimal as quantity, (y.x ->'konto')\\:\\:decimal as konto " +
            "from supply_analytics," +
            "LATERAL (SELECT jsonb_array_elements(supply_analytics.supply_analytics_properties) x) y " +
            "WHERE supply_analytics.import_type='Home' " +
            "group by report_id, import_type, quantity, konto )supplyMP on COALESCE(supplyMP.konto, '0') = finish.konto " +
            "and supplyMP.report_id = :reportId " +
            "left join " +
            "(SELECT report_id, (y.x ->'exiting')\\:\\:decimal as quantity, (y.x ->'konto')\\:\\:decimal as konto " +
            "from supply_analytics," +
            "LATERAL (SELECT jsonb_array_elements(supply_analytics.supply_analytics_properties) x) y " +
            "WHERE supply_analytics.import_type='Away' " +
            "group by report_id, import_type, quantity, konto ) supplyVP on COALESCE(supplyVP.konto, '0') = finish.konto " +
            "and supplyVP.report_id = :reportId " +
            "WHERE finish.report_id = :reportId " +
            "and salesMP.report_id = :reportId " +
            "and salesVP.report_id = :reportId " +
            "and supplyVP.report_id = :reportId " +
            "and supplyMP.report_id = :reportId " +
            "order by kontoCode", nativeQuery = true)
    List<QuantityControl> validateQuantityControl(@Param("reportId") Long reportId);

    /**
     * Sum entire column "base" from sales_recap and check if the result is equal to "demandTurnover"
     * of brutto_balance single row. Sales_recap konto code is manually added by the user through account_book for the
     * entire excel.
     */
    @Query(value = "SELECT sales_recap.report_id as reportId, sales_recap.sales_recap_id as id," +
            "(sales_recap.total_selling_price - (totalReturns.baseTotal / 2)) as salesRepTotalSellingPrice," +
            "bruto.bruto_id as brutoId, bruto.bruto_name as brutoArticleName, bruto.bruto_final_state_balance as brutoFinalStateBalance," +
            "bruto.code as brutoCode " +
            "from " +
            "(SELECT report_id, sales_recap.id as sales_recap_id, AB.code as salesRecapCode," +
            "SUM((y.x ->'base')\\:\\:decimal) as total_selling_price " +
            "from sales_recap " +
            "join account_book AB on AB.id = sales_recap.account_book_id," +
            "LATERAL (SELECT jsonb_array_elements(sales_recap.sales_recap_properties) x) y " +
            "group by report_id, sales_recap_id, salesRecapCode ) as sales_recap," +
            "(SELECT report_id, id as bruto_id, (y.x ->'kontoName')\\:\\:text as bruto_name," +
            "(y.x ->'demandTurnover')\\:\\:decimal as bruto_final_state_balance, (y.x ->'konto')\\:\\:decimal as code " +
            "from brutto_balance," +
            "LATERAL (SELECT jsonb_array_elements(brutto_balance.brutto_balance_properties) x) y " +
            "group by report_id, id, code, bruto_final_state_balance, bruto_name ) as bruto, " +
            "(SELECT report_id, SUM((y.x ->'base')\\:\\:decimal) as baseTotal " +
            "from sales_recap," +
            "LATERAL (SELECT jsonb_array_elements(sales_recap.sales_recap_properties) x) y " +
            "WHERE sales_recap.import_type='Returns' " +
            "group by report_id) as totalReturns " +
            "where sales_recap.report_id = :reportId " +
            "and bruto.report_id = :reportId " +
            "and totalReturns.report_id = :reportId " +
            "and bruto.code = salesRecapCode " +
            "group by sales_recap.report_id, sales_recap.sales_recap_id, bruto.bruto_id," +
            "bruto.bruto_name, bruto.bruto_final_state_balance, bruto.code, salesRepTotalSellingPrice", nativeQuery = true)
    List<BrutoSalesRepValidate> validateBrutoSalesRecap(@Param("reportId") Long reportId);

    //TODO delete everything under this point once confirmed that it will not be used

    /**
     * Validates bruto balance initial / final state balance by sum(whole column).
     */
    @Query(value = "SELECT bruto.report_id as reportId, bruto.bruto_id as id, SUM(bruto.bruto_init_state_balance) as initStateBalance," +
            "SUM(bruto.bruto_final_state_balance) as finalStateBalance " +
            "from" +
            "(SELECT report_id, id as bruto_id, (y.x ->'initStateBalance')\\:\\:decimal as bruto_init_state_balance," +
            " (y.x ->'finalStateBalance')\\:\\:decimal as bruto_final_state_balance " +
            "from brutto_balance," +
            "LATERAL (SELECT jsonb_array_elements(brutto_balance.brutto_balance_properties) x) y " +
            "group by report_id, bruto_id, bruto_init_state_balance, bruto_final_state_balance ) as bruto " +
            "where bruto.report_id = :reportId " +
            "group by bruto.report_id, bruto.bruto_id", nativeQuery = true)
    List<BrutoBalanceValidate> validateBrutoBalance(@Param("reportId") Long reportId);

    /**
     * Checks if columns from analytics are equal to konto block of bruto balance. If they are not equal, return the
     * data for user to see which are incorrect. Extracts data from JSONb columns. Result from supply_analytic should be
     * equal to bruto balance row, but here, we only display information to the user.
     */
    @Query(value = "SELECT bruto_init_state.bruto_init_id as brutoId, supply.id as supplyId," +
            "bruto_init_state.bruto_init_state_balance as brutoInitStateBalance, bruto_init_state.bruto_article_name as brutoInitArticleName," +
            "supply.final_outgoing as supplyFinalOutgoing, bruto_final_state.bruto_article_name as brutoFinalArticleName," +
            "bruto_final_state.bruto_final_state_balance as brutoFinalStateBalance, supply.saldo_final as supplySaldoFinal," +
            "bruto_init_state.bruto_konto as brutoInitCode, bruto_final_state.bruto_konto as brutoFinalCode " +
            "from" +
            "(SELECT sa.report_id, sa.id, WT.value as warehouse, AB.code as final_state_code, AB2.code as init_state_code," +
            "SUM((y.x ->'valueOutgoing')\\:\\:decimal) as final_outgoing, SUM((y.x ->'finalValue')\\:\\:decimal) as saldo_final " +
            "from supply_analytics sa " +
            "join warehouse_type WT on WT.id = sa.warehouse_type_id " +
            "join account_book AB on AB.id = WT.final_state_balance_code " +
            "join account_book AB2 on AB2.id = WT.init_state_balance_code," +
            "LATERAL (SELECT jsonb_array_elements(sa.supply_analytics_properties) x) y " +
            "group by init_state_code, warehouse, final_state_code, sa.report_id, sa.id, sa.warehouse_type_id) as supply," +
            "(SELECT bb_init.report_id, (y.x ->'initStateBalance')\\:\\:decimal as bruto_init_state_balance, (y.x ->'konto')\\:\\:decimal as init_code," +
            "bb_init.id as bruto_init_id, (y.x ->'kontoName')\\:\\:text as bruto_article_name, (y.x ->'konto')\\:\\:text as bruto_konto " +
            "from brutto_balance bb_init," +
            "LATERAL (SELECT jsonb_array_elements(bb_init.brutto_balance_properties) x) y " +
            "group by bb_init.report_id, bb_init.id, init_code, bruto_init_state_balance, bruto_article_name, bruto_konto ) as bruto_init_state," +
            "(SELECT bb_final.report_id, (y.x ->'finalStateBalance')\\:\\:decimal as bruto_final_state_balance, (y.x ->'konto')\\:\\:decimal as final_code," +
            "bb_final.id as bruto_final_id, (y.x ->'kontoName')\\:\\:text as bruto_article_name, (y.x ->'konto')\\:\\:text as bruto_konto " +
            "from brutto_balance bb_final," +
            "LATERAL (SELECT jsonb_array_elements(bb_final.brutto_balance_properties) x) y " +
            "group by bb_final.report_id, bb_final.id, final_code, bruto_final_state_balance, bruto_article_name, bruto_konto ) as bruto_final_state " +
            "where bruto_final_state.report_id = :reportId AND bruto_init_state.report_id = :reportId and supply.report_id = :reportId " +
            "and bruto_init_state.init_code = init_state_code and bruto_final_state.final_code = final_state_code " +
            "and bruto_init_state.bruto_init_id = bruto_final_state.bruto_final_id " +
            "and bruto_init_state.bruto_init_state_balance != supply.final_outgoing " +
            "and bruto_final_state.bruto_final_state_balance != supply.saldo_final", nativeQuery = true)
    List<BrutoSupplyValidate> validateBrutoSupply(@Param("reportId") Long reportId);

    /**
     * Sum "final state" column from customer where "konto" (konto code grouped) exists in bruto balance sheet row.
     * Both results should be equal, but here, we only display information to the user.
     */
    @Query(value = "SELECT customer.report_id as reportId, bruto.code as brutoCode," +
            "bruto.bruto_final_state_balance as brutoCost, bruto.bruto_id as brutoId, customer.customer_id as customerId," +
            "bruto.bruto_name as brutoName, SUM(customer.customer_final_state) as customerSum " +
            "from " +
            "(SELECT report_id, id as customer_id, (y.x ->'konto')\\:\\:decimal as customer_code, (y.x ->'finalState')\\:\\:decimal as customer_final_state " +
            "from customer," +
            "LATERAL (SELECT jsonb_array_elements(customer.customer_supplier_properties) x) y " +
            "group by report_id, customer_code, customer_id, customer_final_state ) as customer," +
            "(SELECT report_id, id as bruto_id, (y.x ->'kontoName')\\:\\:text as bruto_name," +
            "(y.x ->'finalStateBalance')\\:\\:decimal as bruto_final_state_balance, (y.x ->'konto')\\:\\:decimal as code " +
            "from brutto_balance," +
            "LATERAL (SELECT jsonb_array_elements(brutto_balance.brutto_balance_properties) x) y " +
            "group by report_id, id, code, bruto_final_state_balance, bruto_name ) as bruto " +
            "where customer.customer_code = bruto.code " +
            "and customer.customer_final_state != bruto.bruto_final_state_balance " +
            "and customer.report_id = :reportId " +
            "and bruto.report_id = :reportId " +
            "group by reportId, brutoCost, brutoCode, brutoId, customerId, brutoName", nativeQuery = true)
    List<BrutoCustomerValidate> validateBrutoCustomer(@Param("reportId") Long reportId);

    /**
     * Sum property "soldAmount" and "itemSellingPrice" from sales_recap and check if they are equal to the values of
     * supply_analytics row based on article code on both sides.
     */
    @Query(value = "SELECT sales.report_id as reportId, sales.id as salesId," +
            "supply.id as supplyId, sales.article_number as salesArticleNumber, sales.sold_amount as salesSoldAmount," +
            "sales.item_selling_price as salesItemSellingPrice, supply.article_number as supplyArticleNumber," +
            "supply.amount_outgoing as supplyAmountOutgoing, supply.value_outgoing as supplyValueOutgoing, supply.article_name as supplyArticleName " +
            "from " +
            "(SELECT report_id, id, (y.x ->'articleNumber')\\:\\:decimal as article_number," +
            "SUM((y.x ->'soldAmount')\\:\\:decimal) as sold_amount," +
            "SUM((y.x ->'itemSellingPrice')\\:\\:decimal) as item_selling_price " +
            "from sales_recap," +
            "LATERAL (SELECT jsonb_array_elements(sales_recap.sales_recap_properties) x) y " +
            "group by report_id, id, article_number ) as sales," +
            "(SELECT report_id, id, (y.x ->'articleNumber')\\:\\:decimal as article_number," +
            "(y.x -> 'articleName')\\:\\:text as article_name," +
            "(y.x ->'amountOutgoing')\\:\\:decimal as amount_outgoing," +
            "(y.x ->'valueOutgoing')\\:\\:decimal as value_outgoing " +
            "from supply_analytics," +
            "LATERAL (SELECT jsonb_array_elements(supply_analytics.supply_analytics_properties) x) y " +
            "group by report_id, id, article_number, article_name, amount_outgoing, value_outgoing ) as supply " +
            "WHERE sales.article_number = supply.article_number " +
            "AND sales.sold_amount != supply.amount_outgoing " +
            "AND sales.report_id = :reportId " +
            "AND supply.report_id = :reportId " +
            "GROUP BY sales.report_id, sales.id, supply.id, sales.article_number, sales.sold_amount, sales.item_selling_price," +
            "supply.article_number, supply.article_name, supply.amount_outgoing, supply.value_outgoing", nativeQuery = true)
    List<SalesRepSupplyValidate> validateSalesRepSupply(@Param("reportId") Long reportId);

}
