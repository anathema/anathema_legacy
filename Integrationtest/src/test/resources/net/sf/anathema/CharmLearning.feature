@Integration
Feature: Characters can learn their native Charm
  'There is no Wind' is a Solar Archery 4/Essence 2 Root Charm.
  'Essence Arrow Attack' is a Solar Archery Root Charm and direct prerequisite to 
  'Phantom Arrow Technique', a Solar Archery 4/Essence 3 Charm.  

  Scenario: Charm is not learnable if prerequisites are not met
    Given a new default Solar
    When I set her Archery to 0
    Then she can not learn the Charm Solar.ThereIsNoWind

  Scenario: Charm is learnable if prerequisites are met
    Given a new default Solar
    When I set her Archery to 4
    Then she can learn the Charm Solar.ThereIsNoWind

  Scenario: Parent Charms are automatically learned
    Given a new default Solar
    And I set her Archery to 4
    And I set her Essence to 3
    When she learns the Charm Solar.PhantomArrowTechnique
    Then she knows the Charm Solar.EssenceArrowAttack

  Scenario: Child Charms are automatically forgotten
    Given a new default Solar
    And I set her Archery to 4
    And I set her Essence to 3
    And she learns the Charm Solar.PhantomArrowTechnique
    When she forgets the Charm Solar.EssenceArrowAttack
    Then she does not know the Charm Solar.PhantomArrowTechnique