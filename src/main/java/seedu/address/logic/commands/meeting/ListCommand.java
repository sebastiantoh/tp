package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Lists all meetings in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "meeting list";

    public static final String MESSAGE_SUCCESS = "Listed all meetings";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Meeting> meetings = model.getMeetingList();

        String output;
        if (meetings.size() == 0) {
            output = "No meetings found!";
        } else {
            output = IntStream
                    .range(0, meetings.size())
                    .mapToObj(i -> String.format("%d. %s", Index.fromZeroBased(i).getOneBased(), meetings.get(i)))
                    .collect(Collectors.joining("\n"));
        }

        return new CommandResult(output);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand; // instanceof handles nulls
    }
}
