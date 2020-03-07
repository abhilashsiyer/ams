@Home
Feature: Home features
  @LaunchWebPage
  Scenario: Launch Webpage
    Given Launch "https://www.seleniumeasy.com/test/"

  @OpenInputForm
  Scenario: Open Input Form
    When click "InputForm" from json "home.json"

  @LaunchWebPageAndOpenInputForm
  Scenario: Open Input Form
    Given Run steps from tag"@LaunchWebPage"
    And Run steps from tag"@OpenInputForm"



