# Proyecte_3
This repository contain different projects: 2 android apps, a desktop app coded in c#, a webservice coded in php and the script to initialize the database with some demo data.

#1 Android app
User app for our product. Connects with the webservice to authenticate the user. Once you are authenticated you are granted acces to the different features: search new spotify songs, request them to be added to the list of songs that can be played, select a song to be played, see the queue, the history and the current song and modify user private information and read a barcode in order to earn tokens.

#2 Android app
Admin app. Can resolve either if the songs that are requested have to be accepted or denied. See the queue, the history and the current song, pass, pause and rewind the playing song. Modify the songs that are accepted or not.

#3 C# App
Generates longs and adds them to the DB so the users can read them and earn points.

#3 Webservice
Has all the endpoints to give service to all the apps. Connects to the spotify web api and resolves all the users' demands.
