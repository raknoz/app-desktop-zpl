package demo_zpl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class StoreHoursDto {

    private String dayOfWeek;
    private String openTime;
    private String closeTime;
}
