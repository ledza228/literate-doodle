package by.ledza;


import by.ledza.values.BaseValue;
import by.ledza.values.DollarValue;
import by.ledza.values.RubbleValue;

public class Main {
    public static void main(String[] args) {
        String param = args[0];
        Parser parser = new Parser();
        TreeValue tree = parser.parse(param);
        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
        BaseValue res = expressionExecutor.executeTree(tree);

        printAnswer(res);
    }


    public static void printAnswer(BaseValue baseValue){
        if (baseValue instanceof DollarValue){
            System.out.println("$" + ((baseValue.getValue() / 100.0)));
        }
        else if (baseValue instanceof RubbleValue){
            System.out.println(((baseValue.getValue() / 100.0)) + "р");
        }
    }
}