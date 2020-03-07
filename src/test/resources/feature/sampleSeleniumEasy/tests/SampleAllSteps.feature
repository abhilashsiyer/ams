Feature: Input value in a Sample Input Form

  Background: User has launched web page and opened Input Form
    Given Run steps from tag"@LaunchWebPageAndOpenInputForm"
    Given Launch "https://www.seleniumeasy.com/test/"

  Scenario: Sample click examples
    When click by text "Simple Form Demo"
    When click by id "sampleID"
    When click "showMessage" from json "home.json"

  Scenario: Sample enter Text examples
    When enter value as "This is my first test" at id "user-message"
    When enter value as "1 street" at "Address" from json" InputForm.json"

  Scenario: Sample scroll examples
    When scroll to "InputForm" from json "home.json"

  Scenario: Sample select from dropdown
    When select visible text "Alaska" in "State" dropdown from json "InputForm.json"

  Scenario: Sample verification examples
    Then verify that at id "display" shows "This is my first test"
    Then verify "This is my first test" id is shown
    Then verify "This is my first test" text is shown
    Then verify "InputForm" in "home.json" json shows "This is my first test"




