Feature: Product search

  Background:
    Given user is in home page

  @smoke
  Scenario Outline: User search for a product and appropriate results should be shown in results page
    When user search for "<product>" in search box
    Then verify product listed in the result is appropriate

    Examples:
      | product |
      | Acer    |
      | Tab     |

  @sanity
  Scenario Outline: User type some product name in the search box and appropriate suggestions should be shown in drop down
    When user type "<product>" name in search box
    Then verify appropriate suggestions should be shown in drop down

    Examples:
      | product |
      | Battery |
      | Oneplus |