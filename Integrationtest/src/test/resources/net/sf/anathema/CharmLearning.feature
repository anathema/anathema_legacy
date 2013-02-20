Feature: Characters can learn their native Charm

  Scenario: Charm is not learnable if prerequisites are not met
    When I create a new default Sidereal
    Then she can not learn the Charm Sidereal.GeneralizedAmmunitionTechnique

  Scenario: Charm is learnable if prerequisites are met
    When I create a new default Sidereal
    And I set her Archery to 2
    Then she can learn the Charm Sidereal.GeneralizedAmmunitionTechnique