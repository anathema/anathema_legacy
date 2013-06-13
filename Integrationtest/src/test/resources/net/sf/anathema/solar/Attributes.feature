@Integration
Feature: Attribute Handling of young Solars

  Scenario: A default Solar starts with all her attributes with 1 dot
    Given a new default Solar
    Then she has 1 dots in all her attributes

  Scenario Outline: A young default Solar respects boundaries of all attributes
    Given a new default Solar
    When I set any of her attributes to <illegalValue>
    Then she has <legalValue> dots in the attribute

  Examples:
    | illegalValue | legalValue |
    | 6            | 5          |
    | 0            | 1          |

