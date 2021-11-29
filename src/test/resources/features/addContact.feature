Feature: AddContact

  @validDataAddContact
  Scenario: AddContact with valid data
    Given Navigate to Page PhoneBook
    When Click on Login tab
    And Enter a valid data
    And Click on Login button
    And Click ADD tab
    And Enter a valid data of contact
    And Click on Save button
    Then Is Contact created