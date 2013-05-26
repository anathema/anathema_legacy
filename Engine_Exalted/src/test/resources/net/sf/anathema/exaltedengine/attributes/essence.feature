Feature: Attributes

  Scenario: A character has an Essence trait
    When I create an Exalted character
    Then the character has the trait 'Essence'

  Scenario: Essence starts at 1
    When I create an Exalted character
    Then the character has the trait 'Essence' at 1

  Scenario: Players can modify Essence
    Given an Exalted character
    When I increase the trait 'Essence' to 4
    Then the character has the trait 'Essence' at 4