package hr.alphacloud.server.model.dto.settings;

import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BusinessTypeDTO implements Serializable {
    private Long id;
    private String value;
    private String locale;
    private Long companyId;

    public static BusinessTypeDTO of(BusinessType businessType) {
        if (businessType != null) {
            return builder()
                    .id(businessType.getId())
                    .value(businessType.getValue())
                    .locale(businessType.getLocale())
                    .companyId(businessType.getCompanyId())
                    .build();
        }
        return null;
    }

    public static List<BusinessTypeDTO> of(List<BusinessType> businessTypeList) {
        return businessTypeList.stream()
                .map(BusinessTypeDTO::of)
                .collect(Collectors.toList());
    }
}
