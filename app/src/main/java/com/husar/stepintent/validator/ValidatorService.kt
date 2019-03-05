package com.husar.stepintent.validator

import android.text.TextUtils

class ValidatorService {

    fun isNullorEmpty(input: String): Boolean {
        return TextUtils.isEmpty(input)
    }

    fun isFirstLetterUppercase(input: String): Boolean {
        return input.first().isUpperCase()
    }

    fun minCharacters(input: String): Boolean {
        return input.length >= 3
    }
}