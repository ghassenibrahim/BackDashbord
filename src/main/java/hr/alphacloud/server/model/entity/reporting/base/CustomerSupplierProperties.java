package hr.alphacloud.server.model.entity.reporting.base;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Properties shared between Customer, Supplier, Customer advance, Supplier advance, Given loans and Received loans
 */


@Data
public class CustomerSupplierProperties {

    private Integer kontoPartner;

    private String kontoPartnerName;

    private String location;

    private BigDecimal owes;

    private BigDecimal demands;

    private BigDecimal balance;

    public List<String> toList() {
        List<String> list = new ArrayList<>();
        addToList(list, kontoPartner);
        addToList(list, kontoPartnerName);
        addToList(list, location);
        addToList(list, owes);
        addToList(list, demands);
        addToList(list, balance);

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

    private void addToList(List<String> list, LocalDate value) {
        if (value != null) {
            this.addToList(list, value.toString());
        } else {
            list.add(null);
        }
    }

    /**
     * Populate the properties for use of the table on the front end side.
     */

    public static ArrayList<String> getPropertyList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("kontoPartner");
        list.add("kontoPartnerName");
        list.add("location");
        list.add("owes");
        list.add("demands");
        list.add("balance");
        return list;
    }
}
