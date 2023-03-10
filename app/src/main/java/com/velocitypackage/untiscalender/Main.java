package com.velocitypackage.untiscalender;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import org.bytedream.untis4j.Session;
import org.bytedream.untis4j.UntisUtils;
import org.bytedream.untis4j.responseObjects.Timetable;

import java.time.LocalDate;
import java.time.ZoneId;

import static spark.Spark.get;

public class Main
{
    public static void main(String[] args) {
        get("/api/ical", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            String server = req.queryParams("server");
            String school = req.queryParams("school");
            String from = req.queryParams("from");
            String to = req.queryParams("to");
            Session session = Session.login(username, password, "https://" + server, school);
            Timetable timetable;
            try {
                timetable = session.getTimetableFromPersonId(LocalDate.parse(from), LocalDate.parse(to), session.getInfos().getPersonId());
            } catch (Exception e)
            {
                return e.toString();
            }
            Calendar calendar = new Calendar();
            calendar.getProperties().add(new ProdId("-//VelocityPackage//UntisCalender 1.0//EN"));
            calendar.getProperties().add(Version.VERSION_2_0);
            calendar.getProperties().add(CalScale.GREGORIAN);
            for (Timetable.Lesson lesson : timetable) {
                if (lesson.getCode() == UntisUtils.LessonCode.CANCELLED) continue;
                String name = "School";
                try {
                    name = lesson.getSubjects().getLongNames().get(0);
                } catch (Exception ignore)
                {
                }
                VEvent event = new VEvent(
                        new DateTime(Date.from(lesson.getStartTime().atDate(lesson.getDate()).atZone(ZoneId.systemDefault()).toInstant())),
                        new DateTime(Date.from(lesson.getEndTime().atDate(lesson.getDate()).atZone(ZoneId.systemDefault()).toInstant())),
                        name
                );

                try {
                    event.getProperties().add(new Description(String.format("Room: %s\nTeacher: %s", lesson.getRooms().get(0).getLongName(), lesson.getTeachers().get(0).getFullName())));
                } catch (Exception ignore )
                {
                }

                calendar.getComponents().add(
                        event
                );
            }
            session.logout();
            return calendar.toString();
        });
    }
}
