---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## Introduction

**StonksBook** is an all-in-one contact management application targeted at salespeople. 
From helping you managing your clients to your sales information, StonksBook is sure to boost your performance and conversion rates, all while reducing the time you spend on menial tasks such as generating performance reports.

Here are a few ways **StonksBook** can help you:
- **Data Management**: Store and organise all your client and sales information easily.
- **Personal Management**:  Track and easily review all your reminders and meetings in a single application.
- **Real-Time Insights**: Get real-time insights into your sales performance so you can further optimise and tweak your sales strategy. 
- **Ease and Speed of Use**: No more fumbling through clunky user interfaces filled with obscure buttons which you do not even you use most of the time!
StonksBook combines the best of a Command Line Interface (CLI) as well as a Graphical User Interface (GUI) - interact with the application by typing commands in the CLI and view the results in a clear GUI! 

--------------------------------------------------------------------------------------------------------------------
## About this document

Before continuing, it may be helpful to familiarise yourself with a few symbols and the different text markups that you will encounter in this User Guide:

These are the symbols used in this document: 

<div markdown="block" class="alert alert-info">
**:information_source: Additional information:**<br>

This provides you with additional information that you should take note of.
</div>

:bulb: This provides you with helpful tips.

The different text markups and their meanings are explained in the table below: 

| Markup                          | Meaning                                                           |
|---------------------------------|-------------------------------------------------------------------|
| `sample  text`                  | Represents technical terms related to StonksBook's implementation |
| <kbd>Enter</kbd>                | Represents a keystroke that can be pressed on the keyboard        |


