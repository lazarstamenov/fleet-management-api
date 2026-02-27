# Fleet Management API

Backend aplikacija za upravljanje korisnicima i njihovim vozilima.
Projekat je razvijen koriscenjem Spring Boota i MySQL baze.

---

### Pokretanje aplikacije

1. Klonirati repozitorijum:
    - git clone https://github.com/lazarstamenov/fleet-management-api.git
2. Otvoriti projekat u IntelliJ IDEA(ili nekom drugom IDE)
3. Proveriti da je podesena Java 21
4. Podesiti konekciju ka bazi u application.properties fajlu
5. Pokrenuti glavnu klasu InternshipApplication
Aplikacija se podize na : http://localhost:8080

---

### Podizanje baze

Potrebno je imati instaliran MySQL.

Kreirati bazu komandom: CREATE DATABASE fleet_db;

Zatim u application.properties uneti parametre:

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/fleet_db
spring.datasource.username=root
spring.datasource.password=root123

Hibernate automatski kreira tabele pri prvom pokretanju aplikacije.

---

### Postman kolekcija i testiranje API

U projektu se nalazi fajl postman_collection.json.

Import kolekcije:
1. Otvoriti Postman
2. Kliknuti na "Import"
3. Izabrati fajl postman_collection.json
4. Kolekcija ce se pojaviti u levom panelu

Testiranje:
* Kreirati korisnika (POST /api/users)
* Kreirati vozilo (POST /api/cars)
* Testirati dodatne funkcije:
  * GET /api/users{id}/cars
  * PATCH /api/users{id}/status
* Testirati negativne scenarije (Dupli email)

---

### Primer konfiguracije (.env/application.properties)

Primer konfiguracije bez lozinke:

spring.datasource.url=jdbc:mysql://localhost:3306/fleet_db
spring.datasource.username=root
spring.datasource.password=root123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

---

### Funkcionalnosti

* CRUD operacije za User
* Crud operacije za Car
* Validacije (email unique, VIN unique, year range)
* Globalni exception handler
* Dodatne funkcije:
  * Pregled svih vozila jednog korisnika
  * Promena statusa vozila
