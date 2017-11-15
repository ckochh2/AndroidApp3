# AndroidApp3
Working on fragments,orientation change,broadcast receiver and permissions
1. Application A1 consists of a single activity containing two fragments.
The ﬁrst fragment consists of a list naming at least 6 Chicago landmarks
(e.g., the Museum of Science and Industry, Wrigley Field, the Lincoln Park Zoo, etc.)
Interactive app users can select one of the landmarks. When this happens, the selected item stays 
highlighted and the second fragment displays the web page of the highlighted item. 

This app also deﬁnes an options menu with atleast two items: (1)exiting A1 and(2)launchingthenext application, A2.
A1 launches A2 by sending a system broadcast. In addition, application A1 maintains an action bar.
The action bar shows the name of the application (your choice) and the overﬂow area. 

2. Application A2 isapicturerepository;picturesaredisplayedinanAndroidGallery. 
Thegalleryshows images from each of the landmarks discussed in application A1.
There should be at least one picture from each landmark contained in A1. 
A1 ﬁrst starts the gallery in A2 by sending a global broadcast;
A2 contains a broadcast receiver with appropriate ﬁlters to catch A1’s broadcast. 
When it receives a broadcast, A2 launches the gallery even though A2 was not running before A1 sent the broadcast.
In addition,A2 deﬁnesanew,dangerous-levelpermission. ThebroadcastreceivercontainedinA2 requires that the broadcast sender 
(e.g., application A1) own this permission in order to respond to a broadcast. 
Use a meaningful and unique permission name of your choosing for the new permission. 
Also, make sure that A1 has the permission before sending the broadcast. 
