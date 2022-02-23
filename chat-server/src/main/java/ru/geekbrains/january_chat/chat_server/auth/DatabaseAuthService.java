package ru.geekbrains.january_chat.chat_server.auth;

import ru.geekbrains.january_chat.chat_server.entity.User;

public class DatabaseAuthService implements AuthService {

    private ClientsDataBaseService dataBaseService;

    @Override
    public void start() {
        dataBaseService = ClientsDataBaseService.getInstance();
    }

    @Override
    public void stop() {
        dataBaseService.closeConnection();
    }

    @Override
    public String authorizeUserByLoginAndPassword(String login, String password) {
        return dataBaseService.getClientsNameByLoginPass(login, password);
    }

    @Override
    public String changeNick(String login, String newNick) {
        return dataBaseService.changeNick(login, newNick);
    }

    @Override
    public User createNewUser(String login, String password, String nick) {
        return null;
    }

    @Override
    public void deleteUser(String login, String pass) {
    }

    @Override
    public void changePassword(String login, String oldPass, String newPass) {
    }

    @Override
    public void resetPassword(String login, String newPass, String secret) {
    }
}
