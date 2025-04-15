# 📘 BookFlux – Backend

BookFlux is a **personalized reading tracker** designed to help users manage their reading habits, track progress, write reviews, and gain insights into their reading behavior — all powered by a **modern tech stack** and clean design.

> ⚠️ **Work in Progress** – This project is being built as a personal portfolio app during my free time. Contributions and suggestions are welcome!

---

## 🚀 Features (Planned & In Progress)

### ✅ Core Functionality
- User authentication with **OAuth2** (Google, GitHub) via **Spring Security**
- Reactive REST APIs with **Spring WebFlux**
- MongoDB as the **primary NoSQL database**
- CRUD endpoints for:
    - 📚 Books (custom or fetched via external APIs)
    - ⏱️ Reading sessions (pages read, time logged)
    - 📝 Notes & Reviews
- Progress tracking and dashboard-ready data

### 📊 Insights & Analytics
- Personalized statistics per user (books read, streaks, time spent)
- Reading speed estimation
- Genre distribution and charts

### 🔒 Security
- OAuth2 login with JWT-based token authentication
- Secured routes for user-specific data
- Refresh token support (planned)

---

## 🛠️ Tech Stack

| Layer        | Technology                     |
|--------------|--------------------------------|
| Language     | Java 21                        |
| Framework    | Spring Boot 3, Spring WebFlux  |
| Auth         | Spring Security + OAuth2 + JWT |
| Database     | MongoDB (Reactive Driver)      |
| Build Tool   | Maven                          |
| Packaging    | Docker                         |
| Deployment   | AWS ECS + MongoDB Atlas        |
| IaC          | Terraform                      |

---

## ☁️ Deployment (Planned)
- 🟣 **MongoDB Atlas**
- ☁️ **AWS ECS (Fargate)** with Terraform-managed infrastructure
- 📦 **S3** (for assets if needed)
- 🔐 **AWS Secrets Manager** for secure OAuth2 credentials

## 📌 Status
- **🧪 In development phase** — endpoints and infrastructure will evolve. Focus is on building a clean, secure, and scalable reactive backend.

## Want to know more?
- 🚀 **Contact me on** [LinkedIn](https://www.linkedin.com/in/felipe-aneves/)

