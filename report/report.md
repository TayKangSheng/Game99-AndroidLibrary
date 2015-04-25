#Final Presentation: 
In week 13, you will present the final product to the instructors and the classmates. You are expected to present.
the software development process employed in your project;
a live demo of your app;
and the hiccups and lessons learned through your project.
Final Deliverable: You will deliver a self-contained package of the app, including: 

(A) the code itself; (B) installation and user manuals;
(C) a report detailing what are the system requirements, how are the system designed, how are the system evaluated/tested. In particular, we would like to know how you ensure your implementation is thread/safe. (D) a separate report, delivered by individual group member on contribution of every member in the group.
 Scoring
Presentation and Demonstration of the APP (40%): Score by the classmates (10%); Score by the instructors and/or external judges (30%)
Report/Code (60%): Clear documentation of the system requirements (10%); Clear documentation of the system design (15%); Code structure (15%); Clear documentation on how the system is tested (10%); Clear explanation on how concurrency issues are handled or avoided (10%)

```
Markdown is an easy way to format a document HTML style. 
Wonderfully simple Markdown syntax: https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#headers

paste document into this to view result: http://dillinger.io
We use markdown because it makes display of code(with syntax highlighting!) and quotes extremely easy. 
 ```

#1. Introduction `Anusha`
###Purpose
The purpose of this report is to document the requirements and design process for creating a multiplayer android game for 50.003 Elements of software construction.
###Goals and Objectives
To make full use of the software design practices and methods to handle concurrency that we have learnt during 50.003 Elements of Software Construction to build a multiplayer game that can be played by two or more people at the same time.
###Project Scope
We are to implement our game on the Android Operating System. The game is to be played on mobile devices that run on Android and two or more players should be able to play the game concurrently with one another.
###Operating System
The game will run on Android 4.0 and above. All devices that are running on this version of android or later will be able to support the application. The game is developed using Android Development Tool (ADT) and hosted on an external server.
###Design and Implementation Constraints
The game must run on Android operating System only. All designs of the game are limited by the constraints provided by ADT such as restrictions on the file size and memory. The game must also be able to support two or more users playing the game concurrently. [Hardware restrictions etc]
###Assumptions and dependencies
* The end user must have a mobile phone running Android 4.0 or higher
* The mobile phones used by end users must be connected to the internet throughout the duration of gameplay. 
* [THINK OF MORE]

 
#2. Game – Galactical Real Estate `Anusha`
###2.1 Game Overview
‘Galactical Real Estate’ is a two-player grid based game, which involves conquering as many planets as you can in 90 seconds. Two players will start by choosing their planet avatars before entering the battle arena. Once inside the battle arena, players will compete on a 5 x 7 grid of planets to conquer as many planets as they can within 90 seconds. To conquer a planet, players must tap the grid button with the lowest number in order to change it to their chosen planet avatar. Power-ups will appear randomly on the grid to help players gain an advantage over their opponent. The player with the most number of planets conquered at the end of 90 seconds wins. 
###2.2 Game Design process 
#####Agile Development
Our group adopted the agile development model for the design and implementation of the game. Agile development is a combination of rapid prototyping as well as iterative and incremental development. We started off by outlining system requirements and a brief overview of the system design. we then created the first prototype of our game which consisted only of the grid with numbers. The game was single player at the time as the server-client portion of the code had not been implemented. Our next prototype consisted of the starting page, game grid and results page with the server-client component implemented as well. Two users could now play the game with one another. 

After gathering feedback from our peers on the initial prototypes, we implemented power ups as well as replaced ‘number of lives’ with a health bar to indicate how much of the grid was under each player’s possession. We continued to tweak the features and updated game graphics as well as sound effects, animation and music with subsequent prototypes. Receiving feedback from our instructors as well as peers on each prototype provided us with valuable information such as feedback on how we could improve user experience and prolong gameplay. With each iteration, we also adjusted our initial set of requirements and ensured our design fit the scope of the project. 

###2.3 Game Functionality
#####start Screen

Our game starts out by displaying the application’s name, Galactical Real-estate and introducing the user with the space theme for the first time. We have intended to stick to the minimalistic design philosophy where graphics and layout was concerned. 

#####Avatar Page

Here, players can choose their planet avatar. This will help them differentiate their teritory from their opponent’s. 




