# MedRembourse — Version Web

Interface web connectant un frontend HTML/CSS/JS à un backend Java 
(serveur HTTP natif), pour une recherche de médicament en temps réel 
avec consultation du taux de remboursement.

## Contexte
Troisième version du projet, pensée pour pratiquer : un backend HTTP en 
Java sans framework, la communication client/serveur en JSON, et un 
frontend soigné visuellement (HTML/CSS/JS vanilla).

## Fonctionnalités
- [x] Recherche en temps réel (à chaque lettre tapée)
- [x] Backend Java exposant un endpoint `/recherche?nom=...` en JSON
- [x] Réutilisation des classes de la version Java (`RechercheService`, `Repository<T>`, etc.)
- [x] Affichage dynamique des résultats avec statut visuel (pastille + badge vert/corail selon le remboursement)
- [x] Gestion des cas "champ vide" et "aucun résultat"
- [x] Design distinctif (palette violet/vert menthe/corail, cartes arrondies)

## Fonctionnalités futures (bonus)
- [ ] Récupérer la vraie forme pharmaceutique depuis la BDPM (actuellement codée en dur côté serveur)
- [ ] Hébergement en ligne (frontend sur GitHub Pages, backend sur Render/Railway)
- [ ] Debounce sur la recherche (éviter une requête à chaque lettre tapée trop vite)

## Architecture
-index.html → structure de la page
-style.css → design (variables CSS, palette, mise en page)
-script.js → logique frontend (fetch, affichage dynamique)
-Serveur.java → serveur HTTP, expose /recherche en JSON
-Medicament.java, MedicamentRepository.java, -Presentation.java,
-PresentationRepository.java, Repository.java → repris de la v2-java


## Stack technique
- Frontend : HTML, CSS (variables natives, Flexbox), JavaScript vanilla (fetch, async/await)
- Backend : Java, `com.sun.net.httpserver` (aucun framework, aucune dépendance externe)
- Format d'échange : JSON construit manuellement côté serveur

## Installation

**1. Lancer le backend**
```bash
cd v3-web
javac Serveur.java Medicament.java MedicamentRepository.java Presentation.java PresentationRepository.java Repository.java
java Serveur
```
Laisser ce terminal ouvert — le serveur tourne sur `http://localhost:8080`.

**2. Ouvrir le frontend**
Ouvrir `index.html` dans un navigateur (double-clic, ou via une extension type Live Server).

## Auteur
Anfal Bensaou — étudiante ingénieure, Polytech Nice Sophia (Sciences Informatiques)

---

## English

Web interface connecting a vanilla HTML/CSS/JS frontend to a dependency-free 
Java backend (`com.sun.net.httpserver`), reusing the OOP logic from the Java 
version to serve real-time medication search and reimbursement data as JSON.

**Tech stack**: HTML/CSS/JS (fetch, async/await), Java HTTP server (no framework), hand-built JSON.

---