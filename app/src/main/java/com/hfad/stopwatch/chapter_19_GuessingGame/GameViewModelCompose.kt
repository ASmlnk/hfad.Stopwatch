package com.hfad.stopwatch.chapter_19_GuessingGame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModelCompose: ViewModel() {
    private val words = listOf("Android", "Activity", "Fragment")
    private val secretWord = words.random().uppercase()         //слово которое нужно угадать,

    // uppercase приводит все буквы к нижнему регистру
    private val _secretWordDisplay =
        MutableLiveData<String>()    //Вид в котором слово отображается на эеране
    val secretWordDisplay: LiveData<String>
        get() = _secretWordDisplay

    private var correctGuesses = ""                              //количество правельных ответов

    private val _incorrectGuesses = MutableLiveData("")     //количество неправельных ответов
    val incorrectGuesses: LiveData<String>
        get() = _incorrectGuesses

    private val _livesLeft = MutableLiveData(8)             //количество оставшихся жизней
    val livesLeft: LiveData<Int>
        get() = _livesLeft

    private val _gameOver = MutableLiveData(false)
    val gameOver: LiveData<Boolean>
        get() = _gameOver

    init {
        //определить в каком виде отображается загадоное слово, и обновить экран
        _secretWordDisplay.value = deriveSecretWordDisplay()
    }

    //Здесь создается строка с формой, в которой загаданное слово должно отображаться на экране.
    private fun deriveSecretWordDisplay(): String {
        var display = ""
        secretWord.forEach {
            /*Вызываем checkLetter для каждой буквы secretWord и присоединяем возвращаемое
           значение к переменной display*/
            display += checkLetter(it.toString())
        }
        return display
    }

    /*Проверяет, содержит ли загаданное слово букву, введенную пользователем.
    Если содержит, то возвращается буква, а если нет, возвращается «_»*/
    private fun checkLetter(str: String) = when (correctGuesses.contains(str)) {
        true -> str
        else -> "_"
    }

    /*Вызывается каждый раз, когда пользователь вводит предположение.*/
    fun makeGuess(guess: String) {
        if (guess.length == 1) {
            if (secretWord.contains(guess)) {
                /*Для каждого правильного предположения обновить correctGuesses и secretWordDisplay*/
                correctGuesses += guess
                _secretWordDisplay.value = deriveSecretWordDisplay()
            } else {
                /*Для каждого ошибочного предположения обновить incorrectGuesses и livesLeft*/
                _incorrectGuesses.value += "$guess"
                _livesLeft.value = _livesLeft.value?.minus(1)
            }
            if (isWon() || isLost()) _gameOver.value = true
        }
    }

    //Пользователь выиграл, если загаданное слово совпадает с secretWordDisplay.
    private fun isWon() = secretWord.equals(secretWordDisplay.value, true)

    /* Пользователь проиграл, если у него кончились жизни*/
    private fun isLost() = livesLeft.value ?: 0 <= 0

    /*wonLostMessage() возвращает строку, которая сообщает, выиграл или
    проиграл пользователь и какое слово было загадано.*/
    fun wonLostMessage(): String {
        var message = ""
        if (isWon()) message = "You won!"
        else if (isLost()) message = "You lost!"
        message += "The word was $secretWord."
        return message
    }

    fun finishGame() {
        _gameOver.value = true
    }
}