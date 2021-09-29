package Counter;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is the basic class for counting the amount of messages.
 * It is holding the amount of messages for a specific day.
 */
public class DateCounter
{
    private LocalDate date;
    private int counter;

    private final int INITIALIZE_COUNTER = 0;

    //Regex that roughly describes a valid Date
    private final static String REGEX_ALMOST_VALID_DATE = "[0-9][0-9].[0-9][0-9].[0-9][0-9]";

    //Constants for building LocalDate out of String
    private final static int EXTRACT_YEAR       = 2;
    private final static int EXTRACT_MONTH      = 1;
    private final static int EXTRACT_DAY        = 0;
    private final static int VALID_DATE_ARRAY   = 3;
    private final static int TWO_MILLENIUMS     = 2000;

    //Get the date out of a line of the chat file
    private final static int START_CHAT_LINE_DATE   = 0;
    private final static int END_CHART_LINE_DATE    = 8;

    /**
     * Constructor of the class DateCounter.
     *
     * @param date The DateCounter will count the messages for a specific day.
     */
    public DateCounter(final LocalDate date)
    {
        this.date = date;
        counter = INITIALIZE_COUNTER;
    }

    /**
     * Creates a LocalDate out of String. The String has to be the format 'DD-MM-YY'.
     * Example for a valid date: '24-11-15'. '24' is the day of the month, '11' is the month itself
     * and '15' stands for the year 2015.
     *
     * @param date the string will be converted to a Date
     * @return LocalDate if the String is valid, otherwise null
     */
    public static LocalDate buildDate(final String date)
    {
        //Validate incoming date (a line of the chat file)
        if(date == null || date.length() < END_CHART_LINE_DATE)
        {
            return null;
        }

        //Validate again with regex
        Matcher matcher = Pattern.compile(REGEX_ALMOST_VALID_DATE).matcher(
                date.substring(START_CHAT_LINE_DATE, END_CHART_LINE_DATE));

        if (!matcher.find())
        {
            return null;
        }

        //The array contains the day, month and year after the split
        final String[] dateArray = matcher.group(0).split("\\.");

        if(dateArray.length != VALID_DATE_ARRAY)
        {
            //The date is not valid
            return null;
        }

        return LocalDate.of((Integer.parseInt(dateArray[EXTRACT_YEAR])+TWO_MILLENIUMS),
                Integer.parseInt(dateArray[EXTRACT_MONTH]), Integer.parseInt(dateArray[EXTRACT_DAY]));

    }

    /**
     * Get a String representation for a specific state of the counter.
     *
     * @return The year, month and day of the counter, also the current amount of messages for this day
     */
    public String toString()
    {
        return "DateCounter for Date (Y-M-D): " + this.date.getYear() + ", "+ this.date.getMonth() + ", "
                + this.date.getDayOfMonth() + " | " + "Amount of messages: " + this.counter;
    }

    /**
     * Increase the messages for this day by one.
     */
    public void increaseCounter()
    {
        counter++;
    }

    /**
     * Get the current amount of messages for this day.
     *
     * @return The amount of messages
     */
    public int getCounter()
    {
        return counter;
    }

    /**
     * Get the specific month this counter is for  (not representative without year and day).
     * @return  The month (between 1 and 12)
     */
    int getMonthInt()
    {
        return date.getMonthValue();
    }

    /**
     * Get the year this counter is for (not representative without month and day).
     * @return The year (> 2000)
     */
    int getYear()
    {
        return date.getYear();
    }

}
