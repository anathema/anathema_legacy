Feature: Sidereals have minimum Abilities depending on their Caste

  Scenario: Serenity Caste defaults its choice to Craft
    When I create a new default Sidereal
    And I set her Caste to Serenity
    Then she has 2 dots in Craft

  Scenario: Serenity Caste counts preset dots in Craft (Issue #311)
    When I create a new default Sidereal
    And I set her Caste to Serenity
    Then she has 7 favored dots spent.

  Scenario: First Age Serenity Caste has free dots in Craft
    When I create a new Sidereal using rules for Dreams
    And I set her Caste to Serenity
    Then she has 0 ability dots spent.