package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class DecisionTree {
    @Getter
    @Setter
    String answer;
    Decision<Boolean> condition1 = new Variable<>("Старше 35 лет ?");          // Пользователь старше 50 лет
    Decision<Boolean> condition2 = new Variable<>("Опыт вождения больше 2х лет");  // Пользователь имеет опыт вождения более 3 лет
    Decision<Boolean> condition3 = new Variable<>("Мощность автомобиля больше 200 л.с. ?");
    Decision<Boolean> condition4 = new Variable<>("Больше шести случаев в прошлом году ?");
    Decision<String> trueBranch = new Constant<>("Одобрено страхование");   // Одобрить заявку
    Decision<String> falseBranch = new Constant<>("Страхование не одобрено");  // Отклонить заявку

    Map<String, Boolean> questions = new HashMap<>();

    public DecisionTree(User user) {

        questions.put("Старше 35 лет ?", user.getAge() > 35);
        questions.put("Опыт вождения больше 2х лет ?", user.getDrivingExperience() > 2);
        questions.put("Мощность автомобиля больше 200 л.с. ?", user.getVehicleHorsePower() > 200);
        questions.put("Больше шести страховых случаев в прошлом году ?", user.getAccidentsInPrevYear() > 6);

        Decision<String> condition4Branch = condition4
                .setTrueBranch(falseBranch) // age > 35 && accidents > 6
                .setFalseBranch(trueBranch);  // age > 35 && accidents <= 6

        Decision<String> condition3Branch = condition3
                .setTrueBranch(falseBranch) // age <= 35 && experience <= 2 && vehicle HP > 200
                .setFalseBranch(trueBranch); // age <= 35 && experience <= 2 && vehicle HP <= 200

        Decision<String> condition2Branch = condition2
                .setTrueBranch(trueBranch) // age <= 35 && experience > 2
                .setFalseBranch(condition3Branch); // age <= 35 && experience <= 2

        Decision<String> condition1Branch = condition1
                .setTrueBranch(condition4Branch) // age > 35
                .setFalseBranch(condition2Branch);  // age <= 35


        Decision<String> tree = condition1Branch;
        setAnswer(tree.evaluate(questions));
    }
}