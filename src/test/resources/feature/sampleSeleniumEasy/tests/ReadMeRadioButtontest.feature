@SampleRadioButton
Feature: Sample Radio Button test

  Scenario: User can click Male radio button
    Given Launch "https://www.seleniumeasy.com/test/"
    When click "InputForm" from json "home.json"
    And click by text "Radio Buttons Demo"
    And click by text "Male"
    And click by id "buttoncheck"
    Then verify "RadioButton" in "radioButton.json" json shows "Radio button"

