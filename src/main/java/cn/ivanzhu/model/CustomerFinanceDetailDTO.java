package cn.ivanzhu.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @author ivanzhu
 * @date 2020/6/18
 * @time 10:32
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerFinanceDetailDTO {
    @ExcelProperty(value = "收付款单号", index = 0)
    private String paymentNumber;

    @ExcelProperty(value = "类型", index = 1)
    private String paymentType;

    @ExcelProperty(value = "账户", index = 2)
    private String bankAccount;

    @ExcelProperty(value = "收付款日期", index = 3)
    private String paymentDate;

    @ExcelProperty(value = "金额", index = 4)
    private BigDecimal paymentMoney;

    @ExcelProperty(value = "核销金额", index = 5)
    private BigDecimal writtenMoney;

    @ExcelProperty(value = "发退货单", index = 6)
    private String deliveryNumber;

    @ExcelProperty(value = "出入库单", index = 7)
    private String inOutInventoryNumber;

    @ExcelProperty(value = "出入库日期", index = 8)
    private String operateTime;

    @ExcelProperty(value = "产品名称", index = 9)
    private String productName;

    @ExcelProperty(value = "产品编号", index = 10)
    private String productCode;

    @ExcelProperty(value = "订单号", index = 11)
    private String orderNumber;

    @ExcelProperty(value = "数量", index = 12)
    private BigDecimal optQty;

    @ExcelProperty(value = "单位", index = 13)
    private String unit;

    @ExcelProperty(value = "核销金额", index = 14)
    private BigDecimal writtenMoney2;

    @ExcelProperty(value = "单价", index = 15)
    private BigDecimal price;
}
