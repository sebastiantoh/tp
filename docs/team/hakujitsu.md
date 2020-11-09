---
layout: page
title: Kwek Min Yih's Project Portfolio Page
---

## Project: StonksBook

StonksBook is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, StonksBook can get your contact management tasks done faster than traditional GUI apps.
 
## Summary of Contributions

* **Code contributed**
    * Click [here](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=hakujitsu) to view the code I contributed.
* **Enhancements implemented**
    * New Feature: Sales
        * What it does: allows the user to manage sales within the application. The following functionalities are implemented:
            * Creation of Sale model and storage.
            * List, add, edit and delete commands for sales objects.
            * Mass commands for addition, editing and deletion of sales, that can take in multiple sales as parameters and add/edit/delete them.
            * Viewing a breakdown of the number of sales belonging to each of the top 5 tags.
            * A GUI that contains the list of sales.
        * Justification: This feature is crucial in StonksBook as creating and managing sales is a core functionality of the application, 
        and forms the foundation of the sales analytics features.
        * Highlights: This enhancement was a very large and challenging feature to implement.
            * The amount of work was greater than implementing the Person feature in the AddressBook, as it not only consisted of 
            functionalities belonging to Person but also additional features, such as the sale breakdown function.
            * It affected commands to be added in future, namely the command to view sales statistics. 
        * It required an in-depth analysis of design alternatives, as the relationship between Person and Sale needed to be considered carefully.
    * New Feature: Ad Hoc Graphical User Interface (GUI)
        * What it does: The Ad Hoc GUI can display either the Sales Panel or Tag Panel, 
        toggling between both depending on which command was most recently executed. 
        For example, upon executing a Sale command, the Sale Panel will be displayed, and vice versa with the Tag Panel.
        * Justification: This feature reduces the number of panels needed to display the different models.
        It also allows for relevant information to be shown after every command execution, improving the general user experience.
        * Highlights: This feature required changes to the existing CommandResult class, and affected existing Command classes for Sale and Tag.
    * Enhancement: Addition of Remark field to Person class
        * What it does: allows the user to add miscellaneous remarks to contacts in the address book. 
        * Justification: allows for the user to store information that does not correspond to any particular person field.
        For example, they can use it to take note of the client's needs, wants and preferences.
        * Highlights: This was a relatively simple feature to implement. 
* **Contributions to documentation**
    * User guide:
        * Wrote the section to explain the user interface.
        * Wrote the sections for `sale list`, `sale add`, `sale delete`, `sale edit` and `sale breakdown`commands in the User Guide.
    * Developer guide: 
        * Wrote the sections for the implementation and use cases related to the 'Sale' feature in the Developer Guide,
        and added all UML diagrams seen in those sections.
* **Contributions to team-based tasks**
    * Handled release management for an informal milestone set by the team, v1.3a, intended to be a checkpoint within v1.3.
    * Handled the JAR release in Week 10.
    * Maintained the issue tracker by removing duplicate issues after the conclusion of the mock PE. 
    * Wrote an explanation of the user interface in the User Guide.
    * Added the parameter tables for Person related commands.
    * Update DeleteSequenceDiagram with updated implementation.
    * Managed the user stories section of the Developer Guide.
* **Review/mentoring contributions**
    * Here is a list of PRs reviewed by me with non-trivial comments: [#68](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/68), [#77](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/77), [#114](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/114), [#148](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/148), [#153](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/153), [#162](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/162)
* **Contributions beyond the project team**
    * Here is a list of issues reported by me for other project teams: [#1](https://github.com/hakujitsu/ped/issues/1), 
  [#2](https://github.com/hakujitsu/ped/issues/2), [#3](https://github.com/hakujitsu/ped/issues/3), 
  [#4](https://github.com/hakujitsu/ped/issues/4), [#5](https://github.com/hakujitsu/ped/issues/5), 
  [#6](https://github.com/hakujitsu/ped/issues/6), [#7](https://github.com/hakujitsu/ped/issues/7),
  [#8](https://github.com/hakujitsu/ped/issues/8)
