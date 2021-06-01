package ru.itmo.p3114.s312198;

import ru.itmo.p3114.s312198.exceptions.InputInterruptedException;
import ru.itmo.p3114.s312198.io.ConsoleReader;
import ru.itmo.p3114.s312198.parsers.RequestParser;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.io.IOException;
import java.util.LinkedHashSet;

public class Test {
    public static void main(String[] args) {
        LinkedHashSet<StudyGroup> studyGroups = new LinkedHashSet<>();
        try (ConsoleReader consoleReader = new ConsoleReader()) {
            studyGroups.add(new RequestParser().requestStudyGroup(consoleReader));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InputInterruptedException inputInterruptedException) {
            System.out.println(inputInterruptedException.getMessage());
        }
        for (StudyGroup studyGroup : studyGroups) {
            System.out.println(studyGroup.toReadableString());
        }
    }
}
