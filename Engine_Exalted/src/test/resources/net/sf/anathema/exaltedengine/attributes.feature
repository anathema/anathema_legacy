Feature: Attributes

  Scenario Outline: A character has all nine attributes
    When I create an Exalted character
    Then the character has the Attribute <name>

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