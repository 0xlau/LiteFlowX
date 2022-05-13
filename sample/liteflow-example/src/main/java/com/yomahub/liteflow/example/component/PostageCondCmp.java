package com.yomahub.liteflow.example.component;

import com.yomahub.liteflow.core.NodeCondComponent;
import com.yomahub.liteflow.example.slot.PriceSlot;
import org.springframework.stereotype.Component;

/**
 * 运费条件组件
 */
@Component("postageCondCmp")
public class PostageCondCmp extends NodeCondComponent {
    @Override
    public String processCond() throws Exception {
        PriceSlot priceSlot = this.getSlot();
        //根据参数oversea来判断是否境外购，转到相应的组件
        boolean oversea = priceSlot.isOversea();
        if(oversea){
            return "overseaPostageCmp";
        }else{
            return "postageCmp";
        }
    }
}
