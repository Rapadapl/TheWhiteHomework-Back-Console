package Calculator;

import java.util.List;

public abstract class AbstractCalculator implements Operation {
   protected List<Integer> nums;
    private String operationName;

    public AbstractCalculator(List<Integer> nums) {
        this.nums = nums;
    }

    public List<Integer> getNums() {
        return nums;
    }

    public void setNums(List<Integer> nums) {
        this.nums = nums;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
