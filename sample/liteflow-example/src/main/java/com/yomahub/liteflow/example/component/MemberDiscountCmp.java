package com.yomahub.liteflow.example.component;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.example.bean.PriceStepVO;
import com.yomahub.liteflow.example.enums.PriceTypeEnum;
import com.yomahub.liteflow.example.slot.PriceSlot;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;

/**
 * 会员折扣计算组件
 */
@Component("memberDiscountCmp")
public class MemberDiscountCmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        PriceSlot priceSlot = this.getSlot();
        String memberCode = priceSlot.getMemberCode();

        /***这里Mock下通过memberCode去查会员等级表然后获取的会员折扣为9折的代码***/
        BigDecimal memberDiscount = new BigDecimal("0.9");

        //进行计算会员折扣
        BigDecimal prePrice = priceSlot.getLastestPriceStep().getCurrPrice();
        BigDecimal currPrice = prePrice.multiply(memberDiscount).setScale(2, RoundingMode.HALF_UP);

        //加入到价格步骤中
        priceSlot.addPriceStep(new PriceStepVO(PriceTypeEnum.MEMBER_DISCOUNT,
                memberCode,
                prePrice,
                currPrice.subtract(prePrice),
                currPrice,
                PriceTypeEnum.MEMBER_DISCOUNT.getName()));

    }

    @Override
    public boolean isAccess() {
        PriceSlot priceSlot = this.getSlot();
        if(CollectionUtils.isNotEmpty(priceSlot.getProductPackList())
                && StringUtils.isNotBlank(priceSlot.getMemberCode())){
            return true;
        }else{
            return false;
        }
    }
}
