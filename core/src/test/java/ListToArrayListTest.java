import aI.AverageMoveCalculator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ListToArrayListTest {
    private AverageMoveCalculator averageCalculator;
    @Test
    public void ListToArrayTest()
    {
        averageCalculator = new AverageMoveCalculator();
        List<String> strings = new ArrayList<>();
        strings.add("test1");
        strings.add("test2");
        strings.add("random123test");
        ArrayList<String> test = averageCalculator.testListToArray(strings);
        assertEquals(strings, test);
    }
}
