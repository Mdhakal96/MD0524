package org.example;

public class Tool {
    private final String toolCode;
    private final String toolType;
    private final String brand;
    private final double dailyCharge;
    private final boolean chargesOnWeekdays;
    private final boolean chargesOnWeekends;
    private final boolean chargesOnHolidays;

    public Tool(String toolCode, String toolType, String brand, double dailyCharge, boolean chargesOnWeekdays, boolean chargesOnWeekends, boolean chargesOnHolidays) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.chargesOnWeekdays = chargesOnWeekdays;
        this.chargesOnWeekends = chargesOnWeekends;
        this.chargesOnHolidays = chargesOnHolidays;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean isChargesOnWeekdays() {
        return chargesOnWeekdays;
    }

    public boolean isChargesOnWeekends() {
        return chargesOnWeekends;
    }

    public boolean isChargesOnHolidays() {
        return chargesOnHolidays;
    }
}
