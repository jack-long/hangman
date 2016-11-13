# network-programming

The server chooses a word from a dictionary, and the client (the player) tries to guess the word chosen by the server by suggesting letters (one letter at a time) or a whole word.  The client is given a certain number of attempts (failed attempts) when it may suggest a letter that does not occur in the word. If the client suggests a letter that occurs on the word, the server places the letter in all its positions in the word; otherwise the counter of allowed failed attempts is decremented. At any time the client is allowed to guess the whole word. The client wins when the client completes the word, or guesses the whole word correctly. The server wins over when the counter of allowed failed attempts reaches zero. The client and the server communicate by sending messages over a TCP connection according to the following protocol. The server should compute the total score of games using a score counter: if a client wins the score counter is incremented, if the client loses the score counter is decremented.

If the client (player) wants to start a new game it sends a special "start game" message to the server. The server randomly chooses a word from a dictionary, sets the failed attempt counter to the number of allowed failed attempts, and replies with a message that includes a row of dashes (a current view of the word), giving the number of letters in the chosen word (e.g. "----------" for the word "programming"), and the number of allowed failed attempts (e.g. 10).
 If the client (player) wants to suggest a letter or the whole word, it sends the letter or the guessed word to the server. The server replies as follows, depending on the word it has chosen and the client's guess.
If the client guesses the whole word correctly, the server sends a congratulation message together with the word and the total score (see below);

If the letter guessed by the client occurs in the word and the client has completed the entire word, the server sends a congratulation message together with the word and the total score;e
If the letter guessed by the client occurs in the word and the has not completed yet the whole word, the server sends to the client the current view of the word with the letter placed  in all its correct positions and the current value of the failed attempts counter. For example, if the client suggest "g" then the current view of the word is "---g------g", if the client then suggest "m" then the current view of the work becomes "---g--mm--g";
If the letter guessed by the client does not occur in the word or the client guesses the whole word incorrectly, the server decrements the failed attempt counter and, depending on the counter's value, sends either the current view of the word together with the value of the failed attempt counter (if the counter > 0), or a "gave over; you loose" message together with the total score (if the counter == 0).
The client G(UI) should display a current view of the word, the current value of the failed attempts counter, and the current value of the total score. 
As a source of words for the server, the server can use an online dictionary in /usr/dict/words under Solaris or Linux. The file is also available here, words.txt. The file contains 25,143 words (and is used by the Unix spell command).
Problem 2. The Rock/Scissors/Paper Game
Develop a distributed peer-to-peer application for the multi-player rock/scissors/paper game. (see a description in Wikipedia).


Each player runs a Java application (a peer) with a (graphical) user interface that allows to play the game and show scores. Using (G)UI, each player chooses one of rock, scissors, or paper. Then the players (peers) communicate their choices to each other in order to compare the choices to see who "won." Rock smashes scissors, scissors cut paper, and paper covers rock. Points are awarded as follows. Assume n > 1 players. Award m players (n - m) points each if they choose the same gesture and beat the other (n - m) players. This rule implies that if all n players choose the same gesture (i.e. if m == n), then points are not awarded; if a player beats all the others (i.e. if m == 1), then the winner is awarded (n -1) points. Then the players play another game. After each game (G)UI shows the score of the game and the total scores. You do not need to provide an advanced and complicated (G)UI, try to make it simple. 


Use one peer process for each player; do not use an additional coordinator process. The peers should communicate with each other using either TCP sockets (see Socket and ServerSocket classes) or datagram (UDP) sockets. Your program should allow at least two players to play the game.


Optionally, you can implement this game as a set of applets interacting with each other via a relay server that resides on the host from which the applets were downloaded (i.e. the host that runs the Web server). The applets can open and use a TCP connection to the server from which the applet has been loaded.