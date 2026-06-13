package com.andreich.bookreader.ui.profilescreen

import com.andreich.bookreader_domain.model.User

sealed interface ProfileCommand {

    object SignOut : ProfileCommand

    class SaveNewData(val user: User) : ProfileCommand
}