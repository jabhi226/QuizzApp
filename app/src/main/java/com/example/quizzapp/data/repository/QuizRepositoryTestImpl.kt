package com.example.quizzapp.data.repository

import com.example.quizzapp.data.network.models.QuestionV1Response
import com.example.quizzapp.domain.entites.QuizModel
import com.example.quizzapp.domain.repository.QuizRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuizRepositoryTestImpl : QuizRepository {

    override suspend fun getQuizQuestions(): ArrayList<QuizModel> {
        val apiResponseString =
            """[{"category":"history","correctAnswer":"Sputnik","difficulty":"medium","id":"622a1c3d7cc59eab6f951c78","incorrectAnswers":["Endeavour","Landsat","Terra"],"isNiche":false,"question":{"text":"What was the name of the first artificial satellite to be launched into space?"},"regions":[],"tags":["history"],"type":"text_choice"},{"category":"music","correctAnswer":"Rihanna","difficulty":"medium","id":"622a1c357cc59eab6f94fe95","incorrectAnswers":["Drake","Nicki Minaj","Ricky Martin"],"isNiche":false,"question":{"text":"Which singer released the song \u0027Te Amo\u0027?"},"regions":[],"tags":["songs","music"],"type":"text_choice"},{"category":"film_and_tv","correctAnswer":"\"You motorboatin’ son of a b****!\"","difficulty":"medium","id":"622a1c347cc59eab6f94fbd8","incorrectAnswers":["\"I could do this all day\"","\"I’m the guy who does his job. You must be the other guy.\"","\"I\u0027m gonna make him an offer he can\u0027t refuse.\""],"isNiche":false,"question":{"text":"Which of these quotes is from the film \u0027Wedding Crashers\u0027?"},"regions":[],"tags":["quotes","film","film_and_tv"],"type":"text_choice"},{"category":"science","correctAnswer":"Buck","difficulty":"medium","id":"622a1c3a7cc59eab6f951052","incorrectAnswers":["Doe","Colt","A Johnny"],"isNiche":false,"question":{"text":"Which of these is a name for a male deer?"},"regions":[],"tags":["science"],"type":"text_choice"},{"category":"music","correctAnswer":"Metallica","difficulty":"hard","id":"622a1c397cc59eab6f950c82","incorrectAnswers":["Alice Cooper","The Pussycat Dolls","Three 6 Mafia"],"isNiche":false,"question":{"text":"Which American heavy metal band released the studio album \u0027Hardwired… to Self-Destruct\u0027?"},"regions":[],"tags":["music"],"type":"text_choice"},{"category":"society_and_culture","correctAnswer":"Oxford","difficulty":"medium","id":"64824eaa7778562fd76a9664","incorrectAnswers":["Cambridge","Bristol","London"],"isNiche":false,"question":{"text":"Which British city gives its name to a type of laced shoe as well as a shirting fabric?"},"regions":[],"tags":["fashion","society_and_culture","clothing","cities","uk"],"type":"text_choice"},{"category":"science","correctAnswer":"A glider","difficulty":"medium","id":"645cb0ee602ff42ca52fb41a","incorrectAnswers":["A leaper","A flyer","A hanger"],"isNiche":false,"question":{"text":"What name is often used to refer to a type of flying possum?"},"regions":[],"tags":["animals","marsupials","biology","science"],"type":"text_choice"},{"category":"food_and_drink","correctAnswer":"Poland","difficulty":"hard","id":"624c60ee50d1a5e051325a7d","incorrectAnswers":["Belgium","Brunei","Japan"],"isNiche":false,"question":{"text":"Where in the world would you most expect to be served Bigos?"},"regions":[],"tags":["food","food_and_drink"],"type":"text_choice"},{"category":"science","correctAnswer":"The fetus","difficulty":"medium","id":"622a1c377cc59eab6f950539","incorrectAnswers":["The future","Feet","Fetishes"],"isNiche":false,"question":{"text":"What is Fetology the study of?"},"regions":[],"tags":["science","words"],"type":"text_choice"},{"category":"science","correctAnswer":"Iron","difficulty":"hard","id":"622a1c3a7cc59eab6f951035","incorrectAnswers":["Copper","Gold","Nickel"],"isNiche":false,"question":{"text":"What is the heaviest element that can be formed by regular fusion reactions in the core of a star?"},"regions":[],"tags":["physics","chemistry","science"],"type":"text_choice"}]"""
        val list: ArrayList<QuestionV1Response> = (Gson().fromJson(
            apiResponseString,
            object : TypeToken<ArrayList<QuestionV1Response>>() {}.type
        ))

        return ArrayList<QuizModel>().apply {
            addAll(list.map { it.getDomainModel() })
        }

        /*
        val l = arrayListOf<QuizModel>()
        for (i in 0..9) {
            val correctAnswer = Random(0).nextInt(0, 3)
            val options = arrayListOf<Option>()
            for (j in 0..3) {
                val item = Option(
                    "$j",
                    "$j",
                    correctAnswer == j
                )
                options.add(item)
            }
            val correctAnswerPosition =
                options.map { it.optionValue }.indexOf(correctAnswer.toString())

            l.add(
                QuizModel(
                    quizId = i.toString(),
                    question = "question?.text.toString()",
                    options = options,
                    correctAnswerPosition = correctAnswerPosition
                )
            )
        }
        return l
         */
    }
}