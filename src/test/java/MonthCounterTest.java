import Counter.DateCounter;
import Counter.MonthCounter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MonthCounterTest {

    private List<DateCounter> data;

    @Before
    public void prepare() {
        data = new LinkedList<>();

        DateCounter one = new DateCounter(LocalDate.of(2021, 10, 5));
        one.increaseCounter();

        DateCounter two = new DateCounter(LocalDate.of(2021, 10, 22));
        two.increaseCounter();

        DateCounter three = new DateCounter(LocalDate.of(2021, 12, 22));
        three.increaseCounter();

        data.add(one);
        data.add(two);
        data.add(three);
    }

    @Test
    public void monthCounterShouldCountCorrectly()
    {
        MonthCounter monthCounter = new MonthCounter(data);
        assertEquals(2, monthCounter.getAmountOfMessageForMonthAndYear(10, 2021));
        assertEquals(0, monthCounter.getAmountOfMessageForMonthAndYear(11, 2021));
        assertEquals(1, monthCounter.getAmountOfMessageForMonthAndYear(12, 2021));
    }
}
