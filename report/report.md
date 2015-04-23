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
