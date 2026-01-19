# 💺 Cinema Booking Web Application

**A simple web app for cinema ticket booking and basic film and user management.**

> ℹ️ This project is not open source and does not grant any usage rights.
> For usage terms and legal information, see [Code Ownership & Usage Terms](#-code-ownership--usage-terms).

## 📕 Overview
This project is a full-stack cinema web application that demonstrates fundamental web development concepts through a practical, functional example. The application provides a user-friendly interface that allows visitors to browse and reserve movie tickets, while showcasing core backend operations for data management.  

> 📌 The **`resources`** folder contains supporting material such as screenshots and database configuration files.

## ⚡Features

### 🔐 Authentication
 - Secure login system (password hashing) for Customers, Admins, and Content Admins
 - Role-based access control with dynamic navigation

### 🎟️ Customer Functionality
 - Browse available projections
 - Make reservations with film and cinema selection
 - View and cancel existing reservations
 - Receive confirmation feedback for actions

### 🧑‍💼 Admin Functionality
 - View all users
 - Search, update, delete users
 - Register new users with role assignment

### 🎬 Content Admin Functionality
 - Insert and manage films
 - View film listings and projections
 - Assign films to cinemas (create projections)
 - Delete films

## 🧠 Technologies Used
 - MVC architecture
 - Java for Business Logic
 - HTML5
 - MySQL for data persistence
 - Bootstrap for UI

## 🎯 Purpose
The purpose of this application is to deliver a modular, role-based cinema reservation system tailored for administrators, content managers, and customers. It streamlines user management, film curation, and ticket booking through intuitive web interfaces, ensuring efficient operations and a smooth user experience across all roles. **It is developed solely for academic and research purposes.**

## 🧰 Prerequisites
Before building and running this application, ensure you have the following:
 - **Eclipse IDE** for Enterprise Java and Web Developers
 - **MySQL Workbench** (version 8.0 or newer)
 - **MySQL Server** (version 8.0 or newer)
 - **Access to a database schema compatible with the MySQL Workbench** (the database schema is provided <a href="resources/database_config/database_schema.sql">here</a>)
 - **Apache Tomcat** (version 9.0 or newer)

## 📦 Installation

1. **Clone the repository (or download and decompress the ZIP file)**

```bash
git clone https://github.com/kpavlis/cinema-booking-web-app.git
cd cinema-booking-web-app
```
2. **Use** MySQL Workbench to create a new database in your MySQL Server
3. **Open** the provided `FINAL_DATABASE.mwb` file in MySQL Workbench and use the **Forward Engineer** to import the schema into your newly created database 
4. **Create** a new **Dynamic Web Project** in Eclipse IDE for Enterprise Java and Web Developers
5. **Replace** the default `src` folder of the newly created project in Eclipse with the `src` folder contained in this repository
6. **Set** the required attributes in the `context.xml` file to complete the database connection
7. **Run** the project on the installed Tomcat server and create new users via the **Sign Up** page

## 📷 Screenshots

**_App Screens:_**  
> <img width="300" height="145" alt="Cinema_Booking_1" src="resources/screenshots/log_in.png" />
> <img width="300" height="145" alt="Cinema_Booking_2" src="resources/screenshots/customer_make_reservation.png" />
> <img width="300" height="145" alt="Cinema_Booking_3" src="resources/screenshots/customer_reservations.png" />
> <img width="300" height="145" alt="Cinema_Booking_4" src="resources/screenshots/content_admin_available_films.png" />
> <img width="300" height="145" alt="Cinema_Booking_5" src="resources/screenshots/content_admin_delete_films.png" />
> <img width="300" height="145" alt="Cinema_Booking_6" src="resources/screenshots/admin_sign_up_user.png" />

# 🔒 Code Ownership & Usage Terms
This project was created and maintained by:

- Konstantinos Pavlis (@kpavlis)
- Theofanis Tzoumakas (@theofanistzoumakas)
- Michael-Panagiotis Kapetanios (@KapetaniosMP)

🚫 **Unauthorized use is strictly prohibited.**  
No part of this codebase may be copied, reproduced, modified, distributed, or used in any form without **explicit written permission** from the owners.

Any attempt to use, republish, or incorporate this code into other projects—whether commercial or non-commercial—without prior consent may result in legal action.

For licensing inquiries or collaboration requests, please contact via email: konstantinos1125 _at_ gmail.com .

© 2026 Konstantinos Pavlis, Theofanis Tzoumakas, Michael-Panagiotis Kapetanios. All rights reserved.
