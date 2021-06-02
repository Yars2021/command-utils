package ru.itmo.p3114.s312198;

import ru.itmo.p3114.s312198.commands.actions.simple.Help;

public class Test {
    public static void main(String[] args) {
        for (String line : new Help().execute().getOutput()) {
            System.out.println(line);
        }
    }
}
