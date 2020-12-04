package nl.bobbeldijk.day4;

public enum Field {
    BYR("byr", FieldStatus.REQUIRED), // (Birth Year)
    IYR("iyr", FieldStatus.REQUIRED), // (Issue Year)
    EYR("eyr", FieldStatus.REQUIRED), // (Expiration Year)
    HGT("hgt", FieldStatus.REQUIRED), // (Height)
    HCL("hcl", FieldStatus.REQUIRED), // (Hair Color)
    ECL("ecl", FieldStatus.REQUIRED), // (Eye Color)
    PID("pid", FieldStatus.REQUIRED), // (Passport ID)
    CID("cid", FieldStatus.OPTIONAL); // (Country ID)

    private final String code;
    private final FieldStatus fieldStatus;

    Field(String code, FieldStatus fieldStatus) {
        this.code = code;
        this.fieldStatus = fieldStatus;
    }

    public String getCode() {
        return code;
    }

    public FieldStatus getFieldStatus() {
        return fieldStatus;
    }
}
