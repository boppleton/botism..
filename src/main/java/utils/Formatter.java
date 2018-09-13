package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Formatter {

    public static double getRoundedPrice(double p) {

        if (p % 0.5 == 0) {
            return p;
        } else {
            return Math.floor(p);
        }


    }

    public static BigDecimal getethpointoh5round(double p) {


        if (p % 0.05 == 0) {
            return new BigDecimal(p).setScale(2, RoundingMode.HALF_EVEN);
        } else {


            return new BigDecimal(p - (p%0.05)).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    public static BigDecimal getpoint5round(double p) {
        if (p % 0.5 == 0) {
            return new BigDecimal(p).setScale(1, RoundingMode.UP);
        } else if (p % 1 > 0.7) {

            return new BigDecimal(Math.ceil(p)).setScale(1, RoundingMode.UP);

        } else if (p % 1 < 0.3) {

            return new BigDecimal(Math.floor(p)).setScale(1, RoundingMode.UP);

        } else {

            return new BigDecimal(p-(p%1) + 0.5).setScale(1, RoundingMode.UP);
        }
    }

    public static int getNumber(String text) {

        text = text.replaceAll("\\D", "");

        return Integer.parseInt(text);

    }

}
