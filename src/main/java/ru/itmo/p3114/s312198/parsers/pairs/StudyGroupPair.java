package ru.itmo.p3114.s312198.parsers.pairs;

import ru.itmo.p3114.s312198.structures.StudyGroup;

public class StudyGroupPair {
    private final StudyGroup studyGroup;
    private final Integer skipped;

    public StudyGroupPair(StudyGroup studyGroup, Integer skipped) {
        this.studyGroup = studyGroup;
        this.skipped = skipped;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public Integer getSkipped() {
        return skipped;
    }
}
