package by.ledza;

import by.ledza.values.BaseValue;
import by.ledza.values.DollarValue;
import by.ledza.values.RubbleValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

import static by.ledza.OperatorType.*;

public class ExpressionExecutor {

    Stack<BaseValue> operandStack = new Stack<>();

    Map<OperatorType, Function<TreeValue, BaseValue>> mapExecutor;

    public BaseValue executeTree(TreeValue now){

        Function<TreeValue, BaseValue> func = mapExecutor.get(now.getOperatorType());
        if (func != null){
            return func.apply(now);
        }

        return executeListTree(now.getChilds());
    }

    public BaseValue executeListTree(List<TreeValue> operands){
        if (operands.size() == 1){
            return executeTree(operands.get(0));
        }

        for (int i=0; i<operands.size(); i++){
            if (operands.get(i).getOperatorType().equals(PLUS)){
                BaseValue firstValue = operandStack.pop();
                BaseValue res = firstValue.plus(executeTree(operands.get(++i)));
                operandStack.push(res);
                continue;
            }
            else if (operands.get(i).getOperatorType().equals(MINUS)){
                BaseValue firstValue = operandStack.pop();
                BaseValue res = firstValue.minus(executeTree(operands.get(++i)));
                operandStack.push(res);
                continue;
            }
            operandStack.push(executeTree(operands.get(i)));
        }
        return operandStack.pop();
    }

    public ExpressionExecutor() {
        operandStack.push(new RubbleValue(0));

        mapExecutor = new HashMap<>();
        mapExecutor.put(TO_DOLLAR_CONVERTER, this::executeToDollarConverter);
        mapExecutor.put(TO_RUBBLE_CONVERTER, this::executeToRubbleConverter);
        mapExecutor.put(DOLLAR_VALUE, this::executeDollarValue);
        mapExecutor.put(RUBBLE_VALUE, this::executeRubbleValue);

    }



    public DollarValue executeToDollarConverter(TreeValue value){
        return CurrencyConverter.convertRublesToDollar((RubbleValue) executeListTree(value.getChilds()));
    }

    public RubbleValue executeToRubbleConverter(TreeValue value){
        return CurrencyConverter.convertDollarsToRubbles((DollarValue) executeListTree(value.getChilds()));
    }

    public DollarValue executeDollarValue(TreeValue value){
        return DollarValue.parseDollarValue(value.getValue());
    }

    public RubbleValue executeRubbleValue(TreeValue value){
        return RubbleValue.parseRubleValue(value.getValue());
    }



}
