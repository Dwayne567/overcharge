Feature: Deck View

  Scenario: Successful Deck View
    Given I am on the view deck page
    When I click on the flashcard
    Then I should be able to see the answer
    And I click on the flashcard again
    Then I should be able to see the question