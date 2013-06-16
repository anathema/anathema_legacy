@Integration
Feature: Anathema supports a number of different character types

  Scenario: Users can create characters of different types
    Given Anathema is running
    Then I can create a new default Solar
    And I can create a new default Mortal
    And I can create a new Mortal using rules for HeroicMortal