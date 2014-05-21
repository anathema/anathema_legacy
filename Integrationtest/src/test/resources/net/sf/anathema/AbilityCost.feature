@Integration
Feature: Anathema calculates Bonus Point costs for Abilities
  The scenarios for the various cases of overspending work with a fresh Mortal so we can use their allotment.
  The overlap with cost calculation scenarios is incidental, these are not intended as such.
  
  Scenario: A fresh character does not pay for Abilities
    Given a new Character of any kind
    Then she has 0 ability dots spent
    And she has 0 favored dots spent
    And she has spent 0 bonus points

  Scenario: A fresh character has no Ability favored
    Given a new Character of any kind
    Then she has 0 favored picks spent

  Scenario: A character pays general dots for general Abilities
    Given a new Character of any kind
    When I set her Archery to 1  
    Then she has 1 ability dots spent
    And she has 0 favored dots spent
    And she has spent 0 bonus points

  Scenario: A character pays favored dots for favored Abilities
    Given a new Character of any kind
    When I favor her Archery
    And I set her Archery to 1
    Then she has 0 ability dots spent
    And she has 1 favored dots spent
    And she has spent 0 bonus points

  Scenario: A character pays bonus when she raises an Ability above the threshold
    Given a new Mortal using rules for Default
    When I set her Archery to 4
    Then she has 3 ability dots spent
    And she has 0 favored dots spent
    And she has spent 2 bonus points
    
  Scenario: A character pays bonus points when she overspends on Abilities
    Given a new Mortal using rules for Default
    When she exceeds her Ability allotment by 1 dot
    Then she has all her ability dots spent
    And she has 0 favored dots spent
    And she has spent 2 bonus points
                          
  Scenario: A character pays general dots points when she overspends on favored Abilities
    Given a new Mortal using rules for HeroicMortal
    When I favor her Archery
    And I set her Archery to 1
    Then she has 1 ability dots spent
    And she has 0 favored dots spent
    And she has spent 0 bonus points
  
  Scenario: A character prefers to pay favored Abilities with bonus points
    Given a new Mortal using rules for HeroicMortal
    When I favor her Archery
    And I set her Archery to 1
    And she spends all her Ability dots
    Then she has all her ability dots spent
    And she has 0 favored dots spent
    And she has spent 1 bonus points
    
  