Check out the list below on how to use this User Guide:
- If you are a first-time user, start from the [Quick start](#quick-start) section for instructions on downloading and setting up StonksBook.
- If you are interested to learn about the various features StonksBook has, check out the [Features](#features) section.
- If you are already familiar with StonksBook, but need a slight refresher, check out the [Command summary](#command-summary) section.
--------------------------------------------------------------------------------------------------------------------
## User Interface

This is what StonksBook looks like when started:

<figure>
    <img src="images/LabelledUiDiagram.png" alt="LabelledUiDiagram">
    <figcaption>Fig. 1 - Components of the GUI</figcaption>
</figure>

The user interface features several panels labelled above, that contain the **Contact List**, **Meeting List**, **Reminder List** and **Sale List**.
The bottom right panel (which currently contains the **Sale List**) is the **Ad-Hoc Panel**, 
which displays either the **Sale List** or **Tag List** depending on which command was executed most recently.
If a sale command was executed most recently, the **Sale List** is shown, 
whereas if a tag command was executed most recently, the **Tag List** is shown.

The figure below displays the User Interface with the **Tag List** being shown. 
The **Tag List** consists of two parts, the **Contact Tag List** and the **Sale Tag List**.
Besides the **Ad-Hoc Panel**, the display is unchanged for all other lists.

<figure>
    <img src="images/LabelledUiDiagram2.png" alt="LabelledUiDiagram">
    <figcaption>Fig. 2 - Components of the GUI</figcaption>
</figure>

--------------------------------------------------------------------------------------------------------------------

## Quick start

This section will provide a quick guide to get StonksBook up and running on your computer.

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `stonksbook.jar` from [here](https://github.com/AY2021S1-CS2103T-T11-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for StonksBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   
    <figure>
        <img src="images/Ui.png" alt="Ui" width="900px">
        <figcaption>Fig. 2 - The GUI</figcaption>
    </figure>

1. Type the command in the **Command Box** and press <kbd>Enter</kbd> to execute it. e.g. typing **`help`** and pressing
 <kbd>Enter</kbd> will open the help window.<br>
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
  e.g. in `contact add n/NAME`, `NAME` is a parameter which can be used as `contact add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Contacts

#### Listing all contacts: `contact list`
Shows a list of all contacts in StonksBook.

**Format**:`contact list`

#### Adding a contact: `contact add`
Adds a contact to StonksBook.

**Format**: `contact add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]… [r/REMARK]​`

| Parameter      | What it is                    | Requirements                                                                                                                                                                                                                                                                     |
|----------------|-------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `NAME`         | Name of contact               | Must contain only alphabetical characters                                                                                                                                                                                                                                        |
| `PHONE_NUMBER` | Phone number of contact       | Must contain only numerical characters                                                                                                                                                                                                                                           |
| `EMAIL`        | Email of contact              | Must be in format `local-part@domain` <br> Local-part must only contain alphanumeric characters <br> Domain name must <ul> <li> be at least 2 characters long </li> <li>start and end with alphanumeric characters </li> <li> consist of alphanumeric characters, a period or a hyphen for the characters in between </li> </ul>|
| `ADDRESS`      | Address of contact            | Can take any value                                                                                                                                                                                                                                                               |
| `TAG`          | Tag to be assigned to contact | Must exist in StonksBook first before you can associate the contact to them.                                                                                                                                                                                                     |
| `REMARK`       | Remark related to contact     | Can take any value                                                                                                                                                                                                                                                               |

:bulb: Tip: A contact can have any number of tags (including 0)

* Duplicate contacts cannot be added. A contact with the same name and same phone number/email address will be flagged as a duplicate.

**Examples**:
* `contact add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `contact add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal r/blacklisted`

#### Deleting a contact: `contact delete`
Deletes the specified contact from StonksBook. All associated sales, reminders and meetings will be deleted as well.

**Format**: `contact delete INDEX`

| Parameter      | What it is                                       | Requirements                               |
|----------------|--------------------------------------------------|--------------------------------------------|
| `INDEX`        | Index number shown in the displayed contact list | Must be a positive integer 1, 2, 3, …      |

* Deletes the contact at the specified `INDEX`.

**Examples**:
`contact list` followed by `contact delete 2` deletes the 2nd contact in StonksBook.
`contact find Betsy` followed by `contact delete 1` deletes the 1st contact in the results of the find command.

#### Editing a contact: `contact edit`
Edits an existing contact in StonksBook.

**Format**: `contact edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]… [r/REMARK]`

| Parameter      | What it is                                       | Requirements                                                                                                                                                                                                                                                                     |
|----------------|--------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`        | Index number shown in the displayed contact list | Must be a positive integer 1, 2, 3, …                                                                                                                                                                                                                                            |
| `NAME`         | Name of contact                                  | Must contain only alphabetical characters                                                                                                                                                                                                                                        |
| `PHONE_NUMBER` | Phone number of contact                          | Must contain only numerical characters                                                                                                                                                                                                                                           |
| `EMAIL`        | Email of contact                                 | Must be in format `local-part@domain` <br> Local-part must only contain alphanumeric characters <br> Domain name must <ul> <li> be at least 2 characters long </li> <li>start and end with alphanumeric characters </li> <li> consist of alphanumeric characters, a period or a hyphen for the characters in between </li> </ul>|
| `ADDRESS`      | Address of contact                               | Can take any value                                                                                                                                                                                                                                                               |
| `TAG`          | Tag to be assigned to contact                    | Must exist in StonksBook first before you can associate the contact to them.                                                                                                                                                                                                     |
| `REMARK`       | Remark related to contact                        | Can take any value                                                                                                                                                                                                                                                               |

* Edits the contact at the specified `INDEX`. 
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing a contact's tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* When editing a contact's remark, the previous remark will also be removed/overwritten.
* You can remove all the contact’s tags/remark by typing `t/` or  `r/` respectively without specifying any tags/remark after it.
* The contact tags provided must exist in StonksBook before you can associate this contact with them.

**Examples**:
* `contact edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st contact to be 91234567 and johndoe@example.com respectively.
* `contact edit 2 n/Betsy Crower t/` edits the name of the 2nd contact to be Betsy Crower and clears all existing tags.

#### Locating contacts by name: `contact find` \[Aaron Seah\]
Finds contacts whose name exactly matches or is similar to any of the given keywords.

**Format**: `contact find KEYWORD [MORE_KEYWORDS]...`

| Parameter      | What it is                                       | Requirements                                                                                                                                                                                                                                                                     |
|----------------|--------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `KEYWORD`      | Keyword for finding contacts by name             | Can take any value                                                                                                                                                                                                                                         |


* The search is case-insensitive. e.g. keyword 'hans' will match the contact with the name 'Hans'.

* The contact list is ordered by non-ascending similarity to the given keywords.

* Exact matches (if exist) will appear as the top results.

**Example**:

Let's say you want to find the contact with the name 'Alex Yeoh' but you do not know the correct spelling of the name. 
You decide to search for 'alx yo' which has a similar pronunciation.
You can still find the contact with the name 'Alex Yeoh' as shown.

1. Type `contact find alx yo` in the **Command Box** and press <kbd>Enter</kbd>.
   
    <figure>
        <img src="images/contact-find/contactFindFirstStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>
   
2. The **Contact List** now contains contacts whose name is similar to 'alx yo'.

    <figure>
        <img src="images/contact-find/contactFindSecondStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>
    
3. The contact with the name 'Alex Yeoh' appears as the first contact on the **Contact List**.

    <figure>
        <img src="images/contact-find/contactFindThirdStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>

#### Sorting contacts: `contact sort` \[Aaron Seah\]
Sorts contacts based on the contact's name or email address.

**Format**: `contact sort KEYWORD [ORDER]`

| Parameter      | What it is                                       | Requirements                                                                                                                                                                                                                                                                     |
|----------------|--------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `KEYWORD`      | Keyword for sorting contacts                     | Must be either `n/` or `e/`                                                                                                                                                                                                                                            |
| `ORDER`        | Sorting order                                    | Must be `desc`                                                                                                                                                                                                                                        |

* If `ORDER` is present, the contacts will be sorted in reverse alphabetical order.
Otherwise, the contacts will be sorted in alphabetical order.

* The sorting order is case-insensitive. e.g. A contact with the name 'Alex' is equivalent to a contact with the name 'alex' in the sorting process.

* The sorting effect will only last until another contact command except `contact delete` is executed.

**Example**:

Let's say you want to see your contacts in reverse alphabetical order based on their name.

1. Type `contact sort n/ desc` in the **Command Box** and press <kbd>Enter</kbd>.

    <figure>
        <img src="images/contact-sort/contactSortFirstStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>
    
2. The contacts in the **Contact List** is sorted in reverse alphabetical order based on the contact name.

    <figure>
        <img src="images/contact-sort/contactSortSecondStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>

### Tags

#### Adding a tag: `tag add` \[Wang Luo\]

Adds a new customised contact tag or sales tag to StonksBook.

<div markdown="block" class="alert alert-info">
StonksBook will not allow you to add a new contact tag (or sales tag) if there already exists a contact tag (or sales tag) of the same name.
</div>

**Format**: `tag add (ct/ or st/)TAG`

* `ct/` stands for contact tag, `st/` stands for sales tag.
* Adds a contact tag (if `ct/` is typed) or a sales tag (if `st/` is typed) with the specified `TAG` as the tag name to the contact tag list (or sales tag list).
* The `TAG` field must be provided.

**Examples**:

Let's say that you want to add a new sales tag called electronics. Here are the steps to follow:

1. Type `tag add st/electronics` in the **Command Box** and press <kbd>Enter</kbd>.

    <figure>
        <img src="images/tag-add/tag-add.png" alt="Enter 'tag add st/electronics' in the Command Box" width="900px">
    </figure>
    
2. The **Result Box** will display a message noting that the command was successful, and the **Sales Tags** will now contain this new sales tag.

    <figure>
        <img src="images/tag-add/tag-add-result.png" alt="Result for 'tag add st/electronics'" width="900px">
    </figure>

#### Listing all tags: `tag list` \[Wang Luo\]

Displays a list of all tags created so far.

**Format**: `tag list`

**Examples**:

Let's say that you want to see all the tags you have created so far. Here are the steps to follow:

1. Type `tag list` in the **Command Box** and press <kbd>Enter</kbd>.

    <figure>
        <img src="images/tag-list/tag-list.png" alt="Enter 'tag list'" width="900px">
    </figure>

2. The **Result Box** will display a message noting that the command was successful, and the bottom right box will now display **Contact Tags** and **Sales Tags**.

    <figure>
        <img src="images/tag-list/tag-list-result.png" alt="Result for 'tag list'" width="900px">
    </figure>

#### Editing a tag: `tag edit` \[Wang Luo\]

Edits an existing tag in StonksBook to the specified tag name.

<div markdown="block" class="alert alert-info">
All entries (contacts or sales) previously associated with this tag will be updated to associate with the updated tag.
</div>

**Format**: `tag edit (ct/ or st/)INDEX t/TAG`

* Edits the name of the contact tag or sales tag at the specified `INDEX` to be the specified `NAME`. The `INDEX` refers to the index number shown in the contact tags list or sales tags list displayed by the `tag list` command.
* The `INDEX` must be a positive integer 1, 2, 3, ...
* The `TAG` and `INDEX` fields must be provided.
* All contacts or sales that have been previously associated with this tag will be updated automatically to be associated with the updated tag.

**Examples**:

Let's say that you decides to change the tag colleagues to teammates instead. Here are the steps to follow:

1. Type `tag list` in the **Command Box** and press <kbd>Enter</kbd>.
    - This is to display the contact tags and sales tags in StonksBook.

    <figure>
        <img src="images/tag-edit/tag-list-second.png" alt="Result for 'tag list'" width="900px">
    </figure>

2. Type `tag edit ct/2 t/teammates` in the **Command Box** and press <kbd>Enter</kbd>.

    <figure>
        <img src="images/tag-edit/tag-edit.png" alt="Enter 'tag edit ct/2 t/teammates'" width="900px">
    </figure>

3. The **Result Box** will display a message noting that the command was successful, and the **Contact Tags** will now contain teammates instead of colleagues. Note that the tags are sorted alphabetically, hence, you may need to scroll up or down to see the updated tag. Also, note that the contact Bernice Yu is now tagged to teammates.

    <figure>
        <img src="images/tag-edit/tag-edit-result.png" alt="Enter 'tag edit ct/2 t/teammates'" width="900px"> 
    </figure>    

#### Deleting a tag: `tag delete` \[Wang Luo\]

Deletes the specified tag from the tag list. The tag information in all entries previously associated with this tag will also be cleared.

**Format**: `tag delete (st/ or ct/)INDEX`

* Deletes the the contact tag or sales tag at the specified `INDEX`. The `INDEX` refers to the index number shown in the list displayed by the `tag list` command.
* The `INDEX` must be a positive integer 1, 2, 3, ...
* The `INDEX` field must be provided.
* All contacts that have been previously associated with this tag will be updated so that their associations with this tag will be cleared.

**Examples**:

Let's say that you think one contact tag (colleagues) you created before is no longer relevant and you would like to get rid of it. Here are the steps to follow:

1. Type `tag list` in the **Command Box** and press <kbd>Enter</kbd>.
    - This is to display the contact tags and sales tags in StonksBook, and the contact tag colleagues is found to be at index 2.

    <figure> 
        <img src="images/tag-edit/tag-list-second.png" alt="Result for 'tag list'" width="900px">
    </figure>  

2. Type `tag delete ct/2` in the **Command Box** and press <kbd>Enter</kbd>.

    <figure> 
        <img src="images/tag-delete/tag-delete.png" alt="Enter 'tag delete ct/2'" width="900px">
    </figure> 

3. The **Result Box** will display a message noting that the command was successful, and the **Contact Tags** will no longer contain the contact tag colleagues. Also note that Bernice Yu is no longer tagged with colleagues.

    <figure> 
        <img src="images/tag-delete/tag-delete-result.png" alt="Enter 'tag delete ct/2'" width="900px">
    </figure>

#### Retrieving entries by tag: `tag find` \[Wang Luo\]

Displays all entries (contacts or sales) that are associated with the specified tag.

**Format**: `tag find (ct/ or st/)INDEX [cl/]`

* Displays all entries of associated with the tag at `INDEX`. The `INDEX` refers to the index number shown in the list displayed by the `tag list` command.
* The `INDEX` must be a positive integer 1, 2, 3, ...
* The `INDEX` field must be provided.
* An additional `cl/` (stands for 'client') field can be provided when performing searching on sales tags. If provided, instead of displaying sales associated to this tag, StonksBook will display the clients who have purchased items with this tag.
* If you are searching for contact tags using `ct/`, adding the `cl/` tag will have no effect on the search results.

**Examples**:

Let's say that you would like to find out who are the contacts tagged with friends. Here are the steps to follow:

1. Type `tag list` in the **Command Box** and press <kbd>Enter</kbd>.
    - This is to display the contact tags and sales tags in StonksBook, and the contact tag friends is found to be at index 3.

    <figure> 
        <img src="images/tag-find/tag-list-third.png" alt="Result for 'tag list'" width="900px"> 
    </figure>

2. Type `tag find ct/3` in the **Command Box** and press <kbd>Enter</kbd>. 

    <figure> 
        <img src="images/tag-find/tag-find.png" alt="Enter 'tag delete ct/2'" width="900px"> 
    </figure>
    
3. The **Result Box** will display a message noting that the command was successful, as well as a list of contacts who are tagged with friends.

    <figure>  
        <img src="images/tag-find/tag-find-result.png" alt="Result for 'tag delete ct/2'" width="900px"> 
    </figure>    

Now suppose that you would like to find out who purchased items that are tagged with music. Here are the steps to follow:

1. Type `tag list` in the **Command Box** and press <kbd>Enter</kbd>.
    - This is to display the contact tags and sales tags in StonksBook, and the contact tag friends is found to be at index 3.

    <figure>   
        <img src="images/tag-find/tag-list-sale-second.png" alt="Result for 'tag list'" width="900px"> 
    </figure>  

2. Type `tag find st/2` in the **Command Box** and press <kbd>Enter</kbd>.

    <figure>   
        <img src="images/tag-find/tag-find-sale-cl.png" alt="Enter 'tag find st/2'" width="900px"> 
    </figure>

3. The **Result Box** will display a message noting that the command was successful, as well as a list of clients who purchased items tagged with music.

    <figure>    
        <img src="images/tag-find/tag-find-sale-cl-result.png" alt="Result for 'tag find st/2'" width="900px">
    </figure>

### Sales

StonksBook allows you to manage the sales you made within the application. 

#### Listing all sales: `sale list` \[Kwek Min Yih\] \[Aaron Seah\]

Shows a list of sales.

**Format:** `sale list [c/CONTACT_INDEX] [m/MONTH y/YEAR]`

| Parameter       | What it is                                       | Requirements                                   |
|-----------------|--------------------------------------------------|------------------------------------------------|
| `CONTACT_INDEX` | Index number shown in the displayed contact list | Must be a positive integer 1, 2, 3, …          |
| `MONTH`         | Month                                            | Must be an integer between 1 and 12 inclusive. |
| `YEAR`          | Year                                             | Must be an integer greater than 0.             |

* `c/CONTACT_INDEX` and `m/MONTH y/YEAR` cannot be present at the same time.

* If no parameter is present:
   * all sales are listed.

* If `c/CONTACT_INDEX` is present:
   * all sales made to a contact with the specified index are listed.

* If `m/MONTH y/YEAR` is present:
   * all sales whose associated date is in the specified `MONTH` and `YEAR` are listed.
   
* The list is sorted in ascending order based on the date the sale is made.
   
**Example:** Let's say you want to view all sales made to Bernice Yu. This is what you need to do:

1. Type `contact find bernice` in the **Command Box** and press <kbd>Enter</kbd>. 
    - This is to identify the contact index corresponding to Bernice Yu.     
<figure>
    <img src="images/sale-list/salelistcontactfirststep.png" alt="enter 'contact find bernice'" width="900px">
</figure>

2. The **Result Box** will display a message of the number of contacts listed and the **Contact List** updates to
 show only contacts with the name 'Bernice'. Identify the correct index that corresponds to Bernice Yu, which is 1 in this example. 
<figure>
    <img src="images/sale-list/salelistcontactsecondstep.png" alt="result for 'contact find bernice'" width="900px">
</figure>

3. Type `sale list c/1` in the **Command Box** and press <kbd>Enter</kbd>.

<figure>
    <img src="images/sale-list/salelistcontactthirdstep.png" alt="enter 'sale list c/1'" width="900px">
</figure>

4. The **Result Box** will display a message noting that the command was successful, and the **Sale List** will
 show a list of sales made to Bernice Yu.

<figure>
    <img src="images/sale-list/salelistcontactfourthstep.png" alt="result for 'sale list c/1'" width="900px">
</figure>

#### Adding a sale to a customer: `sale add` \[Kwek Min Yih\] 

Adds a sale to the specified contact in StonksBook.

**Format:** `sale add c/CONTACT_INDEX… n/ITEM_NAME d/DATETIME_OF_PURCHASE p/UNIT_PRICE q/QUANTITY t/TAG…`

| Parameter              | What it is                                       | Requirements                                                                                                            |
|------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| `CONTACT_INDEX`        | Index number shown in the displayed contact list | Must be a positive integer 1, 2, 3, …                                                                                   |
| `ITEM_NAME`            | Name of the item(s) sold                         | Must contain only alphabetical characters                                                                               |
| `DATETIME_OF_PURCHASE` | Datetime of when the sale was made               | Must be in the format `yyyy-MM-dd HH:mm`                                                                                |
| `UNIT_PRICE`           | Unit price of each item sold                     | Must be a positive number greater than 0 and less than 10 million with 2 decimal places, in the format `DOLLARS.CENTS`. |
| `QUANTITY`             | Quantity of items sold                           | Must be a positive integer greater than 0 and less than 10 million.                                                     |
| `TAG`                  | Tag to be assigned to the sale                   | Must exist in StonksBook first before you can associate the sales item to them.                                         |

* Multiple `CONTACT_INDEX` can be specified, meaning that you can add a sale to multiple contacts.
* It is compulsory to have a tag for the sales item. This is to ensure the ease of data analytics.
* Duplicate sales cannot be added. A sale with the same item name, contact, datetime of purchase, unit price and quantity will be flagged as a duplicate.

**Example:**
Suppose you successfully sold 100 guitar tuners at a unit price of $10.00 with Bernice Yu on 30 October 2020, 3pm. 
Here's how you can add this new sale into StonksBook:
 
1. Type `contact find bernice` in the **Command Box** and press <kbd>Enter</kbd>. 
     - This is to identify the contact index corresponding to Bernice Yu.      

<figure>
    <img src="images/sale-list/salelistcontactfirststep.png" alt="enter 'contact find bernice'" width="900px">
</figure>

2. The **Result Box** will display a message of the number of contacts listed and the **Contact List** updates to
  show only contacts with the name 'Bernice'. Identify the correct index that corresponds to Bernice Yu, which is 1 in this example.  

<figure>
    <img src="images/sale-list/salelistcontactsecondstep.png" alt="result for 'contact find bernice'" width="900px">
</figure>

3. Type `sale add c/1 n/Guitar Tuner d/2020-10-30 15:00 p/10.00 q/100 t/music` in the **Command Box** and press <kbd>Enter</kbd>.

<figure>
    <img src="images/sale-add/saleaddthirdstep.png" alt="enter 'sale add c/1 n/Guitar Tuner d/2020-10-30 15:00 p/10.00 q/100 t/music'" width="900px"> 
</figure>
  
4. The **Result Box** will display a message noting that the command was successful, and the **Sale List** will
 contain this newly created sale.
    - You may have to scroll through your **Sale List** to find this newly created sale since sales are
     sorted in ascending order based on the date the sale is made.

<figure>
    <img src="images/sale-add/saleaddfourthstep.png" alt="result for 'sale add c/1 n/Guitar Tuner d/2020-10-30 15:00 p/10.00 q/100 t/music'" width="900px"> 
</figure>

#### Deleting a sales item: `sale delete` \[Kwek Min Yih\]

Deletes a specified sales item from StonksBook.

**Format:** `sale delete s/SALE_INDEX…`

| Parameter    | What it is                                    | Requirements                          |
|--------------|-----------------------------------------------|---------------------------------------|
| `SALE_INDEX` | Index number shown in the displayed sale list | Must be a positive integer 1, 2, 3, … |

* Multiple `SALE_INDEX` can be specified, meaning that you can delete multiple sales with the same command.

**Examples:**
Let's say that you have just received the unfortunate news from Bernice Yu who no longer wishes to buy 300 black pens from you.
Here's how you can delete this sale in StonksBook:

1. Type `contact find bernice` in the **Command Box** and press <kbd>Enter</kbd>. 
    - This is to identify the contact index corresponding to Bernice Yu.

<figure>
    <img src="images/sale-list/salelistcontactfirststep.png" alt="enter 'contact find bernice'" width="900px">
</figure>
2. The **Result Box** will display a message of the number of contacts listed and the **Contact List** updates to
 show only contacts with the name 'Bernice'. Identify the correct index that corresponds to Bernice Yu, which is 1 in this example.

<figure>
    <img src="images/sale-list/salelistcontactsecondstep.png" alt="result for 'contact find bernice'" width="900px">
</figure>

3. Type `sale list c/1` in the **Command Box** and press <kbd>Enter</kbd>.

<figure>
    <img src="images/sale-list/salelistcontactthirdstep.png" alt="enter 'sale list c/1'" width="900px">
</figure>

4. The **Result Box** will display a message noting that the command was successful, and the **Sale List** will
 show a list of sales made to Bernice Yu. Identify the index of the sale to be deleted. Let us assume it is at the third index.

<figure>
    <img src="images/sale-list/salelistcontactfourthstep.png" alt="result for 'sale list c/1'" width="900px">
</figure>

5. Type `sale delete s/3` in the **Command Box**, and press <kbd>Enter</kbd> to execute it.

<figure>
    <img src="images/sale-delete/saledeletefifthstep.png" alt="enter 'sale delete s/3'" width="900px">
</figure>  
6. You should see that the sale has been deleted from the sale list.

<figure>
    <img src="images/sale-delete/saledeletesixthstep.png" alt="result for 'sale delete s/3'" width="900px">
</figure>

#### Editing an existing sale: `sale edit` \[Kwek Min Yih\]

Edits an existing sale in StonksBook.

**Format:** `sale edit s/SALE_INDEX… [c/CONTACT_INDEX] [n/ITEM_NAME] [d/DATETIME_OF_PURCHASE] [p/UNIT_PRICE] [q/QUANTITY] [t/TAG]…`

| Parameter              | What it is                                       | Requirements                                                                                                            |
|------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| `CONTACT_INDEX`        | Index number shown in the displayed contact list | Must be a positive integer 1, 2, 3, …                                                                                   |
| `ITEM_NAME`            | Name of the item(s) sold                         | Must contain only alphabetic characters                                                                               |
| `DATETIME_OF_PURCHASE` | Datetime of when the sale was made               | Must be in the format `yyyy-MM-dd HH:mm`                                                                                |
| `UNIT_PRICE`           | Unit price of each item sold                     | Must be a positive number greater than 0 and less than 10 million with 2 decimal places, in the format `DOLLARS.CENTS`. |
| `QUANTITY`             | Quantity of items sold                           | Must be a positive integer greater than 0 and less than 10 million.                                                     |
| `TAG`                  | Tag to be assigned to the sale                   | Must exist in StonksBook first before you can associate the sales item to them.                                         |

* Multiple `SALE_INDEX` can be specified, meaning that you can edit multiple sales with the same command.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing a sale's tags, the existing tags of the sale will be removed i.e adding of tags is not cumulative.

**Example:**
Suppose you have just received the good news that Bernice Yu would like to buy 1000 Black Pens instead of the 300 she previously specified. 
Here's how you can edit her sale in StonksBook:
 
1. Identify the index corresponding to the sale to be edited in the sale list. If you have a large list of
  sales, it may be convenient to filter for all sales made to Bernice Yu. Assuming that Bernice Yu is the 2nd contact
   currently displayed in the contact list, you can type `sale list c/2`.

<figure>
    <img src="images/sale-edit/saleeditfirststep.png" alt="enter 'sale list c/2'" width="900px">
</figure>

2. The sale list will update to show only sales made to Bernice Yu.

<figure>
    <img src="images/sale-edit/saleeditsecondstep.png" alt="result for 'sale list c/2'" width="900px">
</figure>
    
3. Suppose the sale to be edited is at the third index in the sale list. 
Then, type `sale edit s/3 q/20` in the **Command Box**, and press <kbd>Enter</kbd> to execute it.
  
<figure>
<img src="images/sale-edit/saleeditthirdstep.png" alt="enter 'sale edit s/3 q/20'" width="900px">
</figure>

4. You should see that the sale has been updated to reflect this new quantity.

<figure>
    <img src="images/sale-edit/saleeditfourthstep.png" alt="result for 'sale edit s/3 q/20'" width="900px">
</figure>

#### Viewing a breakdown of sales made in each tag: `sale breakdown` \[Kwek Min Yih\]

Displays the number of sales belonging to the top 5 tags.

**Format:** `sale breakdown`

* This will display a bar graph showing the top 5 tags with the most sales, 
and the number of sales belonging to each tag in an external window.
* The bar graph in the external window will not automatically refresh upon updating of sales. 
To see the new updated bar graph, close the current external window and run the `sale breakdown` command again.

**Example:**

1. Type `sale breakdown` in the **Command Box** and press <kbd>Enter</kbd>. 

<figure>
    <img src="images/sale-breakdown/salebreakdownfirststep.png" alt="enter 'sale breakdown'" width="900px">
</figure>
2. A new window containing the bar chart pops up.

<figure>
    <img src="images/sale-breakdown/salebreakdownsecondstep.png" alt="enter 'sale breakdown'" width="900px">
</figure>

#### Viewing monthly sale count: `sale stats` \[Aaron Seah\]
Views the monthly sale count for the current month and previous months.

**Format**: `sale stats NUMBER_OF_MONTHS`

| Parameter              | What it is                                       | Requirements                                                                                                            |
|------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| `NUMBER_OF_MONTHS`     | The number of monthly sale counts, inclusive of the current month   | Must be a positive integer between 2 and 6 inclusive. e.g. 2, 3, ... , 6                                                                                  |
     
**Example**: Let's say you want to compare the number of sales you have made for each month of the past 5 months and the current month.
Let's assume that the current month and year is November 2020.
You can do so as shown.

1. Type `sale stats 6` and press <kbd>Enter</kbd>.

    <figure>    
        <img src="images/sale-stats/saleStatsFirstStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>

2. A new window containing a bar chart will appear. The bar chart will contain the monthly meeting count
for each the previous 5 months: June 2020, July 2020, August 2020, September 2020, October 2020, 
followed by the current month and year November 2020.

    <figure>    
        <img src="images/sale-stats/saleStatsSecondStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>


### Meetings

StonksBook allows you to manage your meetings within the application.

#### Listing all meetings: `meeting list` \[Sebastian Toh Shi Jian\]

Shows a list of all meetings. By default, the list only shows upcoming meetings. This list is sorted in ascending order
 based on the date the meeting is scheduled.

**Format**: `meeting list [c/CONTACT_INDEX] [a/]`

* When a contact index is specified, the list will only show meetings associated with the contact at the specified
 contact index.
* You can show all meetings, including past meetings, by including `a/`.

**Example**:
Suppose you want to view all upcoming meetings scheduled with Alex Yeoh. This is what you need to do:

1. Type `contact find alex` in the **Command Box** and press <kbd>Enter</kbd>. 
    - This is to identify the contact index corresponding to Alex Yeoh and can be skipped if you already know the
     index. Let us assume that Alex Yeoh is at the first index.
     
    <figure>    
        <img src="images/meeting-list/contact-find-alex.png" alt="Enter 'contact find alex' in the command box" width="900px">
    </figure>
    
2. The **Result Box** will display a message of the number of contacts listed and the **Contact List** updates to
 show only contacts with the name 'Alex'.
 
     <figure>    
        <img src="images/meeting-list/contact-find-alex-result.png" alt="Result for 'contact find alex'" width="900px">
     </figure>
 
3. Type `meeting list c/1` in the **Command Box** and press <kbd>Enter</kbd>.

     <figure>    
        <img src="images/meeting-list/meeting-list.png" alt="Enter 'meeting list c/1' in the command box" width="900px">
     </figure>

4. The **Result Box** will display a message noting that the command was successful, and the **Meeting List** will
 show a list of upcoming meetings scheduled with Alex Yeoh.

     <figure>    
        <img src="images/meeting-list/meeting-list-result.png" alt="Result for 'meeting list c/1'" width="900px">
     </figure>

#### Adding a meeting: `meeting add` \[Sebastian Toh Shi Jian\]

Adds a meeting with the specified contact in StonksBook.

<div markdown="block" class="alert alert-info">
**:information_source: Note on conflicting meetings:**<br>
To prevent the situation in which you unknowingly scheduled conflicting meetings, StonksBook will not allow you to
 add a new meeting if it conflicts with some meeting in StonksBook!
</div>

**Format**: `meeting add c/CONTACT_INDEX m/MESSAGE d/START_DATETIME du/DURATION`

* Adds a meeting with the contact at the specified `CONTACT_INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be a positive integer 1, 2, 3, …​
* The start datetime must be in the format `yyyy-MM-dd HH:mm`
* The duration is specified in minutes and must be a positive integer between 1 and 1000000 (inclusive).

**Example**:
Let's say that you successfully secured a lunch meeting with Alex Yeoh that is scheduled for 3 December 2020 12pm
and lasts 90 minutes. Here's how you can add this new meeting into StonksBook:
 
 1. Type `contact find alex` in the **Command Box** and press <kbd>Enter</kbd>. 
     - This is to identify the contact index corresponding to Alex Yeoh and can be skipped if you already know the
      index. Let us assume that Alex Yeoh is at the first index.

     <figure>    
        <img src="images/meeting-list/contact-find-alex.png" alt="Enter 'contact find alex' in the command box" width="900px">
     </figure>
 
 2. The **Result Box** will display a message of the number of contacts listed.
  
     <figure>    
        <img src="images/meeting-list/contact-find-alex-result.png" alt="Result for 'contact find alex'" width="900px">
     </figure>
 
 3. Type `meeting add c/1 m/Lunch with Alex Yeoh d/2020-12-03 12:00 du/90` in the **Command Box** and press <kbd>Enter</kbd>.
 
    <figure>    
        <img src="images/meeting-add/meeting-add.png" alt="Enter 'meeting add' in the command box" width="900px">
    </figure>
  
4. The **Result Box** will display a message noting that the command was successful, and the **Meeting List** will
 contain this newly created meeting.
    - You may have to scroll through your **Meeting List** to find this newly created meeting since meetings are
     sorted in ascending order based on the date the meeting is scheduled.

    <figure>    
        <img src="images/meeting-add/meeting-add-result.png" alt="Result for 'meeting add'" width="900px">
    </figure>  

#### Deleting a meeting: `meeting delete` \[Sebastian Toh Shi Jian\]

Deletes the specified meeting from StonksBook.

**Format**: `meeting delete INDEX`

* Deletes the schedule at the specified `INDEX`.
* The index refers to the index number shown in the displayed meetings list.
* The index must be a positive integer 1, 2, 3, …​

**Example**:
Let's say that you have just received an unfortunate email from Alex Yeoh who no longer wishes to meet with you on 3
 December 2020, 12pm. Here's how you can delete this meeting in StonksBook:

 1. Type `meeting list` in the **Command Box** and press <kbd>Enter</kbd>. 
     - This is to display all upcoming meetings in the **Meeting List**, which is necessary to identify the index of
      the meeting that is to be deleted. This step can be skipped if you already know the index. Let
       us assume that the meeting is at the third index.

    <figure>    
        <img src="images/meeting-delete/meeting-third-index.png" alt="Meeting at the third index" width="900px">
    </figure>      

 2. Type `meeting delete 3` in the **Command Box**, and press <kbd>Enter</kbd> to execute it.

    <figure>    
        <img src="images/meeting-delete/meeting-delete.png" alt="Enter command 'meeting delete'" width="900px">
    </figure>      
     
 3. The **Result Box** will display a message noting that the command was successful, and the meeting is deleted from
  the **Meeting List**.

    <figure>    
        <img src="images/meeting-delete/meeting-delete-result.png" alt="Result for 'meeting delete'" width="900px">
    </figure>   
 
#### Editing a meeting: `meeting edit` \[Sebastian Toh Shi Jian\]

Edits an existing meeting in StonksBook.

<div markdown="block" class="alert alert-info">
**:information_source: Note on conflicting meetings:**<br>
Similar to when adding a meeting, StonksBook will not allow you to edit a meeting if it will conflicts with some meeting
 in StonksBook!
</div>

**Format**: `meeting edit INDEX [c/CONTACT_INDEX] [m/MESSAGE] [d/START_DATETIME] [du/DURATION]`

* Edits the meeting at the specified `INDEX`. The index refers to the index number shown in the displayed meeting list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

**Example**:
Let's say that you have just received an email from Bernice Yu who wishes to reschedule her lunch meeting with you to 15
 December 2020, 12pm. Here's how you can make this change in StonksBook:
 
1. Type `meeting list` in the **Command Box** and press <kbd>Enter</kbd>. 
    - This is to display all upcoming meetings in the **Meeting List**, which is necessary to identify the index of
     the meeting that is to be edited. This step can be skipped if you already know the index. Let us assume that the meeting is at the third index.

    <figure>    
        <img src="images/meeting-edit/meeting-third-index.png" alt="Meeting at the third index" width="900px">
    </figure>   
     
2. Type `meeting edit 3 d/2020-12-15 12:00` in the **Command Box**, and press <kbd>Enter</kbd> to execute it.

    <figure>    
        <img src="images/meeting-edit/meeting-edit.png" alt="Enter command 'meeting edit'" width="900px">
    </figure>  
    
3. The **Result Box** will display a message noting that the command was successful, and the meeting is updated in the 
     the **Meeting List**.

    <figure>    
        <img src="images/meeting-edit/meeting-edit-result.png" alt="Result for 'meeting edit'" width="900px">
    </figure>  
    
#### Viewing monthly meetings: `meeting stats` \[Aaron Seah\]
Views the monthly meeting count for the current month and optionally for the previous months.

**Format**: `meeting stats [NUMBER_OF_MONTHS] [m/MONTH y/YEAR]`

* `NUMBER_OF_MONTHS` and `m/MONTH y/YEAR` cannot be present at the same time.

| Parameter              | What it is                                       | Requirements                                                                                                            |
|------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| `NUMBER_OF_MONTHS`     | The number of monthly meeting counts, inclusive of the current month   | Must be a positive integer between 2 and 6 inclusive. e.g. 2, 3, ... , 6                                                                                  |
| `MONTH`                | month                                            | Must be an integer between 1 and 12 inclusive. e.g. 1, 2, ... , 12                                                                                  |
| `YEAR`                 | year                                             | Must be a positive integer 1, 2, 3 ...                                                                                 |

* If no parameter is present:
   * The result is the number of meetings whose start date is in the current month and year.

* If `[m/MONTH y/YEAR]` is present:
   * The result is the number of meetings whose start date is in the specified `MONTH` and `YEAR`.

* If `[NUMBER_OF_MONTHS]` is present:
   * The result is a bar chart on the number of meetings in each of the previous `NUMBER_OF_MONTHS` - 1 months and
     the current month and year.
     
**Example**:
Let's say you want to compare the number of meetings you had for each of the past 5 months and the current month.
Let's assume that the current month and year is November 2020.
You can do so as shown.

1. Type `meeting stats 6` and press <kbd>Enter</kbd>.

    <figure>    
        <img src="images/meeting-stats/meetingStatsFirstStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>  

2. A new window containing a bar chart will appear. The bar chart will contain the monthly meeting count
for each the previous 5 months: June 2020, July 2020, August 2020, September 2020, October 2020, 
followed by the current month and year November 2020.

    <figure>    
        <img src="images/meeting-stats/meetingStatsSecondStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>

**Additional Examples**:
* `meeting stats` will return the number of meetings whose start date is in the October 2020
   if the current month and year is October 2020.
* `meeting stats m/8 y/2020` will return the number of meetings whose start date is in August 2020.
* `meeting stats 3` will return a bar chart containing the number of meetings whose start date is within
   June 2020, July 2020 and August 2020 respectively if the current month and year is August 2020.

### Reminders

StonksBook allows you to manage your reminders within the application.

#### Listing reminders: `reminder list` \[Sebastian Toh Shi Jian\] and \[Wang Luo\]

Shows a list of all reminders created, sorted in ascending order based on the date the reminder is scheduled.

**Format**: `reminder list [st/STATUS]`

* `STATUS` can be either `completed` or `pending`.

**Example**:
Let's say that you want to view all your completed reminders. Here's how you can do so:
 
1. Type `reminder list st/completed` in the **Command Box** and press <kbd>Enter</kbd>. 

    <figure>    
        <img src="images/reminder-list/reminder-list.png" alt="Enter command 'reminder list'" width="900px">
    </figure>

2. The **Result Box** will display a message noting that the command was successful, and the **Reminder List** will
 show a list of completed reminders.

    <figure>
        <img src="images/reminder-list/reminder-list-result.png" alt="Result for 'reminder list'" width="900px">
    </figure>

#### Adding reminders: `reminder add` \[Sebastian Toh Shi Jian\]

Adds a reminder scheduled on a particular date that is associated with the specified contact to StonksBook.

**Format**: `reminder add c/CONTACT_INDEX m/MESSAGE d/DATETIME`

* Adds a reminder associated with the contact at the specified `CONTACT_INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index must be a positive integer 1, 2, 3, …​
* The datetime must be in the format `yyyy-MM-dd HH:mm`

**Example**:
Let's say that you need to send a follow-up email to Bernice Yu on 30 November 2020, 3PM. Here's how you can add a reminder into StonksBook:
 
 1. Type `contact find bernice` in the **Command Box** and press <kbd>Enter</kbd>. 
     - This is to identify the contact index corresponding to Bernice Yu and can be skipped if you already know the
      index. Let us assume that Bernice Yu is at the first index.
       
    <figure>
        <img src="images/reminder-add/contact-find-bernice.png" alt="Enter 'contact find bernice' in the command box" width="900px">
    </figure>
    
 2. The **Result Box** will display a message of the number of contacts listed.

    <figure>
        <img src="images/reminder-add/contact-find-bernice-result.png" alt="Result for 'contact find bernice'" width="900px">
    </figure>
    
  
 3. Type `reminder add c/1 m/Send email to follow up d/2020-11-30 15:00` in the **Command Box** and press <kbd>Enter</kbd>.

    <figure>
        <img src="images/reminder-add/reminder-add.png" alt="Enter command 'reminder add'" width="900px">
    </figure>
   
4. The **Result Box** will display a message noting that the command was successful, and the **Reminder List** will
 contain this newly created reminder.
    - You may have to scroll through your **Reminder List** to find this newly created reminder since reminders are
     sorted in ascending order based on the date the reminder is scheduled.   

    <figure>
        <img src="images/reminder-add/reminder-add-result.png" alt="Result for 'reminder add'" width="900px">
    </figure> 
    
#### Deleting a reminder: `reminder delete` \[Sebastian Toh Shi Jian\]

Deletes the specified reminder from StonksBook.

**Format**: `reminder delete INDEX`

* Deletes the reminder at the specified `INDEX`.
* The index refers to the index number shown in the displayed reminders list.
* The index must be a positive integer 1, 2, 3, …​

**Examples**:
Let's say that a reminder in StonksBook is no longer applicable and you wish to delete it. Here's you can delete
 a reminder from StonksBook:

 1. Type `reminder list` in the **Command Box** and press <kbd>Enter</kbd>. 
     - This is to display all all reminders in the **Reminder List**, which is necessary to identify the index of
      the reminder that is to be deleted. This step can be skipped if you already know the index. Let
       us assume that the reminder is at the third index.

    <figure>
        <img src="images/reminder-delete/reminder-third-index.png" alt="Reminder at third index" width="900px">
    </figure> 

 2. Type `reminder delete 3` in the **Command Box**, and press <kbd>Enter</kbd> to execute it.

    <figure>
        <img src="images/reminder-delete/reminder-delete.png" alt="Enter command 'reminder delete'" width="900px">
    </figure>  
     
 3. The **Result Box** will display a message noting that the command was successful, and the reminder is deleted from
  the **Reminder List**.

    <figure>
        <img src="images/reminder-delete/reminder-delete-result.png" alt="Result for 'reminder delete'" width="900px">
    </figure>

#### Editing a reminder: `reminder edit` \[Sebastian Toh Shi Jian\] and \[Wang Luo\]

Edits an existing reminder in StonksBook.

**Format**: `reminder edit INDEX [c/CONTACT_INDEX] [m/MESSAGE] [d/DATETIME] [st/STATUS]`

* Edits the reminder at the specified `INDEX`. The index refers to the index number shown in the displayed reminder list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* `STATUS` can be either `pending` or `completed`.

**Examples**:

Let's say that Alex Yeoh would like to hear from you sooner for the follow up and you just sent the follow up email. Here are the steps to follow:

1. Type `reminder list` in the **Command Box** and press <kbd>Enter</kbd>.
    - This is to display all your reminders in StonksBook, and the reminder for follow up email for Alex Yeoh is found at index 1.

    <figure>
        <img src="images/reminder-edit/reminder-list-first.png" alt="Enter 'reminder list'" width="900px"> 
    </figure>

2. Type `reminder edit 1 d/2020-10-30 16:30 st/completed` in the **Command Box** and press <kbd>Enter</kbd>. 

    <figure> 
        <img src="images/reminder-edit/reminder-edit.png" alt="Enter 'reminder edit 1 d/2020-10-30 16:30 st/completed'" width="900px"> 
    </figure>

3. The **Result Box** will display a message noting that the command was successful, as well as the details of the updated reminder.

    <figure>  
        <img src="images/reminder-edit/reminder-edit-result.png" alt="Result of 'reminder edit 1 d/2020-10-30 16:30 st/completed'" width="900px"> 
    </figure>

### Archive

StonksBook allows you to archive contacts who are no longer active so that you can focus on contacts who can bring you the Stonks.

#### Adding a contact to the archive: `archive add` \[Leong Jin Ming\]

Adds the specified contact to the archive.

<div markdown="block" class="alert alert-info">
StonksBook will not archive a contact if it is already archived!
In addition, archiving a contact will not remove the sales, reminders and meetings linked to this contact.
</div>

**Format**: `archive add INDEX`

* Adds the contact at the specified `INDEX` to the archive. The sales, reminders and meetings linked to this contact will not be removed.
* Can only be used when the contacts list is displayed.
* The index must be a positive integer 1, 2, 3, …

**Example**:
Suppose you want to archive your contact Alex Yeoh, who is no longer doing business with you. Simply follow the steps below:

1. Identify the index of the contact entry in the contact list. Here Alex Yeoh is the first contact in the list. Alternatively you can use `contact find` to find the contact.

   <img src="images/archive-add/archive-add-prelude.png" alt=" Identify contact index " width="900px">

2. Type `archive add 1` in the **Command Box** and press <kbd>Enter</kbd>.

   <img src="images/archive-add/archive-add.png" alt=" Enter 'archive add 1' " width="900px">

3. The contact entry will disappear from the contact list, and the **Result Box** will inform you that the command is successful.

   <img src="images/archive-add/archive-add-result.png" alt=" Result of 'archive add 1' " width="900px">

#### Listing contacts in the archive: `archive list` \[Leong Jin Ming\]

Shows the list of your archived contacts.

**Format**: `archive list`

* When entered, if the contacts list is displayed it will be replaced with the archived contacts list.
* You can edit and delete contacts in the archive using `contact edit` and `contact delete` when the archived contacts list is displayed.

**Example**:
To see all your archived contacts, type `archive list` into the **Command Box** and press <kbd>Enter</kbd>. The **Result Box** will inform you that the command was successful, and the archived contacts list will appear.

#### Removing contacts from the archive: `archive remove` \[Leong Jin Ming\]

Removes the specified contact from the archive. The specified contact will appear on your contact list again.

<div markdown="block" class="alert alert-info">
StonksBook will not remove a contact from the archive if it is not in the archive!
</div>

**Format**: `archive remove INDEX`

* Removes the contact at the specified `INDEX` from the archive.
* Does not delete the contact from StonksBook. To delete the contact use `contact delete`.
* The index must be a positive integer 1, 2, 3, …

**Example**:
Let's say your archived contact Alex Yeoh wants to start buying electronics from you again, and so you would like to bring his contact entry back to the contacts list. Simply follow the steps below:

1. Make sure you are on the archived contacts list. If not, simply use the `archive list` command to get all your archived contacts.

   <img src="images/archive-remove/archive-remove-prelude.png" alt=" List archived contacts " width="900px">

2. Identify the index of your contact in the list. In this case, Alex is the first entry in your list.
3. Type `archive remove 1` in the **Command Box** and press <kbd>Enter</kbd>.

   <img src="images/archive-remove/archive-remove.png" alt=" Enter 'archive remove 1' " width="900px">

4. The contact entry will disappear from your archive, and the **Result Box** will inform you that the command is successful.

   <img src="images/archive-remove/archive-remove-result.png" alt=" Result of 'archive remove 1' " width="900px">

### Miscellaneous

#### Error resolution suggestion: \[Aaron Seah\]
Gives an approximate match (if exists) of the most similar command to an unknown user input.

**Example**:
Let's say you want to use the contact add command but you mistyped the command as cont add.

1. Type `cont add` in the **Command Box** and press <kbd>Enter</kbd>.

    <figure>  
        <img src="images/error-suggestion/suggestionFirstStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>
    
2. A suggestion of `contact add` appears. 

    <figure>  
        <img src="images/error-suggestion/suggestionSecondStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>

**Examples**:
* `contac add` will return a suggestion of `contact add`
* `contt ad` will return a suggestion of `contact add`
* `contacta ` will return a suggestion of `contact add`

#### Navigating between previous inputs within the session:
Pressing the up and down keys retrieves the previous and next input respectively, if there is one.

#### Switching between light and dark themes: `lightmode`/`darkmode`
Changes the theme of the GUI to light and dark theme respectively. The default theme for StonksBook is dark, and the light theme looks like the following:

<figure>  
    <img src="images/lightTheme.png" alt="Light theme" width="900px">
</figure>

Format: `lightmode`/`darkmode`

#### Viewing help: `help` \[Aaron Seah\]
Lists the command word, command description and example usage for each available command as well as the link to the User Guide.

**Example**:
Let's say you want to recall some commands in StonksBook. 
You can do so by accessing the help page as shown.

1. Type `help` in the **Command Box** and press <kbd>Enter</kbd>.

    <figure>  
        <img src="images/help/helpFirstStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>
    
2. The help window pops up.

    <figure>  
        <img src="images/help/helpSecondStep.png" alt="result for 'contact sort keyword'" width="900px">
    </figure>

**Format**: `help`

#### Clearing all past interactions: `clear`
Clears all past interactions with the StonksBook GUI within the session.

**Format**: `clear`

#### Removing all data: `purge`
Clears all data from StonksBook.

**Format**: `purge`
#### Exiting the program: `exit`

Exits the program.

**Format**: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous StonksBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Tag List** | `tag list`
**Tag Add** | `tag add (ct/ or st/)TAG` <br> e.g., `tag add ct/important`
**Tag Delete** | `tag delete (ct/ or st/)INDEX` <br> e.g., `tag delete ct/1`
**Tag Edit** | `tag edit (ct/ or st/)INDEX t/TAG` <br> e.g., `tag edit st/1 t/fruits`
**Tag Find** | `tag find (ct/ or st/)INDEX [cl/]` <br> e.g., `tag find st/1 cl/`
**Archive List** | `archive list`
**Archive Add** | `archive add INDEX` <br> e.g., `archive add 1`
**Archive Remove** | `archive remove INDEX` <br> e.g., `archive remove 2`
**Light/Dark Theme** | `lightmode`/`darkmode`
**Help** | `help`
**Clear Chatbox** | `clear`
**Delete All Data Entries** | `purge`
**Exit Application** | `exit`

### Contacts

| Command                                                                                    | Summary                                                  | Example (if applicable)                                                                  |
|--------------------------------------------------------------------------------------------|----------------------------------------------------------|------------------------------------------------------------------------------------------|
| `contact add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​[r/REMARK]`                   | Adds a contact with the specified information            | `contact add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` |
| `contact delete INDEX`                                                                     | Deletes the specified contact                            | `contact delete 3`                                                                       |
| `contact edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​[r/REMARK]`    | Edits an existing contact                                | `contact edit 1 p/91234567 e/johndoe@example.com`                                        |
| `contact list`                                                                             | Lists all contacts                                       | `contact list`                                                                           |
| `contact find KEYWORD [MORE_KEYWORDS]...`                                                     | Finds contacts with names matching the given keywords    | `contact find alx yo`                                                                    |
| `contact sort KEYWORD [ORDER]`                                                             | Sorts contacts based on the parameter specified          | `contact sort n/ desc`                                                                   |

### Tags

### Sales

| Command                                                                                                                    | Summary                                                  | Example (if applicable)                                                |
|----------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------|------------------------------------------------------------------------|
| `sale add c/CONTACT_INDEX… n/ITEM_NAME d/DATETIME_OF_PURCHASE p/UNIT_PRICE q/QUANTITY t/TAG…`                              | Adds a sale to the specified contact                     | `sale add c/1 n/Guitar Tuner d/2020-10-30 15:00 p/10.00 q/100 t/music` |
| `sale delete s/SALE_INDEX…`                                                                                                | Deletes the specified sale                               | `sale delete s/3`                                                      |
| `sale edit s/SALE_INDEX… [c/CONTACT_INDEX] [n/ITEM_NAME] [d/DATETIME_OF_PURCHASE] [p/UNIT_PRICE] [q/QUANTITY] [t/TAG]…`    | Edits an existing sale                                   | `sale edit s/2 n/B5 Notebook p/4.00 q/10`                              |
| `sale list [c/CONTACT_INDEX] [m/MONTH y/YEAR]`                                                                             | Lists sale based on provided parameters                  | `sale list c/1`                                                        |
| `sale breakdown`                                                                                                           | Displays the number of sales belonging to the top 5 tags | `sale breakdown`                                                       |
| `sale stats NUMBER_OF_MONTHS`                                                                                              | Displays statistics on sale                              | `sale stats 6`                                                         |

### Meetings

| Command                                                                              | Summary                                            | Example (if applicable)                                                                  |
|--------------------------------------------------------------------------------------|----------------------------------------------------|------------------------------------------------------------------------------------------|
| `meeting add c/CONTACT_INDEX m/MESSAGE d/START_DATETIME du/DURATION`                 | Adds a meeting with the specified contact          | `meeting add c/2 m/Product Demo d/2020-10-30 15:00 du/60`                                |
| `meeting delete INDEX`                                                               | Deletes the specified meeting                      | `meeting delete 3`                                                                       |
| `meeting edit INDEX [c/CONTACT_INDEX] [m/MESSAGE] [d/START_DATETIME] [du/DURATION]`  | Edits an existing meeting                          | `meeting edit 3 d/2020-12-15 12:00`                                                      |
| `meeting list [c/CONTACT_INDEX] [a/]`                                                | Lists meetings based on provided parameters        | `meeting list c/1 a/`                                                                    |
| `meeting stats [NUMBER_OF_MONTHS] [m/MONTH y/YEAR]`                                  | Displays statistics on meetings                    | `meeting stats 3`                                                                        |

### Reminders

| Command                                                                              | Summary                                            | Example (if applicable)                                                                  |
|--------------------------------------------------------------------------------------|----------------------------------------------------|------------------------------------------------------------------------------------------|
| `reminder add c/CONTACT_INDEX m/MESSAGE d/DATETIME`                                  | Adds a reminder with the specified contact         | `reminder add c/2 m/Send email to follow up d/2020-10-30 15:00`                          |
| `reminder delete INDEX`                                                              | Deletes the specified reminder                     | `reminder delete 4`                                                                      |
| `reminder edit INDEX [c/CONTACT_INDEX] [m/MESSAGE] [d/DATETIME] [st/STATUS]`         | Edits an existing reminder                         | `reminder edit 1 d/2020-10-30 16:30 st/completed`                                        |
| `reminder list [st/STATUS]`                                                          | Lists reminders based on provided parameters       | `reminder list st/completed`                                                             |

### Archive

### Miscellaneous

--------------------------------------------------------------------------------------------------------------------

## Appendix A: Glossary
