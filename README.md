![](https://img.shields.io/github/license/maxmielchen/UntisCalender?style=flat-square)
![](https://img.shields.io/github/repo-size/maxmielchen/UntisCalender?style=flat-square)
![](https://img.shields.io/github/actions/workflow/status/maxmielchen/UntisCalender/docker-image.yml?style=flat-square)
![](https://img.shields.io/github/actions/workflow/status/maxmielchen/UntisCalender/docker-publish.yml?label=publish&style=flat-square)
![Downloads](https://img.shields.io/github/downloads/maxmielchen/UntisCalender/total?style=flat-square)

## UntisCalender
UntisCalender is a software written in Java that host's your Timetable via iCal.

## Usage

Install Docker on Ubuntu
```Bash
sudo apt-get remove docker docker-engine docker.io containerd runc
sudo apt-get update
sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
sudo mkdir -m 0755 -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```



Create instance
```Bash
docker pull ghcr.io/maxmielchen/untiscalender:main
docker run \
    -e TIMEZONE="Germany/Berlin" \
    -e ROOM=Room \
    -e TEACHER=Teacher \
    -e SUMMARY=School \
    -e USERNAME=your_username \
    -e PASSWORD=your_password \
    -e SERVER=niobe.webuntis.com \
    -e SCHOOL=your_school \
    -e TOKEN=secret \
    -p 80:8080 \
    ghcr.io/maxmielchen/untiscalender:main
```

iCal Link
```http
https://host:port/ical?token=secret
```

## Roadmap
> 1.0.4 Set Europe/Berlin as default Timezone

> 1.0.3 Add option to change Timezone

> 1.0.2 Fix token bug

> 1.0.1 Save version, with some security settings and env variables

> 1.0.0 Unsafe version, but already working

