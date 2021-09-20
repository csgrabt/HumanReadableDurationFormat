package humanRDF;

public enum NameOfUnits {
    YEAR("year"), DAY("day"), HOUR("hour"), MINUTE("minute"), SECOND("second");

    private String nameOfTheUnit;

    NameOfUnits(String nameOfTheUnit) {
        this.nameOfTheUnit = nameOfTheUnit;
    }

    public String getNameOfTheUnit() {
        return nameOfTheUnit;
    }
}
