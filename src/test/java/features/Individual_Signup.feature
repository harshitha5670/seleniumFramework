Feature: Signup for Individual

  As a user, I should be able to navigate to screen and be able to signup for Individual section.

  Background: Validate user is able to navigate to application
    Given I navigate to signup page in the application
    Then I validate url of the page: "https://transfermate.io/en/register.asp"
    Then I validate title of the page: "Sign up for your free TransferMate account"

  Scenario: Validate fields in signup page
    Then Validate below mentioned labels are displayed in page
      |Education|
      |Individual|
      |Corporate|
      |Partnership|
      |Sole Trader|
      |Country|
      |First Name|
      |Last Name|
      |Email address|
      |Mobile phone|
      |I have read and agree with the Terms of Use and Privacy Policy.|
      |I'd like to hear about news and offers from TransferMate.|
      |Please enter the result:|

  Scenario: Validate user is able signup with valid data
    Then I navigate to "Individual" signup page
    Then I validate default India country is selected in "Nationality" dropdown
    Then I enter "first_name" mandatory field: "Harshitha"
    Then I enter "last_name" mandatory field: "Murlaidhar"
    Then I enter "email" mandatory field: "hm@gmail.com"
    Then I enter "mobile_phone" mandatory field: "67564677677"
    Then I select register terms and agree checkbox
    Then I select register newsletter and offer checkbox
    Then I enter captcha value as per given sum value
    Then I click on open account button

  Scenario: Validate user is not able to signup without email id field
    Then I navigate to "Individual" signup page
    Then I validate default India country is selected in "Nationality" dropdown
    Then I enter "first_name" mandatory field: "Harshitha"
    Then I enter "last_name" mandatory field: "Muralidhar"
    Then I enter "mobile_phone" mandatory field: "612738102"
    Then I select register terms and agree checkbox
    Then I select register newsletter and offer checkbox
    Then I enter captcha value as per given sum value
    When I click on open account button
    Then I should see "The field is mandatory" error message tooltip at "email"

  Scenario: Validate user is not able to signup without mobile number field
    Then I navigate to "Individual" signup page
    Then I validate default India country is selected in "Nationality" dropdown
    Then I enter "first_name" mandatory field: "Harshitha"
    Then I enter "last_name" mandatory field: "Muralidhar"
    Then I enter "email" mandatory field: "hm@gmail.com"
    Then I select register terms and agree checkbox
    Then I select register newsletter and offer checkbox
    Then I enter captcha value as per given sum value
    When I click on open account button
    Then I should see "Please enter correct information!" error message tooltip at "mobile_phone"

  Scenario: Validate user is not able to signup with invalid email address
    Then I navigate to "Individual" signup page
    Then I validate default India country is selected in "Nationality" dropdown
    Then I enter "first_name" mandatory field: "Harshitha"
    Then I enter "last_name" mandatory field: "Muralidhar"
    Then I enter "email" mandatory field: "harshithagmail.com"
    Then I enter "mobile_phone" mandatory field: "276378122"
    Then I select register terms and agree checkbox
    Then I select register newsletter and offer checkbox
    Then I enter captcha value as per given sum value
    When I click on open account button
    Then I should see "Please enter correct information!" error message tooltip at "email"

  Scenario: Validate user is not able to signup without selecting terms checkbox
    Then I navigate to "Individual" signup page
    Then I validate default India country is selected in "Nationality" dropdown
    Then I enter "first_name" mandatory field: "Harshitha"
    Then I enter "last_name" mandatory field: "Muralidhar"
    Then I enter "email" mandatory field: "hm@gmail.com"
    Then I enter "mobile_phone" mandatory field: "16278367"
    Then I select register newsletter and offer checkbox
    Then I enter captcha value as per given sum value
    When I click on open account button
    Then I should see "Click OK to return and check the box that you have read and agree with our Terms of Use" error message tooltip at "terms checkbox"

  Scenario: Validate user is able signup for default India country
    Then I navigate to "Individual" signup page
    Then I select "France" in "Nationality" dropdown
    Then I enter "first_name" mandatory field: "harshitha"
    Then I enter "last_name" mandatory field: "muralidhar"
    Then I enter "email" mandatory field: "hm@gmail.com"
    Then I enter "mobile_phone" mandatory field: "377823367"
    Then I select register terms and agree checkbox
    Then I select register newsletter and offer checkbox
    Then I enter captcha value as per given sum value
    Then I click on open account button