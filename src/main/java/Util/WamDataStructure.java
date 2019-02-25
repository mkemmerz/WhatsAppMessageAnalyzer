package Util;

/**
 * Simple data structure for holding basic information for the WamAnalyzerImpl.
 */
public class WamDataStructure
{
    private final int month;
    private final int year;
    private final int amountMessages;
    private final int daySinceCommunication;

    /**
     * Constructor for the data structure.
     * @param month                     The month of the year
     * @param year                      The year itself
     * @param amountMessages            Amount of whats app messages
     * @param daySinceCommunication     Days since communication started
     */
    public WamDataStructure(int month, int year, int amountMessages, int daySinceCommunication)
    {
        this.month = month;
        this.year = year;
        this.amountMessages = amountMessages;
        this.daySinceCommunication = daySinceCommunication;
    }

    public int getMonth() { return month; }
    public int getYear() { return year; }
    public int getAmountOfMessages() { return amountMessages; }
    public int getDaysSinceCommunicationStarted() { return daySinceCommunication; }
}

