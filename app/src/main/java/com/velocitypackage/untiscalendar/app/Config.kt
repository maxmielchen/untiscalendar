package com.velocitypackage.untiscalendar.app

object Config
{
    var timezone : String = System.getenv("TIMEZONE")
    var defaultRoomAlias : String = System.getenv("ROOM")
    var defaultTeacherAlias : String = System.getenv("TEACHER")
    var defaultSummary : String = System.getenv("SUMMARY")
    var username : String = System.getenv("USERNAME")
    var password : String = System.getenv("PASSWORD")
    var server : String = System.getenv("SERVER")
    var school : String = System.getenv("SCHOOL")
    var ssl : Boolean = System.getenv("SSL") == "true"

    fun getURL() : String
    {
        if (ssl) return "https://${server}" else return "http://${server}"
    }

    var token : String = System.getenv("TOKEN")
}