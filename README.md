# Sports Academies Management System

Welcome to the Sports Academies Management System! This project is designed to manage the operations of sports academies, including athlete registration, subscription management, reservation handling, and payment processing. 

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Class Structure](#class-structure)
- [Price List](#price-list)
- [Installation](#installation)
- [Usage](#usage)
- [Class Diagram](##class-diagram)

## Project Overview

The Sports Academies Management System is a comprehensive solution to streamline the administrative tasks of sports academies. It enables administrators to register and manage athletes, coaches, sports, facilities, reservations, subscriptions, and payments efficiently.

## Features

- **Athlete Management**: Register and update athlete details.
- **Subscription Management**: Manage subscriptions for training programs.
- **Reservation Management**: Handle weekly reservations for training programs.
- **Payment Processing**: Record payments for registrations and subscriptions.
- **User Interface**: Intuitive main window for easy navigation and operations.

## Class Structure

The project includes the following classes and interfaces:

1. **User**: A base class for common fields (unique ID, first name, last name, gender, date of birth, contact information).
2. **Athlete**: Extends `User` with additional fields: `professional` and `experience level`.
3. **Coach**: Extends `User` with additional fields: `Sport` and `degrees`.
4. **Sport**: Includes fields `name` and `professionalOnly`.
5. **Facility**: Includes fields `name` and `maxCapacity`.
6. **TrainingProgram**: Includes fields `unique ID`, `Sport`, `Facility`, `Coach`, `minExperienceLevel`, `reservationRequired`, `participantGender`, `duration`, and `dayOfWeek`.
7. **TrainingProgramReservation**: Includes fields `unique ID`, `Athlete`, `TrainingProgram`, and `reservationDate`.
8. **Subscription**: Implements `PriceList` and includes fields `unique ID`, `Athlete`, `TrainingProgram`, and `monthlyCost`.
9. **Enrollment**: Implements `PriceList` and includes fields `unique ID`, `Athlete`, `date`, `cost`, and `discountPercentage`.
10. **Payment**: Implements `PriceList` and includes fields `unique ID`, `date`, `paymentMethod`, `relatedEntity`, and `totalCost`.
11. **PriceList**: Interface with method `calculateTotalPrice()`.

## Price List

### Registration Price List
- Regular athlete: €50
- Professional athlete: €20

### Subscription Discount Percentage
- Levels 1-2: 10%
- Levels 3-4: 20%
- Level 5: 30%
- Additional discount for professional athletes: 20%

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/anas-farooq8/SportsAcademiesManagement.git
    ```
2. Navigate to the project directory:
    ```bash
    Open in intellij idea.
    ```
3. Build the project using your preferred IDE or build tool.
    ```bash
    Must have JavaFx.
    Just run the app.java file.
    ```

## Usage

1. Run the main class to start the application.
2. Use the main window to navigate to different sections:
    - **Athlete Management**: Register and update athlete details.
    - **Subscription Management**: Manage subscriptions and payments.
    - **Reservation Management**: Create and cancel reservations.

![Screenshot 2024-07-11 090358](https://github.com/anas-farooq8/SportsAcademiesManagement/assets/150327092/4ccbc320-40da-4418-a960-ad7c5110669c)


## Class Diagram


# Demo
* Main Window:
![1](https://github.com/anas-farooq8/SportsAcademiesManagement/assets/150327092/9fb50d58-9d94-4dbf-8090-11c4b9f281f8)

* Athlete Management Window:
![2](https://github.com/anas-farooq8/SportsAcademiesManagement/assets/150327092/e6024c55-d660-4d2a-b449-17c96cd0c716)

* Subscription Management Window:
![3](https://github.com/anas-farooq8/SportsAcademiesManagement/assets/150327092/2d0f10a1-10bd-4bf5-a2f6-45656d19acc1)

* Reservation Management Window:
![4](https://github.com/anas-farooq8/SportsAcademiesManagement/assets/150327092/83e203d0-903c-4e21-b286-f7a793d1f420)

# Video
https://github.com/anas-farooq8/SportsAcademiesManagement/assets/150327092/972a48c2-b170-43bd-98f3-f258a7c6c1f4

