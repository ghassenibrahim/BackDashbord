package hr.alphacloud.server.model.entity.reporting;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class BruttoBalanceProperties {

    private Integer konto;

    private String kontoName;

    private BigDecimal startingBalance;

    private BigDecimal debtTurnover;

    private BigDecimal demandTurnover;

    private BigDecimal finishedBalance;
//
//    private BigDecimal incomingPayments;
//
//    private BigDecimal finalStateIncoming;
//
//    private BigDecimal finalStateOutgoing;
//
//    private BigDecimal finalStateBalance;

    public List<String> toList() {
        List<String> list = new ArrayList<>();
        addToList(list, this.konto);
        addToList(list, this.kontoName);
        addToList(list, this.startingBalance);
        addToList(list, this.debtTurnover);
        addToList(list, this.demandTurnover);
        addToList(list, this.finishedBalance);

        // konto names before
//        addToList(list, this.konto);
//        addToList(list, this.kontoName);
//        addToList(list, this.initStateIncoming);
//        addToList(list, this.initStateOutgoing);
//        addToList(list, this.initStateBalance);
//        addToList(list, this.outgoingPayments);
//        addToList(list, this.incomingPayments);
//        addToList(list, this.finalStateIncoming);
//        addToList(list, this.finalStateOutgoing);
//        addToList(list, this.finalStateBalance);

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

    public static ArrayList<String> getPropertyList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("konto");
        list.add("kontoName");
        list.add("startingBalance");
        list.add("debtTurnover");
        list.add("demandTurnover");
        list.add("finishedBalance");
//        list.add("incomingPayments");
//        list.add("finalStateIncoming");
//        list.add("finalStateOutgoing");
//        list.add("finalStateBalance");

        return list;
    }


}
