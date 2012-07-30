Feature:  Characters can have qualities and operate on them

  Scenario: Characters have qualities that are added to them
    Given a character
    When I add the Attribute 'Toughness' to the character
    Then the character can operate with the Attribute 'Toughness'

  Scenario: Characters ignore qualities they don't know about
    Given a character
    When the character does not know a quality
    Then the character ignores commands for the quality

  Scenario: Qualities start at their default values
    Given a character
    And a rule that an Attribute starts with value 1
    When I add the Attribute 'Toughness' to the character
    Then Attribute 'Toughness' has the value 1

  Scenario: Qualities' values are modifiable
    Given a character
    And a rule that an Attribute starts with value 1
    When I add the Attribute 'Toughness' to the character
    And I increase the value of the Attribute 'Toughness' by 2
    Then Attribute 'Toughness' has the value 3

  Scenario: Qualities notify interested parties of executed commands
    Given a character
    When I register a listener for the Attribute 'Toughness' on the character
    And I add the Attribute 'Toughness' to the character
    Then I am notified about the new Attribute 'Toughness'
