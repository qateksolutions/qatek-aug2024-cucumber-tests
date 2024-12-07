@Mortgage_Calculator
Feature: Mortgage Calculator

  @RealAPR
  Scenario Outline: Calculate Real APR Rate
    Given user is in mortgage calculator home page
    And user navigate to Real Apr page through Rates
    When user click on calculate button upon entering the data of "<HomePrice>", "<DownPayment>", and "<InterestRate>"
    Then the real apr rate is "<ExpectedAprRate>"

    Examples:
      | HomePrice | DownPayment | InterestRate | ExpectedAprRate |
      |200000     |15000        |3             |3.130%           |


