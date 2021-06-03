package ru.itmo.p3114.s312198.structures.csv;

import ru.itmo.p3114.s312198.exceptions.InvalidCSVFormatException;

public interface CSVConvertible {
    String toCSV();
    CSVConvertible fromCSV(String csv) throws InvalidCSVFormatException;
}
