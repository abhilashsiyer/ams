@InputFormSample
Feature: Input value in a Sample Input Form

  Background: User has launched web page and opened Input Form
    Given Run steps from tag"@LaunchWebPageAndOpenInputForm"

  Scenario: Simple Input Form example
    When Run steps from tag"@FillInputForm"
    Then verify "Demo Home" text is shown









