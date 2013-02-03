Feature: Sidereals pay for Backgrounds

  Scenario: A default Sidereal pays extra for his Celestial Manse
    When I create a new default Sidereal
    And she has the background CelestialManse at 5
    Then she has spent 8 bonus points