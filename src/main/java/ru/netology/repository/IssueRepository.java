package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public boolean remove(Issue issue) {
        return issues.remove(issue);
    }

    public boolean removeById(int id) {
        return issues.removeIf(issue -> issue.getId() == id);
    }

    public List<Issue> findAll() {
        return issues;
    }

    public boolean saveAll(Collection<? extends Issue> issues) {
        return this.issues.addAll(issues);
    }

    public boolean removeAll(Collection<? extends Issue> issues) {
        return this.issues.removeAll(issues);
    }

    public void clear() {
        issues.clear();
    }
}
