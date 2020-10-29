package seedu.address.logic.commands.sale;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import seedu.address.logic.commands.Command;

//@@author AaronnSeah
public abstract class ListCommand extends Command {
    //@@author hakujitsu
    public static final String COMMAND_WORD = "sale list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of sales.\n"
            + "Parameters: "
            //@@author hakujitsu
            + "[" + PREFIX_SALE_CONTACT_INDEX + "CONTACT_INDEX (must be a positive integer)] "
            //@@author AaronnSeah
            + "[" + PREFIX_MONTH + "MONTH " + PREFIX_YEAR + "YEAR]\n"
            //@@author hakujitsu
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SALE_CONTACT_INDEX + "3 or "
            //@@author AaronnSeah
            + COMMAND_WORD + " " + PREFIX_MONTH + "10 " + PREFIX_YEAR + "2020";
}
