package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DURATION;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MONTH;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NUMBER_OF_MONTHS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_REMINDER_STATUS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_YEAR;
import static seedu.address.logic.commands.sale.EditCommand.MESSAGES_SALES_MISSING_TAGS;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Message;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd HH:mm")
                    .parseDefaulting(ChronoField.ERA, 1).toFormatter();
    public static final int DURATION_LOWER_LIMIT_INCLUSIVE = 1;
    public static final int DURATION_UPPER_LIMIT_INCLUSIVE = 1000000;

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        // checks if the index is too big, i.e. greater than Integer.MAX_VALUE
        try {
            new BigInteger(trimmedIndex).intValueExact();
        } catch (NumberFormatException | ArithmeticException e) {
            return Index.tooLargeIndex(oneBasedIndex);
        }

        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code Collection<String> oneBasedIndexes} into a {@code List<Index>}.
     */
    public static List<Index> parseIndexes(Collection<String> oneBasedIndexes) throws ParseException {
        requireNonNull(oneBasedIndexes);
        final List<Index> indexList = new ArrayList<>();
        for (String indexString : oneBasedIndexes) {
            indexList.add(parseIndex(indexString));
        }
        return indexList;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);

        // Prefix present with no tags specified (t/)
        if (tags.size() == 1 && tags.contains("")) {
            throw new ParseException(MESSAGES_SALES_MISSING_TAGS);
        }

        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String message} into a {@code Message}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code message} is invalid.
     */
    public static Message parseMessage(String message) throws ParseException {
        requireNonNull(message);
        String trimmedMessage = message.strip();
        if (!Message.isValidMessage(trimmedMessage)) {
            throw new ParseException(Message.MESSAGE_CONSTRAINTS);
        }
        return new Message(trimmedMessage);
    }

    /**
     * Parses a {@code String dateTime} into a {@code LocalDateTime}.
     *
     * @throws ParseException if the given {@code dateTime} is not of the format 'yyyy-MM-dd HH:mm'.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();

        try {
            return LocalDateTime.parse(trimmedDateTime, DATE_TIME_FORMATTER.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATETIME);
        }
    }

    /**
     * Parses a {@code status} into a boolean flag that indicates whether a reminder is completed.
     */
    public static Boolean parseCompletionStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();

        if (trimmedStatus.equals(ReminderStatus.COMPLETED.getStatus())) {
            return true;
        } else if (trimmedStatus.equals(ReminderStatus.PENDING.getStatus())) {
            return false;
        }

        throw new ParseException(MESSAGE_INVALID_REMINDER_STATUS);
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     *
     * @throws ParseException if the given {@code duration} is not a positive integer.
     */
    public static Duration parseDuration(String duration) throws ParseException {

        requireNonNull(duration);
        String trimmedDuration = duration.trim();

        try {
            long minutes = Long.parseLong(trimmedDuration);

            if (minutes < DURATION_LOWER_LIMIT_INCLUSIVE) {
                throw new ParseException(MESSAGE_INVALID_DURATION);
            } else if (minutes > DURATION_UPPER_LIMIT_INCLUSIVE) {
                throw new ParseException(MESSAGE_INVALID_DURATION);
            }

            return Duration.ofMinutes(minutes);
        } catch (NumberFormatException | ArithmeticException e) {
            throw new ParseException(MESSAGE_INVALID_DURATION);
        }
    }

    /**
     * Parses a {@code String month} into a {@code Month}.
     *
     * @throws ParseException if the given {@code month} is not a valid month
     */
    public static Month parseMonth(String month) throws ParseException {
        requireNonNull(month);
        try {
            return Month.of(Integer.parseInt(month.trim()));
        } catch (DateTimeException | NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_MONTH);
        }
    }

    /**
     * Parses a {@code String year} into a {@code Year}.
     *
     * @throws ParseException if the given {@code year} is not a valid year.
     */
    public static Year parseYear(String year) throws ParseException {
        requireNonNull(year);

        try {
            int yearValue = Integer.parseInt(year);
            if (yearValue <= 0) {
                throw new ParseException(MESSAGE_INVALID_YEAR);
            }
            return Year.of(yearValue);
        } catch (DateTimeException | NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_YEAR);
        }
    }

    /**
     * Parses a {@code String name} into a {@code ItemName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ItemName parseItemName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ItemName.isValidItemName(trimmedName)) {
            throw new ParseException(ItemName.MESSAGE_CONSTRAINTS);
        }
        return new ItemName(trimmedName);
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        Integer parsedQuantity;

        try {
            parsedQuantity = Integer.parseInt(trimmedQuantity);
        } catch (NumberFormatException e) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }

        if (!Quantity.isValidQuantity(parsedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }

        return new Quantity(parsedQuantity);
    }

    /**
     * Parses a {@code String unitPrice} into a {@code UnitPrice}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code unitPrice} is invalid.
     */
    public static UnitPrice parseUnitPrice(String unitPrice) throws ParseException {
        requireNonNull(unitPrice);
        String trimmedUnitPrice = unitPrice.trim();

        if (!UnitPrice.isValidUnitPriceString(trimmedUnitPrice)) {
            throw new ParseException(UnitPrice.MESSAGE_CONSTRAINTS);
        }

        BigDecimal parsedPrice = new BigDecimal(unitPrice);
        return new UnitPrice(parsedPrice);
    }

    /**
     * Parses a {@code String numberOfMonthsString} into an {@code int}.
     *
     * @throws ParseException if the given {@code numberOfMonthString} is
     * not an integer between 2 and 6 inclusive
     */
    public static int parseNumberOfMonths(String numberOfMonthsString) throws ParseException {
        requireNonNull(numberOfMonthsString);
        try {
            int numberOfMonths = Integer.parseInt(numberOfMonthsString);
            if (2 > numberOfMonths || numberOfMonths > 6) {
                throw new ParseException(MESSAGE_INVALID_NUMBER_OF_MONTHS);
            }
            return numberOfMonths;
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_OF_MONTHS);
        }
    }

    public enum ReminderStatus {
        COMPLETED("completed"),
        PENDING("pending");

        private String status;

        ReminderStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}
