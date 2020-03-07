@SampleRadioButton
Feature: Input value in a Sample Input Form

  Background: User has launched web page and opened Input Form
    Given Run steps from tag"@LaunchWebPageAndOpenInputForm"

  Scenario: Simple Input Form example
    When click text "Radio Buttons Demo"
    And click text "Male"
    And click by id "buttoncheck"
    Then verify "RadioButton" in "radioButton.json" json shows "Radio button"

