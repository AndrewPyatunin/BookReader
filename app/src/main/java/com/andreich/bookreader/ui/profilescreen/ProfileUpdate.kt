package com.andreich.bookreader.ui.profilescreen

import ru.tinkoff.kotea.core.dsl.DslUpdate
import ru.tinkoff.kotea.core.dsl.DslUpdate.*
import com.andreich.bookreader.ui.profilescreen.ProfileEvent.*

class ProfileUpdate : DslUpdate<ProfileState, ProfileEvent, ProfileCommand, ProfileNews>() {

    override fun NextBuilder.update(
        event: ProfileEvent
    ) {
        when (event) {
            is ProfileCommandResultEvent.DataSaved -> {
                handleCommandResultEvent(event)
            }
            is ProfileCommandResultEvent.ShowError -> {
                handleCommandResultEvent(event)
            }
            is ProfileCommandResultEvent.SignOut -> {
                handleCommandResultEvent(event)
            }
            is ProfileUiEvent.ChangeName -> {
                handleUiEvent(event)
            }
            is ProfileUiEvent.ChooseImage -> {
                handleUiEvent(event)
            }
            is ProfileUiEvent.EditData -> {
                handleUiEvent(event)
            }
            is ProfileUiEvent.SaveNewData -> {
                handleUiEvent(event)
            }
            is ProfileUiEvent.SignOut -> {
                handleUiEvent(event)
            }
        }
    }

    private fun NextBuilder.handleUiEvent(event: ProfileUiEvent) {
        when (event) {
            is ProfileUiEvent.ChangeName -> {

            }
            is ProfileUiEvent.ChooseImage -> {
                news(ProfileNews.OpenGallery)
            }
            is ProfileUiEvent.EditData -> {
                news(ProfileNews.EditMode)
            }
            is ProfileUiEvent.SaveNewData -> {
                commands(ProfileCommand.SaveNewData(event.user))
            }
            is ProfileUiEvent.SignOut -> {
                commands(ProfileCommand.SignOut)
            }
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: ProfileCommandResultEvent) {
        when (event) {
            is ProfileCommandResultEvent.DataSaved -> {
                state { state.copy(user = event.user) }
                news(ProfileNews.SaveSuccess)
            }

            is ProfileCommandResultEvent.SignOut -> {
                news(ProfileNews.SignOut)
            }

            is ProfileCommandResultEvent.ShowError -> {
                news(ProfileNews.ShowError(event.message))
            }
        }
    }
}