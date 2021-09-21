@All
Feature: Post API Tests - Getting user Details

Background: populate POST URL
  Given populate post url
  When submit username and password
  Then logged in
@PostSuccessUser
  Scenario Outline: Successful UserID details
    Given User Details as an Input <id> "<title>" "<body>" <userid>
    When triggered Post API call with correct details
    Then validate the success response code 201
  Examples:
    |id|title|body|userid|
    |110|boo|test|10|
  @PostUnsuccessUser
  Scenario: UnSuccessful User Details
    Given Wrong userDetails as an Input
    When triggered post API call with wrong details
    Then validate the error response code 500
  @PostInvalidUser
  Scenario Outline: User Id specified in the body does not exist
    Given Pass user details <id> "<title>" "<body>" <userid>
    When triggered post API call with user details
    Then validate the user id passed in body 21
    Examples:
      |id|title|body|userid|
      |111|foo|bar|20|