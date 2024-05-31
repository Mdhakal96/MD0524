package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckoutService {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final int MIN_RENTAL_DAYS = 1;
    private static final int MIN_DISCOUNT_PERCENT = 0;
    private static final int MAX_DISCOUNT_PERCENT = 100;

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, String checkoutDateStr) {
        validateInputs(rentalDayCount, discountPercent);

        Tool tool = ToolRentalSystem.getTool(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Tool code is invalid.");
        }

        LocalDate checkoutDate = LocalDate.parse(checkoutDateStr, DATE_FORMATTER);
        return new RentalAgreement(tool, rentalDayCount, discountPercent, checkoutDate);
    }

    private void validateInputs(int rentalDayCount, int discountPercent) {
        if (rentalDayCount < MIN_RENTAL_DAYS) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < MIN_DISCOUNT_PERCENT || discountPercent > MAX_DISCOUNT_PERCENT) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }
    }
}
