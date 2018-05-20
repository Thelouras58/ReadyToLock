# ReadyToLock
<mark>Mobile and Wireless Communication Networks Security<br/>
    Lab Assignment 2018, Spring Semester</mark>
<br/><br/>
Entering a gesture-based unlock pattern is still the basic means of first-step user authentication in Android devices. Significant research has been conducted on how to break or circumvent the unlocking mechanism of such devices (see selected reading material). Some of the solutions that achieve good results rely on the use of monitoring mechanisms, e.g. installed software or external cameras. In this assignment, we aim to extract several behavioral characteristics from the users’ interaction with the smartphone screen. Additionally, we will attempt to extract meaningful statistics from the users’ patters and examine how “close” in terms of similarity the behavior of the users is.

This project is an Android app that provides the necessary functionalities to meet its purpose, like:
<ul>
    <li>Add a new user </li>
    <li> Start/Stop the capturing process </li>
    <li> Export data to CSV files </li>
    <li>  Generate statistics </li> 
</ul>
</br>
<h2> Data Loging </h2>
</br>

<ul>
    <li> <b> Raw pattern: </b>< number_of_activated_point; xpoint; ypoint; timestamp; pressure></li>
    <li><b> Sensor data: </b>< timestamp; accel_x; accel_y; accel_z; gyro_x; gyro_y; gyro_z; laccel_x; laccel_y; laccel_z></li>
    <li><b>Pattern metadata:</b>< Username; Attempt_number; Sequence; Seq_length; Time_to_complete; PatternLength; Avg_speed; Avg_pressure; Highest_pressure; Lowest_pressure; HandNum; FingerNum></li>
    <li> <b> Pair metadata: </b> < Username; Attempt_number; Screen_resolution; Pattern_number_A; Pattern_number_B; Xcoord_of_central_Point_of_A; Ycoord_of_central_Point_of_A; Xcoord_of_central_Point_of_B; Ycoord_of_central_Point_of_B; First_Xcoord_of_A; First_Ycoord_of_A; Last_ Xcoord_of_B; Last_Ycoord_of_B; Distance_AB; Intertime_AB; Avg_speeadAB; Avg_pressure ></li>
</ul>
<h2> Statistics</h2>
<ul>
    <li>Long runs </li>
    <li> Closed curves </li>
    <li>Long curves </li>
    <li> Long Edges </li> 
    <li> Short Edges </li> 
    <li> Long Orthogonal edges</li> 
    <li> Short Orthogonal edges </li> 
    <li> Short Orthogonal edges </li> 
    <li>Number frequency </li>
</ul>

