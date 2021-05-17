package hr.alphacloud.server.model.entity.reporting;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReceivableMaturityProperties {

    private String name;
    private String oib;
    private BigDecimal totalValue;
    private BigDecimal dueValue;
    private BigDecimal overdueValue;
    private BigDecimal from0to30;
    private BigDecimal from31to60;
    private BigDecimal from61to90;
    private BigDecimal from91to120;
    private BigDecimal from121to180;
    private BigDecimal from181to360;
    private BigDecimal from361to450;
    private BigDecimal from451to540;
    private BigDecimal from541to730;
    private BigDecimal over731;


    public List<String> toList() {
        List<String> list = new ArrayList<>();
        addToList(list, this.name);
        addToList(list, this.oib);
        addToList(list, this.totalValue);
        addToList(list, this.dueValue);
        addToList(list, this.overdueValue);
        addToList(list, this.from0to30);
        addToList(list, this.from31to60);
        addToList(list, this.from61to90);
        addToList(list, this.from91to120);
        addToList(list, this.from121to180);
        addToList(list, this.from181to360);
        addToList(list, this.from361to450);
        addToList(list, this.from451to540);
        addToList(list, this.from541to730);
        addToList(list, this.over731);

        return list;
    }

    private void addToList(List<String> list, BigDecimal value) {
        if (value != null) {
            list.add(value.toString());
        } else {
            list.add(null);
        }
    }

    private void addToList(List<String> list, String value) {
        list.add(value);
    }

    public static ArrayList<String> getPropertyList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("name");
        list.add("oib");
        list.add("totalValue");
        list.add("dueValue");
        list.add("overdueValue");
        list.add("from0to30");
        list.add("from31to60");
        list.add("from61to90");
        list.add("from91to120");
        list.add("from121to180");
        list.add("from181to360");
        list.add("from361to450");
        list.add("from451to540");
        list.add("from541to730");
        list.add("over731");

        return list;
    }
}
