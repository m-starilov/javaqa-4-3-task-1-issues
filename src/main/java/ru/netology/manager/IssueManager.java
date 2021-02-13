package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.Tag;
import ru.netology.domain.User;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static ru.netology.domain.IssuePredicates.*;

public class IssueManager {
    private IssueRepository repository;

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
        return filterIssues(repository.findAll(), thisAuthor(author));
    }

    public List<Issue> filterByLabel(HashSet<Tag> tag) {
        if (tag.isEmpty()) return List.of();
        return filterIssues(repository.findAll(), thisLabel(tag));
    }

    public List<Issue> filterByAssignee(HashSet<User> assignee) {
        if (assignee.isEmpty()) return List.of();
        return filterIssues(repository.findAll(), thisAssignee(assignee));
    }

    public List<Issue> sortIssues(Comparator<Issue> comparator) {
        List<Issue> sortedIssues = repository.findAll();
        sortedIssues.sort(comparator);
        return sortedIssues;
    }

    public boolean openIssueById(int id) {
        Issue issue = getById(id);
        if (issue.isClosed()) {
            issue.setClosed(false);
            issue.setUpdate(new Date());
        }
        return true;
    }

    public boolean closeIssueById(int id) {
        Issue issue = getById(id);
        if (!issue.isClosed()) {
            issue.setClosed(true);
            issue.setUpdate(new Date());
        }
        return true;
    }
}
