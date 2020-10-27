package seedu.address.logic.parser.sale;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.sale.AddCommand;
import seedu.address.logic.commands.sale.BreakdownCommand;
import seedu.address.logic.commands.sale.DeleteCommand;
import seedu.address.logic.commands.sale.EditCommand;
import seedu.address.logic.commands.sale.ListCommand;
import seedu.address.logic.commands.sale.StatsCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the command word to create a Sale Command Object corresponding to the command word.
 */
public class SaleCommandsParser implements GroupCommandsParser {

    public static final List<String> ALL_SALE_COMMAND_WORDS = Arrays.asList(
            AddCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            StatsCommand.COMMAND_WORD,
            BreakdownCommand.COMMAND_WORD
    );

    /**
     * Parses the command word and arguments to create the appropriate Command Object for Sale.
     *
     * @param commandWord The command to be executed for Sale.
     * @param arguments   The arguments to be executed with the commandWord.
     * @return Command Object for Sale.
     * @throws ParseException If the commandWord given is unknown or if the arguments given are invalid.
     */
    @Override
    public Command parse(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case StatsCommand.COMMAND_WORD:
            return new StatsCommandParser().parse(arguments);

        case BreakdownCommand.COMMAND_WORD:
            return new BreakdownCommand();

        default:
            return new UnknownCommand(commandWord);
        }
    }
}
