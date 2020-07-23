Feature: UserBlog

  As a blog user
  I want to fetch comments on each posts
  So that I can check that email addresses are in proper format

  Scenario: Validate api response when user calls the users endpoint
    When Client calls the "GET" method of "/users" endpoint
    Then Response code 200 is returned

  Scenario: Validate api response when user calls the posts endpoint
    When Client calls the "GET" method of "/posts" endpoint
    Then Response code 200 is returned

  Scenario: Validate api response when user calls the posts endpoint
    When Client calls the "GET" method of "/comments" endpoint
    Then Response code 200 is returned

  Scenario: Validate api response when user calls the endpoint which does not exist
    When Client calls the "GET" method of "/test" endpoint
    Then Response code 404 is returned

  Scenario: Validate api response when user provides wrong value in parameter
    When Client calls the "GET" method of "/posts?userId=12355" endpoint
    Then Api should return empty response

  Scenario: Validate that a user is found in users data
    Given Client calls the "GET" method of "/users" endpoint
    And User gets the JSON response from user list
    Then "Delphine" user name found in user list


  Scenario: Validate the count of user's posts
    Given Client calls the "GET" method of "/users" endpoint
    And User gets the JSON response from user list
    When User fetch the id of user "Delphine" from user list
    And Client calls the "GET" method of "/posts" endpoint by appending "userId" as parameter
    And User collects all posts against the user id from the JSON response
    Then Count of user's post should be 10

  Scenario: Validate email address format on comments of each post
    Given Client calls the "GET" method of "/users" endpoint
    And User gets the JSON response from user list
    When User fetch the id of user "Delphine" from user list
    And Client calls the "GET" method of "/posts" endpoint by appending "userId" as parameter
    And User collects all posts against the user id from the JSON response
    And Client calls the "GET" method of "/comments" endpoint by appending each "postId" as parameter
    Then Email address on each comment should be in proper format