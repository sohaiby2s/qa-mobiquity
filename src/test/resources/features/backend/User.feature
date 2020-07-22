Feature: User

  Scenario: Validate api response when user calls the users endpoint
    When Client calls the "GET" method of "/users" endpoint
    Then Response code 200 is returned

  Scenario: Validate that a user is found in users data
    Given Client calls the "GET" method of "/users" endpoint
    And User gets the JSON response from user list
    Then "Delphine" user name found in user list

  Scenario: Validate the count of user's posts
    Given Client calls the "GET" method of "/users" endpoint
    And User gets the JSON response from user list
    When User fetch the user id of user "Delphine" from user list
    And Client calls the "GET" method of "/posts" endpoint by appending user id as parameter
    And User collects all posts against the user id from the JSON response
    Then Count of user's post should be 10

  Scenario: Validate email address format on comments of each post
    Given Client calls the "GET" method of "/users" endpoint
    And User gets the JSON response from user list
    When User fetch the user id of user "Delphine" from user list
    And Client calls the "GET" method of "/posts" endpoint by appending user id as parameter
    And User collects all posts against the user id from the JSON response
    And Client calls the "GET" method of "/comments" endpoint by appending each post id as parameter
    Then Email address on each comment should be in proper format