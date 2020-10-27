package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.enums.GroupEnum.ARCHIVE;
import static seedu.address.commons.enums.GroupEnum.CONTACT;
import static seedu.address.commons.enums.GroupEnum.MEETING;
import static seedu.address.commons.enums.GroupEnum.REMINDER;
import static seedu.address.commons.enums.GroupEnum.SALE;
import static seedu.address.commons.enums.GroupEnum.TAG;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DarkThemeCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LightThemeCommand;
import seedu.address.logic.commands.PurgeCommand;
import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.parser.archive.ArchiveCommandsParser;
import seedu.address.logic.parser.contact.ContactCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.meeting.MeetingCommandsParser;
import seedu.address.logic.parser.reminder.ReminderCommandsParser;
import seedu.address.logic.parser.sale.SaleCommandsParser;
import seedu.address.logic.parser.tag.TagCommandsParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string.
     * @return the command based on the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher firstCommandWordMatcher = this.getMatcherFromInput(userInput);
        final String commandWord = firstCommandWordMatcher.group("commandWord");
        final String arguments = firstCommandWordMatcher.group("arguments");

        if (this.isSingleKeyWordCommand(commandWord)) {
            return this.parseSingleKeyWordCommand(commandWord);
        } else if (this.isDoubleKeyWordCommand(commandWord, arguments)) {

            final Matcher secondCommandWordMatcher = this.getMatcherFromInput(arguments);
            final String secondCommandWord = secondCommandWordMatcher.group("commandWord");
            final String trueArguments = secondCommandWordMatcher.group("arguments");

            return this.parseTwoKeyWordCommand(commandWord, secondCommandWord, trueArguments);
        } else {
            String fullCommand = String.format("%s %s", commandWord, arguments);
            return new UnknownCommand(fullCommand);
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
        case DarkThemeCommand.COMMAND_WORD:
        case LightThemeCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
            return true;

        default:
            return false;
        }
    }

    private Command parseSingleKeyWordCommand(String commandWord) {
        switch (commandWord) {
        case PurgeCommand.COMMAND_WORD:
            return new PurgeCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case DarkThemeCommand.COMMAND_WORD:
            return new DarkThemeCommand();

        case LightThemeCommand.COMMAND_WORD:
            return new LightThemeCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            return new UnknownCommand(commandWord);
        }
    }

    private boolean isDoubleKeyWordCommand(String commandWord, String arguments) {
        if (arguments.isBlank()) {
            return false;
        }

        boolean isContactCommand = commandWord.equals(CONTACT.name().toLowerCase());
        boolean isTagCommand = commandWord.equals(TAG.name().toLowerCase());
        boolean isReminderCommand = commandWord.equals(REMINDER.name().toLowerCase());
        boolean isMeetingCommand = commandWord.equals(MEETING.name().toLowerCase());
        boolean isSaleCommand = commandWord.equals(SALE.name().toLowerCase());
        boolean isArchiveCommand = commandWord.equals(ARCHIVE.name().toLowerCase());

        return isContactCommand || isTagCommand || isReminderCommand || isMeetingCommand || isSaleCommand
                || isArchiveCommand;
    }

    private Command parseTwoKeyWordCommand(String commandWord, String secondCommandWord, String arguments)
        throws ParseException {

        final String fullCommand = String.format("%s %s", commandWord, secondCommandWord);

        if (commandWord.equals(CONTACT.name().toLowerCase())) {
            return new ContactCommandsParser().parse(fullCommand, arguments);
        } else if (commandWord.equals(ARCHIVE.name().toLowerCase())) {
            return new ArchiveCommandsParser().parse(fullCommand, arguments);
        } else if (commandWord.equals(TAG.name().toLowerCase())) {
            return new TagCommandsParser().parse(fullCommand, arguments);
        } else if (commandWord.equals(REMINDER.name().toLowerCase())) {
            return new ReminderCommandsParser().parse(fullCommand, arguments);
        } else if (commandWord.equals(MEETING.name().toLowerCase())) {
            return new MeetingCommandsParser().parse(fullCommand, arguments);
        } else if (commandWord.equals(SALE.name().toLowerCase())) {
            return new SaleCommandsParser().parse(fullCommand, arguments);
        } else {
            return new UnknownCommand(fullCommand);
        }
    }
}
