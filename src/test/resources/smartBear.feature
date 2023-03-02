Feature: Validate SmartBear features

  Background:
    Given user is on "http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx"
    When user enters username as "Tester"
    And user enters password as "test"

  @Smoke
  Scenario: Validate invalid login attempt
    When user enters username as "abcd"
    And user enters password as "abcd1234"
    And user clicks on Login button
    Then user should see "Invalid Login or Password." message
  #NOTE: This is a Smoke test

  @Smoke
  Scenario: Validate valid login attempt
    And user clicks on Login button
    Then user should be routed to "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/"
  #NOTE: This is a Smoke test


  @Regression
  Scenario: Validate "Web Orders" menu items
    Given user is on "http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx"
    When user enters username as "Tester"
    And user enters password as "test"
    And user clicks on Login button
    Then user should be routed to "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/"
    And validate below menu items are displayed
      | View all orders | View all products | Order |

  @Regression
  Scenario: Validate "Check All" and "Uncheck All" links
    And user clicks on Login button
    Then user should be routed to "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/"
    When user clicks on "Check All" button
    Then all rows should be checked
    When user clicks on "Uncheck All" button
    Then all rows should be unchecked
  #NOTE: This is a Regression test

  Scenario: Validate adding new order
    And user clicks on Login button
    Then user should be routed to "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/"
    When user clicks on "Order" menu item
    And user selects "FamilyAlbum" as product
    And user enters 2 as quantity
    And user enters all address information
      | John Doe | 123 TechGlobal Ave | Chicago | IL | 12345 |
    And user enters all payment information
      | Visa | 1234567891234567 | 04/23 |
    And user clicks on "Process" button
    And user clicks on "View all orders" menu item
    Then user should see their order displayed in the "List of All Orders" table
    And validate all information entered displayed correct with the order
      |  | John Doe | FamilyAlbum | 2 | 03/02/2023 | 123 TechGlobal Ave | Chicago | IL | 12345 | Visa | 1234567891234567 | 04/23 |

  Scenario: Validate "Delete Selected" button
    And user clicks on Login button
    Then user should be routed to "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/"
    When user clicks on "Check All" button
    And user clicks on "Delete Selected" button
    Then validate all orders are deleted from the "List of All Orders"
    And validate user sees "List of orders is empty. In order to add new order use this link." message