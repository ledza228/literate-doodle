package by.ledza.values;

public abstract class BaseValue {

    public String VALUE_TYPE;

    private final Integer value;

    public abstract BaseValue plus(BaseValue secondValue);

    public abstract BaseValue minus(BaseValue secondValue);

    public void checkValueTypes(BaseValue secondValue){
        if (! this.VALUE_TYPE.equals(secondValue.VALUE_TYPE)){
            throw new RuntimeException("Can't do operation with " + this.VALUE_TYPE + "" +
                    " and " + secondValue.VALUE_TYPE);
        }
    }

    public BaseValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                "VALUE_TYPE='" + VALUE_TYPE + '\'' +
                ", value=" + value +
                '}';
    }
}
