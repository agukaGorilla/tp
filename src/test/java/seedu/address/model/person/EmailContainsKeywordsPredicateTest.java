package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        EmailContainsKeywordsPredicate firstPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("amy@gmail.com"));
        EmailContainsKeywordsPredicate secondPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("bob@gmail.com"));

        // same object -> true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> true
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(Collections.singletonList("amy@gmail.com"));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different type -> false
        assertFalse(firstPredicate.equals(1));

        // null -> false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // one keyword matches
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("amy@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));

        // multiple keywords, one matches
        predicate = new EmailContainsKeywordsPredicate(
                Arrays.asList("bob@gmail.com", "amy@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));

        // case-insensitive match
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("AMY@GMAIL.COM"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // empty keyword list
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));

        // non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("bob@gmail.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));

        // partial match should fail
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("amy"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));

        // matches name but not email
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("Amy Bee"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Amy Bee")
                .withEmail("amy@gmail.com")
                .build()));
    }

    @Test
    public void toStringMethod() {
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Arrays.asList("amy@gmail.com", "bob@gmail.com"));

        String expected = EmailContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=[amy@gmail.com, bob@gmail.com]}";
        assertEquals(expected, predicate.toString());
    }
}
