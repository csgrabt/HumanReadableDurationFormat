package humanRDF;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeFormatterTest {

    @Test
    void testCaseFromTheWebPage() {
        assertEquals("1 minute and 2 seconds", TimeFormatter.formatDuration(62));
        assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662));
        assertEquals("1 year and 1 second", TimeFormatter.formatDuration(31_536_001));
        assertEquals("1 minute and 1 second", TimeFormatter.formatDuration(61));
    }

    @Test
    void timeIsZero() {

        assertEquals("now", TimeFormatter.formatDuration(0));
    }

    @Test
    void timeIsNotValid() {

        Exception ae = assertThrows(IllegalTimeFormatException.class, () ->
        {
            TimeFormatter.formatDuration(-1);
        });
    }

    @Test
    void theUnitIsSingular() {

        assertEquals("1 second", TimeFormatter.formatDuration(1));
        assertEquals("1 minute", TimeFormatter.formatDuration(60));
        assertEquals("1 hour", TimeFormatter.formatDuration(3600));
        assertEquals("1 day", TimeFormatter.formatDuration(3600*24));
        assertEquals("1 year", TimeFormatter.formatDuration(31_536_000));


    }

    @Test
    void theUnitIsPlural() {

        assertEquals("2 seconds", TimeFormatter.formatDuration(2));
        assertEquals("2 minutes", TimeFormatter.formatDuration(120));
        assertEquals("2 hours", TimeFormatter.formatDuration(7200));
        assertEquals("3 days", TimeFormatter.formatDuration(3600*24*3));
        assertEquals("2 years", TimeFormatter.formatDuration(63_072_000));
    }


    @Test
    void differentTimes() {
        assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662));
        assertEquals("2 years, 10 days, 3 hours, 45 minutes and 25 seconds", TimeFormatter.formatDuration(63_949_525));
        assertEquals("2 years and 1 second", TimeFormatter.formatDuration(63_072_000+1));

    }

}