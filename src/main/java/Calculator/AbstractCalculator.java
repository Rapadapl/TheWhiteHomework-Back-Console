package Calculator;

import java.util.List;

public abstract class AbstractCalculator implements Operation {
   protected List<Integer> nums;

    public AbstractCalculator(List<Integer> nums) {
        this.nums = nums;
    }


    public List<Integer> getNums() {
        return nums;
    }
}
