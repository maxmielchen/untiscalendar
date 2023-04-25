![](https://img.shields.io/github/license/maxmielchen/UntisCalendar?style=flat-square)
![](https://img.shields.io/github/repo-size/maxmielchen/UntisCalendar?style=flat-square)
![](https://img.shields.io/github/actions/workflow/status/maxmielchen/UntisCalendar/docker-image.yml?style=flat-square)
![](https://img.shields.io/github/actions/workflow/status/maxmielchen/UntisCalendar/docker-publish.yml?label=publish&style=flat-square)

# UntisCalendar 📆

UntisCalendar is a software written in Kotlin that serves as a host for your Untis Timetable via iCal. With UntisCalendar, you can easily access your timetable from any device or service that supports iCal. 

## Features

✅ Sync your Untis timetable with **iCal**, **Google Calendar** or **Microsoft Outlook**\
✅ View your timetable on any device or service that supports iCal

## Requirements

- 2GB RAM 
- Preinstalled **Docker**

## Installation

To install UntisCalendar, follow these simple steps:

1. Pull image:
```bash
docker pull ghcr.io/maxmielchen/untiscalendar:latest && \
docker tag ghcr.io/maxmielchen/untiscalendar:latest untiscalendar:latest
```

2. Start server:
```Bash
docker run \
    -e TIMEZONE="Europe/Berlin" \
    -e ROOM=Room \
    -e TEACHER=Teacher \
    -e SUMMARY=School \
    -e USERNAME=your_username \
    -e PASSWORD=your_password \
    -e SERVER=niobe.webuntis.com \
    -e SCHOOL=your_school \
    -e TOKEN=secret \
    -p 80:8080 \
    untiscalendar:latest
```
> The variables 'ROOM', 'TEACHER' and 'SUMMARY' are there if you want them in another language, because then they will be translated into the language or word you specify!!!

3. Try it
Enter the link below in a search engine and if a file is now downloaded then you have done everything correctly.

http://HOST:80/ical?token=SECRET

And now you can take this link and embed it in Google calendar or Microsoft Outlook.

## Usage

1. Prepare your link:
http://HOST:80/ical?token=SECRET

2. Add calendar to your existing calendar
Here are a few articles that will show you how to add the calendar you've just created to your existing calendar
- [Google Calendar](https://support.google.com/calendar/answer/37100)
    > You have to go down to "Use a link to add a public calendar"
- [Microsoft Outlook](https://support.microsoft.com/en-us/office/import-calendars-into-outlook-8e8364e1-400e-4c0f-a573-fe76b5a2d379)
    > You have to go down to "Add internet calendars"

## Contributing

If you would like to contribute to UntisCalendar, please fork the repository and submit a pull request with your changes. We welcome all contributions!

## License

This project is licensed under the Apache-2.0 License. See the [LICENSE](LICENSE) file for more information.

