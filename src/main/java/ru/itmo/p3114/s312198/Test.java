package ru.itmo.p3114.s312198;

import ru.itmo.p3114.s312198.io.DataFileWriter;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.io.IOException;
import java.util.LinkedHashSet;

public class Test {
    public static void main(String[] args) {
        LinkedHashSet<StudyGroup> studyGroups = new LinkedHashSet<>();
        studyGroups.add(null);

        try (DataFileWriter dataFileWriter = new DataFileWriter("test.txt")) {
            dataFileWriter.writeCollection(studyGroups);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
