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

        Map<Units, Integer> date = getDate(numberToBeConverted);
        deleteZeroValue(date);

        return dateFromMap(date);
    }

    private static Map<Units, Integer> getDate(int numberToBeConverted) {
        Map<Units, Integer> date = new HashMap<>();

        for (int i = 0; i < ConvertNumbers.values().length; i++) {
            int convertNumber = ConvertNumbers.values()[i].getConvertNumber();
            Units unit = Units.values()[i];
            date.put(unit, numberToBeConverted / convertNumber);
            numberToBeConverted = numberToBeConverted % convertNumber;

            if (i == ConvertNumbers.values().length - 1) {
                date.put(Units.values()[i + 1], numberToBeConverted);

            }
        }
        return date;
    }


    private static void deleteZeroValue(Map<Units, Integer> date) {
        Units[] units = Units.values();

        for (int i = 0; i < units.length; i++) {
            if (date.get(units[i].getNameOfTheUnit()) == 0) {
                date.remove(units[i].getNameOfTheUnit());
            }
        }

    }


    private static String dateFromMap(Map<Units, Integer> date) {

      //  List<Units> keys = new ArrayList<>(date.keySet());
       Units[] units =  Units.values();

        StringBuilder sb = new StringBuilder();

        String sb1 = justOneUnitHasValue(date, units, sb);
        if (sb1 != null) return sb1;


        for (int i = 0; i < units.length; i++) {

            sb.append(date.get(units[i])).append(" ").append(units[i]);


            if (date.get(units[i]) != 1) {
                sb.append("s");
            }

            if (i < units.length - 2) {
                sb.append(COMMON_REGEX);
            }
            if (i == units.length - 2) {
                sb.append(FINAL_REGEX);
            }
        }


        return sb.toString();
    }

    private static String justOneUnitHasValue(Map<Units, Integer> date, Units[] unit, StringBuilder sb) {
        if (unit.length == 1) {
            sb.append(date.get(unit[0]));
            sb.append(" ").append(unit[0]);
            if (date.get(unit[0]) != 1) {
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

