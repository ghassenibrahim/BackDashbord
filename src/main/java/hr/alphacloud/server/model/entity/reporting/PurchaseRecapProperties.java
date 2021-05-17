package hr.alphacloud.server.model.entity.reporting;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PurchaseRecapProperties {

    private String documentNumber;
    private String documentCode;
    private String date;
    private String articleCode;
    private String articleName;
    private String unitMeasure;
    private String partnerName;
    private String oib;
    private BigDecimal quantity;
    private BigDecimal purchaseValue;

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
        addToList(list, this.purchaseValue);

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
        list.add("purchaseValue");

        return list;
    }
}
