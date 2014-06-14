@Integration
Feature: Anathema calculates creation point costs for Attributes
  The scenarios for the various cases of overspending work with Mortals so we can use their allotment.
  The overlap with cost calculation scenarios is incidental, these are not intended as such.
  
  Scenario: A fresh character does not pay for Attributes
    Given a new Character of any kind
    Then she has spent 0 points on Primary Attributes
    And she has spent 0 points on Secondary Attributes
    And she has spent 0 points on Tertiary Attributes
    And she has spent 0 bonus points    
                 
  Scenario: Anathema determines group assignment based on actual spending
    Given a new Mortal using rules for HeroicMortal
    When she spends 6 points on Mental Attributes
    And she spends 4 points on Social Attributes
    And she spends 3 points on Physical Attributes
    Then she has spent 6 points on Primary Attributes
    And she has spent 4 points on Secondary Attributes
    And she has spent 3 points on Tertiary Attributes
    And she has spent 0 bonus points

  Scenario: Characters spend bonus points when exceeding the allotment for one group
    Given a new Mortal using rules for HeroicMortal
    When she spends 7 points on Mental Attributes
    Then she has spent 6 points on Primary Attributes
    And she has spent 4 bonus points
    
  
