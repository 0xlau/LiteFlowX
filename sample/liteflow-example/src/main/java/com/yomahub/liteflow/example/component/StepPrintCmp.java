package com.yomahub.liteflow.example.component;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.example.bean.PriceStepVO;
import com.yomahub.liteflow.example.bean.ProductPackVO;
import com.yomahub.liteflow.example.slot.PriceSlot;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;

/**
 * 步骤日志生成组件
 */
@Component("stepPrintCmp")
public class StepPrintCmp extends NodeComponent {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void process() throws Exception {
        PriceSlot slot = this.getSlot();
        StringBuilder logStr = new StringBuilder();

        logStr.append(MessageFormat.format("订单号[{0}]的价格计算的明细结果:\n", slot.getOrderNo()));
        logStr.append("|====================================================================\n");
        for(ProductPackVO pack : slot.getProductPackList()){
            logStr.append(MessageFormat.format("|   {0} [{1}] [{2}]   {3} X {4}\n",
                    pack.getSkuName(),
                    pack.getProductCode(),
                    pack.getSkuCode(),
                    pack.getSalePrice().setScale(2, RoundingMode.HALF_UP).toString(),
                    pack.getCount()));
        }

        logStr.append("|====================================================================\n");
        for(PriceStepVO step : slot.getPriceStepList()){
            logStr.append(MessageFormat.format("|   [{0} : {1}]\n",step.getStepDesc(),step.getPriceChange().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
        }
        logStr.append(MessageFormat.format("|   [最终价 : {0}]\n",slot.getFinalOrderPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
        logStr.append("|====================================================================\n");
        log.info(logStr.toString());
        slot.setPrintLog(logStr.toString());
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
