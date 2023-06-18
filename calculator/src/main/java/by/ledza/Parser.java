package by.ledza;

import java.util.Arrays;
import java.util.List;

import static by.ledza.OperatorType.*;

public class Parser {

    private final static String GET_VALUE = "get";

    private final ParserValidator validator = new ParserValidator();

    /*
    * Creating tree with operators
    * */
    public TreeValue parse(String expression){
        List<String> lexemes = splitOnLexemes(expression);
        TreeValue head = new TreeValue(null, START_EXECUTE, GET_VALUE);

        TreeValue now = head;
        for (String lexeme: lexemes){
            OperatorType operatorType = validator.validate(lexeme);
            if (operatorType == null){
                throw new RuntimeException("Can't validate lexeme: " + lexeme);
            }

            if (operatorType.equals(TO_DOLLAR_CONVERTER) || operatorType.equals(TO_RUBBLE_CONVERTER)){
                TreeValue treeValue = new TreeValue(now, operatorType, lexeme);
                now.getChilds().add(treeValue);
                now = treeValue;
            }
            else if (operatorType.equals(OPEN_BRACKET)){
                TreeValue treeValue = new TreeValue(now, operatorType, GET_VALUE);
                now.getChilds().add(treeValue);
                now = treeValue;
            }
            else if (operatorType.equals(CLOSE_BRACKET)){
                now = now.getParent();
                if (now.getOperatorType().equals(TO_DOLLAR_CONVERTER) || now.getOperatorType().equals(TO_RUBBLE_CONVERTER)){
                    now = now.getParent();
                }
            }
            else if (operatorType.equals(PLUS) ||
                    operatorType.equals(MINUS) ||
                    operatorType.equals(DOLLAR_VALUE) ||
                    operatorType.equals(RUBBLE_VALUE)){
                now.getChilds().add(new TreeValue(now, operatorType, lexeme));
            }

        }

        return head;
    }

    private List<String> splitOnLexemes(String expression){
        return Arrays.stream(simplifyStringWithSpaces(expression)
                .split(" ")).filter((s) -> s.length() > 0).toList();
    }

    private String simplifyStringWithSpaces(String expression){
        return expression
                .replace("+", " + ")
                .replace("-", " - ")
                .replace(")", " ) ")
                .replace("(", " ( ");
    }

}
