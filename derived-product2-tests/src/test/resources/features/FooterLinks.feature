Feature: Get all Footer Link and Write them to CSV

  Scenario: Write all Footer link to CSV and determine duplicate links
    Given User navigates to Application Home Page
    When Scroll to the Footer
    Then Write All Links to CSV and Print Duplicate Links