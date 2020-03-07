@InputForm
Feature: Home features

  @FillInputForm
  Scenario: Open Input Form
    When click by text "Input Form Submit"
    And enter value as "FirstTest" at "FirstName" from json" InputForm.json"
    And enter value as "LastTest" at "LastName" from json" InputForm.json"
    And enter value as "abc@testwew.com" at "Email" from json" InputForm.json"
    And enter value as "009099999" at "Phone" from json" InputForm.json"
    And enter value as "1 street" at "Address" from json" InputForm.json"
    And enter value as "Sample" at "City" from json" InputForm.json"
    And select visible text "Alaska" in "State" dropdown from json "InputForm.json"
    And enter value as "2120" at "ZIP" from json" InputForm.json"
    And scroll to "Send" from json "InputForm.json"
    And click "Send" from json "InputForm.json"