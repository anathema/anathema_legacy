@Integration
Feature: Some Characters can learn their Charms from other character types

  Scenario: A Solar cannot learn Abyssal Charms
    Given a new default Solar
    When I set her Archery to 4
    Then she can not learn the Charm Abyssal.PulseOfThePrey

  Scenario: An Eclipse Caste Solar can learn Abyssal Charms
    Given a new default Solar
    When I set her Archery to 4
    And I set her Caste to Eclipse
    Then she can learn the Charm Abyssal.PulseOfThePrey

  Scenario: A character forgets all alien Charms when he loses the ability to learn them
    Given a new default Solar
    And I set her Archery to 4
    And I set her Caste to Eclipse
    And she learns the Charm Abyssal.PulseOfThePrey
    When I set her Caste to Dawn
    Then she does not know the Charm Abyssal.PulseOfThePrey