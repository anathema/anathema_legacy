@Integration
Feature: Anathema supports a number of different character types

  Scenario Outline: Users can create characters of different types
    Given Anathema is running
    Then I can create a new <Character Type> using rules for <Subtype>

  Examples:
    | Character Type | Subtype      |
    | Solar          | Default      |
    | Mortal         | Default      |
    | Mortal         | HeroicMortal |