package ru.itmo.p3114.s312198.utils;

import ru.itmo.p3114.s312198.exceptions.InvalidInputException;
import ru.itmo.p3114.s312198.structures.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FiledParser {
    public String parseName(String input) throws InvalidInputException {
        if (input == null || input.length() == 0 || input.trim().length() == 0) {
            throw new InvalidInputException("This name is not valid");
        } else {
            return input.trim();
        }
    }

    public Long parseId(String input) throws InvalidInputException {
        if (input != null) {
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException formatExc) {
                throw new InvalidInputException("Invalid id format");
            }
        } else {
            throw new InvalidInputException("This id is not valid");
        }
    }

    public Coordinates parseCoordinates(String input) throws InvalidInputException {
        if (input != null) {
            String[] split = input.split("\\s");
            if (split.length != 2) {
                throw new InvalidInputException("Invalid line format");
            } else {
                try {
                    double y = Double.parseDouble(split[1]);
                    if (y < 0 || y > 426) {
                        throw new InvalidInputException("Value is out of bounds");
                    } else {
                        return new Coordinates(Long.parseLong(split[0]), y);
                    }
                } catch (NumberFormatException formatExc) {
                    throw new InvalidInputException("Invalid coordinates format");
                }
            }
        } else {
            throw new InvalidInputException("These coordinates are not valid");
        }
    }

    public LocalDate parseDate(String input) {
        if (input == null) {
            throw new InvalidInputException("This date is not valid");
        } else {
            try {
                return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException parseExc) {
                throw new InvalidInputException("Invalid date format");
            }
        }
    }

    public Integer parseNaturalNumber(String input) {
        if (input == null) {
            throw new InvalidInputException("This number is not valid");
        } else {
            try {
                int num = Integer.parseInt(input);
                if (num < 0) {
                    throw new InvalidInputException("Value is out of bounds");
                } else {
                    return num;
                }
            } catch (NumberFormatException formatExc) {
                throw new InvalidInputException("Invalid number format");
            }
        }
    }

    public FormOfEducation parseFromOfEducation(String input) {
        if (input == null) {
            throw new InvalidInputException("This form of education is not valid");
        } else {
            try {
                return FormOfEducation.valueOf(input);
            } catch (IllegalArgumentException argumentExc) {
                throw new InvalidInputException("This form of education value is not valid");
            }
        }
    }

    public Color parseHairColor(String input) {
        if (input == null) {
            throw new InvalidInputException("This hair color is not valid");
        } else {
            try {
                return Color.valueOf(input);
            } catch (IllegalArgumentException argumentExc) {
                throw new InvalidInputException("This hair color value is not valid");
            }
        }
    }

    public Country parseNationality(String input) {
        if (input == null) {
            throw new InvalidInputException("This nationality is not valid");
        } else {
            try {
                return Country.valueOf(input);
            } catch (IllegalArgumentException argumentExc) {
                throw new InvalidInputException("This nationality value is not valid");
            }
        }
    }

    public Location parseLocationCoordinates(String input) {
        if (input != null) {
            String[] split = input.split("\\s");
            if (split.length != 3) {
                throw new InvalidInputException("Invalid line format");
            } else {
                try {
                    return new Location(Float.parseFloat(split[0]), Float.parseFloat(split[1]),
                            Float.parseFloat(split[2]), null);
                } catch (NumberFormatException formatExc) {
                    throw new InvalidInputException("Invalid coordinates format");
                }
            }
        } else {
            throw new InvalidInputException("These coordinates are not valid");
        }
    }
}
