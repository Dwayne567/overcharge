Feature: Deck List

  Scenario: Successful Deck List
    Given I am on the deck list page
    When I click the view deck button
    Then I should be directed to the view deck page
    And I navigate back
    And I click the edit deck button
    Then I should be directed to the edit deck page
    And I navigate back again
    And I click the delete deck button
