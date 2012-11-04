Feature:  Qualities are governed by rules

  Scenario: Commands are not executed if a rule forbids
    Given a character
    And a rule that an Attribute starts with value 1
    And a rule that an Attribute must at least be 1
    When I add the Attribute 'Toughness' to the character
    And I lower the value of the Attribute 'Toughness' by 1
    Then Attribute 'Toughness' has the value 1