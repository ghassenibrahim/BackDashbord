package hr.alphacloud.server.model.entity.reporting;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class SupplyAnalyticsProperties {

    private Integer articleCode;
    private String articleName;
    private String measuringUnit;
    private BigDecimal startingQuantity;
    private BigDecimal enterQuantity;
    private BigDecimal exitQuantity;
    private BigDecimal finishQuantity;
    private BigDecimal startingValue;
    private BigDecimal enterValue;
    private BigDecimal exitValue;
    private BigDecimal finishValue;
    private BigDecimal averagePrice;

    public List<String> toList() {
        List<String> list = new ArrayList<>();
        addToList(list, articleCode);
        addToList(list, articleName);
        addToList(list, measuringUnit);
        addToList(list, startingQuantity);
        addToList(list, startingValue);
        addToList(list, enterQuantity);
        addToList(list, enterValue);
        addToList(list, exitQuantity);
        addToList(list, exitValue);
        addToList(list, finishQuantity);
        addToList(list, finishValue);
        addToList(list, averagePrice);

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
        list.add("articleCode");
        list.add("articleName");
        list.add("measuringUnit");
        list.add("startingQuantity");
        list.add("startingValue");
        list.add("enterQuantity");
        list.add("enterValue");
        list.add("exitQuantity");
        list.add("exitValue");
        list.add("finishQuantity");
        list.add("finishValue");
        list.add("averagePrice");

        return list;
    }
}
