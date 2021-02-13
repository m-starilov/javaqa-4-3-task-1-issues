package ru.netology.com.manager;

import org.junit.jupiter.api.*;
import ru.netology.domain.Issue;
import ru.netology.domain.Tag;
import ru.netology.domain.User;
import ru.netology.domain.comparators.NewestFirst;
import ru.netology.domain.comparators.OldestFirst;
import ru.netology.manager.IssueManager;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IssueManagerTest {
    private final IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
    private NewestFirst newestFirstComparator = new NewestFirst();
    private OldestFirst oldestFirstComparator = new OldestFirst();
    private HashSet<Tag> tags = new HashSet<>();
    private HashSet<User> assignee = new HashSet<>();

    private User user01 = new User(1, "user01");
    private User user02 = new User(2, "user02");
    private User user03 = new User(3, "user03");
    private User user1 = new User(4, "user1");
    private User user2 = new User(5, "user2");
    private User user3 = new User(6, "user3");

    private Tag tag1 = new Tag("tag1");
    private Tag tag2 = new Tag("tag2");
    private Tag tag3 = new Tag("tag3");
    private Tag tag4 = new Tag("tag4");

    private Issue issue1 = new Issue();
    private Issue issue2 = new Issue();
    private Issue issue3 = new Issue();
    private Issue issue4 = new Issue();
    private Issue issue5 = new Issue();
    private Issue issue6 = new Issue();
    private Issue issue7 = new Issue();
    private Issue issue8 = new Issue();
    private Issue issue9 = new Issue();

    public void setIssues() {

        issue1.setId(1);
        issue1.setDate(new Date(120, 5, 4, 12, 15, 0));
        issue1.setUpdate(new Date(120, 5, 5, 12, 15, 0));
        issue1.setType(Issue.bug);
        issue1.setTitle("title 1");
        issue1.setText("text 1");
        issue1.addTag(tag1);
        issue1.addTag(tag2);
        issue1.setClosed(false);
        issue1.setAuthor(user1);
        issue1.addAssignee(user01);
        issue1.addAssignee(user03);


        issue2.setId(2);
        issue2.setDate(new Date(120, 4, 2, 12, 12, 0));
        issue2.setUpdate(new Date(120, 4, 2, 12, 12, 0));
        issue2.setType(Issue.bug);
        issue2.setTitle("title 2");
        issue2.setText("text 2");
        issue2.addTag(tag1);
        issue2.addTag(tag2);
        issue2.setClosed(true);
        issue2.setAuthor(user2);
        issue2.addAssignee(user02);


        issue3.setId(3);
        issue3.setDate(new Date(120, 4, 3, 12, 13, 0));
        issue3.setUpdate(new Date(120, 4, 3, 12, 13, 0));
        issue3.setType(Issue.bug);
        issue3.setTitle("title 3");
        issue3.setText("text 3");
        issue3.addTag(tag1);
        issue3.addTag(tag3);
        issue3.setClosed(false);
        issue3.setAuthor(user3);
        issue3.addAssignee(user01);
        issue3.addAssignee(user02);


        issue4.setId(4);
        issue4.setDate(new Date(120, 4, 4, 12, 14, 0));
        issue4.setUpdate(new Date(120, 4, 4, 12, 14, 0));
        issue4.setType(Issue.bug);
        issue4.setTitle("title 4");
        issue4.setText("text 4");
        issue4.addTag(tag1);
        issue4.addTag(tag4);
        issue4.setClosed(false);
        issue4.setAuthor(user2);
        issue4.addAssignee(user01);
        issue4.addAssignee(user02);


        issue5.setId(5);
        issue5.setDate(new Date(120, 8, 4, 12, 15, 0));
        issue5.setUpdate(new Date(120, 8, 4, 12, 15, 0));
        issue5.setType(Issue.bug);
        issue5.setTitle("title 5");
        issue5.setText("text 5");
        issue5.addTag(tag1);
        issue5.addTag(tag4);
        issue5.setClosed(true);
        issue5.setAuthor(user2);
        issue5.addAssignee(user02);


        issue6.setId(6);
        issue6.setDate(new Date(121, 1, 4, 12, 15, 0));
        issue6.setUpdate(new Date(121, 1, 4, 12, 15, 0));
        issue6.setType(Issue.question);
        issue6.setTitle("title 6");
        issue6.setText("text 6");
        issue6.addTag(tag1);
        issue6.addTag(tag4);
        issue6.setClosed(true);
        issue6.setAuthor(user1);
        issue6.addAssignee(user01);
        issue6.addAssignee(user02);


        issue7.setId(7);
        issue7.setDate(new Date(121, 1, 4, 12, 15, 0));
        issue7.setUpdate(new Date(121, 1, 4, 12, 15, 0));
        issue7.setType(Issue.question);
        issue7.setTitle("title 7");
        issue7.setText("text 7");
        issue7.addTag(tag1);
        issue7.addTag(tag4);
        issue7.setClosed(true);
        issue7.setAuthor(user2);
        issue7.addAssignee(user03);


        issue8.setId(8);
        issue8.setDate(new Date(121, 1, 4, 12, 15, 0));
        issue8.setUpdate(new Date(121, 1, 4, 12, 15, 0));
        issue8.setType(Issue.bug);
        issue8.setTitle("title 8");
        issue8.setText("text 8");
        issue8.addTag(tag1);
        issue8.addTag(tag4);
        issue8.setClosed(true);
        issue8.setAuthor(user2);
        issue8.addAssignee(user03);


        issue9.setId(9);
        issue9.setDate(new Date(121, 1, 4, 12, 15, 0));
        issue9.setUpdate(new Date(121, 1, 4, 12, 15, 0));
        issue9.setType(Issue.feature);
        issue9.setTitle("title 9");
        issue9.setText("text 9");
        issue9.addTag(tag1);
        issue9.addTag(tag4);
        issue9.setClosed(true);
        issue9.setAuthor(user2);
        issue9.addAssignee(user03);

    }

    @Nested
    public class Empty {

        @BeforeEach
        public void setUp() {
            setIssues();
        }

        @Test
        public void shouldAddIssue() {
            manager.add(issue1);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldAddTwoIssues() {
            manager.add(issue1);
            manager.add(issue2);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldAddManyIssues() {
            manager.add(issue1);
            manager.add(issue2);
            manager.add(issue3);
            manager.add(issue4);
            manager.add(issue5);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldAddManyIssuesFromList() {
            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue1);
            newIssues.add(issue2);
            newIssues.add(issue3);
            newIssues.add(issue4);
            newIssues.add(issue5);

            assertTrue(manager.addAll(newIssues));

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotDeleteIssue() {
            manager.delete(issue1);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotDeleteByIssueId() {
            manager.deleteById(1);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotDeleteIssuesFromList() {
            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue1);
            newIssues.add(issue2);
            newIssues.add(issue3);
            newIssues.add(issue4);
            newIssues.add(issue5);

            assertFalse(manager.deleteAll(newIssues));

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);

        }

        @Test
        public void shouldNotFindOpenedIssue() {
            manager.findAllOpenedIssues();

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);

        }

        @Test
        public void shouldNotFindClosedIssue() {
            manager.findAllClosedIssues();

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);

        }
    }

    @Nested
    public class SingleItem {

        @BeforeEach
        public void setUp() {
            setIssues();
            manager.add(issue1);
        }

        @Test
        public void shouldAddIssue() {
            manager.add(issue2);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldAddTwoIssues() {
            manager.add(issue2);
            manager.add(issue3);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2, issue3);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldAddManyIssues() {
            manager.add(issue2);
            manager.add(issue3);
            manager.add(issue4);
            manager.add(issue5);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldAddManyIssuesFromList() {
            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue2);
            newIssues.add(issue3);
            newIssues.add(issue4);
            newIssues.add(issue5);

            assertTrue(manager.addAll(newIssues));

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldDeleteIssue() {
            manager.delete(issue1);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldDeleteByIssueId() {
            manager.deleteById(1);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldDeleteIssuesList() {
            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue1);
            assertTrue(manager.deleteAll(newIssues));

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetById() {
            Issue actual = manager.getById(1);
            Issue expected = issue1;

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotGetById() {
            assertNull(manager.getById(10));
        }

        @Test
        public void shouldFindOpenedIssue() {
            List<Issue> actual = manager.findAllOpenedIssues();
            List<Issue> expected = List.of(issue1);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindClosedIssue() {
            manager.clear();
            manager.add(issue2);

            List<Issue> actual = manager.findAllClosedIssues();
            List<Issue> expected = List.of(issue2);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotFindOpenedIssue() {
            manager.clear();
            manager.add(issue2);

            List<Issue> actual = manager.findAllOpenedIssues();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotFindClosedIssue() {
            List<Issue> actual = manager.findAllClosedIssues();
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFilteredByAuthor() {
            List<Issue> actual = manager.filterByAuthor(user1);
            List<Issue> expected = List.of(issue1);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotFilteredByAuthor() {
            List<Issue> actual = manager.filterByAuthor(user02);
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFilteredByLabel() {
            tags.add(tag1);

            List<Issue> actual = manager.filterByLabel(tags);
            List<Issue> expected = List.of(issue1);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotFilteredByLabel() {
            List<Issue> actual = manager.filterByLabel(tags);
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFilteredByAssignee() {
            assignee.add(user01);

            List<Issue> actual = manager.filterByAssignee(assignee);
            List<Issue> expected = List.of(issue1);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotFilteredByAssignee() {
            List<Issue> actual = manager.filterByAssignee(assignee);
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldOpenClosedIssueById() {
            manager.clear();
            manager.add(issue2);

            assertTrue(manager.getById(2).isClosed());
            assertTrue(manager.openIssueById(2));
            assertFalse(manager.getById(2).isClosed());
        }

        @Test
        public void shouldCloseOpenedIssueById() {
            assertFalse(manager.getById(1).isClosed());
            assertTrue(manager.closeIssueById(1));
            assertTrue(manager.getById(1).isClosed());
        }

        @Test
        public void shouldOpenIssueStayOpenedById() {
            assertFalse(manager.getById(1).isClosed());
            assertTrue(manager.openIssueById(1));
            assertFalse(manager.getById(1).isClosed());
        }

        @Test
        public void shouldCloseIssueStayClosedById() {
            manager.clear();
            manager.add(issue2);

            assertTrue(manager.getById(2).isClosed());
            assertTrue(manager.closeIssueById(2));
            assertTrue(manager.getById(2).isClosed());
        }
    }

    @Nested
    public class MultipleItems {

        @BeforeEach
        public void setUp() {
            setIssues();
            manager.add(issue1);
            manager.add(issue2);
            manager.add(issue3);
            manager.add(issue4);
            manager.add(issue5);
            manager.add(issue6);
        }

        @Test
        public void shouldAddIssue() {
            manager.add(issue7);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5, issue6, issue7);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldAddTwoIssues() {
            manager.add(issue7);
            manager.add(issue8);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5, issue6, issue7, issue8);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldAddManyIssues() {
            manager.add(issue7);
            manager.add(issue8);
            manager.add(issue9);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5, issue6, issue7, issue8, issue9);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldAddManyIssuesFromList() {
            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue7);
            newIssues.add(issue8);
            newIssues.add(issue9);
            boolean added = manager.addAll(newIssues);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5, issue6, issue7, issue8, issue9);

            assertTrue(added);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldDeleteIssue() {
            manager.delete(issue1);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue2, issue3, issue4, issue5, issue6);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldDeleteByIssueId() {
            manager.deleteById(2);

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue3, issue4, issue5, issue6);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldDeleteIssuesList() {
            List<Issue> newIssues = new ArrayList<>();
            newIssues.add(issue2);
            newIssues.add(issue4);
            newIssues.add(issue6);

            assertTrue(manager.deleteAll(newIssues));

            List<Issue> actual = manager.getAll();
            List<Issue> expected = List.of(issue1, issue3, issue5);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindOpenedIssue() {
            List<Issue> actual = manager.findAllOpenedIssues();
            List<Issue> expected = List.of(issue1, issue3, issue4);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFindClosedIssue() {
            List<Issue> actual = manager.findAllClosedIssues();
            List<Issue> expected = List.of(issue2, issue5, issue6);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFilteredByAuthor() {
            List<Issue> actual = manager.filterByAuthor(user1);
            List<Issue> expected = List.of(issue1, issue6);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotFilteredByAuthor() {
            List<Issue> actual = manager.filterByAuthor(user01);
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFilteredByLabel() {
            tags.add(tag4);
            tags.add(tag1);

            List<Issue> actual = manager.filterByLabel(tags);
            List<Issue> expected = List.of(issue4, issue5, issue6);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotFilteredByLabel() {
            tags.add(tag1);
            tags.add(tag2);
            tags.add(tag3);
            tags.add(tag4);

            List<Issue> actual = manager.filterByLabel(tags);
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldFilteredByAssignee() {
            assignee.add(user01);

            List<Issue> actual = manager.filterByAssignee(assignee);
            List<Issue> expected = List.of(issue1, issue3, issue4, issue6);

            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotFilteredByAssignee() {
            assignee.add(user1);

            List<Issue> actual = manager.filterByAssignee(assignee);
            List<Issue> expected = List.of();

            assertEquals(expected, actual);
        }

        @Test
        public void shouldOpenClosedIssueById() {
            assertTrue(manager.getById(2).isClosed());
            assertTrue(manager.openIssueById(2));
            assertFalse(manager.getById(2).isClosed());
        }

        @Test
        public void shouldCloseOpenedIssueById() {
            assertFalse(manager.getById(1).isClosed());
            assertTrue(manager.closeIssueById(1));
            assertTrue(manager.getById(1).isClosed());
        }

        @Test
        public void shouldOpenIssueStayOpenedById() {
            assertFalse(manager.getById(1).isClosed());
            assertTrue(manager.openIssueById(1));
            assertFalse(manager.getById(1).isClosed());
        }

        @Test
        public void shouldCloseIssueStayClosedById() {
            assertTrue(manager.getById(2).isClosed());
            assertTrue(manager.closeIssueById(2));
            assertTrue(manager.getById(2).isClosed());
        }

        @Test
        void shouldSortedByNewest() {
            List<Issue> actual = manager.sortIssues(newestFirstComparator);
            List<Issue> expected = List.of(issue6, issue5, issue1, issue4, issue3, issue2);

            assertEquals(expected, actual);
        }

        @Test
        void shouldSortedByOldest() {
            List<Issue> actual = manager.sortIssues(oldestFirstComparator);
            List<Issue> expected = List.of(issue2, issue3, issue4, issue1, issue5, issue6);

            assertEquals(expected, actual);
        }
    }
}
