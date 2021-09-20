package humanRDF;

/*
https://www.codewars.com/kata/human-readable-duration-format?utm_source=newsletter&utm_medium=email&utm_campaign=java_fejleszto_probafeladat&utm_term=2021-09-20
*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeFormatter {

    public final static String COMMON_REGEX = ", ";
    public final static String FINAL_REGEX = " and ";


    public static String formatDuration(int numberToBeConverted) {
        validator(numberToBeConverted);
        if (numberToBeConverted == 0) {
            return "now";
        }

        Map<String, Integer> date = getDate(numberToBeConverted);
        deleteZeroValue(date);

        return dateFromMap(date);
    }

    private static Map<String, Integer> getDate(int numberToBeConverted) {
        Map<String, Integer> date = new HashMap<>();


        for (int i = 0; i < ConvertNumbers.values().length; i++) {
            int convertNumber = ConvertNumbers.values()[i].getConvertNumber();
            String unit = NameOfUnits.values()[i].getNameOfTheUnit();
            date.put(unit, numberToBeConverted / convertNumber);
            numberToBeConverted = numberToBeConverted % convertNumber;

            if (i == ConvertNumbers.values().length - 1) {
                date.put(NameOfUnits.values()[i + 1].getNameOfTheUnit(), numberToBeConverted);

            }
        }
        return date;
    }


    private static void deleteZeroValue(Map<String, Integer> date) {
        NameOfUnits[] nameOfUnits = NameOfUnits.values();

        for (int i = 0; i < nameOfUnits.length; i++) {
            if (date.get(nameOfUnits[i].getNameOfTheUnit()) == 0) {
                date.remove(nameOfUnits[i].getNameOfTheUnit());
            }
        }

    }


    private static String dateFromMap(Map<String, Integer> date) {
        List<String> keys = new ArrayList<>(date.keySet());

        StringBuilder sb = new StringBuilder();

        String sb1 = justOneUnitHasValue(date, keys, sb);
        if (sb1 != null) return sb1;


        for (int i = 0; i < keys.size(); i++) {

            if (date.get(keys.get(i)) == 1) {
                sb.append(date.get(keys.get(i)));

            }

        }


        return null;
    }

    private static String justOneUnitHasValue(Map<String, Integer> date, List<String> keys, StringBuilder sb) {
        if (keys.size() == 1) {
            sb.append(date.get(keys.get(0)));
            sb.append(" ").append(keys.get(0));
            if (date.get(keys.get(0)) != 1) {
                sb.append("s");
            }
            return sb.toString();
        }
        return null;
    }


    private static void validator(int i) {
        if (i < 0) {
            throw new IllegalTimeFormatException("Time must be higher than Zero");
        }
    }

    public static void main(String[] args) {
        System.out.println(formatDuration(63_949_345));
    }
}

