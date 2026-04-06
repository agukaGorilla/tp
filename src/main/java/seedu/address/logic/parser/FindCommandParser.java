package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBERSHIP_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.ExpiryDateContainsKeywordsPredicate;
import seedu.address.model.person.MembershipExpiryDate;
import seedu.address.model.person.MembershipId;
import seedu.address.model.person.MembershipIdContainsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.PostalCodeContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        Prefix[] allPrefixes = new Prefix[] {
            PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
            PREFIX_ADDRESS, PREFIX_MEMBERSHIP_EXPIRY_DATE, PREFIX_EMAIL
        };
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPrefixes);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(allPrefixes);

        Prefix usedPrefix = argMultimap.verifyExactlyOnePrefixPresentFor(FindCommand.MESSAGE_USAGE, allPrefixes);

        String value = argMultimap.getValue(usedPrefix).get().trim().replaceAll("\\s+", " ");
        if (value.isEmpty()) {
            throw new ParseException(getConstraintMessageForPrefix(usedPrefix));
        }

        String[] tokens = value.split(" ");
        return tokens.length == 1
            ? parseSingleKeyword(usedPrefix, tokens[0])
            : parseMultipleKeywords(usedPrefix, Arrays.asList(tokens));
    }

    /**
     * Parses a single keyword and returns a FindCommand with the appropriate predicate.
     */
    private FindCommand parseSingleKeyword(Prefix prefix, String keyword) throws ParseException {
        return parseMultipleKeywords(prefix, List.of(keyword));
    }

    /**
     * Parses multiple keywords and returns a FindCommand with the appropriate predicate.
     */
    private FindCommand parseMultipleKeywords(Prefix prefix, List<String> keywords) throws ParseException {
        switch (prefix.getPrefix()) {
        case "n/":
            validateKeywordsByPrefix(prefix, keywords);
            return new FindCommand(new NameContainsKeywordsPredicate(keywords));
        case "p/":
            validateKeywordsByPrefix(prefix, keywords);
            return new FindCommand(new PhoneContainsKeywordsPredicate(keywords));
        case "e/":
            validateKeywordsByPrefix(prefix, keywords);
            return new FindCommand(new EmailContainsKeywordsPredicate(keywords));
        case "a/":
            validateKeywordsByPrefix(prefix, keywords);
            return new FindCommand(new PostalCodeContainsKeywordsPredicate(keywords));
        case "id/":
            validateKeywordsByPrefix(prefix, keywords);
            return new FindCommand(new MembershipIdContainsPredicate(keywords));
        case "m/":
            validateKeywordsByPrefix(prefix, keywords);
            return new FindCommand(new ExpiryDateContainsKeywordsPredicate(keywords));
        default:
            throw new ParseException("Find by this prefix is not yet supported.");
        }
    }

    private void validateKeywordsByPrefix(Prefix prefix, List<String> keywords) throws ParseException {
        for (String keyword : keywords) {
            switch (prefix.getPrefix()) {
            case "n/":
                ParserUtil.parseName(keyword);
                break;
            case "p/":
                ParserUtil.parsePhone(keyword);
                break;
            case "e/":
                ParserUtil.parseEmail(keyword);
                break;
            case "a/":
                ParserUtil.parseAddress(keyword);
                break;
            case "id/":
                ParserUtil.parseMembershipId(keyword);
                break;
            case "m/":
                ParserUtil.parseMembershipExpiryDate(keyword);
                break;
            default:
                throw new ParseException("Find by this prefix is not yet supported.");
            }
        }
    }

    private String getConstraintMessageForPrefix(Prefix prefix) throws ParseException {
        switch (prefix.getPrefix()) {
        case "n/":
            return Name.MESSAGE_CONSTRAINTS;
        case "p/":
            return Phone.MESSAGE_CONSTRAINTS;
        case "e/":
            return Email.MESSAGE_CONSTRAINTS;
        case "a/":
            return Address.MESSAGE_CONSTRAINTS;
        case "id/":
            return MembershipId.MESSAGE_CONSTRAINTS;
        case "m/":
            return MembershipExpiryDate.MESSAGE_CONSTRAINTS;
        default:
            throw new ParseException("Find by this prefix is not yet supported.");
        }
    }
}
