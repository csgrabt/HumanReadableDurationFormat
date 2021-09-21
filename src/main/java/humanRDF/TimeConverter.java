package humanRDF;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TimeConverter {

    public static Map<Units, Integer> getTheDateInAMap(int numberToBeConverted) {
        Map<Units, Integer> readableDate = new TreeMap<>();
        List<Units> units = getUnitsAsAList();
        getTheNumberOfTheYearsAndPutItInTheMap(readableDate, units, numberToBeConverted / units.get(0).getConvertNumberToSecond(), 0);
        for (int i = 1; i < units.size(); i++) {
            fillTheMap(numberToBeConverted, readableDate, units, i);
        }


        return readableDate;
    }

    private static List<Units> getUnitsAsAList() {
        return Arrays.stream(Units.values()).toList();

    }

    private static void getTheNumberOfTheYearsAndPutItInTheMap(Map<Units, Integer> readableDate, List<Units> units, int numberOfYear, int indexOfTheUnit) {
        if ((numberOfYear) != 0) {
            readableDate.put(units.get(indexOfTheUnit), numberOfYear);
        }
    }

    private static void fillTheMap(int numberToBeConverted, Map<Units, Integer> date, List<Units> units, int i) {
        int value = (numberToBeConverted % units.get(i - 1).getConvertNumberToSecond()) / units.get(i).getConvertNumberToSecond();
        getTheNumberOfTheYearsAndPutItInTheMap(date, units, value, i);

    }

}
