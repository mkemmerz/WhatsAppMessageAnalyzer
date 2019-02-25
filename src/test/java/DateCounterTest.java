import Counter.DateCounter;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class DateCounterTest
{
    @Test
    public void createDateTest()
    {
        Date a = new Date(18, 05, 31);
        System.out.println(a);
        assertEquals(new Date(18, 05, 31), DateCounter.buildDate("31.05.18"));
    }
}
