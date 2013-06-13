@Integration
Feature: Dragon-Blooded have special Elemental charms that correlate with their Aspect

  Scenario: A Dragon-Blooded learns the first elemental variation if no other is selected
    Given a new default Dragon-Blooded
    And I set her Dodge to 5
    And I set her Essence to 3
    And she learns the Charm Dragon-Blooded.1stExcellency.Dodge
    When she learns the Charm Dragon-Blooded.ElementalDefenseTechnique
    Then she has chosen the effect Air for the Charm Dragon-Blooded.ElementalDefenseTechnique