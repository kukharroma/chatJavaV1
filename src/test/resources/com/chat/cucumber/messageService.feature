Feature: MessageService

  Scenario: saving a message in db
    Given save a message in db with text "simple message"

  Scenario: getting all messages
    Given get all messages

  Scenario: getting last hundred messages
    Given get last hundred messages

    Scenario: deleting all messages
      Given delete all messages