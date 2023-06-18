package by.ledza.values;

public class RubbleValue extends BaseValue {

    public RubbleValue(Integer value) {
        super(value);
        super.VALUE_TYPE = "rubble";
    }

    public static RubbleValue parseRubleValue(String value){
            return new RubbleValue(
                    Integer.parseInt(
                            value.substring(0,value.length()-1)
                                    .replace(",","")
                    ) * (value.contains(",") ? ((Double) Math.pow(10, 3 - (value.length() - value.indexOf(',')))).intValue() : 100)
            );
        }

    @Override
    public BaseValue plus(BaseValue secondValue) {
        checkValueTypes(secondValue);
        RubbleValue rubbleValue = (RubbleValue) secondValue;
        return new RubbleValue(rubbleValue.getValue() + this.getValue());
    }

    @Override
    public BaseValue minus(BaseValue secondValue) {
        checkValueTypes(secondValue);
        RubbleValue rubbleValue = (RubbleValue) secondValue;
        return new RubbleValue(this.getValue() - rubbleValue.getValue());    }
}
