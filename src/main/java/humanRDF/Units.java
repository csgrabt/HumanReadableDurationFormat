package humanRDF;

public enum Units {
    YEAR("year", 31_536_000), DAY("day", 86400), HOUR("hour", 3600),
    MINUTE("minute", 60), SECOND("second", 1);

    private final String nameOfTheUnit;
    private final Integer convertNumberToSecond;

    Units(String nameOfTheUnit, Integer convertNumberToSecond) {
        this.nameOfTheUnit = nameOfTheUnit;
        this.convertNumberToSecond = convertNumberToSecond;
    }

    public String getNameOfTheUnit() {
        return nameOfTheUnit;
    }

    public Integer getConvertNumberToSecond() {
        return convertNumberToSecond;
    }
}
