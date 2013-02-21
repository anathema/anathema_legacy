@Integration
Feature: Characters can learn their native Charm

  Scenario: Charm is not learnable if prerequisites are not met
    Given a new default Sidereal
    When I set her Archery to 0
    Then she can not learn the Charm Sidereal.GeneralizedAmmunitionTechnique

  Scenario: Charm is learnable if prerequisites are met
    Given a new default Sidereal
    When I set her Archery to 2
    Then she can learn the Charm Sidereal.GeneralizedAmmunitionTechnique