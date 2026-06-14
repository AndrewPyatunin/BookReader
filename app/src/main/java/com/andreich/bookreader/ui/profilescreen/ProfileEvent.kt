package com.andreich.bookreader.ui.profilescreen

import com.andreich.bookreader_domain.model.User

sealed interface ProfileEvent {

    sealed interface ProfileUiEvent : ProfileEvent {

        object EditData : ProfileUiEvent

        object ChooseImage : ProfileUiEvent

        object ChangeName : ProfileUiEvent

        object SignOut : ProfileUiEvent

        class SaveNewData(val user: User) : ProfileUiEvent
    }

    sealed interface ProfileCommandResultEvent : ProfileEvent {

        class DataSaved(val user: User) : ProfileCommandResultEvent

        class ShowError(val message: String) : ProfileCommandResultEvent

        object SignOut : ProfileCommandResultEvent
    }
}