package org.example;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;


public class App {
    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot("sfvnkjfnBot", "5150552312:AAE5KwxvCt3rQtLOfTrlW8zpK2bJa8RsNNA");
        telegramBotsApi.registerBot(bot);
    }
}
