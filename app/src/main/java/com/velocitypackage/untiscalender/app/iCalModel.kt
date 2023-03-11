package com.velocitypackage.untiscalender.app

import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.Date
import net.fortuna.ical4j.model.DateTime
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.CalScale
import net.fortuna.ical4j.model.property.Description
import net.fortuna.ical4j.model.property.ProdId
import net.fortuna.ical4j.model.property.Version
import org.bytedream.untis4j.UntisUtils
import org.bytedream.untis4j.responseObjects.Timetable
import java.time.ZoneId

fun Timetable.getCalender() : Calendar
{
    val calendar = Calendar()
    calendar.properties.add(ProdId("-//VelocityPackage//UntisCalender 1.0//EN"))
    calendar.properties.add(Version.VERSION_2_0)
    calendar.properties.add(CalScale.GREGORIAN)
    for (lesson in this)
    {
        if (lesson.code == UntisUtils.LessonCode.CANCELLED) continue
        var name = Config.defaultSummary
        try
        {
            name = lesson.subjects.longNames[0]
        } catch (_ : Exception) { }
        val event = VEvent(
            DateTime(Date.from(lesson.startTime.atDate(lesson.date).atZone(ZoneId.systemDefault()).toInstant())),
            DateTime(Date.from(lesson.endTime.atDate(lesson.date).atZone(ZoneId.systemDefault()).toInstant())),
            name
        )
        try
        {
            event.properties.add(Description("${Config.defaultRoomAlias}: ${lesson.rooms[0].longName} \n${Config.defaultTeacherAlias}: ${lesson.teachers[0].fullName}"))
        } catch (_ : Exception) { }
        calendar.components.add(
            event
        )
    }
    return calendar
}