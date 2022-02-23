package ru.geekbrains.january_chat.chat_server;

import ru.geekbrains.january_chat.chat_server.auth.DatabaseAuthService;
import ru.geekbrains.january_chat.chat_server.auth.InMemoryAuthService;
import ru.geekbrains.january_chat.chat_server.server.Server;

public class App {
    public static void main(String[] args) {
        //new Server(new InMemoryAuthService()).start();
        new Server(new DatabaseAuthService()).start();
    }
}
