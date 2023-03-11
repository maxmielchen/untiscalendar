package com.velocitypackage.untiscalender.app

import org.bytedream.untis4j.Session
import org.bytedream.untis4j.responseObjects.Timetable

val session = Session.login(Config.username, Config.password, Config.getURL(), Config.school)

fun getUserSpecificTable() : Timetable
{
    return session.getTimetableFromPersonId(session.currentSchoolYear.startDate, session.currentSchoolYear.endDate, session.infos.personId)
}