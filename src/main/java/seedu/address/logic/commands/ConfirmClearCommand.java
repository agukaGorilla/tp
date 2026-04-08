package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book after confirmation.
 */
public class ConfirmClearCommand extends Command {

    public static final String COMMAND_WORD = "confirmclear";

    public static final String MESSAGE_SUCCESS = "All data has been deleted successfully";
    private static final Logger logger = LogsCenter.getLogger(ConfirmClearCommand.class);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.info("Clear confirmation received: clearing all contacts.");
        model.setAddressBook(new AddressBook());
        logger.info("Address book cleared successfully.");

        return new CommandResult(
                MESSAGE_SUCCESS,
                false,
                false,
                false,
                true,
                MESSAGE_SUCCESS
        );
    }
}
