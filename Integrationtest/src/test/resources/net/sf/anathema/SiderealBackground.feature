@Integration
Feature: Sidereals pay for Backgrounds

  Scenario: A default Sidereal pays extra for his Celestial Manse
    Given a new default Sidereal
    When I set the background CelestialManse to 5
    Then she has spent 8 bonus points