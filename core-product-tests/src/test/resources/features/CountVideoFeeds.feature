Feature: Video feeds in News & Features

  Scenario: Count total Video Feeds and video feeds older than three days
    Given User navigates to News and Features Page from CP Home Page
    When Count total video feeds
    Then Count total video feeds older than three days