package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        CheckoutService service = new CheckoutService();
        try {
//            RentalAgreement agreement1 = service.checkout("JAKR", 5, 101, "09/03/2015");
            RentalAgreement agreement2 = service.checkout("LADW", 3, 10, "07/02/2020");
            RentalAgreement agreement3 = service.checkout("CHNS", 5, 25, "07/02/2015");
            RentalAgreement agreement4 = service.checkout("JAKD", 6, 0, "09/03/2015");
            RentalAgreement agreement5 = service.checkout("JAKR", 9, 0, "07/02/2015");
            RentalAgreement agreement6 = service.checkout("JAKR", 4, 50, "07/02/2020");
//            agreement1.printAgreement();
            agreement2.printAgreement();
            agreement3.printAgreement();
            agreement4.printAgreement();
            agreement5.printAgreement();
            agreement6.printAgreement();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage());
        }
    }
}