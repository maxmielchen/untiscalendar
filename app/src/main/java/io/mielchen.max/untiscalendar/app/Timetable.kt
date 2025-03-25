package io.mielchen.max.untiscalendar.app

import org.bytedream.untis4j.Session
import org.bytedream.untis4j.responseObjects.Timetable

fun getUserSpecificTable() : Timetable
{
    val session = Session.login(Config.username, Config.password, Config.getURL(), Config.school)
    session.useCache(false)
    val timetable = session.getTimetableFromPersonId(session.currentSchoolYear.startDate, session.currentSchoolYear.endDate, session.infos.personId)
    session.logout()
    return timetable
}