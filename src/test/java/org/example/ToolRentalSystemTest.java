package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToolRentalSystemTest {
    private final CheckoutService toolRentalSystem = new CheckoutService();

    @Test
    public void testInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> {
            toolRentalSystem.checkout("JAKR", 5, 101, "09/03/2015");
        });
    }

    @Test
    public void testRentalAgreementGeneration_LADW_3days_10percentDiscount() {
        RentalAgreement agreement = toolRentalSystem.checkout("LADW", 3, 10, "07/02/2020");
        assertEquals("$3.58", formatCurrency(agreement.getFinalCharge()));
    }

    @Test
    public void testRentalAgreementGeneration_CHNS_5days_25percentDiscount() {
        RentalAgreement agreement = toolRentalSystem.checkout("CHNS", 5, 25, "07/02/2015");
        assertEquals("$3.35", formatCurrency(agreement.getFinalCharge()));
    }

    @Test
    public void testRentalAgreementGeneration_JAKD_6days_0percentDiscount() {
        RentalAgreement agreement = toolRentalSystem.checkout("JAKD", 6, 0, "09/03/2015");
        assertEquals("$8.97", formatCurrency(agreement.getFinalCharge()));
    }

    @Test
    public void testRentalAgreementGeneration_JAKR_9days_0percentDiscount() {
        RentalAgreement agreement = toolRentalSystem.checkout("JAKR", 9, 0, "07/02/2015");
        assertEquals("$14.95", formatCurrency(agreement.getFinalCharge()));
    }

    @Test
    public void testRentalAgreementGeneration_JAKR_4days_50percentDiscount() {
        RentalAgreement agreement = toolRentalSystem.checkout("JAKR", 4, 50, "07/02/2020");
        assertEquals("$1.49", formatCurrency(agreement.getFinalCharge()));
    }

    private String formatCurrency(BigDecimal value) {
        return String.format("$%.2f", value);
    }
}