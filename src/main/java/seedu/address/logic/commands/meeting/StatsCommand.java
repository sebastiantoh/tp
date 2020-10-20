package seedu.address.logic.commands.meeting;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.commands.Command;


/**
 * Statistics on meetings
 */
public abstract class StatsCommand extends Command {

    public static final String COMMAND_WORD = "meeting stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Outputs statistics on meetings.\n"
            + "Parameters: [NUMBER OF MONTHS] "
            + "[" + PREFIX_MONTH + "MONTH "
            + PREFIX_YEAR + "YEAR] "
            + "Example: " + COMMAND_WORD + " m/10 y/2020 or "
            + COMMAND_WORD + " 4";
}
