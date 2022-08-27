package com.srtapps.chemicalelements.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionGenerator {

    public static  List<List<Object>> multipleChoiceQuestionCreator (List<List<Object>> elementList,
                                                      List<Object> question) {

        List<List<Object>> answerList = new ArrayList<>();

        List<Object> prevAnswer = null;
        for(int i = 0; i < 3; i++) {
            int random = new Random().nextInt(elementList.size());
            while(elementList.get(random) == question || elementList.get(random) == prevAnswer) {
                random = new Random().nextInt(elementList.size());
            }
            prevAnswer = elementList.get(random);
            answerList.add(elementList.get(random));
        }
        answerList.add(question);

        return answerList;

    }

    public static List<List<Object>> questionMaker(int randomSize, List<List<Object>> elementList, List<Object> prevQuestion) {
        int random = new Random().nextInt(randomSize);
        List<List<Object>> question = QuestionGenerator.multipleChoiceQuestionCreator(elementList
                , elementList.get(random));

        while (question.get(3) == prevQuestion) {   //Checking previous question
            random = new Random().nextInt(elementList.size());
            question = QuestionGenerator.multipleChoiceQuestionCreator(elementList
                    , elementList.get(random));
        }

        return question;
    }
}
