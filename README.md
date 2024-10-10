# EventOne - Web App

**EventOne** è una web application sviluppata utilizzando **Spring Framework**. L'applicazione consente ai gestori di locali di creare eventi e agli utenti di visualizzarli (eventi e locali per la città di Pesaro).

## Funzionalità Principali

### 1. Home Page
- **Carosello degli eventi**: Gli eventi sono mostrati in un **carosello ordinato per data**, dove gli utenti possono vedere gli eventi imminenti.
- **Navigazione intuitiva**: La barra di navigazione contiene pulsanti per:
  - **Ricerca eventi**: Gli utenti possono cercare gli eventi disponibili per una determinata data.
  - **Accedi**: Link alla pagina di login, pensato per i **gestori di locali**.
  - **Registrati**: Link alla pagina di registrazione per diventare **gestore di locali**.

> **Nota importante**: Attualmente, le funzioni di registrazione e accesso sono dedicate esclusivamente ai **gestori di locali**. Non è ancora implementato un sistema di autenticazione per i clienti. Questa è un'area pianificata per sviluppi futuri.

### 2. Gestore del locale
- **Registrazione e Login**: Il gestore del locale può registrarsi e accedere al sistema utilizzando credenziali salvate in un database.
- **Gestione dei locali**: Il gestore può aggiungere e modificare i dettagli dei locali di cui è responsabile.
- **Creazione di eventi**: Una volta inseriti i locali, il gestore può creare eventi associati a uno specifico locale. Gli eventi includono dettagli come nome, data, e descrizione.

  
### 3. Visualizzazione degli eventi
- **Partecipazione agli eventi**: L'idea originale prevedeva che i clienti potessero registrarsi e partecipare agli eventi. Anche se questa funzionalità non è completamente implementata, è stata pianificata l'integrazione di un sistema per permettere agli utenti di iscriversi e cancellarsi dagli eventi.

- ##Note per lo Sviluppo##
Backend: La parte backend è stata completata, ma ci sono alcune funzionalità e dettagli da implementare.
Frontend: È prevista una fase di completamento del frontend, con l'obiettivo di migliorare l'esperienza utente e l'aspetto visivo dell'applicazione.

##Database##
-importare il databse su mysql. 
-inserire paswword del databse nell' application properties per il corretto avvio dell' applicazione

- ## Requisiti del progetto

- **Java 17**
- **Spring Boot 3.3.2**
- **MySQL** per la gestione del database
- **Thymeleaf** come motore di template per la parte frontend

**Autore** - Team di 5 studenti di Generation Italy. 
           - Il sottoscritto si è occupato principalmente della parte beackend e ha effettuato delle modifiche nella parte frontend.
