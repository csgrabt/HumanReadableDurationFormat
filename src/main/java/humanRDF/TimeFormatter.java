package humanRDF;

/*
https://www.codewars.com/kata/human-readable-duration-format?utm_source=newsletter&utm_medium=email&utm_campaign=java_fejleszto_probafeladat&utm_term=2021-09-20
*/


import java.util.*;


public class TimeFormatter {

    public final static String COMMON_REGEX = ", ";
    public final static String FINAL_REGEX = " and ";


    public static String formatDuration(int numberToBeConverted) {
        validator(numberToBeConverted);
        if (numberToBeConverted == 0) {
            return "now";
        }
        Map<Units, Integer> result = dayGenerator(numberToBeConverted);

        return toString(result);
    }

    private static Map<Units, Integer> dayGenerator(int numberToBeConverted) {

        Map<Units, Integer> date = new TreeMap<>();

        List<Units> units = Arrays.stream(Units.values()).toList();

        if ((numberToBeConverted / units.get(0).getConvertNumberToSecond()) != 0) {

            date.put(units.get(0), numberToBeConverted / units.get(0).getConvertNumberToSecond());
        }
        for (int i = 1; i < units.size(); i++) {
            fillTheMap(numberToBeConverted, date, units, i);

        }


        return date;
    }

    private static void fillTheMap(int numberToBeConverted, Map<Units, Integer> date, List<Units> units, int i) {
        int value = (numberToBeConverted % units.get(i - 1).getConvertNumberToSecond()) / units.get(i).getConvertNumberToSecond();

        if (value != 0) {

            date.put(units.get(i), value);
        }

    }

    private static void validator(int i) {
        if (i < 0) {
            throw new IllegalTimeFormatException("Time cannot be negative!");
        }
    }


    private static String toString(Map<Units, Integer> map) {
        List<Units> units = map.keySet().stream().toList();
        StringBuilder sb = new StringBuilder();

        String sb1 = oneUnitOnly(map, units, sb);
        if (!sb.isEmpty()) return sb1;

        for (int i = 0; i < units.size() - 1; i++) {
            sb.append(map.get(units.get(i))).append(" ").append(units.get(i).getNameOfTheUnit());
            if (map.get(units.get(i)) != 1) {
                sb.append("s");
            }
            if (i < units.size() - 2)
                sb.append(COMMON_REGEX);
        }
        sb.append(FINAL_REGEX).append(map.get(units.get(units.size() - 1))).append(" ").append(units.get(units.size() - 1).getNameOfTheUnit());
        if (map.get(units.get(units.size() - 1)) != 1) {
            sb.append("s");
        }


        return sb.toString();
    }

    private static String oneUnitOnly(Map<Units, Integer> map, List<Units> units, StringBuilder sb) {
        if (units.size() == 1) {
            sb.append(map.get(units.get(0))).append(" ").append(units.get(0).getNameOfTheUnit());
            if (map.get(units.get(0)) != 1) {
                sb.append("s");
            }

        }

        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(formatDuration(12158585));
    }
}

