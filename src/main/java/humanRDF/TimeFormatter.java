package humanRDF;

/*
https://www.codewars.com/kata/human-readable-duration-format?utm_source=newsletter&utm_medium=email&utm_campaign=java_fejleszto_probafeladat&utm_term=2021-09-20
*/


import java.util.*;

import static humanRDF.TimeConverter.getTheDateInAMap;


public class TimeFormatter {

    public final static String COMMON_REGEX = ", ";
    public final static String FINAL_REGEX = " and ";


    public static String formatDuration(int numberToBeConverted) {
        validator(numberToBeConverted);
        if (numberToBeConverted == 0) {
            return "now";
        }
        Map<Units, Integer> readableDate = getTheDateInAMap(numberToBeConverted);
        return toString(readableDate);
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

            sb.append(String.format("%d %s", map.get(units.get(i)),
                    (map.get(units.get(i)) == 1) ? units.get(i).getNameOfTheUnit() : units.get(i).getNameOfTheUnit() + "s"));
            if (i < units.size() - 2)
                sb.append(COMMON_REGEX);
        }
        sb.append(FINAL_REGEX).append(String.format("%d %s", map.get(units.get(units.size() - 1)),
                (map.get(units.get(units.size() - 1)) == 1) ? units.get(units.size() - 1)
                        .getNameOfTheUnit() : units.get(units.size() - 1).getNameOfTheUnit() + "s"));
        return sb.toString();
    }

    private static String oneUnitOnly(Map<Units, Integer> map, List<Units> units, StringBuilder sb) {
        if (units.size() == 1) {
            sb.append(String.format("%d %s", map.get(units.get(0)),
                    (map.get(units.get(0)) == 1) ? units.get(0).getNameOfTheUnit() : units.get(0).getNameOfTheUnit() + "s"));
        }

        return sb.toString();
    }
}

