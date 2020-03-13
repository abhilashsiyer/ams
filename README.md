# Automation made simple - AMS

## Are you 👇
- testing your web application manually ?
- not having expertise in building test automation ?
- worried to invest huge time in building a test automation framework ?


### How about I say, You can resolve above problems without even having knowledge of programming! 😉

## Introducing AMS (Automation Made Simple)


What is AMS ?
-------------------

AMS (Automation Made Simple) is a Java-Maven based project that is designed to enable one to automate their test cases in English using [Gherkin](https://cucumber.io/docs/gherkin/reference/)

How it works ?
-------------------

Lets take an example web application - "https://www.seleniumeasy.com/test/"

Your test scenario is to verify Radio button functionality in the above web application.

Say following are the test steps-

1. Launch the web site
2. Navigate to Radio Buttons Demo from Input Forms
3. Click Male radio button and verify the checked value starts with Radio button

With AMS, You write the above test steps as :
```
Given Launch "https://www.seleniumeasy.com/test/"
When click "InputForm" from json "home.json"
And click by text "Radio Buttons Demo"
And click by text "Male"
And click by id "buttoncheck"
Then verify "RadioButton" in "radioButton.json" json shows "Radio button"
```
That's it you have automated your test now !! You are only required to write the steps in english and you're good to go ! 

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
-------------------

First, pick any sample test, Lets use the same steps of the example mentioned above ,i.e

1. Launch the web site https://www.seleniumeasy.com/test/
2. Navigate to Radio Buttons Demo from Input Forms
3. Click Male radio button and verify the checked value starts with Radio button

Try doing the above steps manually before proceeding to automate.

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

5. Next step is Navigate to Radio Buttons Demo from Input Forms. When you do this manually you see that you are doing two clicks. One on Input Form which opens a dropdown and then you click Radio Buttons Demo. In a very simple manner you may write - 
```
When click by text "InputForm" 
And click by text "Radio Buttons Demo"
```
6. Further, you need to click Male radio button, Click button checked value and verify the checked value starts with Radio button. As Male doesn't have a unique ID , we will use text identifer as above but ```Get Checked ``` button has a unique ID which is ```buttoncheck``` . So how do you write when you have a unique ID ? You say :
```
And click by text "Male"
And click by id "buttoncheck"
```
**Thus, If your web application has a unique id or a unique text, You can straight away input in AMS steps using :**
```
When click by text "Sample Text"
When click by id "sampleID"
```
Before I proceed to "verify the checked value starts with Radio button", Lets run the steps we have created till now.

Running your test -
-------------------

Since this is a maven project, you just need to run this project as a maven build. If you're unaware here is [Maven](https://www.jetbrains.com/help/idea/work-with-maven-goals.html)

1. Click Maven from your right panel and then click on the big fat m icon
2. You will be presented with Working Directory and command line.
3. Enter command as clean test -Dcucumber.options="--tags @SampleRadioButton". This is the same value that yu have provided at the top of .feature file.
4. Click execute
5. If you have done all the above steps correctly, A chrome browser will launch and execute your test ! 


Feature details -
------------------
As mentioned above, The aim of AMS is to automate test cases without need of knowledge on programming language.

The basis of building this framework is on the most commonly performed actions by a user on a web application which are Click, Enter a value or scroll a page.

Now lets see how do you automate a click -

You can write a step with click in following three ways -

When click by text "Simple Form Demo" -- You're clicking on a visible text
When click by id "sampleID" --  You inspect the element and if an ID tag is available, you enter value here. 
When click "showMessage" from json "home.json" -- As many a times a ID wouldn't be feasible to be provided, That's when you use this step.

So , how does it work or what does click "showMessage" from json "home.json" mean ?

Open home.json from support/pages and you will see a list of elements. Here is where you mention your keyword "showMessage" in our case and provide the identifier values.

I am using xpath in this scenario, So I will add a list to this element as -

{
    "name": "RadioButton",
    "identifier": "xpath",
    "value": "//*[@id=\"easycont\"]/div/div[2]/div[1]/div[2]/p[3]"
}

Thus, what we are saying to the framework is click on showMessage using details from the json Radiobutton.json and the framework will click on the xpath identifer mentioned above.

If you are not aware of how to find unique identifiers, You ncan google it or can also follow - https://www.protechtraining.com/content/selenium_tutorial-locators

Very simalarly is how an enter text work -

When enter value as "This is my first test" at id "user-message"
When enter value as "1 street" at "Address" from json" InputForm.json"

If you are looking to scroll then -
When scroll to "InputForm" from json "home.json"


Dropdown is using - 

When select visible text "Alaska" in "State" dropdown from json "InputForm.json"


As this primarly focuses on Test Automation, You need verification checks which are achieved by -

Then verify that at id "display" shows "This is my first test"
    Then verify "This is my first test" id is shown
    Then verify "This is my first test" text is shown
    Then verify "InputForm" in "home.json" json shows "This is my first test"


Maintainence -
------------------

You may argue, Hey how I am going to main so many steps. One change and I need to change ecerywhere !

Solution - 


















