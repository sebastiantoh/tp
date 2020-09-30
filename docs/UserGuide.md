---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## Introduction

StonksBook is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, StonksBook can get your contact management tasks done faster than traditional GUI apps. 

--------------------------------------------------------------------------------------------------------------------

## Quick start (WIP)

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `stonksbook.jar` from [here](https://github.com/AY2021S1-CS2103T-T11-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your StonksBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`contact list`** : Lists all contacts.

   * **`contact add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to StonksBook.

   * **`contact delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`purge`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Contacts

#### Adding a contact: `contact add`
Adds a contact to StonksBook.

Format: `contact add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]… [r/REMARK]…​`

:bulb: Tip: A contact can have any number of tags (including 0)

Examples:
* `contact add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `contact add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal r/blacklisted`


#### Editing a contact: `contact edit`
Edits an existing contact in StonksBook.

Format: `contact edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]… [r/REMARK]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* When editing remarks, the previous remarks will also be removed/overwritten, similar to tags
* You can remove all the contact’s tags/remarks by typing `t/` or  `r/` respectively without specifying any tags/remarks after it.

Examples:
* `contact edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st contact to be 91234567 and johndoe@example.com respectively.
* `contact edit 2 n/Betsy Crower t/` edits the name of the 2nd contact to be Betsy Crower and clears all existing tags.

#### Listing all contacts: `contact list`
Shows a list of all contacts in StonksBook.

Format:`contact list`

#### Locating contacts by name: `contact find`
Finds contacts whose names contain any of the given keywords.

Format: `contact find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g hans will match Hans

* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans

* Only the name is searched.

* Contacts matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang

* There are two search result categories: exact matches and similar matches.

* For exact matches:

    * Only full words will be matched e.g. Han will not match Hans

* For similar matches:

    * Partial words will be matched e.g.  Han will match Hans

![result for 'contact find keyword'](images/contactFindMockup.png)

* Format: `contact find REGEX`

* Returns results that satisfies the regular expression under exact matches.

![result for 'contact find regex'](images/contactFindRegexMockup.png)

Examples:
* `contact find John` returns john and John Doe
* `contact find alex david` returns Alex Yeoh, David Li

#### Deleting a contact: `contact delete`
Deletes the specified contact from StonksBook.

Format: `contact delete INDEX`
* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
`contact list` followed by `contact delete 2` deletes the 2nd contact in StonksBook.
`contact find Betsy` followed by `contact delete 1` deletes the 1st contact in the results of the find command.

### Tags

#### Adding a tag: `tag add`

Adds a new customised tag of the specified name. If there is an existing tag with this name, this command will not result in any change in state.

Format: `tag add t/TAG`

* Adds a tag with the specified `TAG` as name. If this tag name already exists in the tag list, there will be no change in the program state.
* The `TAG` field must be provided.

Examples:

* `tag add t/friends` adds the tag `friends` to the tag list in StonksBook.

#### Listing all tags: `tag list`

Displays a list of all tags created so far.

![result for 'tag list'](images/tagListMockup.png)

Format: `tag list`

Examples:

* `tag list` displays all tags available in StonksBook.

#### Editing a tag: `tag edit`

Edits an existing tag in StonksBook to the specified tag name. All entries previously associated with this tag will be updated to associated with the updated tag.

Format: `tag edit INDEX n/NAME`

* Edits the name of the tag at the specified `INDEX` to be the specified `NAME`. The `INDEX` refers to the index number shown in the list displayed by the `tag list` command.
* The `INDEX` must be a positive integer 1, 2, 3, ...
* The `NAME` and `INDEX` fields must be provided.
* All contacts that have been previously associated with this tag will be updated automatically to be associated with the updated tag.

Examples:

* `tag edit 1 n/friends` updates the name of the first tag to `close friends`.

#### Deleting a tag: `tag delete`

Deletes the specified tag from the tag list. The tag information in all entries previously associated with this tag will also be cleared.

Format: `tag delete INDEX`

* Deletes the name of the tag at the specified `INDEX`. The `INDEX` refers to the index number shown in the list displayed by the `tag list` command.
* The `INDEX` must be a positive integer 1, 2, 3, ...
* The `INDEX` field must be provided.
* All contacts that have been previously associated with this tag will be updated so that their associations with this tag will be cleared.

Examples:

* `tag delete 1` deletes the first tag from the tag list.

#### Retrieving entries by tag: `tag find`

Displays all entries (including contacts, items, etc.) that are associated with the specified tag.

![result for 'tag find'](images/tagFindMockup.png)

Format: `tag find INDEX [MODEL]`

* Displays all entries of `[MODEL]` (optional, see point 3 below) associated with the tag at `INDEX`. The `INDEX` refers to the index number shown in the list displayed by the `tag list` command and `[MODEL]` refers to the model to search for (i.e. contacts, sales, etc.).
* The `INDEX` must be a positive integer 1, 2, 3, ...
* The `INDEX` field must be provided and the `[MODEL]` field is optional; when not provided, this command will display all entries that have been associated with the specified tag.

### Sales

#### Adding a sale to a customer: `sale add`

#### Listing all sales items: `sale list`

#### Deleting a sales item: `sale delete`

### Scheduled Appointments

#### Adding a scheduled appointment: `appointment add`

Adds a scheduled appointment with the specified contact in StonksBook.

Format: `appointment add CONTACT_INDEX t/TITLE s/START_DATETIME d/DURATION`