#####Waiting for player

Once an avatar is selected, the player waits for another player to select their avatar and join their game.


#####Instruction page

A connection between two players has been established and the game is about to start. There is a reminder for the players that they need to tap the smallest number in order to claim galactical real-estate.




#####Game grid

The game grid is concurrent for both players - they’re playing against each other on the same grid. Each game lasts for 90 seconds with a countdown timer on top to indicate how much time players have left. The health bar on the top of the screen also gives players in indication of their standing throughout the game - whether they’re winning or losing and by how much. 

##### Power Ups



###2.4 User interaction
###progression of prototypes and user experience###
###2.5 Audience
The game is built to entertain users 5 years and up. We believe that this game is fun, addictive and suitable for all ages provided they are capable of handling a mobile devices and know how to count to ten. 

#3. Game Framework `Kang Sheng & Janice `
## 3.1 Basic framework (Interface and stuff) 
Our basic framework is built from scratch entirely based on native android libraries. Building our own basic game framework allows us to be in control of what is needed and allows for optimisation of our code compared to using existing third party game development frameworks such as LibGDX. 
Third party game development frameworks are mostly built taking into account the need for extensive game development such as 3-D rendering which is much more than what we needed for our game. By using a third party game development framework, we will be importing a lot of libraries with many unused methods and classes which are unnecessary. This greatly complicates the development of a simple game with its extensive libraries. 
Therefore we decided to build our own version of Game Framework and implement methods and classes as needed for our game. This keeps the development of our game streamlined and hierarchical instead of having a lot of deadweight code which are not used. 
The approach to making this game takes place in three basic steps. These steps are 
* Creating Interfaces classes
* Implementing Interface classes
* Building the game

These Class and Interface files are created with reference to a framework provided on [www.kilobolt.com](www.kilobolt.com). 

Hence, our Game framework is split into a total of 3 packages.  
1. com.init.framework
2. com.init.framework.implementation
3. com.init.Game99_AndroidLibrary

#### 3.1.1 com.init.framework
This package makes up the basic building blocks for our Game Framework. This package is made up of interface files. All basic methods needed for the game are declared here. These interfaces cover all basic aspects of the game from managing sounds and user-inputs to game screens.

#### 3.1.2 com.init.framework.implementation
In this package, we created our Game Framework by implementing the interfaces created in _com.init.framework_ and creating helper classes from the framework level to streamline implementation. 
On the framework level, the flow of the game is decided with specifications on how different aspects of the game such as sounds, user-input, graphics and Activity Lifecycle come together. As with most game frameworks, one class file will form the backbone of the framework. Our framework AndroidGame which extends the activity and implements the game, ties the graphics, screen, audio, input and a game renderer together. 

#### 3.1.3 com.init.Game99_AndroidLibrary

Class files that implements ‘Galactical Real Estate’ using the framework we built previously goes in this package. The class files in this package is again split into 4 different categories.

- #####Main Activity
NNGame.java is the main and only activity of the game. By initialising NNGame.java which extends AndroidGame which is a subclass of Activity and implements game, it calls the onCreate() method of the Activity Lifecycle and initialises AndroidGraphics, AndroidAudio, AndroidFileIO, AndroidInput, Screen, runnable game renderer AndroidFastRenderView(Elaborated in 3.3) and ties all of them to NNGame game class.  
**_Sequence Diagram or UML Diagram about the initialisation of a game_**

- #####Screens  
Screen_xxx.java(s) are the main controllers of the game flow. With the help of the AndroidFastRenderView(Game Renderer) the screen classes paints and changes game assets accordingly using various methods and classes such as AndroidGraphics etc.

- #####Objects  
Objects_xxx.java(s) make use of OO-Programming to objectify and simplify game objects. Game Objects can either be objects such as buttons that contain its own data parameters; or handlers such as button-handler that adds an extra layer to coordinate all buttons on the screens; or helper such as timer to help keep track of parameters such as time.

- #####utilities  
Utility classes are for example Assets.java, SocketIO.java and utils.java. These classes run in the background to help with the organisation of data, connections or algorithms which is needed to support the functioning of the game. For example, SocketIO.java takes care of the transmission of data between the client and the server while the game interacts with the user.

## 3.2 The power of one activity 

