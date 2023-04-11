package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    // enum состояний пользователя
    private String botToken = "5150552312:AAE5KwxvCt3rQtLOfTrlW8zpK2bJa8RsNNA";
    private String botName = "sfvnkjfnBot";

    private User driver = new User();

    enum UserState {
        START,
        PARAM1, // первый параметр
        PARAM2, // второй параметр
        PARAM3, // третий параметр
        PARAM4,  // четвертый параметр
        END
    }

    // текущее состояние пользователя
    private UserState userState = UserState.START;

    // метод для отправки сообщения
    public Bot(String name, String token) {
        this.botName = name;
        this.botToken = token;
    }

    private void sendMessage(Message message, String text) {
        SendMessage msg = new SendMessage();
        msg.setChatId(message.getChatId().toString());
        msg.setText(text);
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        DecisionTree decisionTree = new DecisionTree(driver); // построение дерева принятия решений

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                switch (userState) {
                    case START:
                        if (message.getText().equals("/start")) {
                            sendMessage(message, "Введите ваш возраст");
                            userState = UserState.PARAM1;
                            break;
                        } else {
                            sendMessage(message, "Введите /start");
                        }
                    case PARAM1: //age going here
                        if (Integer.parseInt(message.getText()) >= 18) {
                            sendMessage(message, "Введите ваш стаж вождения");
                            userState = UserState.PARAM2;
                            driver.setAge(Integer.parseInt(message.getText()));
                        } else {
                            sendMessage(message, "Некорректный возраст. Введите возраст заново");
                        }
                        break;
                    case PARAM2:
                        if (Integer.parseInt(message.getText()) <= driver.getAge() - 18) {
                            sendMessage(message, "Укажите мощность вашего автомобиля (л.с.)");
                            userState = UserState.PARAM3;
                            driver.setDrivingExperience(Integer.parseInt(message.getText()));

                        } else {
                            sendMessage(message, "Некорректный опыт вождения");
                        }
                        break;
                    case PARAM3:
                        if (Integer.parseInt(message.getText()) > 0) {
                            sendMessage(message, "Сколько дтп у вас было за прошлый год ?");
                            userState = UserState.PARAM4;
                            driver.setVehicleHorsePower(Integer.parseInt(message.getText()));
                        } else {
                            sendMessage(message, "Некорректная мощность т. с.");
                        }
                        break;
                    case PARAM4:
                        if (Integer.parseInt(message.getText()) >= 0) {
                            userState = UserState.END;
                            driver.setAccidentsInPrevYear(Integer.parseInt(message.getText()));
                            sendMessage(message, "Ваши данные: \nВозраст: " + driver.getAge()
                                    + "\nСтаж вождения: " + driver.getDrivingExperience()
                                    + "\nМощность тс : " + driver.getVehicleHorsePower()
                                    + "\nДтп в прошлом году: " + driver.getAccidentsInPrevYear());
                            sendMessage(message, decisionTree.getAnswer());
                            sendMessage(message, "Чтобы ввести форму заново, нажмите /again");
                            break;
                        } else {
                            sendMessage(message, "Количество дтп не может быть отрицательным");
                        }
                    case END:
                        if (message.getText().equals("/again")) {
                            sendMessage(message, "Введите возраст");
                            userState = UserState.PARAM1;
                        }

                    default:
                        break;
                }
            }
        }


    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}