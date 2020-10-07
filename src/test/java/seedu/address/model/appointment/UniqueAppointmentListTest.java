package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.appointment.TypicalAppointments.MEET_ALICE;
import static seedu.address.testutil.appointment.TypicalAppointments.PRESENT_PROPOSAL_BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;


public class UniqueAppointmentListTest {
    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(MEET_ALICE));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(MEET_ALICE);
        assertTrue(uniqueAppointmentList.contains(MEET_ALICE));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(MEET_ALICE);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(MEET_ALICE));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(MEET_ALICE));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(MEET_ALICE);
        uniqueAppointmentList.remove(MEET_ALICE);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueAppointmentList.setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(MEET_ALICE);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(PRESENT_PROPOSAL_BENSON);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(MEET_ALICE);
        List<Appointment> appointmentsList = Collections.singletonList(PRESENT_PROPOSAL_BENSON);
        uniqueAppointmentList.setAppointments(appointmentsList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(PRESENT_PROPOSAL_BENSON);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(MEET_ALICE, MEET_ALICE);
        assertThrows(DuplicateAppointmentException.class, ()
            -> uniqueAppointmentList.setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }

}
