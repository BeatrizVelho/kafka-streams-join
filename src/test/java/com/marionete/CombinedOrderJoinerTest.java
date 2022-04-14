package com.marionete;

import com.marionete.model.CombinedOrder;
import com.marionete.model.FruitOrder;
import com.marionete.model.VegetableOrder;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class CombinedOrderJoinerTest {

    @Test
    public void apply() {

        CombinedOrder returnedCombinedOrder;

        FruitOrder fruitOrder = FruitOrder.newBuilder().setFruitId("fruit-id").setOrderId("order-id").setUserId("user-id").build();
        VegetableOrder vegetableOrder = VegetableOrder.newBuilder().setVegetableId("vegetable-id").setOrderId("order-id").setUserId("user-id").build();
        CombinedOrder expectedMusicInterest = CombinedOrder.newBuilder().setFruitId("fruit-id").setVegetableId("vegetable-id").setOrderId("order-id").setUserId("user-id").build();

        CombinedOrderJoiner joiner = new CombinedOrderJoiner();
        returnedCombinedOrder = joiner.apply(fruitOrder, vegetableOrder);

        assertEquals(returnedCombinedOrder, expectedMusicInterest);
    }
}
