# Project Proposal: Battleship

## "*Look at me. I am the captain now.*" - Captain Phillips (2013)

My goal is to build a game that resembles the board game Battleship. Time permitting, I may add new rules and hopefully
give the user control over the shape of the board and/or ships. I want to make this as user-friendly as possible to 
make it so anyone who can play the board game can play my game. I have always been interested in building a game, and
while I have attempted to do so in the past, I have never had the chance to make a game on my own where I have full
creative control. 


Some *possible* additional features:
- give user the ability to custom make ship sizes (with some restraints)
- give the user the ability to create maps to force players to make new strategies
- create a new user action that allows a user to select more than one coordinate per turn


## User Stories:

- As a user, I want to be able to add a new ship to the game 
- As a user, I want to be able to choose which coordinate to scout
- As a user, I want to be able to know the result of scouting a coordinate
- As a user, I want to be able to keep track of how many enemy ships remain

- As a user, I want to be able to save my game
- As a user, I want to be able to load a previously saved game


# Instructions for Grader:

- You can generate the first required event related to adding Xs to a Y by selecting "new game", which prompts the 
player to choose how many ships (X's) they want to add to the board (Y)
- You can generate the second required event related to adding Xs to a Y after choosing the number of ships to add. You 
will be prompted with various options for the properties of each individual ship (X) before adding it to the board (Y)
- You can locate my visual component by playing the game. Each button has a picture that depicts the state of the
coordinate: water for unchecked, splash for a miss, and explosion for a hit.
- You can save the state of my application by using the menu option on Player 1's turn only.
- You can reload the state of my application by typing 'l' in the first alert box.