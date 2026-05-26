# 📖 Story Seeds — Life Lessons Story Generator

An AI-powered Spring Boot app that generates short moral stories for children based on a life value like Loyalty, Honesty, or Courage.

---

## 🚀 How to Run

### 1. Add your OpenAI API Key
Open `src/main/resources/application.properties` and replace:
```
spring.ai.openai.api-key=YOUR_OPENAI_API_KEY_HERE
```
with your actual key from https://platform.openai.com/api-keys

### 2. Run the app
```bash
mvn spring-boot:run
```

### 3. Open in browser
```
http://localhost:8080
```

---

## ✨ Features

- 🌱 Enter any life value (Loyalty, Courage, Kindness, etc.)
- 🎂 Choose age group: 5–7, 8–10, or 11–13 years
- 🌍 Stories in English, Hindi, or Telugu
- 👶 Personalize with child's name — they become the hero!
- 📋 Copy story with one click
- 🔄 Generate unlimited fresh stories

---

## 🏗️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Spring Boot 3.2 |
| AI | Spring AI + OpenAI GPT-4o mini |
| Frontend | Thymeleaf + Vanilla JS |
| Build | Maven |

---

## 💰 Cost

GPT-4o mini is very affordable:
- ~800 tokens per story
- Roughly **$0.0001 per story** (less than 1 paisa)
- 10,000 stories ≈ ₹8

---

## 📁 Project Structure

```
src/main/java/com/storygen/
├── StoryGeneratorApplication.java   ← Main class
├── controller/
│   └── StoryController.java         ← REST endpoints
├── service/
│   └── StoryService.java            ← AI prompt logic
└── model/
    ├── StoryRequest.java            ← Input model
    └── StoryResponse.java           ← Output model

src/main/resources/
├── templates/index.html             ← UI
└── application.properties           ← Config
```
