Feature: Log in to the web application

  @regression
  Scenario Outline: login to soucedemo
    Given login page displayed
    When user input <username> as username
    And user input <password> as password
    And user click login button
    Then user see <status> displayed

    @positive
    Examples:
      |username       |password     |status   |
      |standard_user  |secret_sauce |success  |

    @negative
    Examples:
      |username       |password     |status   |
      |standard_user  |123456789010 |failed   |
      |locked_out_user|secret_sauce |locked   |