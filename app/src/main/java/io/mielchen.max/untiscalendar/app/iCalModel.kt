package io.mielchen.max.untiscalendar.app

import net.fortuna.ical4j.model.*
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
    calendar.properties.add(ProdId("-//Max Mielchen//UntisCalender 1.0//EN"))
    calendar.properties.add(Version.VERSION_2_0)
    calendar.properties.add(CalScale.GREGORIAN)
    calendar.components.add(TimeZoneRegistryFactory.getInstance().createRegistry().getTimeZone(Config.timezone).vTimeZone)
    for (lesson in this)
    {
        if (lesson.code == UntisUtils.LessonCode.CANCELLED) continue
        var name = Config.defaultSummary
        try
        {
            name = lesson.subjects.longNames[0]
        } catch (_ : Exception) { }
        val event = VEvent(
            DateTime(Date.from(lesson.startTime.atDate(lesson.date).atZone(ZoneId.of(Config.timezone)).toInstant())),
            DateTime(Date.from(lesson.endTime.atDate(lesson.date).atZone(ZoneId.of(Config.timezone)).toInstant())),
            name.replaceUmlaute()
        )
        try
        {
            event.properties.add(Description("${Config.defaultRoomAlias.replaceUmlaute()}: ${lesson.rooms[0].longName.replaceUmlaute()} \n${Config.defaultTeacherAlias.replaceUmlaute()}: ${lesson.teachers[0].fullName.replaceUmlaute()}"))
        } catch (_ : Exception) { }
        calendar.components.add(
            event
        )
    }
    return calendar
}

fun String.replaceUmlaute(): String {
    val umlauteMap = mapOf(
        "ä" to "ae",
        "ö" to "oe",
        "ü" to "ue",
        "Ä" to "Ae",
        "Ö" to "Oe",
        "Ü" to "Ue"
    )
    var outputString = this
    umlauteMap.forEach { (umlaut, replacement) ->
        outputString = outputString.replace(umlaut, replacement)
    }
    return outputString
}