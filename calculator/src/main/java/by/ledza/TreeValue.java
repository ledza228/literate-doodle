package by.ledza;

import java.util.ArrayList;
import java.util.List;

public class TreeValue {
    private final TreeValue parent;

    private final OperatorType operatorType;

    private final String value;

    private final List<TreeValue> childs;

    public TreeValue(TreeValue parent, OperatorType operatorType, String value) {
        this.parent = parent;
        this.operatorType = operatorType;
        this.value = value;
        childs = new ArrayList<>();
    }

    public TreeValue getParent() {
        return parent;
    }

    public String getValue() {
        return value;
    }

    public List<TreeValue> getChilds() {
        return childs;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

}
