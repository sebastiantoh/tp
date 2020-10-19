package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Month;
import java.time.Year;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.MonthlyCountData;
import seedu.address.commons.MonthlyList;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of meetings that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueMeetingList implements Iterable<Meeting> {

    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    private final MonthlyList<Meeting> monthlyMeetingList = new MonthlyList<>();

    /**
     * Returns true if the list contains an equivalent meeting as the given argument.
     */
    public boolean contains(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a meeting to the list.
     * The meeting must not already exist in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetingException();
        }
        internalList.add(toAdd);
        monthlyMeetingList.addItem(toAdd.getStartDate().getMonth(),
                Year.of(toAdd.getStartDate().getYear()), toAdd);
    }

    /**
     * Removes the equivalent meeting from the list.
     * The meeting must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
        monthlyMeetingList.removeItem(toRemove.getStartDate().getMonth(),
                Year.of(toRemove.getStartDate().getYear()), toRemove);
    }

    /**
     * Removes all meetings associated with the given {@code contact} from the list.
     *
     * @param contact The contact whose associated meetings are to be removed.
     */
    public void removeMeetingsWithContact(Person contact) {
        requireNonNull(contact);
        List<Meeting> meetingsToRemove =
                internalList.stream().filter(meeting -> meeting.getPerson().equals(contact))
                        .collect(Collectors.toList());

        for (Meeting meeting : meetingsToRemove) {
            this.remove(meeting);
        }
    }

    public void setMeetings(UniqueMeetingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        this.setMonthlyMeetingList(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsAreUnique(meetings)) {
            throw new DuplicateMeetingException();
        }

        internalList.setAll(meetings);
        this.setMonthlyMeetingList(meetings);
    }

    private void setMonthlyMeetingList(List<Meeting> list) {
        this.monthlyMeetingList.clear();
        list.forEach(x -> this.monthlyMeetingList.addItem(
                x.getStartDate().getMonth(), Year.of(x.getStartDate().getYear()), x));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Gets the number of meetings in {@code month} and {@code year}.
     */
    public int getMonthMeetingsCount(Month month, Year year) {
        return this.monthlyMeetingList.getItemCount(month, year);
    }

    public List<MonthlyCountData> getMultipleMonthMeetingsCount(Month month, Year year, int numberOfMonths) {
        return this.monthlyMeetingList.getMultipleMonthCount(month, year, numberOfMonths);
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueMeetingList // instanceof handles nulls
            && internalList.equals(((UniqueMeetingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code meetings} contains only unique meetings.
     */
    private boolean meetingsAreUnique(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).equals(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