* Adds a scheduled appointment with the contact at the specified `CONTACT_INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be a positive integer 1, 2, 3, …​
* The start datetime must be in the format `YYYY-MM-DD HH:mm`
* The duration is specified in hours and must be a positive number (not necessarily an integer).

Examples:
* `appointment add 2 t/Follow-up appointment s/2020-10-30 15:00 d/1` Adds a 1-hour long appointment titled `Follow-up appointment` with the 2nd contact in StonksBook that is scheduled for 30th October 2020 at 3PM.
* `appointment add 3 t/Call to finalise details s/2020-10-30 08:00 d/0.5` Adds a 30-minute long appointment titled `Call to finalise details` with the 3rd contact in StonksBook that is scheduled for 30th October 2020 at 8AM.

#### Listing all appointments: `appointment list`

Shows a list of all appointments. By default, the list only shows upcoming appointments. This list is sorted in increasing order based on the date the appointment is scheduled.

![result for 'appointment list'](images/appointmentListMockup.png)

Format: `appointments list [CONTACT_INDEX] [a/]`

* When an index is specified, the list will only show appointments associated with the contact at the specified index.
* You can show all appointments, including those that have passed, by typing `a/`.

#### Deleting an appointment: `appointment delete`

Deletes the specified appointment from StonksBook.

Format: `appointment delete INDEX`

* Deletes the schedule at the specified `INDEX`.
* The index refers to the index number shown in the displayed appointments list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `appointment list 5` followed by `appointment delete 2` deletes the 2nd appointment that is associated with the 5th contact in StonksBook.

### Reminders

#### Adding reminders: `reminder add`

Adds a reminder scheduled on a particular date that is associated with the specified contact to StonksBook.

Format: `reminder add CONTACT_INDEX m/MESSAGE d/DATETIME`

* Adds a reminder associated with the contact at the specified `CONTACT_INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be a positive integer 1, 2, 3, …​
* The datetime must be in the format `YYYY-MM-DD HH:mm`

Examples:
* `reminder add 2 m/Send follow-up email d/2020-10-30 15:00` Adds a reminder associated with the 2nd contact that is scheduled for 30th October 2020 3PM, with the message `Send follow-up email`

#### Listing all reminders: `reminder list`

Shows a list of all reminders created, sorted in increasing order based on the date the reminder is scheduled.

![result for 'reminder list'](images/reminderListMockup.png)

Format: `reminder list`

#### Deleting a reminder: `reminder delete`

Deletes the specified reminder from StonksBook.

Format: `reminder delete INDEX`

* Deletes the reminder at the specified `INDEX`.
* The index refers to the index number shown in the displayed reminders list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `reminder list` followed by `reminder delete 2` deletes the 2nd reminder in StonksBook.

### Miscellaneous

#### Viewing help: `help`
Lists the available commands, command description and example usage as well as the link to the User Guide.

Lists the available commands, command description and example usage as well as the link to the User Guide.

Format: `COMMAND help`

* If `COMMAND` is not present,
    * list the available commands and the link to the User Guide.
![result for 'help'](images/helpAllMockup.png)

* If `COMMAND` is present
    * list the command description and example usage.
![result for 'help command'](images/helpPerCommandMockup.png)

#### Clearing all past interactions: `clear`
Clears all past interactions with the StonksBook GUI within the session.

Format: `clear`
#### Removing all data: `purge`
Clears all data from StonksBook.

Format: `purge`
#### Exiting the program: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ (WIP)

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
 the data of your previous StonksBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Contact Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​[r/REMARK]…` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague r/birthday: 20 August`
**Contact Delete** | `contact delete INDEX` <br> e.g., `contact delete 3`
**Contact Edit** | `contact edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​[r/REMARK]…` <br> e.g., `edit 2 n/James Lee e/jameslee@example.com`
**Contact Find** | `contact find KEYWORD [MORE_KEYWORDS]` or `contact find REGEX` <br> e.g., `find James Jake`, `find [^abc]`
**Contact List** | `contact list`
**Sale Add** | `sales add c/CONTACT_INDEX n/ITEM_NAME p/UNIT_PRICE q/QUANTITY` <br> e.g., `sale add c/4 n/Notebook p/6.00 q/2`
**Sale List** | `sale list`
**Sale Delete** | `sale delete c/CONTACT_INDEX s/SALE_INDEX` <br> e.g., `sale delete c/2 s/4`
**Tag Add** | `tag add t/TAG` <br> e.g., `tag add t/important`
**Tag List** | `tag list`
**Tag Edit** | `tag edit INDEX n/NAME` <br> e.g., `tag edit 1 n/family`
**Tag Delete** | `tag delete INDEX` <br> e.g., `tag delete 1`
**Tag Find** | `tag find INDEX [MODEL]` <br> e.g., `tag find 1 contact`
**Appointment Add** | `appointment add CONTACT_INDEX t/TITLE s/START_DATETIME d/DURATION` <br> e.g., `appointment add 2 t/Follow-up appointment s/2020-10-30 15:00 d/1`
**Appointment List** | `appointment list [CONTACT_INDEX] [a/]`
**Appointment Delete** | `appointment delete INDEX` <br> e.g., `appointment delete 3`
**Reminder Add** | `reminder add CONTACT_INDEX m/MESSAGE d/DATETIME` <br> e.g., `reminder add 2 m/Send follow-up email d/2020-10-30 15:00`
**Reminder List** | `reminder list`
**Reminder Delete** | `reminder delete index` <br> e.g., `reminder delete 4`
**Help** | `COMMAND help`
**Clear Chatbox** | `clear`
**Delete All Data Entries** | `purge`
**Exit Application** | `exit`


