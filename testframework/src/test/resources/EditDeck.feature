Feature: Deck Edit

  Scenario: Successful Deck Edit
    Given I am on the edit deck page
    When I clear old deck title
    And I enter a new deck title
    And I remove the old card
    And I add a new card
    And I enter a new question and answer
    And I click the update deck button
    Then I should be redirected to the deck list page
    Then My new deck should be displayed
