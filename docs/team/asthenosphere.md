---
layout: page
title: Wang Luo's Project Portfolio Page
---

## Project: StonksBook

### Overview

As a part of the requirements of CS2103T: Software Engineering, my team of 4 software engineering students and I decided to enhance an existing project
[Address Book Level 3 (AB3)](https://github.com/nus-cs2103-AY1920S1/addressbook-level3) into a new product called **StonksBook** which helps salesmen to manage their critical data.

**StonksBook** is an all-in-one desktop data management application targeted at salesperson. It allows salesmen to manage contacts, sales, tags, meetings and reminders efficiently and effectively
so that their productivity can be improved. It also packs the functionality to perform data analytics on past sales and meetings with ease so that salesmen can gain business insights.

### Summary of Contributions

The following section summarises my coding, documentation and other contributions to the project.

* **New Feature**: 'Tags' feature 
  * What it does: The 'Tags' feature comprises of a set of commands that allows users to add, update, edit and list tags,
  as well as to search for contacts or sales based on tags. To boost the productivity of the user, the delete and edit commands
  automatically update all the sales or contacts associated with the affected tag (batch operations).

  * Justification: One goal of StonksBook is to boost the efficiency of salesmen so that they do not need to waste time managing past records.
  Therefore, the tag system allows users to categorise past records and retrieve them easily later. Moreover, batch operations further enhanced
  productivity as the user no longer needs to update these items one by one himself or herself.
    <div style="page-break-after: always;"></div>

  * Highlights: This feature required an in-depth understanding of almost all aspects of the project as I was responsible for the front-end,
  back-end logic and commands, as well as storage and unit testing for this feature. In addition, as both the contacts and sales models make use
  of this tag system, it also required a deep understanding of how the different models are implemented by the other team members.<br>

* **Major enhancement**: Enhanced the 'Reminders' feature
  * What it does: The 'Reminders' model has been enhanced to include a completion status so that users can focus on pending reminders of higher priority.
  A filtering functionality is also added to filter the reminders based on completion status.

  * Justification: As part of StonksBook's goal to boost productivity of salesperson, this enhancement allows users to mark a reminder as completed or pending,
  so that he or she can focus on the pending reminders.
  
  * Highlights: This feature required a deep understanding of the implementation of the 'Reminders' model by my teammate, Sebastian, in order to enhance it.

* **Code contributed**: Click on this link to see a sample of the code that I contributed:
[RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=Asthenosphere&sort=totalCommits%20dsc&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Asthenosphere&tabRepo=AY2021S1-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code).

* **Documentation**:
  * User Guide:
    * Added documentation for contact related commands `contact add`, `contact edit`, `contact list` and `contact delete`:
    [#232](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/232).
    * Added documentation for tag related commands `tag add`, `tag edit`, `tag delete`, `tag list` and `tag find`:
    [#21](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/21), [#137](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/137),
    [#171](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/171).
    * Added documentation for `reminder list`: [#146](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/146).
  * Developer Guide:
    * Added implementation details of the 'Contacts' features: [#247](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/247).
    * Added implementation details of the 'Tags' feature: [#137](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/137).
    * Added manual testing details for the 'Contacts' and 'Tags' features: [#247](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/247).
    * Added user stories and use cases for the 'Tags' feature: [#20](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/20). <br>

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#125](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/125),
  [#166](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/166), [#215](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/215),
  [#242](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/242).
  * Reported bugs and suggestions for other teams in the class: [#1](https://github.com/Asthenosphere/ped/issues/1),
  [#3](https://github.com/Asthenosphere/ped/issues/3), [#4](https://github.com/Asthenosphere/ped/issues/4), [#5](https://github.com/Asthenosphere/ped/issues/5), [#6](https://github.com/Asthenosphere/ped/issues/6).
