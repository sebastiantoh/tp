package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions for contacts*/
    public static final Prefix PREFIX_CONTACT_NAME = new Prefix("n/");
    public static final Prefix PREFIX_CONTACT_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_CONTACT_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_CONTACT_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_CONTACT_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CONTACT_REMARK = new Prefix("r/");

    public static final Prefix PREFIX_SALE_NAME = new Prefix("n/");
    public static final Prefix PREFIX_SALE_QUANTITY = new Prefix("q/");
    public static final Prefix PREFIX_SALE_UNIT_PRICE = new Prefix("p/");

    public static final Prefix PREFIX_CONTACT = new Prefix("c/");
    public static final Prefix PREFIX_MESSAGE = new Prefix("m/");
    public static final Prefix PREFIX_DATETIME = new Prefix("d/");
    public static final Prefix PREFIX_DURATION = new Prefix("du/");
}
