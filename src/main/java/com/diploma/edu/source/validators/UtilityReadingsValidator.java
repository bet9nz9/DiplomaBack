package com.diploma.edu.source.validators;

import com.diploma.edu.source.exceptions.IncorrectDataException;
import com.diploma.edu.source.model.UtilitiesWithoutReadings;
import com.diploma.edu.source.model.Utility;

public class UtilityReadingsValidator {

    public static void validate(Utility utility) {
        if (utility == null) {
            throw new IncorrectDataException("Ошибка при передаче коммунальной услуги.");
        }
        if (UtilitiesWithoutReadings.INTERNET.utilityName.equals(utility.getService().getServiceType().getName()) ||
                UtilitiesWithoutReadings.OSMD.utilityName.equals(utility.getService().getServiceType().getName())) {
            validateWithoutReadings(utility);
        } else {
            validateWithReadings(utility);
        }
    }

    private static void validateWithoutReadings(Utility utility) {
        if (utility.getAmountToPay() == null) {
            throw new IncorrectDataException("Сумма оплаты неверная.");
        }
    }

    private static void validateWithReadings(Utility utility) {
        if (utility.getStartMonthReading() == null){
            throw new IncorrectDataException("Показания в начале месяца не заполнены.");
        }
        if (utility.getEndMonthReading() == null){
            throw new IncorrectDataException("Показания в конце месяца не заполнены.");
        }
    }

}
