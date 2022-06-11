package com.example.forage.data

import com.example.forage.R
import com.example.forage.model.SettingsListItem

class SettingsListDataSource{
    fun loadSettings(): List<SettingsListItem> {
        return listOf(
            SettingsListItem(R.string.setting_voice_setting, R.string.setting_voice_setting_description),
            SettingsListItem(R.string.setting_more_setting,R.string.setting_more_setting_description),
            SettingsListItem(R.string.setting_tutorial,R.string.setting_tutorial_description),
            SettingsListItem(R.string.setting_faq_and_user_support,R.string.setting_faq_and_user_description),
            SettingsListItem(R.string.setting_rate_and_share,R.string.setting_rate_and_share_description),
            SettingsListItem(R.string.setting_about,R.string.setting_about_description)
        )
    }
}