package demo_zpl.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptItemDto implements Serializable {

    private String itemEzid;
    private BigDecimal soldPrice;
    private ReceiptItemPPPDTO pppSelected;
    private Integer bulkCategory;
    private Integer bulkQuantity;
    private String description;
    private Integer gmGrade;
    private Integer goldColor;
    private String goldColorDisplayText;
    private Integer goldKarat;
    private String goldKaratDisplayText;
    private String categoryName;
    private Float jewelrySize;
    private Integer metalType;
    private String metalTypeDisplayText;
    private Integer stoneCount;
    private Integer platinumQuality;
    private String platinumQualityDisplayText;
    private Integer silverQuality;
    private String silverQualityDisplayText;
    private ItemCategoryDto rootCategoryParent;
}
