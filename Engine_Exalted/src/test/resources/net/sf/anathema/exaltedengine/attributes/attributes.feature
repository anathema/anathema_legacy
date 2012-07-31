Feature: Attributes

  Scenario Outline: A character has all nine attributes
    When I create an Exalted character
    Then the character has the Attribute '<name>'

    Examples:
      | name |
      | Strength |
      | Dexterity |
      | Stamina |
      | Charisma |
      | Manipulation |
      | Appearance |
      | Perception |
      | Intelligence |
      | Wits |

  Scenario: All attributes start at 1
    When I create an Exalted character
    Then each Attribute has value 1

  Scenario: Players can modify Attributes
    Given an Exalted character
    When I increase the Attribute 'Strength' to 3
    Then the character has the Attribute 'Strength' at 3