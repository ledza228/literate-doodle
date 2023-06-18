package by.ledza;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ParserValidator {

    private final List<Pair<OperatorType, Pattern>> patternChain = new ArrayList<>();

    public ParserValidator() {
        patternChain.add(new Pair<>(OperatorType.TO_DOLLAR_CONVERTER, Pattern.compile("toDollars")));
        patternChain.add(new Pair<>(OperatorType.TO_RUBBLE_CONVERTER, Pattern.compile("toRubles")));
        patternChain.add(new Pair<>(OperatorType.OPEN_BRACKET, Pattern.compile("\\(")));
        patternChain.add(new Pair<>(OperatorType.CLOSE_BRACKET, Pattern.compile("\\)")));
        patternChain.add(new Pair<>(OperatorType.PLUS, Pattern.compile("\\+")));
        patternChain.add(new Pair<>(OperatorType.MINUS, Pattern.compile("-")));

        patternChain.add(new Pair<>(OperatorType.RUBBLE_VALUE, Pattern.compile("^[0-9]+([,][0-9]{1,2})?р")));
        patternChain.add(new Pair<>(OperatorType.DOLLAR_VALUE, Pattern.compile("^\\$[0-9]+([,][0-9]{1,2})?")));
    }

    public OperatorType validate(String lexeme){
        for (Pair<OperatorType, Pattern> patternPair: patternChain){
            if (patternPair.getValue().matcher(lexeme).matches()){
                return patternPair.key;
            }
        }
        return null;
    }


    public static class Pair<T, K> {
        private T key;

        private K value;

        public Pair(T key, K value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public K getValue() {
            return value;
        }

        public void setValue(K value) {
            this.value = value;
        }
    }
}
