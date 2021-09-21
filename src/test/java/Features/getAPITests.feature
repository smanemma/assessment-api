@All
Feature: get API Tests - Getting user Details based on User ID
  Background: populate GET URL
    Given populate get url
    When enter username and password
    Then succeesfully logged in
  @GetValidUser
  Scenario: send a valid request to Get user details
    Given perform Get operation for user
    When  perform Get for the user "/users/1"
    Then  should see response code is 200
  @GetNonmatchinguser
    Scenario: send a nonmatching userid to validate response
    Given perform Get operation for nonmatching user
    When perform get for the user with nonmatching userid "/99999"
    Then should verify response code 404
  @GetInvaliUser
    Scenario Outline: send a unformat request to validate response
    Given  perform Get operation for checking invalid user
    When   perform get for th user with unformat "<user>"
    Then   should verify response <code>

    Examples:
      |user|code|
      |$|404|
