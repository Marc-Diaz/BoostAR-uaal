package com.example.boostar_uaal.core.utils

import android.content.Context
import android.content.pm.PackageManager

class CameraKitConfig() {

    companion object {
        fun getDefaultGroupId(context: Context): String {
            val appInfo = context.packageManager.getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            )
            return appInfo.metaData.getString("com.example.boostar_uaal.DEFAULT_GROUP_ID")!!
        }
    }
}