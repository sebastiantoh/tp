package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DATETIME_1 = "2020/10/30 15:00";
    private static final String INVALID_DATETIME_2 = "2020-30-10 15:00";
    private static final String INVALID_DATETIME_3 = "2020-10-30 26:00";
    private static final String INVALID_DATETIME_4 = "2020-10-30 8:00";
    private static final String INVALID_DATETIME_5 = "2020-10-30 8:61";
    private static final String INVALID_DATETIME_6 = "30/10/2100 08:31";
    private static final String INVALID_DURATION_1 = "0";
    private static final String INVALID_DURATION_2 = "-60";
    private static final String INVALID_DURATION_3 = "2 hours 30 minutes";
    private static final String INVALID_DURATION_4 = "30.5";
    private static final String INVALID_DURATION_5 = "0.00";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_DATETIME = "2020-10-30 15:19";
    private static final LocalDateTime EXPECTED_DATETIME = LocalDateTime.of(2020, 10, 30, 15, 19);

    private static final String VALID_DURATION = "120";
    private static final Duration EXPECTED_DURATION = Duration.ofMinutes(120);

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ITEM, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseDateTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateTime(null));
    }

    @Test
    public void parseDateTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATETIME_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATETIME_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATETIME_3));
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATETIME_4));
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATETIME_5));
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATETIME_6));
    }

    @Test
    public void parseDateTime_validValueWithoutWhitespace_returnsLocalDateTime() throws Exception {
        assertEquals(EXPECTED_DATETIME, ParserUtil.parseDateTime(VALID_DATETIME));
    }

    @Test
    public void parseDateTime_validValueWithWhitespace_returnsLocalDateTime() throws Exception {
        String dateTimeWithWhitespace = WHITESPACE + VALID_DATETIME + WHITESPACE;
        assertEquals(EXPECTED_DATETIME, ParserUtil.parseDateTime(dateTimeWithWhitespace));
    }

    @Test
    public void parseDuration_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDuration(null));
    }

    @Test
    public void parseDuration_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION_3));
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION_4));
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION_5));
    }

    @Test
    public void parseDuration_validValueWithoutWhitespace_returnsDuration() throws Exception {
        assertEquals(EXPECTED_DURATION, ParserUtil.parseDuration(VALID_DURATION));
    }

    @Test
    public void parseDuration_validValueWithWhitespace_returnsDuration() throws Exception {
        String durationWithWhitespace = WHITESPACE + VALID_DURATION + WHITESPACE;
        assertEquals(EXPECTED_DURATION, ParserUtil.parseDuration(durationWithWhitespace));
    }

    @Test
    public void parseMonth_validValue_returnsMonth() throws Exception {
        assertEquals(Month.AUGUST, ParserUtil.parseMonth(String.valueOf(8)));
        assertEquals(Month.DECEMBER, ParserUtil.parseMonth(String.valueOf(12)));
    }

    @Test
    public void parseMonth_invalidValue_throwsParseException() {
        assertThrows(ParseException.class , () -> ParserUtil.parseMonth(String.valueOf(0)));
        assertThrows(ParseException.class , () -> ParserUtil.parseMonth(String.valueOf(13)));
        assertThrows(ParseException.class , () -> ParserUtil.parseMonth(String.valueOf(-1)));
    }

    @Test
    public void parseYear_validValue_returnsYear() throws Exception {
        assertEquals(Year.of(2020), ParserUtil.parseYear("2020"));
        assertEquals(Year.of(1990), ParserUtil.parseYear("1990"));
    }

    @Test
    public void parseYear_invalidValue_throwsParseException() {
        assertThrows(ParseException.class , () -> ParserUtil.parseYear(String.valueOf(-1)));
        assertThrows(ParseException.class , () -> ParserUtil.parseYear(String.valueOf(1000000000)));
    }

    @Test
    public void parseNumberOfMonths_validValue_returnsInt() throws ParseException {
        assertEquals(2, ParserUtil.parseNumberOfMonths("2"));
        assertEquals(6, ParserUtil.parseNumberOfMonths("6"));
        assertEquals(4, ParserUtil.parseNumberOfMonths("4"));
    }

    @Test
    public void parseNumberOfMonths_invalidValue_throwsParseException() throws ParseException {
        assertThrows(ParseException.class, () -> ParserUtil.parseNumberOfMonths("1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseNumberOfMonths("7"));
        assertThrows(ParseException.class, () -> ParserUtil.parseNumberOfMonths("-1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseNumberOfMonths("12"));
        assertThrows(ParseException.class, () -> ParserUtil.parseNumberOfMonths("abc"));
        assertThrows(ParseException.class, () -> ParserUtil.parseNumberOfMonths("1.1"));

    }
}
