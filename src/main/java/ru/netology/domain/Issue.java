package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue {
    private int id;
    private Date date;
    private Date update;
    private String type;
    private String title;
    private String text;
    private HashSet<Tag> tags = new HashSet<>();
    private boolean closed;
    private User author;
    private HashSet<User> assignees = new HashSet<>();

    public static final String bug = "Bug report";
    public static final String feature = "Feature request";
    public static final String question = "Question";

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void addAssignee(User assignee) {
        this.assignees.add(assignee);
    }
}
