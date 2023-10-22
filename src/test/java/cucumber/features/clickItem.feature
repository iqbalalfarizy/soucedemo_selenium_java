Feature: Click item

  @regression @click
  Scenario Outline: Click item to see information item
    Given login page displayed
    When user input <username> as username
    And user input <password> as password
    And user click login button
    And user click item
    Then item is displayed

    @dev
    Examples:
      |username       |password     |
      |standard_user  |secret_sauce |

    @stag
    Examples:
      |username       |password     |
      |problem_user   |secret_sauce |