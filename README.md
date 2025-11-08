# Node.js Traffic-Event Aggregator with GraphQL

This project is a high-performance backend system designed to ingest and aggregate traffic event data from road cameras. I
---

##  architecture

The system is composed of two main backend services and a frontend:

1.  **Ingestor Service  :**
     A lightweight Node.js service.
    * **Role:**  Listens for incoming JSON payloads from cameras..
    * **Action:** Transforms JSON into a GraphQL mutation and forwards it to the Main API.
 
2.  **GraphQL API:**
     A Node.js (Express) server that runs the main business logic.
    * **Role:**  Serves the React frontend and handles all data queries/mutations.
    * **Action:** Connects to a PostgreSQL database (via TypeORM) for persistent storage.
3.  **React Dashboard (The "Frontend"):**
    * A React app (e.g., on port 3000) that consumes the GraphQL API..

### Data Flow

<img width="550" height="550" alt="image" src="https://github.com/user-attachments/assets/ea774235-97c4-40e1-bac1-1199006a9ee3" />
<img width="359" height="550" alt="image" src="https://github.com/user-attachments/assets/e86fc248-5a72-4a98-b281-4f30bd65c43b" />


* **Ingestion:** `[FingerPrint Device]` $\rightarrow$ `POST /api/scan` $\rightarrow$ `[Ingestion-Worker (:8090)]` $\rightarrow$ `gRPC` $\rightarrow$ `[Main-API-Service (:8080)]` $\rightarrow$ `[PostgreSQL DB]`
* **Viewing:** `[Browser]` $\rightarrow$ `[React App (:5173)]` $\rightarrow$ `GET /api/attendance` $\rightarrow$ `[Main-API-Service (:8080)]` $\rightarrow$ `[PostgreSQL DB]`

### Frontend Dashboard 
<img width="518" height="349" alt="image" src="https://github.com/user-attachments/assets/daf6cd79-5efe-4f99-8e79-d53105cfc011" />

---

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed:

* **Java (JDK 17+)**
* **Apache Maven**
* **PostgreSQL**
* **Node.js & npm** (for the React frontend)
* **Postman** (or a similar tool for API testing)

---

## 🚀 Setup

Follow these steps to configure and build the project.

### 1. Database Setup (Main API Service)

This service requires a running PostgreSQL database.

1.  Create a new PostgreSQL database (e.g., `attendance_db`).
2.  Navigate to `Main-API-Service/src/main/resources/application.properties`.
3.  Update the file with your database credentials:

    ```properties
    # Spring Boot Web Server
    server.port=8080

    # PostgreSQL Database
    spring.datasource.url=jdbc:postgresql://localhost:5432/attendance_db
    spring.datasource.username=YOUR_POSTGRES_USER
    spring.datasource.password=YOUR_POSTGRES_PASSWORD
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

    # CORS Configuration
    # (This is handled in WebConfig.java)
    ```

### 2. Service Configuration (Ingestion Worker)

This service just needs to know its own port.

1.  Navigate to `Ingestion-Worker/src/main/resources/application.properties`.
2.  Ensure the port is set (and does not conflict with the main API):

    ```properties
    # This server will listen for the "device"
    server.port=8090
    ```

### 3. Build Protobufs

You must compile the `.proto` contract in **both** services to generate the necessary Java classes.

1.  Open a terminal in the `Main-API-Service` root folder and run:
    ```bash
    mvn clean compile
    ```
2.  Open a terminal in the `Ingestion-Worker` root folder and run:
    ```bash
    mvn clean compile
    ```

---

## ▶️ Execution

You must start the services in order.

### Step 1: Run the Main API Service

1.  Open a terminal in the `Main-API-Service` root folder.
2.  Run the application:
    ```bash
    mvn spring-boot:run
    ```
3.  **Look for these lines** in the log to confirm both servers (REST and gRPC) are running:
    * `Tomcat started on port(s): 8080`
    * `Registering gRPC service: IngestService`

### Step 2: Run the Ingestion Worker

1.  Open a **new** terminal in the `Ingestion-Worker` root folder.
2.  Run the application:
    ```bash
    mvn spring-boot:run
    ```
3.  You will see `Tomcat started on port(s): 8090`.

### Step 3: Run the React Dashboard

1.  Open a **new** terminal in your React project's folder.
2.  Install dependencies and start the app:
    ```bash
    npm install
    npm start
    ```

Your system is now fully operational.
* **Main API:** `http://localhost:8080`
* **Ingestion Worker:** `http://localhost:8090`
* **React App:** `http://localhost:5173`

---

## 🧪 How to Test

You can test the entire pipeline using Postman.

### A. Test the Ingestion Pipeline (Simulate a Device)

Send a `POST` request to the **Ingestion Worker**.

* **URL:** `POST http://localhost:8090/api/scan`
* **Body:** `raw` (JSON)
    ```json
    {
      "employeeId": "e-123",
      "deviceId": "d-001",
      "timestamp": "2025-11-08T10:30:00",
      "eventHash": "unique-hash-1"
    }
    ```
* **Result:** You should get a `200 OK`.
* **Test Idempotency:** Send the **exact same request** a second time. It will also return `200 OK`. Check the `Main-API-Service` logs—you will see a "Duplicate event detected" message.

### B. Test the Public API (Filter & Paginate)

Send `GET` requests to the **Main API Service**.

* **Get All (Paginated):**
    `GET http://localhost:8080/api/attendance?page=0&size=5`

* **Filter by Employee:**
    `GET http://localhost:8080/api/attendance?employeeId=e-123`

* **Filter and Sort:**
    `GET http://localhost:8080/api/attendance?employeeId=e-123&sort=timestamp,desc`
