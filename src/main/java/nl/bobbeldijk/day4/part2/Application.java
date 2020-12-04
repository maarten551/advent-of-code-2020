package nl.bobbeldijk.day4.part2;

import nl.bobbeldijk.day4.ApplicationBase;
import nl.bobbeldijk.day4.Field;
import nl.bobbeldijk.day4.FieldStatus;
import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Application extends ApplicationBase {
    public static void main(String[] args) throws AnswerNotFoundException {
        var application = new Application();

        System.out.println(application.calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY4)));
    }

    @Override
    public Long calculateAnswer(List<String> input) throws AnswerNotFoundException {
        this.parseInput(input);

        return this.parsedPassports.stream()
                .filter(passport -> Arrays.stream(Field.values())
                        .filter(field -> FieldStatus.REQUIRED.equals(field.getFieldStatus()))
                        .allMatch(passport::containsKey))
                .filter(f -> f.entrySet().stream()
                        .allMatch(s -> this.validatePassport(s.getKey(), s.getValue())))
                .count();
    }

    /**
     * byr (Birth Year) - four digits; at least 1920 and at most 2002.
     * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
     * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
     * hgt (Height) - a number followed by either cm or in:
     * If cm, the number must be at least 150 and at most 193.
     * If in, the number must be at least 59 and at most 76.
     * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
     * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
     * pid (Passport ID) - a nine-digit number, including leading zeroes.
     * cid (Country ID) - ignored, missing or not.
     */
    private boolean validatePassport(Field field, String value) {
        switch (field) {
            case BYR:
                return Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2020;
            case IYR:
                return Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020;
            case EYR:
                return Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030;
            case HGT:
                var matcher = Pattern.compile("^(\\d+)((cm)|(in))$").matcher(value);
                if (!matcher.matches()) {
                    return false;
                }

                var number = Integer.parseInt(matcher.group(1));
                var type = matcher.group(2);

                if (type.equals("cm")) {
                    return number >= 150 && number <= 193;
                } else {
                    return number >= 59 && number <= 76;
                }
            case HCL:
                return Pattern.compile("^#[0-9a-f]{6}$").matcher(value).matches();
            case ECL:
                return Arrays.asList((new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"})).contains(value);
            case PID:
                return Pattern.compile("^[0-9]{9}$").matcher(value).matches();
            case CID:
                return true;
            default:
                return false;
        }
    }
}
