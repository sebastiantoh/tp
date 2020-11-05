package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MOST_SIMILAR_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_UNIT_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contact.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.sale.EditCommand.EditSaleDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.person.EditPersonDescriptorBuilder;
import seedu.address.testutil.sale.EditSaleDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    /* Contact commands */
    public static final Name PARSED_VALID_NAME_AMY = new Name("Amy Bee");
    public static final Name PARSED_VALID_NAME_BOB = new Name("Bob Choo");
    public static final Phone PARSED_VALID_PHONE_AMY = new Phone("11111111");
    public static final Phone PARSED_VALID_PHONE_BOB = new Phone("22222222");
    public static final Email PARSED_VALID_EMAIL_AMY = new Email("amy@example.com");
    public static final Email PARSED_VALID_EMAIL_BOB = new Email("bob@example.com");
    public static final Address PARSED_VALID_ADDRESS_AMY = new Address("Block 312, Amy Street 1");
    public static final Address PARSED_VALID_ADDRESS_BOB = new Address("Block 123, Bobby Street 3");
    public static final Tag PARSED_VALID_TAG_HUSBAND = new Tag("husband");
    public static final Tag PARSED_VALID_TAG_FRIEND = new Tag("friend");
    public static final Remark PARSED_VALID_REMARK_AMY = new Remark("");
    public static final Remark PARSED_VALID_REMARK_BOB = new Remark("Likes cats");

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_MESSAGE_CALL_AMY = "Call Amy";
    public static final String VALID_MESSAGE_CALL_BOB = "Call Bob";
    public static final String VALID_DATE_1 = "2020-10-30 15:19";
    public static final String VALID_DATE_2 = "2018-12-20 12:00";
    public static final String VALID_DATE_3 = "2020-12-20 12:12";
    public static final String VALID_REMARK_AMY = "";
    public static final String VALID_REMARK_BOB = "Likes cats";
    public static final String VALID_DURATION_ONE_HOUR = "60";

    public static final String NAME_DESC_AMY = " " + PREFIX_CONTACT_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_CONTACT_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_CONTACT_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_CONTACT_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_CONTACT_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_CONTACT_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_CONTACT_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_CONTACT_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String REMARK_DESC_AMY = " " + PREFIX_CONTACT_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_CONTACT_REMARK + VALID_REMARK_BOB;
    public static final String MESSAGE_CALL_AMY = " " + PREFIX_MESSAGE + VALID_MESSAGE_CALL_AMY;
    public static final String MESSAGE_CALL_BOB = " " + PREFIX_MESSAGE + VALID_MESSAGE_CALL_BOB;
    public static final String DATE_1 = " " + PREFIX_DATETIME + VALID_DATE_1;
    public static final String DATE_2 = " " + PREFIX_DATETIME + VALID_DATE_2;
    public static final String CONTACT_INDEX_SECOND = " " + PREFIX_CONTACT + INDEX_SECOND_ITEM.getOneBased();
    public static final String CONTACT_INDEX_THIRD = " " + PREFIX_CONTACT + INDEX_THIRD_ITEM.getOneBased();
    public static final String DURATION_ONE_HOUR = " " + PREFIX_DURATION + VALID_DURATION_ONE_HOUR;

    public static final String INVALID_NAME_DESC = " " + PREFIX_CONTACT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_CONTACT_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_CONTACT_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC =
            " " + PREFIX_CONTACT_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_DATE = " " + PREFIX_DATETIME + "2020/10/30 15:00";
    public static final String INVALID_CONTACT_INDEX = " " + PREFIX_CONTACT + "-1";
    public static final String INVALID_DURATION = " " + PREFIX_DURATION + "-30";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonDescriptor DESC_AMY;
    public static final EditPersonDescriptor DESC_BOB;

    /* Sale commands */
    public static final String VALID_ITEM_NAME_APPLE = "Apple";
    public static final String VALID_ITEM_NAME_BALL = "Ball";
    public static final String VALID_DATE_APPLE = "2020-10-30 15:00";
    public static final String VALID_DATE_BALL = "2020-09-22 12:40";
    public static final Integer VALID_QUANTITY_APPLE = 10;
    public static final Integer VALID_QUANTITY_BALL = 1;
    public static final String VALID_UNIT_PRICE_APPLE = "3.50";
    public static final String VALID_UNIT_PRICE_BALL = "0.80";
    public static final String VALID_SALE_TAG_EMPTY = "";
    public static final String VALID_SALE_TAG_FRUITS = "fruits";

    public static final String INVALID_ITEM_NAME = " " + PREFIX_SALE_NAME + "@pple";
    public static final String INVALID_SALE_DATE = " " + PREFIX_SALE_DATE + "2020/09/22 12:40";
    public static final String INVALID_QUANTITY = " " + PREFIX_SALE_QUANTITY + "1.5";
    public static final String INVALID_UNIT_PRICE = " " + PREFIX_SALE_UNIT_PRICE + "0.0";

    public static final String ITEM_NAME_DESC_APPLE = " " + PREFIX_SALE_NAME + VALID_ITEM_NAME_APPLE;
    public static final String SALE_DATE_DESC_APPLE = " " + PREFIX_SALE_DATE + VALID_DATE_APPLE;
    public static final String QUANTITY_DESC_APPLE = " " + PREFIX_SALE_QUANTITY + VALID_QUANTITY_APPLE;
    public static final String UNIT_PRICE_DESC_APPLE = " " + PREFIX_SALE_UNIT_PRICE + VALID_UNIT_PRICE_APPLE;
    public static final String SALE_TAG_FRUITS = " " + PREFIX_TAG + "fruits";
    public static final String ITEM_NAME_DESC_BALL = " " + PREFIX_SALE_NAME + VALID_ITEM_NAME_BALL;
    public static final String SALE_DATE_DESC_BALL = " " + PREFIX_SALE_DATE + VALID_DATE_BALL;
    public static final String QUANTITY_DESC_BALL = " " + PREFIX_SALE_QUANTITY + VALID_QUANTITY_BALL;
    public static final String UNIT_PRICE_DESC_BALL = " " + PREFIX_SALE_UNIT_PRICE + VALID_UNIT_PRICE_BALL;

    public static final String VALID_SALE_TAG = " " + PREFIX_TAG + VALID_SALE_TAG_FRUITS;

    public static final EditSaleDescriptor DESC_APPLE;
    public static final EditSaleDescriptor DESC_BALL;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).withRemark(REMARK_DESC_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    static {
        DESC_APPLE = new EditSaleDescriptorBuilder().withItemName(VALID_ITEM_NAME_APPLE).withBuyer(ALICE)
                .withDatetimeOfPurchase(VALID_DATE_APPLE).withUnitPrice(VALID_UNIT_PRICE_APPLE)
                .withQuantity(VALID_QUANTITY_APPLE).withTags(VALID_SALE_TAG_FRUITS).build();
        DESC_BALL = new EditSaleDescriptorBuilder().withItemName(VALID_ITEM_NAME_BALL).withBuyer(BENSON)
                .withDatetimeOfPurchase(VALID_DATE_BALL).withUnitPrice(VALID_UNIT_PRICE_BALL)
                .withQuantity(VALID_QUANTITY_BALL).withTags().build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the sale at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showSaleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredSaleList().size());

        Sale sale = model.getFilteredSaleList().get(targetIndex.getZeroBased());
        model.updateFilteredSaleList(x -> x.equals(sale));

        assertEquals(1, model.getFilteredSaleList().size());
    }

    /**
     * Executes the UnknownCommand for each edited commandWord in the {@code commandWords} and expects
     * the result containing the unedited commandWord.
     */
    public static void testAllCommandWords(List<String> commandWords) {
        for (String commandWord : commandWords) {
            UnknownCommand unknownCommand = new UnknownCommand(commandWord.substring(2));
            CommandResult expectedCommandResult = new CommandResult(
                    String.format(MOST_SIMILAR_COMMAND, commandWord));
            assertEquals(expectedCommandResult, unknownCommand.execute(null));
        }
    }

}