Starting a new activity can be costly process. Every activity manages its own Activity Life-cycle and an on-create method has to be called and all specifications such as screen size have to be redefined. Furthermore, with a new activity, all interfaces have to initialize again and be tied together by the new activity. Having multiple activities also mean that developers have to take into account of the limitations of switching activities such as saving states, calling appropriate activity methods and understanding the use of Android Intents. 

Using just one activity for the game cuts out many unwanted events due to the design of Android API. A important aspect of a game is user interaction, which translate to not irritating the user while executing costly processes which results in Display Lag or Input Lag. Using one activity we can save on costly process and define our game life cycle instead. Costly initialization needs to only be done once when the activity is created. Screen classes will be the game states while switching game states will only need to be done by the main activity in the same activity.

Using only one activity, we can control the state transition of our game. Using different activities for different parts of the game, pressing the back button immediately calls the previous activity which might not be what is intended during certain parts of the game. For example, every time back is pressed at any point in the game, the developer might want always go back to the first main activity. With our own game life cycle, we can specifically implement state transitions as we deem necessary.

## 3.3 Game loop (two threads) 

Our game design is made up of two separate threads running concurrently. Building a real time multi player game, some processes have to be real-time and not limited by different part of the game where extensive context-switching takes place. By running real time processes in a separate thread, we can be assured that the real time portion of the game is not limited by code processing.  

The two threads are (1) **AndroidGame**, main activity of the game; and (2) **AndroidFastRenderView**, game render of the game.

- #####AndroidGame#####
AndroidGame is the the main activity of the game. This thread determines the state flow of the game, the controlling of all game assets and determining how the game interacts with the user. However because a highly interactive game needs to constantly display moving graphics and update its own game parameters while interacting with the user, there is a constant need to check for changing variables and display any changes immediately.  
As all design patterns, there are many methods to do this constant checking and displaying of game data. One example will be creating a method which does a infinite while-loop calling update and paint method in screen class (refer to **Appendix???**) and exiting the loop when necessary. However to simplify this process, we decide to outsource this infinite loop to a separate thread (AndroidFastRenderView) such that the rate of calling of update() and paint() methods can be constant throughout the entire game. Together with simplifying the looping process, multi threading has its advantages and allows for more flexibility which will be further elaborated below. 

- #####AndroidFastRenderView#####
AndroidFastRenderView is the game renderer of our game. AndroidFastRenderView is a runnable file which is run when the activity is first created and remains constantly running in the background for the entire course of the game until the activity is paused or disposed. The advantage of having a separate background thread is that the game loop need not be affected by different code running in the main activity, in other words, it provides a platform that is running and calling methods at constant time. Therefore no matter how extensive or not extensive the code is in different screen classes, the update() and paint() method for every screen will be called at the same rate.  
Having a stable platform is important when dealing with everything that deals with time. AndroidFastRenderView provides accurate delta values taken in by every paint and update methods. (delta is the time lapse from the current method call to the previous method call) Having a stable delta value allow us to keep track of relative timing which is extremely important for implementations such as count-down timer and animation. Essentially a timer should be counting down properly without having long chunks of code affecting its count and animations to be displayed relative to a constant frame/sec throughout every single screen classes. Although keeping track of time in a separate background thread is much more stable than doing it in the main thread, it is limited by the hardware the game is running on. Processing speed of the CPU of different mobile phones allow for different speed of context-switching. However the delta time is still accurate (refer to AndroidFastRenderView.java) given the nature of the delta value just that the rate of calling important methods in the screen classes will be slightly affected.  
However it is important to note that hardware consideration is increasingly less important given cheaper and better hardware made available with technology advancement.

## 3.4 Screen breakdown 

The Screen classes act as states for our game. Different screens serves different functionality and compartmentalize the user experience for the user. 

**_STATE DIAGRAM_??**  

#####3.4.1 Welcome Page (Screen_WelcomePage.java)  
This screen is our game's initial splash screen, showing our "Init Studios" logo and slogan.

The animation displaying the logo on this page is done by incrementing alpha (transparency) of the image file slowly by 5 units every time update() is called and the animation displaying the slogan images are based on time. Animation is manually coordinated to the music background as the animations on this page is minimal hence the use of complicated design patterns is unnecessary.

