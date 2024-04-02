Feature: Deck Creation

  Scenario: Successful Deck Creation
    Given I am on the create deck page
    When I enter my deck title
    And I click the add card button
    And I enter my question and answer
    And I click the create deck button
    Then I should be directed to the deck list page
    Then my deck should be displayed