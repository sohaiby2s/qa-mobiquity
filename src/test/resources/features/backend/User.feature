Feature: User

  Scenario: Validate api response when user calls the users endpoint
    Given User calls the users endpoint
    Then Response code 200 is returned

  Scenario: Validate that a user is found in users data
    Given User calls the users endpoint
    Then "Delphine" user name found in user data

  Scenario: Validate the count of user's posts
    Given User calls the users endpoint
    And User gathers the data of user name "Delphine"
    And User calls the posts endpoint based on the data gathered
    Then Count of user's post is 10

  Scenario: Validate email address format on comments of each post
    Given User calls the users endpoint
    And User gathers the data of user name "Delphine"
    And User calls the posts endpoint based on the data gathered
    And User gathers all the posts based on the user id
    And User gathers comments on each post
    Then Email address on each comment should be in proper format