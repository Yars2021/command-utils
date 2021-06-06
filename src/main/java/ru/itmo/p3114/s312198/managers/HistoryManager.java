package ru.itmo.p3114.s312198.managers;

import ru.itmo.p3114.s312198.commands.HistoryRecord;

import java.util.LinkedList;

public class HistoryManager {
    private final LinkedList<HistoryRecord> historyRecords = new LinkedList<>();
    private final Integer capacity;

    public HistoryManager(Integer capacity) {
        this.capacity = capacity;
    }

    public void push(HistoryRecord historyRecord) {
        historyRecords.addLast(historyRecord);
        while (historyRecords.size() > capacity) {
            historyRecords.removeFirst();
        }
    }

    public LinkedList<HistoryRecord> getHistory() {
        return historyRecords;
    }
}
