package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.enums.GroupEnum;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.PurgeCommand;
import seedu.address.logic.parser.contact.ContactCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final String CONTACT_COMMAND = "contact";

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher firstCommandWordMatcher = this.getMatcherFromInput(userInput);

        final String commandWord = firstCommandWordMatcher.group("commandWord");
        final String arguments = firstCommandWordMatcher.group("arguments");


        if (this.isSingleKeyWordCommand(commandWord)) {
            return this.parseSingleKeyWordCommand(commandWord);
        } else if (this.isDoubleKeyWordCommand(commandWord)) {
            final Matcher secondCommandWordMatcher = this.getMatcherFromInput(arguments);

            final String secondCommandWord = secondCommandWordMatcher.group("commandWord");
            final String trueArguments = secondCommandWordMatcher.group("arguments");
            return this.parseTwoKeyWordCommand(commandWord, secondCommandWord, trueArguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Matcher getMatcherFromInput(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        return matcher;
    }

    private boolean isSingleKeyWordCommand(String commandWord) {
        switch (commandWord) {
        case PurgeCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
            return true;

        default:
            return false;
        }
    }

    private Command parseSingleKeyWordCommand(String commandWord) throws ParseException {
        switch(commandWord) {
        case PurgeCommand.COMMAND_WORD:
            return new PurgeCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private boolean isDoubleKeyWordCommand(String commandWord) {
        switch (commandWord) {
        case CONTACT_COMMAND:
            return true;

        default:
            return false;
        }
    }

    private Command parseTwoKeyWordCommand(String commandWord,
        String secondCommandWord, String arguments) throws ParseException {
        GroupEnum groupEnum;
        try {
            groupEnum = GroupEnum.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        final String fullCommand = String.format("%s %s", commandWord, secondCommandWord);

        switch (groupEnum) {
        case CONTACT:
            return new ContactCommandsParser().parse(fullCommand, arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
