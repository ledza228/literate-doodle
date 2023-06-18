package by.ledza;

import by.ledza.values.DollarValue;
import by.ledza.values.RubbleValue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CurrencyConverter {

    public static Integer COURSE_DOLLAR;

    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        InputStream resourceStream = loader.getResourceAsStream("application.properties");
        try {
            properties.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        COURSE_DOLLAR = Integer.parseInt((String) properties.get("currency.dollar.price"));
    }

    public static DollarValue convertRublesToDollar(RubbleValue rubbles){
        return new DollarValue(rubbles.getValue() / COURSE_DOLLAR);
    }

    public static RubbleValue convertDollarsToRubbles(DollarValue dollars){
        return new RubbleValue(dollars.getValue() * COURSE_DOLLAR);
    }

}
