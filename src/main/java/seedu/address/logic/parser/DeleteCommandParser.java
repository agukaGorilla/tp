package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MembershipId;

/**
 * Parses input arguments and creates a new DeleteCommand object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final Prefix PREFIX_ID = new Prefix("id/");

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID);

        if (!argMultimap.getValue(PREFIX_ID).isPresent() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        String[] idTokens = argMultimap.getValue(PREFIX_ID).get().trim().split("\\s+");

        if (idTokens.length == 1) {
            return new DeleteCommand(parseSingleId(idTokens[0]));
        } else {
            return new DeleteCommand(parseMultipleIds(idTokens));
        }
    }

    /**
     * Parses a single membership ID string and returns a {@code MembershipId}.
     * @throws ParseException if the ID string is invalid.
     */
    private MembershipId parseSingleId(String idString) throws ParseException {
        idString = idString.trim();
        int idValue;
        try {
            idValue = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        if (!idString.equals(Integer.toString(idValue)) || !MembershipId.isValidMembershipId(idValue)) {
            throw new ParseException(MembershipId.MESSAGE_CONSTRAINTS);
        }
        return new MembershipId(idValue);
    }

    /**
     * Parses multiple membership ID strings and returns a list of {@code MembershipId}s.
     * @throws ParseException if any ID string is invalid.
     */
    private List<MembershipId> parseMultipleIds(String[] idTokens) throws ParseException {
        List<MembershipId> membershipIds = new ArrayList<>();
        for (String idString : idTokens) {
            membershipIds.add(parseSingleId(idString));
        }
        return membershipIds;
    }
}
