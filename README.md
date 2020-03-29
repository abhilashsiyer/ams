# Automation made simple - AMS

## Are you 👇
- testing your web application manually?
- not having expertise in building test automation?
- worried about investing huge time in building a test automation framework?



## How about I say, You can resolve above problems without even having knowledge of programming! 😉

# Introducing AMS (Automation Made Simple)


What is AMS ?
-------------

AMS (Automation Made Simple) is a Java-Maven based project that is designed to enable one to automate their test cases in English using [Gherkin](https://cucumber.io/docs/gherkin/reference/)

How it works ?
--------------

Lets take an example web application - "https://www.seleniumeasy.com/test/"

Your test scenario is to verify Radio button functionality in the above web application.

Following are the test steps-

1. Launch the web site
2. Navigate to Radio Buttons Demo from Input Forms
3. Click Male radio button and verify the checked value starts with Radio button


With AMS, You write the above test steps as : :
```
Given Launch "https://www.seleniumeasy.com/test/"
When click "InputForm" from json "home.json"
And click by text "Radio Buttons Demo"
And click by text "Male"
And click by id "buttoncheck"
Then verify "RadioButton" in "radioButton.json" json shows "Radio button"
```
That's it, you have automated your test now. You just need to write the steps in english and you're good to go! 

Before, I proceed with further steps, Lets get you setup -

Tools to download
-------------------
1. IntelliJ  (https://www.jetbrains.com/idea/download/#section=mac)
2. Chromedriver (https://chromedriver.chromium.org/downloads)
3. Git (https://medium.com/@GalarnykMichael/install-git-on-mac-a884f0c9d32c)

Setup -
-------------------
 1. Chromedriver - Follow setup guide as below
 MAC - https://www.edureka.co/community/52315/how-to-setup-chrome-driver-with-selenium-on-macos 
Windows - https://www.youtube.com/watch?v=dz59GsdvUF8

2. Clone the project to local (or download the ZIP from git - https://github.com/abhilashsiyer/ams)

3. Open project in Intellij. IntelliJ will start downloading all the project dependecies. This might take about 5 minutes. Be sure you are not on any network that blocks calls to external internet.

Writing your first test -
-------------------------

First, pick any sample test, let's use the same steps of the example mentioned before:

1. Launch the web site https://www.seleniumeasy.com/test/
2. Navigate to Radio Buttons Demo from Input Forms
3. Click Male radio button and verify the value starts with Radio button

I would suggest doing the above steps manually first before proceeding to automate.

Steps to write your test script:

1. Right click on ams/src/test/resources/feature/sampleSelniumEasy/tests and select New->File. 
Note that you must write your test scripts only under feature directrory. I have further added dir's for better readability and maintainence.
2. Write any name you want for your test, I would name SampleRadioButton.feature
3. Paste below code in the editor -
```
@SampleRadioButton
Feature: Sample Radio Button test

  Scenario: User can click Male radio button
```  

The above is cucumber format of writing a feature. For more details go to - [Gherkin](https://cucumber.io/docs/gherkin/reference/)

4. So, our first step is to Launch the web site https://www.seleniumeasy.com/test/. In AMS we will write ```Launch "https://www.seleniumeasy.com/test/"``` , Appending to above scenario, our scenario will now look like : 

```
@SampleRadioButton
Feature: Sample Radio Button test

  Scenario: User can click Male radio button
  Given Launch "https://www.seleniumeasy.com/test/"
  ```

5. Next step is Navigate to Radio Buttons Demo from Input Forms. When you do this manually you see that you are doing two clicks. One on Input Form which opens a dropdown and then you click on Radio Buttons Demo. In a very simple manner using the text as identifier you may write - 
```
When click by text "InputForm" 
And click by text "Radio Buttons Demo"
```
6. Further, you need to click Male radio button, click on button Checked value and verify the value starts with Radio button. As Male doesn't have a unique ID , we will again use text identifer as above. However, ```Get Checked ``` button has a unique ID which is ```buttoncheck``` . 

If a unique ID is available, it is always the most preferred value as an app developer can still keep the same ID but change the text based on business requirements. For more information on how to find identifers refer to https://www.softwaretestinghelp.com/locate-elements-in-chrome-ie-selenium-tutorial-7/.

Coming back to our step, for our test step, we write -
```
And click by text "Male"
And click by id "buttoncheck"
```
**Thus, If your web application has a unique id or a unique text, You can straight away input in AMS steps using :**
```
When click by text "Sample Text"
When click by id "sampleID"
```
Similary, for step "verify the checked value starts with Radio button", You write :
```
Then verify "Radio button" text is shown
```

Running your test -
-------------------

Since this is a maven project, you just need to run this project as a maven build. If you're unaware here is [Maven](https://www.jetbrains.com/help/idea/work-with-maven-goals.html)

1. Click Maven from your right panel and then click on the big fat m icon
2. You will be presented with Working Directory and command line.
3. Enter command as clean test -Dcucumber.options="--tags @SampleRadioButton". This is the same value that yu have provided at the top of .feature file.
4. Click execute
5. If you have done all the above steps correctly, A chrome browser will launch and execute your test ! 


Using JSON for identifiers -
---------------------------

If you have looked at the DOM of any of the web sites, You always see that it's not feasible to always have a unique ID to every identifier. You use other identifiers such as CSS, XPATH, name etc.

Now, how do you input these other identifiers in AMS?

Let's take another example from the same website using the below steps -

1. Launch the web site https://www.seleniumeasy.com/test/
2. Navigate to Input Form Submit from Input Forms
3. Enter the field First Name as "Test Name"

When you inspect the value of field First Name you see that the unique identifier is name. 

In AMS, you will write this step as :

```
 And enter value as "FirstTest" at "FirstName" from json" InputForm.json"
```
So, what are these values ?

For any non text or ID identifiers, you define a JSON page and provide the value in that JSON.

For the example above, You will create a JSON file with name say InputForm.json in support/pages and enter value as below :

```
{
   "name": "FirstName",
   "identifier": "name",
   "value": "first_name"
  }
```  
under the elements list [] in the json. To further understand this case, let's say you want to enter the last name field as "LastTest", you will do the exact same steps but append to elements list. Your complete json will now look like :

```
{
  "elements": [
  {
    "name": "FirstName",
    "identifier": "name",
    "value": "first_name"
  },
    {
      "name": "LastName",
      "identifier": "name",
      "value": "last_name"
    }
  ]
}
```
If you want to use the CSS identifier instead across the framework, you could write as:
```
{
    "name": "FirstName",
    "identifier": "css",
    "value": "input[name='first_name']"
  }
```
Similarly, XPATH will be 

```
{
    "name": "FirstName",
    "identifier": "xpath",
    "value": "//*[@name=\"first_name\"]"
  }
```
So, your steps to enter Firstname and last name will look like :

```
@SampleRadioButton
Feature: Sample Radio Button test

  Scenario: User can click Male radio button
  Given Launch "https://www.seleniumeasy.com/test/"
  When click "InputForm" from json "home.json"
  And click by text "Input Form Submit"
  And enter value as "FirstTest" at "FirstName" from json" InputForm.json"
  And enter value as "LastTest" at "LastName" from json" InputForm.json"
    
  ```
Maintainence -
------------------
When you see the above scenario, the following two steps are common across all tests:

```
@SampleRadioButton
Feature: Sample Radio Button test

  Scenario: User can click Male radio button
  Given Launch "https://www.seleniumeasy.com/test/"
  When click "InputForm" from json "home.json"
  
```
Do you need to repeat the same steps every time in every test case?

The anwser is No, AMS brings you the capability to reuse your steps. Now, let's split the above steps as  “launching a website” and clicking on “InputForm” is be used for more operations:

```
@Home
Feature: Home features
  @LaunchWebPage
  Scenario: Launch Webpage
    Given Launch "https://www.seleniumeasy.com/test/"

  @OpenInputForm
  Scenario: Open Input Form
    When click "InputForm" from json "home.json"
```

Since we are writing our sample tests in Input Form tab, we can simply write this as :
```
 @LaunchWebPageAndOpenInputForm
  Scenario: Open Input Form
    Given Run steps from tag"@LaunchWebPage"
    And Run steps from tag"@OpenInputForm"
 ```
 Now, our test scenario will look like - 
 ```
 Scenario: User can click Male radio button
    Given Run steps from tag"@LaunchWebPageAndOpenInputForm"
    And click by text "Radio Buttons Demo"
    And click by text "Male"
    
```    
##  Features -

This is an experiment I am trying and looking forward to adding more features to this framework. As of today following features are supported :

```
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

```

Feel free to contribute if you're intersted and I wouldn't mind if anyone wants to replicate the same logic in JS.

Hope you all have fun automating your tests. 


##  Author -
Abhilashsiyer@gmail.com
