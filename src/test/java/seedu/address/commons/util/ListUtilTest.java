package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ListUtilTest {

    @Test
    public void copyDisplayedList_validModel_returnsCopy() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> copy = ListUtil.copyDisplayedList(model);

        assertEquals(lastShownList, copy);
        assertNotSame(lastShownList, copy);
    }

    @Test
    public void isSameList() {
        List<Person> listA = Arrays.asList(ALICE, BENSON);
        List<Person> listB = Arrays.asList(ALICE, BENSON);
        List<Person> listC = Arrays.asList(BENSON, ALICE);
        List<Person> listD = Collections.singletonList(ALICE);

        // same object -> returns true
        assertTrue(ListUtil.isSameList(listA, listA));

        // same elements in same order -> returns true
        assertTrue(ListUtil.isSameList(listA, listB));

        // same elements in different order -> returns false
        assertFalse(ListUtil.isSameList(listA, listC));

        // different elements -> returns false
        assertFalse(ListUtil.isSameList(listA, listD));

        // null -> returns false (as per List.equals behavior)
        assertFalse(ListUtil.isSameList(listA, null));
    }
}
