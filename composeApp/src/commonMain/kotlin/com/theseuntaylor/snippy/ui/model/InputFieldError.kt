package com.theseuntaylor.snippy.ui.model

data class InputFieldError(val shouldShowError: Boolean, val message: String) {
    companion object {
        val Initial = InputFieldError(shouldShowError = false, message = "")
    }
}