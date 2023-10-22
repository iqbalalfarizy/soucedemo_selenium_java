Feature: Proceed checkout

  Background:login with standard user
    Given standard_user success login

  @regression @positive
  Scenario: checkout with one item in cart
    Given user add one item to cart
    When user open the shopping cart
    And user click on the checkout button
    And user fill in valid information
    And user click on the continue button
    Then user click finish button

  @regression @negative
  Scenario: attempt to checkout with an empty cart
    Given user open the shopping cart
    Then user unable to click checkout button


