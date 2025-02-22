Feature: End to End simulation test

# Scenario: As a user I can add new data
#     Given A list of objects are available
#     When I add new objects to etalase
#     Then The object is available

#Login
#User deleted, New User -> user gagal Login
#User deleted : Diana, 123
#New user : " ", " "

Scenario Outline: As a user I can add new data with some data
    Given A list of objects are available
    When I add new "<payload>" to list
    Then The "<payload>" is available
Examples:
    |payload    |
    |addItem    |
    |addItem2   |