#####3.4.2 Game Initialization (Screen_Initialize.java) 
This page initializes all essential game data declared in Assets.java into memory such as images and audio. The update method() and paint() method in this screen is not used at all as the next screen is called immediately at the end of the constructor method. Therefore user will not be aware of the existence of this screen at all. 

#####3.4.3 Main Menu (Screen_First.java) 
This is our main menu screen, like every game, the main menu screen is the first page that interacts with the user. This page provides information such as the name of our game to our user. This page consist of a single start button starting the game experience. 

There is no animation for this page but only a button and 

#####3.4.4 Avatar Page (Screen_AvatarChooser.java) 
User picks avatar...

#####3.4.5 Instruction Page (Screen_Instruction.java)
How to play the game...

#####3.4.6 Game Screen (Screen_Game.java) 
Main competition...

#####3.4.7 Results (Screen_Result.java)
Display results of the game...



#4. Server and Client Design
### 4.1 Server design
#### cloud hosting
For this project, we used a remote cloud server hosted on DigitalOcean.com running node.js code. We chose a cloud server for two reasons:
 
1. Easy to test using wireless network;
2. external server is always running without having to start server program running on our PC.

And we didn't use Google game service or other cloud server services because we want to get exposed to the real problems in server design; in addition, with our own server we implemented a comprehensive log of server-client activities which greatly facilitated debugging of both server and client programs.
####Server written in nodejs
![node](./nodejs.png =300x)
>Node.js is a platform built on Chrome's JavaScript runtime for easily building fast, scalable network applications. Node.js uses an **event-driven**, **non-blocking I/O model** that makes it lightweight and efficient, perfect for data-intensive real-time applications that run across distributed devices. (nodejs.org)

That was the official definition for node.js, so how does it actually work?
#####Non-blocking I/O:
For an application as network-intensive as the server, the latency of I/O is a major problem.
Common strategies for concurrency include starting a new thread or forkig a new process for every request serviced.
Nodejs used a different strategy: it implemented an event loop which proved to be superior in performance than multi-threaded concurrency.
An event loop is "an entity that handles and processes external events and converts them into callback invocations".
They refer to the way that async functions are implemented. Node first dispatches the function and then waits for readFile to send it an event that it has completed. While it is waiting node can go check on other things. Inside node there is a list of things that are dispatched but haven't reported back yet, so node loops over the list again and again checking to see if they are finished. After they finished they get 'processed', e.g. any callbacks that depended on them finishing will get invoked.

To compare the performance of event loops and multi-threaded design, let's compare Apache web server and NGINX web server;
Apache is multi-threaded which starts one OS thread for every client request, whereas NGINX implemented an event loop;

![request/second graph](./apache1.png =543x272)

Evidenced by this graph, NGINX server is much faster and more scalable as the number of connections grows;

![memory graph](./apache2.png =543x272)

Let's move on to memory consumption. Apache's memory usage grows much faster than NGINX. 
This illustrated one of the major problems of multi-threaded server-design:
It is highly memory-intensive (context, stack, stack pointer, program counter, general-purpose registers and condition code), and context-switching is by no means cheap. 
#####One example of a callback function:
#####Asynchronous listeners



###4.2 Protocol: WebSockets
![websocket](websocket.png =200x)

WebSocket is a full-duplex, 
#####socketio
##### Android client
![client](./client1.png =700x)




###4.3 callback functions



###4.4 Concurrency issues 
##### click conflict



#5. Challenges faced:
###5.1 Resolving OutOfMemoryError
###5.2 User Authentication
###5.3 maintaining integrity of the grid
###5.5 contingency for grid conflicts (highly unlikely)

#6. Areas of Improvement

#7. Conclusion `Anusha`
This project has been a challenging and fruitful journey. It has presented us with a lot of difficulties and allowed us to utilize the concepts we learnt in 50.003 to overcome these issues. 
We learnt that it is imperative to begin with a design model that suits our team and stick to it in order to optimize efficiency. In our case, we adopted the agile development design model and it worked well for our team as well as this project. 
The UML we worked on at the beginning helped put the entire project into perspective and gave us a better understanding of the direction our project was heading in. 
Overall, this project has helped us apply the theory and concepts we learnt in 50.003 to a fully functioning mobile application. This not only showed us the value of what we learnt, but also helped solidify our understanding of what we learnt in class. 
After 13 weeks of (agile) development, INIT studios is proud to present: Galactical Real Estate!



