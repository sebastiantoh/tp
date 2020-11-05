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
import seedu.address.model.dataset.DataSet;
import seedu.address.model.dataset.date.MonthlyCountData;
import seedu.address.model.dataset.date.MonthlyListMap;
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

    private final MonthlyListMap<Meeting> monthlyListMap = new MonthlyListMap<>();

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
        monthlyListMap.addItem(toAdd.getStartDate().getMonth(),
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
        monthlyListMap.removeItem(toRemove.getStartDate().getMonth(),
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

    /**
     * Updates the contact details of all meetings within the list that are associated with {@code contact}.
     * This is necessary when the contact details has been updated, but the meeting is still storing an outdated
     * version of the contact details.
     *
     * @param contact The contact whose information has been updated.
     */
    public void updateMeetingsWithContact(Person contact) {
        requireNonNull(contact);
        List<Meeting> meetingsToUpdate =
                internalList.stream().filter(meeting -> meeting.getPerson().hasSameId(contact))
                        .collect(Collectors.toList());

        for (Meeting meeting : meetingsToUpdate) {
            Meeting updatedMeeting =
                    new Meeting(contact, meeting.getMessage(), meeting.getStartDate(), meeting.getDuration());
            this.setMeeting(meeting, updatedMeeting);
        }
    }

    public void setMeetings(UniqueMeetingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        this.setMonthlyListMap(replacement.internalList);
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
        this.setMonthlyListMap(meetings);
    }

    /**
     * Replaces the meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the list.
     * The meeting {@code editedMeeting} must not be the same as another existing meeting in the list.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        if (!target.equals(editedMeeting) && contains(editedMeeting)) {
            throw new DuplicateMeetingException();
        }

        internalList.set(index, editedMeeting);
        this.monthlyListMap.removeItem(target.getStartDate().getMonth(),
                Year.of(target.getStartDate().getYear()), target);
        this.monthlyListMap.addItem(editedMeeting.getStartDate().getMonth(),
                Year.of(editedMeeting.getStartDate().getYear()), editedMeeting);
    }

    private void setMonthlyListMap(List<Meeting> list) {
        this.monthlyListMap.clear();
        list.forEach(x -> this.monthlyListMap.addItem(
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
        return this.monthlyListMap.getItemCount(month, year);
    }

    /**
     * Gets the monthly meeting count for each month between {@code month} and {@code year} and
     * the previous {@code numberOfMonths} - 1 months inclusive.
     */
    public DataSet<MonthlyCountData> getMultipleMonthMeetingsCount(Month month, Year year, int numberOfMonths) {
        return this.monthlyListMap.getMultipleMonthCount(month, year, numberOfMonths);
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
