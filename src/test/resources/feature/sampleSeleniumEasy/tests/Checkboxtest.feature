@SampleCheckbox
Feature: Input value in a Sample Input Form

  Background: User has launched web page and opened Input Form
    Given Run steps from tag"@LaunchWebPageAndOpenInputForm"

  Scenario: Simple Input Form example
    When click by text "Checkbox Demo"
    And click by id "isAgeSelected"
    Then verify that at id "txtAge" shows "Success - Check box is checked"




