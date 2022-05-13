package com.yomahub.liteflow.example.component;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.example.bean.PriceStepVO;
import com.yomahub.liteflow.example.slot.PriceSlot;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 订单最终价格计算器
 */
@Component("priceResultCmp")
public class PriceResultCmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        //算出订单最后的价格，因为priceChange有正负，所以这里统一加起来
        PriceSlot slot = this.getSlot();
        BigDecimal finalPrice = new BigDecimal(0);
        for(PriceStepVO step : slot.getPriceStepList()){
            finalPrice = finalPrice.add(step.getPriceChange());
        }
        slot.setFinalOrderPrice(finalPrice);
    }

    @Override
    public boolean isAccess() {
        PriceSlot slot = this.getSlot();
        if(CollectionUtils.isNotEmpty(slot.getPriceStepList())){
            return true;
        }else{
            return false;
        }
    }
}
