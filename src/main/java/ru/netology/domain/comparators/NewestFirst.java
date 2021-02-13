package ru.netology.domain.comparators;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class NewestFirst implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}


