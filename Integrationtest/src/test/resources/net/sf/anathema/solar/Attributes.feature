@Integration
Feature: Attribute Handling of young Solars

  Scenario: A default Solar starts with all her attributes with 1 dot
    Given a new Solar using rules for Default
    Then she has 1 dots in all her attributes

  Scenario Outline: A young default Solar respects boundaries of all attributes
    Given a new Solar using rules for Default
    When I set any of her attributes to <illegalValue>
    Then she has <legalValue> dots in the attribute

  Examples:
    | illegalValue | legalValue |
    | 6            | 5          |
    | 0            | 1          |

  Scenario: An experienced Solar must not lower her attributes below creation value
    Given a new Solar using rules for Default
    When I set any of her attributes to 3
    And she goes experienced
    And I set the attribute to 2
    Then she has 3 dots in the attribute
