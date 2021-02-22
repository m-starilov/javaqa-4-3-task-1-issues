package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.Tag;
import ru.netology.domain.User;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IssueManager {
    private final IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public void delete(Issue issue) {
        repository.remove(issue);
    }

    public void clear() {
        repository.clear();
    }

    public void deleteById(int id) {
        repository.removeById(id);
    }

    public List<Issue> getAll() {
        return repository.findAll();
    }

    public Issue getById(int id) {
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public boolean addAll(Collection<? extends Issue> issues) {
        return repository.saveAll(issues);
    }

    public boolean deleteAll(Collection<? extends Issue> issues) {
        return repository.removeAll(issues);
    }

    public List<Issue> findAllOpenedIssues() {
        List<Issue> allOpenedIssues = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (!issue.isClosed()) {
                allOpenedIssues.add(issue);
            }
        }
        return allOpenedIssues;
    }

    public List<Issue> findAllClosedIssues() {
        List<Issue> allClosedIssues = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.isClosed()) {
                allClosedIssues.add(issue);
            }
        }
        return allClosedIssues;
    }

    public List<Issue> filterByAuthor(User author) {
        Stream<Issue> stream = repository.findAll().stream();
        return stream.filter(issue -> issue.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public List<Issue> filterByLabel(HashSet<Tag> tags) {
        if (tags.isEmpty()) return List.of();
        Stream<Issue> stream = repository.findAll().stream();
        return stream.filter(issue -> issue.getTags().containsAll(tags)).collect(Collectors.toList());
    }

    public List<Issue> filterByAssignee(HashSet<User> assignees) {
        if (assignees.isEmpty()) return List.of();
        Stream<Issue> stream = repository.findAll().stream();
        return stream.filter(issue -> issue.getAssignees().containsAll(assignees)).collect(Collectors.toList());
    }

    public List<Issue> sortIssuesByNewest() {
        List<Issue> sortedIssues = repository.findAll();
        sortedIssues.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return sortedIssues;
    }

    public List<Issue> sortIssuesByOldest() {
        List<Issue> sortedIssues = repository.findAll();
        sortedIssues.sort(Comparator.comparing(Issue::getDate));
        return sortedIssues;
    }

    public boolean openIssueById(int id) {
        Issue issue = getById(id);
        if (issue.isClosed()) {
            issue.setClosed(false);
            issue.setUpdate(Calendar.getInstance());
        }
        return true;
    }

    public boolean closeIssueById(int id) {
        Issue issue = getById(id);
        if (!issue.isClosed()) {
            issue.setClosed(true);
            issue.setUpdate(Calendar.getInstance());
        }
        return true;
    }
}
