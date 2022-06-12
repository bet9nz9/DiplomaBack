package com.diploma.edu.source.servicies;

import com.diploma.edu.source.exceptions.IncorrectDataException;
import com.diploma.edu.source.model.UtilitiesWithoutReadings;
import com.diploma.edu.source.model.Utility;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class UtilitiesCalculator {

    public static void calculateUtility(Utility utility) {

        if (UtilitiesWithoutReadings.INTERNET.utilityName.equals(utility.getService().getServiceType().getName()) ||
                UtilitiesWithoutReadings.OSMD.utilityName.equals(utility.getService().getServiceType().getName())) {
            calculateAmountToPayWithoutReadings(utility);
        } else {
            calculateAmountToPayWithReadings(utility);
        }
        utility.setStatus(true);
    }

    private static void calculateAmountToPayWithoutReadings(Utility utility) {
        utility.setAmountToPay(utility.getService().getTariff());
    }

    private static void calculateAmountToPayWithReadings(Utility utility) {
        BigInteger startMonthReadings = utility.getStartMonthReading();
        BigInteger endMonthReadings = utility.getEndMonthReading();
        BigDecimal tariff = utility.getService().getTariff();

        if (endMonthReadings == null) {
            utility.setAmountToPay(new BigDecimal(0L));
        } else {
            if (startMonthReadings == null) {
                startMonthReadings = new BigInteger("0");
            }
            if (endMonthReadings.compareTo(startMonthReadings) != 1) {
                throw new IncorrectDataException("Показания в конце месяца долны быть больше показаний в начале месяца!");
            }

            utility.setAmountToPay(tariff.multiply(new BigDecimal(endMonthReadings.subtract(startMonthReadings))));
        }
    }

}
