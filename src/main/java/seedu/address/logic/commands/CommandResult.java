package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Whether the UI should show the clear confirmation window. */
    private final boolean showClearConfirmation;

    /** Whether the UI should close the clear confirmation window. */
    private final boolean closeClearConfirmation;

    /** Message to display in the clear confirmation window. */
    private final String clearConfirmationMessage;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showClearConfirmation, boolean closeClearConfirmation, String clearConfirmationMessage) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showClearConfirmation = showClearConfirmation;
        this.closeClearConfirmation = closeClearConfirmation;
        this.clearConfirmationMessage = clearConfirmationMessage;
    }

    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowClearConfirmation() {
        return showClearConfirmation;
    }

    public boolean isCloseClearConfirmation() {
        return closeClearConfirmation;
    }

    public String getClearConfirmationMessage() {
        return clearConfirmationMessage;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && showClearConfirmation == otherCommandResult.showClearConfirmation
                && closeClearConfirmation == otherCommandResult.closeClearConfirmation
                && clearConfirmationMessage == otherCommandResult.clearConfirmationMessage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showClearConfirmation, closeClearConfirmation, clearConfirmationMessage);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("showClearConfirmation", showClearConfirmation)
                .add("closeClearConfirmation", closeClearConfirmation)
                .add("clearConfirmationMessage", clearConfirmationMessage)
                .toString();
    }

}
