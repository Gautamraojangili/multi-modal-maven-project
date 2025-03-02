Feature: Shop for Jackets

  Scenario: Collect all jacket details and attach to report
    Given User navigates to Men's section from CP Home Page
    When User collects jacket details from all pages
    Then Attach the details file to the test report
