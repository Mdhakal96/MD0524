package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class RentalAgreement {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private final String toolCode;
    private final String toolType;
    private final String toolBrand;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final double dailyRentalCharge;
    private int chargeableDays;
    private BigDecimal preDiscountCharge;
    private final int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        this.toolCode = tool.getToolCode();
        this.toolType = tool.getToolType();
        this.toolBrand = tool.getBrand();
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dailyRentalCharge = tool.getDailyCharge();
        this.discountPercent = discountPercent;
        this.dueDate = this.checkoutDate.plusDays(rentalDays);

        calculateCharges(tool);
    }

    private void calculateCharges(Tool tool) {
        chargeableDays = calculateChargeableDays(tool);
        preDiscountCharge = calculatePreDiscountCharge();
        discountAmount = calculateDiscountAmount();
        finalCharge = preDiscountCharge.subtract(discountAmount);
    }

    private int calculateChargeableDays(Tool tool) {
        int chargeableDaysCount = 0;
        LocalDate date = checkoutDate.plusDays(1);

        while (!date.isAfter(dueDate)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (isChargeableDay(tool, date, dayOfWeek)) {
                chargeableDaysCount++;
            }
            date = date.plusDays(1);
        }

        return chargeableDaysCount;
    }

    private boolean isChargeableDay(Tool tool, LocalDate date, DayOfWeek dayOfWeek) {
        return (tool.isChargesOnWeekdays() && isWeekday(dayOfWeek) && !isHoliday(date)) ||
                (tool.isChargesOnWeekends() && isWeekend(dayOfWeek)) ||
                (tool.isChargesOnHolidays() && isHoliday(date));
    }

    private boolean isWeekday(DayOfWeek dayOfWeek) {
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate date) {
        return date.equals(getObservedHoliday(LocalDate.of(date.getYear(), 7, 4))) ||
                date.equals(getLaborDay(date.getYear()));
    }

    private LocalDate getObservedHoliday(LocalDate holiday) {
        DayOfWeek dayOfWeek = holiday.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return holiday.minusDays(1);
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            return holiday.plusDays(1);
        }
        return holiday;
    }

    private LocalDate getLaborDay(int year) {
        return LocalDate.of(year, 9, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    }

    private BigDecimal calculatePreDiscountCharge() {
        return BigDecimal.valueOf(dailyRentalCharge)
                .multiply(BigDecimal.valueOf(chargeableDays))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateDiscountAmount() {
        return preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void printAgreement() {
        System.out.println("-----------------------");
        System.out.println("\n");
        System.out.println("Tool code: " + toolCode);
        System.out.println("Tool type: " + toolType);
        System.out.println("Tool brand: " + toolBrand);
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(DATE_FORMATTER));
        System.out.println("Due date: " + dueDate.format(DATE_FORMATTER));
        System.out.println("Daily rental charge: $" + String.format("%.2f", dailyRentalCharge));
        System.out.println("Chargeable days: " + chargeableDays);
        System.out.println("Pre-discount charge: $" + preDiscountCharge);
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: $" + discountAmount);
        System.out.println("Final charge: $" + finalCharge);
        System.out.println("\n");
        System.out.println("-----------------------");
    }
}
