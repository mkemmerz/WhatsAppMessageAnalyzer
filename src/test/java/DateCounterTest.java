import Counter.DateCounter;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

public class DateCounterTest {

    @Test
    public void createdDateShouldContainCorrectValues()
    {
        var builtDate = DateCounter.buildDate("31.05.18");
        assertNotNull(builtDate);
        assertEquals(builtDate.getYear(), 2018);
        assertEquals(builtDate.getMonthValue(), 5);
        assertEquals(builtDate.getDayOfMonth(), 31);
    }

    @Test
    public void faultyCreatedDateShouldBeNull() {
        assertNull( DateCounter.buildDate(null));
        assertNull( DateCounter.buildDate("asdf"));
        assertNull( DateCounter.buildDate("11111-22-33"));
        assertNull( DateCounter.buildDate(""));
    }
}
