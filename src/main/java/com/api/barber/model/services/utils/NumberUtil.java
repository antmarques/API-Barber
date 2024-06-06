package com.api.barber.model.services.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {

    public static String numberFormatBr(Double value) {
        return NumberFormat.getCurrencyInstance(Locale.of("pt", "BR")).format(value);
    }

    public static String numberFormatUsa(Double value) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(value);
    }
}
