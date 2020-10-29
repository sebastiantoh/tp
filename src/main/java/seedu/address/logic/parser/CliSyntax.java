package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions for contacts */
    public static final Prefix PREFIX_CONTACT_NAME = new Prefix("n/");
    public static final Prefix PREFIX_CONTACT_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_CONTACT_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_CONTACT_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CONTACT_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_DESC_ORDER = new Prefix("desc");

    /* Prefix definitions for tags */
    public static final Prefix PREFIX_CONTACT_TAG = new Prefix("ct/");
    public static final Prefix PREFIX_SALES_TAG = new Prefix("st/");

    /* Prefix definitions for sales */
    public static final Prefix PREFIX_SALE_CONTACT_INDEX = new Prefix("c/");
    public static final Prefix PREFIX_SALE_NAME = new Prefix("n/");
    public static final Prefix PREFIX_SALE_DATE = new Prefix("d/");
    public static final Prefix PREFIX_SALE_QUANTITY = new Prefix("q/");
    public static final Prefix PREFIX_SALE_UNIT_PRICE = new Prefix("p/");
    public static final Prefix PREFIX_SALE_INDEX = new Prefix("s/");

    public static final Prefix PREFIX_CONTACT = new Prefix("c/");
    public static final Prefix PREFIX_CLIENT = new Prefix("cl/");
    public static final Prefix PREFIX_MESSAGE = new Prefix("m/");
    public static final Prefix PREFIX_DATETIME = new Prefix("d/");
    public static final Prefix PREFIX_DURATION = new Prefix("du/");
    public static final Prefix PREFIX_MONTH = new Prefix("m/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_SALE = new Prefix("s/");
    public static final Prefix PREFIX_REMINDER_STATUS = new Prefix("st/");
    public static final Prefix PREFIX_SHOW_ALL = new Prefix("a/");
}
