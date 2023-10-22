Feature: Proceed fill information to checkout

  Background:login with problem user
    Given problem_user navigate to the checkout: your information page

  @regression
  Scenario Outline: fill information before checkout
    Given user input <firstname> as firstname
    When user input <lastname> as lastname
    And user input <zippostalcode> as zippostalcode
    And user click on continue button
    Then user can see pop up <status> displayed

    @positive
    Examples:
      |firstname      |lastname     |zippostalcode  |status   |
      |iqbal          |al farizy    |12345          |success  |

    @negative
    Examples:
      |firstname      |lastname     |zippostalcode  |status   |
      |               |al farizy    |12345          |failed   |
      |iqbal          |             |12345          |failed   |
      |iqbal          |al farizy    |               |failed   |
