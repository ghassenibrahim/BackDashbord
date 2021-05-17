package hr.alphacloud.server.model.entity.reporting.settings.position_template;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import hr.alphacloud.server.model.entity.reporting.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class PositionTemplate extends BaseEntity {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<PositionProperties> positionProperties;

    @Column
    private String sheetType;

}
