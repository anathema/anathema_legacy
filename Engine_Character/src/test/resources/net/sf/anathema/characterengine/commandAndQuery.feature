Feature:  Characters can have qualities and operate on them

  Scenario: Characters have qualities that are added to them
    Given a character
    When I add the Attribute 'Toughness' to the character
    Then the character can operate with the Attribute 'Toughness'

  Scenario: Characters ignore qualities they don't know about
    Given a character
    When the character does not know a quality
    Then the character ignores commands for the quality