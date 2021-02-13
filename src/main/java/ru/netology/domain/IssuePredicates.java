package ru.netology.domain;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IssuePredicates {

    public static Predicate<Issue> thisAuthor(User author) {
        return issue -> issue.getAuthor().equals(author);
    }

    public static Predicate<Issue> thisLabel(HashSet<Tag> tags) {
        return issue -> issue.getTags().containsAll(tags);
    }

    public static Predicate<Issue> thisAssignee(HashSet<User> assignees) {
        return issue -> issue.getAssignees().containsAll(assignees);
    }

    public static List<Issue> filterIssues(List<Issue> issues, Predicate<Issue> predicate) {
        return issues.stream().filter(predicate)
                .collect(Collectors.<Issue>toList());
    }
}
