# 🌍 Countries Directory — Industry Placement Project

> A full-stack web application that fetches and displays country data from the [REST Countries API](https://restcountries.com/), featuring real-time search functionality, server-side caching, and a clean responsive UI.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [API Reference](#api-reference)
- [Author](#author)

---

## Overview

This project was developed as part of an industry placement at **Sentura Technologies**. It demonstrates a full-stack architecture where a **Spring Boot** backend acts as a proxy/cache layer between the frontend and the public REST Countries API, while a **React + TypeScript** frontend presents the data in a searchable, user-friendly table.

---

## Tech Stack

### Backend
| Technology | Version |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.11 |
| Spring Web (REST) | — |
| Spring Cache | — |
| Lombok | — |
| Maven | — |

### Frontend
| Technology | Version |
|---|---|
| React | 19 |
| TypeScript | 5.9 |
| Tailwind CSS | 4 |
| Vite | 7 |

---

## Features

- 📡 **Fetches live country data** from the REST Countries API (`name`, `capital`, `region`, `population`, `flag`)
- 🔍 **Real-time search** — filter countries by name with a 300 ms debounce on the frontend
- 🗄️ **Server-side caching** — country data is cached in-memory and automatically refreshed every **10 minutes** to reduce external API calls
- 🌐 **CORS-enabled** REST API, ready to be consumed by any frontend
- 🎨 **Responsive UI** built with Tailwind CSS — flag images, sortable columns, and hover highlights
- 🏗️ **Clean architecture** — Controller → Service → DTO separation on the backend

---

## Project Structure

```
Industry-Placements-Sentura-Technologies-Hasindu-Udara/
├── Backend/                        # Spring Boot application
│   └── src/main/java/com/example/Backend/
│       ├── BackendApplication.java         # Entry point
│       ├── controller/
│       │   └── CountryController.java      # REST endpoints
│       ├── service/
│       │   └── CountryService.java         # Business logic & caching
│       └── dto/
│           └── CountryDTO.java             # Data transfer object (record)
│
└── Frontend/                       # React + TypeScript application
    └── src/
        ├── App.tsx                         # Main component (fetch + search + table)
        ├── main.tsx                        # React entry point
        └── index.css / App.css             # Global styles
```

---

## Getting Started

### Prerequisites

- **Java 21+** — [Download](https://adoptium.net/)
- **Maven 3.9+** (or use the included `mvnw` wrapper)
- **Node.js 18+** — [Download](https://nodejs.org/)
- **npm 9+**

---

### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd Backend
   ```

2. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```
   *(On Windows use `mvnw.cmd spring-boot:run`)*

3. The API will be available at `http://localhost:8080`

---

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd Frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

4. Open your browser at `http://localhost:5173`

> ⚠️ Make sure the **Backend is running** before starting the Frontend, as all country data is fetched from `http://localhost:8080/api/countries`.

---

## API Reference

### Base URL
```
http://localhost:8080
```

### Endpoints

#### `GET /api/countries`
Returns a list of all countries.

**Query Parameters**

| Parameter | Type | Required | Description |
|---|---|---|---|
| `search` | `string` | No | Filter countries by name (case-insensitive, partial match) |

**Example Requests**
```
GET /api/countries
GET /api/countries?search=aus
```

**Response** — `200 OK`
```json
[
  {
    "name": "Australia",
    "capital": "Canberra",
    "region": "Oceania",
    "population": 25687041,
    "flag": "https://flagcdn.com/w320/au.png"
  }
]
```

---

## Author

**Hasindu Udara**
📧 [hasiduudara@gmail.com](mailto:hasiduudara@gmail.com)

---

*Industry Placement — Sentura Technologies*
