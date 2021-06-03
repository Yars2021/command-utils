package ru.itmo.p3114.s312198.parsers;

import ru.itmo.p3114.s312198.exceptions.InputInterruptedException;
import ru.itmo.p3114.s312198.exceptions.InvalidInputException;
import ru.itmo.p3114.s312198.structures.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class FieldParser {
    public String parseName(String input) throws InvalidInputException, InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            if (input.length() == 0 || input.trim().length() == 0) {
                throw new InvalidInputException("This name is not valid");
            } else {
                return input.trim();
            }
        }
    }

    public String parseOptionalName(String input) throws InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            return input.trim().isEmpty() ? null : input.trim();
        }
    }

    public Long parseId(String input) throws InvalidInputException, InputInterruptedException {
        if (input != null) {
            try {
                return Long.parseLong(input.trim());
            } catch (NumberFormatException formatExc) {
                throw new InvalidInputException("Invalid id format");
            }
        } else {
            throw new InputInterruptedException();
        }
    }

    public Coordinates parseCoordinates(String input) throws InvalidInputException, InputInterruptedException {
        if (input != null) {
            String[] split = input.trim().split("\\s");
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
            throw new InputInterruptedException();
        }
    }

    public Long parseStudyGroupX(String input) throws InvalidInputException, InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            try {
                return Long.parseLong(input.trim());
            } catch (NumberFormatException formatExc) {
                throw new InvalidInputException("Invalid number format");
            }
        }
    }

    public Double parseStudyGroupY(String input) throws InvalidInputException, InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            try {
                double y = Double.parseDouble(input);
                if (y < 0 || y > 426) {
                    throw new InvalidInputException("Value is out of bounds");
                } else {
                    return y;
                }
            } catch (NumberFormatException formatExc) {
                throw new InvalidInputException("Invalid number format");
            }
        }
    }

    public LocalDate parseDate(String input) throws InvalidInputException, InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            try {
                return LocalDate.parse(input.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException parseExc) {
                throw new InvalidInputException("Invalid date format");
            }
        }
    }

    public Integer parseNaturalNumber(String input) throws InvalidInputException, InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            try {
                int num = Integer.parseInt(input.trim());
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

    public FormOfEducation parseFromOfEducation(String input) throws InvalidInputException, InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            try {
                return FormOfEducation.valueOf(input.trim());
            } catch (IllegalArgumentException argumentExc) {
                throw new InvalidInputException("This form of education value is not valid");
            }
        }
    }

    public Color parseHairColor(String input) throws InvalidInputException, InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            try {
                return Color.valueOf(input.trim());
            } catch (IllegalArgumentException argumentExc) {
                throw new InvalidInputException("This hair color value is not valid");
            }
        }
    }

    public Color parseOptionalHairColor(String input) throws InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            try {
                return Color.valueOf(input.trim());
            } catch (IllegalArgumentException argumentExc) {
                return null;
            }
        }
    }

    public Country parseNationality(String input) throws InvalidInputException, InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            try {
                return Country.valueOf(input.trim());
            } catch (IllegalArgumentException argumentExc) {
                throw new InvalidInputException("This nationality value is not valid");
            }
        }
    }

    public Country parseOptionalNationality(String input) throws InputInterruptedException {
        if (input == null) {
            throw new InputInterruptedException();
        } else {
            try {
                return Country.valueOf(input.trim());
            } catch (IllegalArgumentException argumentExc) {
                return null;
            }
        }
    }

    public Float parseLocationCoordinate(String input) throws InvalidInputException, InputInterruptedException {
        if (input != null) {
            try {
                return Float.parseFloat(input.trim());
            } catch (NumberFormatException formatExc) {
                throw new InvalidInputException("Invalid coordinate format");
            }
        } else {
            throw new InputInterruptedException();
        }
    }

    public Location parseLocationCoordinates(String input) throws InvalidInputException, InputInterruptedException {
        if (input != null) {
            String[] split = input.trim().split("\\s");
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
            throw new InputInterruptedException();
        }
    }
}
