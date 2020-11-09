---
layout: page
title: Aaron Seah's Project Portfolio Page
---

## Project: StonksBook

### Overview

As a part of the requirements of CS2103T: Software Engineering, my team of 4 software engineering students and I decided to enhance an existing project
[Address Book Level 3 (AB3)](https://github.com/nus-cs2103-AY1920S1/addressbook-level3) into a new product called **StonksBook** which helps salesmen to manage their critical data.

**StonksBook** is a Desktop application that allows salesmen to manage their sales, contacts, meetings and reminders
and provide data analysis on meetings and sale data for salesmen gain insights for maximising profitability.

### Summary of Contributions

The following section summarises my coding, documentation and other contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=aaronnseah&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements implemented**:
    * Sorting of contacts by name or email address in alphabetical or reverse alphabetical order.
        * Ensure that the sort order does not affect other features negatively.
    * Find contacts by name that approximately matches the given search key word.
        * Non-trivial as dynamic programming solution is used. 
        * Several custom class logic needed to support this feature.
        * Had to tailor current filtering and ordering logic to implement the filtering and order needed for this feature (most similar matches appear first).
    * Meeting statistics with bar chart GUI.
    * Listing of sale items in a month and year.
    <br><br><br>

    * Sale statistics with bar chart GUI.
        * Custom generic class to store monthly meetings and sale items.
        * Custom class to generate bar chart GUI.
    * Provide suggestions to invalid user input.
        * Hard to give relatively good approximations to both single and double command keywords.
        
    * Help page with all command description and clickable link to User Guide.
        * Static generation of help window content via a text file that stores the command description.
    
* **Documentation**:
    * UG:
        * contact find
        * contact sort
        * meeting stats
        * sale stats
        * help
        * error resolution suggestion
    * DG:
        * Meeting analysis feature
            * Class diagram
            * Object diagrams
            * Sequence diagrams
            * Activity diagram
        * User story
        * Use case

* **Contributions to team-based tasks** : 
    * Add icons to contact list.
    * Modify parser to accomodate single and double keyword commands.
    * Release management.
    
* **Review/mentoring contributions**:

    * Help team members locate bug and offer solutions.
    * Help a team member fix a non-trivial bug [#98](https://github.com/AY2021S1-CS2103T-T11-1/tp/issues/98).
    
    * PR reviews with trivial comments:
    [#221](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/221),
    [#181](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/181),
    [#180](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/180),
    [#177](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/177),
    [#175](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/175),
    [#128](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/128),
    [#126](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/126),
    [#97](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/97),
    [#73](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/73),
    [#70](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/70),
    [#39](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/39),
    [#31](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/31),
    [#24](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/24),
    [#18](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/18),
    [#8](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/8)
    
    * PRs reviewed (with non-trivial review comments):
    [#155](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/155),
    [#133](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/133),
    [#124](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/124),
    [#82](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/82),
    [#38](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/38),
    [#36](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/36),
    [#33](https://github.com/AY2021S1-CS2103T-T11-1/tp/pull/33)
