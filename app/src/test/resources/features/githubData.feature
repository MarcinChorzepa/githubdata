Feature: When user reads github details

  Scenario: user read github details
    When User "userLogin" read github data
    Then Response is not null , Login is "userLogin" and calculation is equal "0.0"
    And Count of request for user "userLogin" is equals to "1" and has been saved in DB

  Scenario: user read github details second time
    When User "userLogin" read github data
    Then Count of request for user "userLogin" is equals to "2" and has been saved in DB