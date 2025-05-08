# MySQL Database Setup Guide

This guide will walk you through setting up the `pbl3` MySQL database using XAMPP and phpMyAdmin.

## Steps

1. **Download the SQL file**  
   Get the file named `pbl3.sql`.

2. **Install XAMPP**  
   Download and install XAMPP from [apachefriends.org](https://www.apachefriends.org/index.html).

3. **Start Apache and MySQL**  
   Open the XAMPP Control Panel and click "Start" for both Apache and MySQL.  
   It should look like this:

   ![XAMPP Control Panel](https://i.imgur.com/HEnVoPA.png)

4. **Create a new database**  
   - Open your browser and go to `http://localhost/phpmyadmin`
   - On the left panel, click **New**
   - Name the database `pbl3` and click **Create** (leave all other settings as default)

5. **Import the SQL file**  
   - Select the newly created `pbl3` database
   - Go to the **Import** tab on the top menu  
   - It should look like this:

   ![Import Menu](https://i.imgur.com/PFaAeuE.png)

   - Click **Choose File**, select the downloaded `pbl3.sql`
   - Scroll down and click **Go** to start the import

6. **Check the result**  
   - You should now see all the tables listed in the `pbl3` database  
   - It should look like this:

   ![Database Tables](https://i.imgur.com/pIReu5i.png)
