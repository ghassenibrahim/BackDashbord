package hr.alphacloud.server.model.entity.reporting;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SalesRecapProperties {

    private String documentNumber;
    private String documentCode;
    private String date;
    private String articleCode;
    private String articleName;
    private String unitMeasure;
    private String partnerName;
    private String oib;
    private BigDecimal quantity;
    private BigDecimal singlePurchasePrice;
    private BigDecimal singleSalesPrice;
    private BigDecimal purchaseValue;
    private BigDecimal singleSalesPriceWithVAT;
    private BigDecimal salesValueWithVAT;
    private BigDecimal margin;
    private BigDecimal singleMargin;
    private BigDecimal salesValue;

    public List<String> toList() {
        List<String> list = new ArrayList<>();
        addToList(list, this.documentNumber);
        addToList(list, this.documentCode);
        addToList(list, this.date);
        addToList(list, this.articleCode);
        addToList(list, this.articleName);
        addToList(list, this.unitMeasure);
        addToList(list, this.partnerName);
        addToList(list, this.oib);
        addToList(list, this.quantity);
        addToList(list, this.singlePurchasePrice);
        addToList(list, this.singleSalesPrice);
        addToList(list, this.purchaseValue);
        addToList(list, this.singleSalesPriceWithVAT);
        addToList(list, this.salesValueWithVAT);
        addToList(list, this.margin);
        addToList(list, this.singleMargin);
        addToList(list, this.salesValue);

        //old values
//        addToList(list, this.articleNumber);
//        addToList(list, this.articleName);
//        addToList(list, this.soldAmount);
//        addToList(list, this.itemBuyingPrice);
//        addToList(list, this.totalBuyingPrice);
//        addToList(list, this.totalBuyingPriceWithVAT);
//        addToList(list, this.itemSellingPrice);
//        addToList(list, this.totalSellingPrice);
//        addToList(list, this.totalSellingPriceWithVAT);
//        addToList(list, this.articleVAT);
//        addToList(list, this.itemMargin);
//        addToList(list, this.totalMargin);
        return list;
    }

    private void addToList(List<String> list, String value) {
        list.add(value);
    }

    private void addToList(List<String> list, BigDecimal value) {
        if (value != null) {
            list.add(value.toString());
        } else {
            list.add(null);
        }
    }

    private void addToList(List<String> list, Integer value) {
        if (value != null) {
            list.add(value.toString());
        } else {
            list.add(null);
        }
    }

    private void addToList(List<String> list, Date value) {
        if (value != null) {
            list.add(value.toString());
        } else {
            list.add(null);
        }
    }

    public static ArrayList<String> getPropertyList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("documentNumber");
        list.add("documentCode");
        list.add("date");
        list.add("articleCode");
        list.add("articleName");
        list.add("unitMeasure");
        list.add("partnerName");
        list.add("oib");
        list.add("quantity");
        list.add("singlePurchasePrice");
        list.add("singleSalesPrice");
        list.add("purchaseValue");
        list.add("singleSalesPriceWithVAT");
        list.add("salesValueWithVAT");
        list.add("margin");
        list.add("singleMargin");
        list.add("salesValue");

        return list;
    }
}
