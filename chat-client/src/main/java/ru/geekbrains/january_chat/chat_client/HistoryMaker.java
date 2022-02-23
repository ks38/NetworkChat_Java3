package ru.geekbrains.january_chat.chat_client;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryMaker {
    private static final int HISTORY_SIZE = 100;
    private static final String HYSTORY_PATH = "chat-history/";
    private String login;
    private File history;

    public HistoryMaker(String login) {
        this.login = login;
        this.history = new File(HYSTORY_PATH + "history_" + login + ".txt");
        if (!history.exists()) {
            File filePath = new File(HYSTORY_PATH);
            filePath.mkdir();
        }
    }

    public List<String> readHistory() {
        if (!history.exists()) {
            return Collections.singletonList("Previous history wasn't found");
        }
        List<String> result = null;
        if (history.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(history))) {
                String historyString;
                ArrayList<String> historyStrings = new ArrayList<>();
                while ((historyString = reader.readLine()) != null) {
                    historyStrings.add(historyString);
                }
                if (historyStrings.size() <= HISTORY_SIZE) {
                    result = historyStrings;
                }
                if (historyStrings.size() > HISTORY_SIZE) {
                    int firstIndex = historyStrings.size() - HISTORY_SIZE;
                    result = new ArrayList<>(HISTORY_SIZE);
                    for (int counter = firstIndex - 1; counter < historyStrings.size(); counter++) {
                        result.add(historyStrings.get(counter));

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("History for " + result.size());
        return result;
    }

    public void writeHistory(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(history, true))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
