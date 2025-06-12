_Hoang An Nguyen, 594216_
# PrimeTime 2.0🎬

**PrimeTime** ist eine Webanwendung, die aus einem ganz persönlichen Projekt entstanden ist: dem gemeinsamen Filmkatalog meiner Freundesgruppe. Was früher mühsam in einer Excel-Tabelle gepflegt wurde, wird nun durch eine benutzerfreundliche Web-Anwendung ersetzt.

Link zur Tabelle: https://docs.google.com/spreadsheets/d/1k2SilSEqoWUufsr4XzK0DlEaR9U5IKZ-cKQS-M9YL6g/edit?gid=0#gid=0

## ⭐ Features
### 🍿Filmlisten verwalten
- Zugriff auf TMDB zur automatisierten Filmdaten-Erfassung
- Filme mit Status versehen: Plan to Watch, Completed, Dropped
- Ratings, Datum, Plattform, Tags hinzufügen (Ratings & Tags auch nachträglich möglich)
- Ratings eindeutig pro Mitglied und Film gespeichert
- Sortier- und Filterfunktionen per Watchlist

### 📊 Auswertungen & Diagramme
Auswertungen von Ratings + Monatsrückblick + Verteilungen von Genre, Plattformen, etc. + co:

| Name                                    | Beschreibung                                                                                               |
  |-----------------------------------------|------------------------------------------------------------------------------------------------------------|
| **Mean Scores**                         | Berechnung der Durchschnitte pro Film/Person/Genre + Overall                                               |
| **Status-Verteilung**                   | Anteile von "Plan to Watch", "Completed", "Dropped" im Balkendiagramm                                      |
| **"Monthly Recap"**                     | Auswertung des vergangenen Monats als Rückblick (Anzahl der Filme, Spielzeit, Top Genre, Best/Worst Rated) |
| **"Ratings over time"**                 | Bewertungen aller Member + kumulierter Durchschnitt im Linien/Scatter-Diagramm                             |
| **Genre-Distribution**                  | Anteile aller Genre + Durchschnittsbewertung pro Genre im Balkendiagramm                                   |
| **Platform-Distribution**               | Anteile aller Plattformen im Donut Chart                                                                   |
| **"Who has spent the most time here?"** | Anteile aller gesehenen Filme + Summe der Spielzeit pro Member im Balkendiagramm                           |

### 🔐 Sicherheit
- Passwortabfrage bei sensiblen Aktionen wie dem Hinzufügen von Ratings oder Löschen von Filmen
- Keine Klartext-Credentials im Code

## 🛠️ Tech Stack
- **Frontend**: Vue.js (SPA mit JavaScript, HTML5 & CSS3)
- **Backend**: Spring Boot (Java, REST API)
- **Datenbank**: PostgreSQL
- **Datenquelle**: TMDB API
- **Kommunikation**: AJAX (asynchron zwischen Client & Server)
- **Versionierung & CI/CD**: GitHub
- **Deployment**: Render.com (später auf Marvins Server)

### 🤖 System Requirements, Lokale Installation, Setup

| Komponente       | Versionsempfehlung                                      |
|------------------|---------------------------------------------------------|
| **Java**         | 21                                                      |
| **Gradle**       | 7.5+ (Toolchain kann Java 21 laden)                     |
| **PostgreSQL**   | 14+                                                     |
| **Node.js**      | 18.x+ (Installation: https://nodejs.org, LTS empfohlen) |
| **npm**          | 9.x+ (wird mit Node.js geliefert)                       |

*siehe Frontend Setup in PrimetimeFrontend\README.md

#### 🥸 Lokaler Datenbank Setup
1. PostgreSQL (mit pgAdmin) herunterladen: https://www.postgresql.org/download/
2. Datenbank über pgAdmin erstellen (empfohlen) oder alternativ übers Terminal:
```sql
-- nur auf Standard-Port 5432 !!
CREATE DATABASE prime_db;
CREATE USER sdd_user WITH PASSWORD 'deinPasswort';
GRANT ALL PRIVILEGES ON DATABASE prime_db TO sdd_user;
```
3. Datenbank-Konfiguration in application.properties speichern:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/prime_db
spring.datasource.username=sdd_user
spring.datasource.password=deinPasswort
```
4. Tabellen automatisch erstellen:
``` bash
# Backend starten
./gradlew bootRun
```

### ⚙️ Patch Notes - Version 1.0, Stand 11.06.25
- Alle Kernfeatures implementiert (und mehr oder weniger getestet)
- Benutzerfreundliches UI & interaktive Diagramme
- Passwortgeschützte Speicher- und Löschfunktionen

(Implementierung auf Marvins Server ausstehend!!!)
