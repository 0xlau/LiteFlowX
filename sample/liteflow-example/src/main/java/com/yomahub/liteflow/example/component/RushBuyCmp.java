package com.yomahub.liteflow.example.component;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.example.bean.PriceStepVO;
import com.yomahub.liteflow.example.bean.ProductPackVO;
import com.yomahub.liteflow.example.bean.PromotionPackVO;
import com.yomahub.liteflow.example.enums.PriceTypeEnum;
import com.yomahub.liteflow.example.enums.PromotionTypeEnum;
import com.yomahub.liteflow.example.slot.PriceSlot;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抢购计算组件
 */
@Component("rushBuyCmp")
public class RushBuyCmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        PriceSlot slot = this.getSlot();
        PromotionPackVO promotionPack = getMatchPromotion();

        /**
         * 这里Mock下根据优惠信息查到的抢购信息为：1块钱抢购
         * 这里要注意的是，实际情况下，这个抢购活动所关联的商品，每个商品的抢购价格都不同
         * 这里为了Mock方便，所关联的商品，每个SKU抢购价都是1元
         * ps:抢购原则上和其他优惠活动互斥，这里就不写出互斥的逻辑了，在设置参数时请注意
         **/
        BigDecimal rushBuyPrice = new BigDecimal(1);

        BigDecimal prePrice = slot.getLastestPriceStep().getCurrPrice();
        BigDecimal rushBuyDiscountPrice = new BigDecimal(0);
        for(ProductPackVO productPack : promotionPack.getRelatedProductPackList()){
            rushBuyDiscountPrice = rushBuyDiscountPrice.add(productPack.getSalePrice().subtract(rushBuyPrice)
                    .multiply(new BigDecimal(productPack.getCount()))).setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal currPrice = prePrice.subtract(rushBuyDiscountPrice);

        slot.addPriceStep(new PriceStepVO(PriceTypeEnum.PROMOTION_DISCOUNT,
                promotionPack.getId().toString(),
                prePrice,
                currPrice.subtract(prePrice),
                currPrice,
                PriceTypeEnum.PROMOTION_DISCOUNT.getName() + "[抢购]"));

    }

    @Override
    public boolean isAccess() {
        //过滤出优惠信息列表中有没有抢购这个活动，如果有，则进入这个组件，反义就不进入
        PromotionPackVO promotionPack = getMatchPromotion();
        if(promotionPack != null){
            return true;
        }else{
            return false;
        }
    }

    private PromotionPackVO getMatchPromotion(){
        PriceSlot slot = this.getSlot();

        List<PromotionPackVO> matchList = slot.getPromotionPackList().stream().filter(promotionPackVO -> {
            if(promotionPackVO.getPromotionType().equals(PromotionTypeEnum.RUSH_BUY)){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());

        if(CollectionUtils.isNotEmpty(matchList)){
            return matchList.get(0);
        }else{
            return null;
        }
    }
}
