package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "All data has been deleted successfully";
    public static final String MESSAGE_CONFIRMATION = "Opened warning window";
    public static final String MESSAGE_NO_DATA = "No data to clear";

    private static final Logger logger = LogsCenter.getLogger(ClearCommand.class);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.getAddressBook().getPersonList().isEmpty()) {
            logger.info("Clear command rejected: address book is already empty.");
            return new CommandResult(MESSAGE_NO_DATA);
        }

        logger.info("Clear command accepted: opening warning window for confirmation.");

        return new CommandResult(
                MESSAGE_CONFIRMATION,
                false,
                false,
                true,
                false,
                "Warning!\n"
                        + "This command will clear all contacts.\n"
                        + "Press Y/N keys to confirm.\n\n"
                        + "Alternatively, click the buttons below.\n"
        );
    }
}
