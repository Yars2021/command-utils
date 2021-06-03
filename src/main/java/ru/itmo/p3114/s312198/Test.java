package ru.itmo.p3114.s312198;

import ru.itmo.p3114.s312198.commands.actions.simple.Info;
import ru.itmo.p3114.s312198.exceptions.InvalidCSVFormatException;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.LinkedHashSet;

public class Test {
    public static void main(String[] args) {
        try {
            StudyGroup studyGroup = new StudyGroup();
            studyGroup.fromCSV("Test,-123,233.2,2021-06-03,123,22,43,FULL_TIME_EDUCATION,21,Admin,123,RED,GABON,-23.2,332,2.2,Location");
            System.out.println(studyGroup.toReadableString());
            System.out.println();
            System.out.println(studyGroup.toCSV());
            LinkedHashSet<StudyGroup> studyGroups = new LinkedHashSet<>();
            studyGroups.add(studyGroup);
            Info info = new Info();
            info.setTarget(studyGroups);
            for (String line : info.execute().getOutput()) {
                System.out.println(line);
            }
        } catch (InvalidCSVFormatException invalidCSVFormatException) {
            invalidCSVFormatException.printStackTrace();
        }
    }
}
