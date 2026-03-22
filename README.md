# Project: Mahjong Scoring System

## Description
This application is intended to keep track of the scores in a game of mahjong, in particular the *Hong Kong-Taiwanese Style Mahjong*. In a typical setting, players would use poker chips to complete transactions, or use pen and paper to record every wins and loses. This application is intended to make the process much simpler, allowing players who are using it to better enjoy the fun of the game.

This project is of great interest to me because playing mahjong is one of my hobbies, and I often go to the *UBC Mahjong Club* to play with friends. Although the club already has an online scoring system for Riichi (Japanese) and Hong Kong style, it does not include any other variants such as the *Hong Kong-Taiwanese Style* due to their low popularity. This would be an opportunity for me to learn more about how such a system operates, and also produce a useful application that might be incorporated by the club.

While the application does not implement the actual game, here is a link to the rulebook by the *Hong Kong Mahjong Association* for reference: https://docs.google.com/document/d/1_TNf3YYWCCi5Z-SMssIRSB0dRPjfMCvbkTp5cCjLv9A/edit?usp=sharing

## User Stories
- As a user, I want to be able to create a new account.
- As a user, I want to be able to add a game to the current game list for recording and select the players.
- As a user, I want to be able to select the round end state and input Faan if necessary.
- As a user, I want to be able to view all recorded games in the past.
- As a user, I want to be able to remove a current game.
- As a user, I want to be able to remove an account from the system.
- As a user, I want to be able to see analysis of a player, such as their win rate and average Faan of winning hand.
- As a user, when I select the quit option from the application menu, I want to be reminded to save all current games and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load unfinished games from file.

# Instructions for End User
- You can view the panel that displays all accounts added to the account list by pressing the button labelled "view players in the system" in the main menu.
- You can generate the first required action related to the user story "adding a new account to the account list in the system" by pressing the button labelled "create a new account" in the main menu and typing in a new username in the text field in the panel shown afterwards. Then press "enter" button at the bottom to create the account.
- You can generate the second required action related to the user story "removing a current game" by pressing "view current ongoing games" button, then press "refresh" button and type in the game number for the game you wish to remove. After pressing "enter" button, you will be in the game panel and pressing the "delete" button will prompt you to confirm removing the game.
- You can locate my visual component by pressing the button labelled "view players in the system" in the main menu. There you will see player icons next to the player names.
- You can save the state of my application by pressing "close the application" button and selecting "yes" when prompted.
- You can reload the state of my application by pressing "load unfinished games from file" button in the main menu.