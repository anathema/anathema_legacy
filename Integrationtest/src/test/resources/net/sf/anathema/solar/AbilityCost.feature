Feature: Anathema calculates Ability point costs for Solars

  Scenario: Solars may raise their Abilities to 5 without spending bonus points
    Given a new Solar using rules for Default
    When I set her Archery to 5
    Then she has spent 0 bonus points
    And she has 5 ability dots spent