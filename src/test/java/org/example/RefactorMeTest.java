package org.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RefactorMeTest {

    private RefactorMe refactorMe;

    @Before
    public void init() {
        refactorMe = new RefactorMe();
    }
    @Test
    public void shouldAnswerWithTrue() {
        List<String> orders = Arrays.asList(
                "pizza large 1 promotion-none topping:cheese,mushrooms",
                "salad medium 3 promotion-happy_hour",
                "salad small 1 promotion-special_offer"
        );

        String bill = refactorMe.getBillFromOrder(orders);

        assertNotNull(bill);

        assertTrue( bill.contains("pizza 1 large 19.0") );
        assertTrue( bill.contains("salad 3 medium 12.0") );
        assertTrue( bill.contains("salad 1 small 3.6") );
        assertTrue( bill.contains("total: 34.6") );
    }

}
