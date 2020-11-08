---
layout: page
title: jmleong666's Project Portfolio Page
---

## Project: StonksBook

StonksBook is a desktop address book application used for managing sales contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to clear past interactions with the GUI.
  * What it does: allows the user to clear up the GUI.
  * Justification: This feature improves user experience by reducing clutter on the GUI.

* **New Feature**: Added the ability to switch between light/dark themes.
  * What it does: allows users to switch between light/dark themes.
  * Justification: This feature improves user experience by giving them the option to have a lighter/darker appearance for the GUI.

* **New Feature**: Added the ability to navigate between previous inputs using the up and down keys.
  * What it does: allows user to retrieve previous inputs.
  * Justification: This feature improves user experience by allowing users to correct erroneous inputs or re-enter previous commands easily.

* **New Feature**: Added the ability to add contacts to the archive.
  * What it does: allows user to stash away inactive contacts.
  * Justification: This feature allows users to focus on more important or active contacts.

* **New Feature**: Added the ability to list archived contacts.
  * What it does: allows user to see archived contacts.
  * Justification: This feature allows users to see and manage archived contacts.

* **New Feature**: Added the ability to remove contacts from the archive.
  * What it does: allows users to remove contacts from the archive so that they appear in the contact list again.
  * Justification: This feature allows users to return archived contacts to the contact list when they become important again.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=jmleong666&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=jmleong666&tabRepo=AY2021S1-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Enhancements to existing features**:
  * GUI enhancements
    * Changed the results display box to a chat box to display all previous interactions with the app within the session.

* **Documentation**:
  * User Guide:
    * Added documentation for the following features:
      * Clearing all data: `purge`
      * Clearing GUI chatbox: `clear`
      * Light/dark themes: `lightmode`/`darkmode`
      * Navigating between previous inputs with up and down keys
      * Archive features: `archive add`, `archive list`, `archive remove`
  * Developer Guide:
    * Added implementation details for the archive features.
    * Added use cases for `purge` and `clear` as well as a few non-functional requirements.

* **Community**:
  * PRs reviewed (with non-trivial comments): [#105](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/105), [#115](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/115), [#150](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/150)
  * Reported bugs and suggestions for other teams in the class: [1](https://github.com/jmleong666/ped/issues/2), [2](https://github.com/jmleong666/ped/issues/1)
