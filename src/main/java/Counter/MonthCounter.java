package Counter;

import java.util.List;

/**
 * This class counts the amount of messages per month.
 */
public class MonthCounter
{
    private final static int LENGTH_VALID_YEAR = 4;
    private final static int START_YEAR_OF_WHATS_APP = 2010;
    private List<DateCounter> data;

    public MonthCounter(List<DateCounter> data)
    {
        this.data = data;
    }

    /**
     * Get the amount of messages for a specific month and year.
     *
     * @param month     The month of the year
     * @param year      The year
     * @return          The amount of messages for the month, '0' if an error occurs
     */
    public int getAmountOfMessageForMonthAndYear(final int month, final int year)
    {
        if(!isMonthValid(month) || !isYearValid(year))
        {
            return 0;
        }

        return calculateAmountOfMessages(month, year);
    }

    /**
     * Helper function that counts the messages for a specific month and year.
     * @param month The specific month for the calculation
     * @param year  The specific year for the month
     * @return  The sum of all messages that were communicated for this month and year, '0' for error cases
     */
    private int calculateAmountOfMessages(final int month, final int year)
    {
        int amount = 0;
        for (final DateCounter tempDc : data)
        {
            if (tempDc.getMonthInt() == month && tempDc.getYear() == year)
            {
                amount += tempDc.getCounter();
            }
        }
        return amount;
    }

    /**
     * Check if a month is valid.
     *
     * @param month The month of the year as number
     *              example: January would be 1, December 12
     *
     * @return true if the month is valid
     */
    private boolean isMonthValid(final int month)
    {
        return month > 0 && month <= 12;
    }

    /**
     * Check if a year is valid.
     * @param year The year you want to check for validation
     * @return True if the year has a correct format, otherwise false
     */
    private boolean isYearValid(final int year)
    {
        //I guess 2010 is when people started to use WhatsApp?
        return (year >= START_YEAR_OF_WHATS_APP && Integer.toString(year).length() == LENGTH_VALID_YEAR);
    }
}
