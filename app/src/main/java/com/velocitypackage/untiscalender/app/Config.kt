package com.velocitypackage.untiscalender.app

object Config
{
    var defaultRoomAlias : String = "Room"
    var defaultTeacherAlias : String = "Teacher"
    var defaultSummary : String = "School"

    var username : String = "null"
    var password : String = "null"
    var server : String = "null"
    var school : String = "null"
    var ssl : Boolean = true

    fun getURL() : String
    {
        if (ssl) return "https://${server}" else return "http://${server}"
    }

    var token : String = "secret"
}