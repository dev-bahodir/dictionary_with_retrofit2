package dev.bahodir.retrofitapifirsttask.user

import java.io.Serializable

data class UserOneItem(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val word: String
): Serializable