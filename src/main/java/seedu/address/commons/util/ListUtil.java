package seedu.address.commons.util;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for working with lists.
 */
public class ListUtil {
    /**
     * Returns a copy of the currently displayed person list.
     */
    public static List<Person> copyDisplayedList(Model model) {
        return new ArrayList<>(model.getFilteredPersonList());
    }

    /**
     * Returns true if both lists contain the same elements in the same order.
     */
    public static boolean isSameList(List<Person> a, List<Person> b) {
        return a.equals(b);
    }
}
