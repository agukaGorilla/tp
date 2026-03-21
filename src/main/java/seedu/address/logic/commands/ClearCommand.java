package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CONFIRMATION = "Please enter y to confirm deletion, or n to cancel.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_CONFIRMATION, false, false, true, false, "Are you sure you want to delete all data?\n\nEnter 'y' to confirm or 'n' to cancel." );
    }
}
