package com.marionete;

import com.marionete.model.CombinedOrder;
import com.marionete.model.FruitOrder;
import com.marionete.model.VegetableOrder;
import org.apache.kafka.streams.kstream.ValueJoiner;

import java.time.Instant;

public class CombinedOrderJoiner implements ValueJoiner<FruitOrder, VegetableOrder, CombinedOrder> {
    public CombinedOrder apply(FruitOrder fruitOrder, VegetableOrder vegetableOrder) {
        return CombinedOrder.newBuilder()
                .setOrderId(fruitOrder.getOrderId())
                .setFruitId(fruitOrder.getFruitId())
                .setVegetableId(vegetableOrder.getVegetableId())
                .setUserId(vegetableOrder.getUserId())
                .build();
    }
}
