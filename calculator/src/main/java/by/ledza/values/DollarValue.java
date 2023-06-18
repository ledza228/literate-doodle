package by.ledza.values;

public class DollarValue extends BaseValue {

    public DollarValue(Integer value) {
        super(value);
        super.VALUE_TYPE = "dollar";
    }

    public static DollarValue parseDollarValue(String value){
        return new DollarValue(
                Integer.parseInt(
                        value.substring(1)
                                .replace(",","")
                ) * (value.contains(",") ? ((Double) Math.pow(10, 3 - (value.length() - value.indexOf(',')))).intValue() : 100)
        );
    }

    @Override
    public BaseValue plus(BaseValue secondValue) {
        checkValueTypes(secondValue);
        DollarValue dollarValue = (DollarValue) secondValue;
        return new DollarValue(this.getValue() + dollarValue.getValue());
    }

    @Override
    public BaseValue minus(BaseValue secondValue) {
        checkValueTypes(secondValue);
        DollarValue dollarValue = (DollarValue) secondValue;
        return new DollarValue(this.getValue() - dollarValue.getValue());
    }

}
