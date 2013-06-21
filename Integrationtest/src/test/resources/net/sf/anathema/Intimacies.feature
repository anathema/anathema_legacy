@Integration
Feature: Characters have a number of intimacies detailing their hearts and minds

  Scenario: Intimacies change the character
    Given a new Character of any kind
    When I add a fresh intimacy
    Then the character needs to be saved