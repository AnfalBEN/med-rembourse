<<<<<<< HEAD
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

## Difficultés rencontrées et ce que j'ai appris

- **Serveur HTTP en Java** : découvrir `com.sun.net.httpserver.HttpServer`, 
  la notion de contexte (`createContext`), et le fonctionnement d'un 
  processus qui écoute en continu plutôt qu'une exécution unique.
- **CORS** : comprendre pourquoi un navigateur bloque par défaut une requête 
  vers un serveur sur un port différent, et comment l'autoriser avec le 
  header `Access-Control-Allow-Origin` — et le piège d'un header dupliqué 
  (`*, *`) qui provoque un blocage même avec la bonne valeur.
- **Encodage des réponses HTTP** : un nouveau problème d'UTF-8, cette fois 
  pas dans un fichier mais dans l'écriture de la réponse réseau — résolu 
  en précisant explicitement l'encodage avec `getBytes("UTF-8")`.
- **Construction manuelle de JSON** : utiliser `StringBuilder` pour 
  assembler une réponse JSON sans bibliothèque externe, avec la gestion 
  de la virgule entre éléments (variable `premier`).
- **Asynchrone en JavaScript** (`async`/`await`, `fetch`, `Promise`) : 
  comprendre qu'une requête réseau ne revient pas instantanément, et 
  comment écrire du code qui "attend" une réponse sans bloquer la page.
- **DOM dynamique** : générer du HTML depuis JavaScript (`createElement`, 
  `innerHTML`, `appendChild`) plutôt que d'écrire des éléments fixes à la main.
- **Design intentionnel** : construire une palette et une hiérarchie 
  visuelle réfléchies (couleurs sémantiques pour le statut de remboursement, 
  typographie différenciée) plutôt que des styles par défaut.
- **Débogage réseau** : apprendre à lire les erreurs dans l'onglet Console 
  des DevTools (erreurs CORS, `Failed to fetch`) pour diagnostiquer un 
  problème de communication client/serveur.
=======
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

## Difficultés rencontrées et ce que j'ai appris

- **Serveur HTTP en Java** : découvrir `com.sun.net.httpserver.HttpServer`, 
  la notion de contexte (`createContext`), et le fonctionnement d'un 
  processus qui écoute en continu plutôt qu'une exécution unique.
- **CORS** : comprendre pourquoi un navigateur bloque par défaut une requête 
  vers un serveur sur un port différent, et comment l'autoriser avec le 
  header `Access-Control-Allow-Origin` — et le piège d'un header dupliqué 
  (`*, *`) qui provoque un blocage même avec la bonne valeur.
- **Encodage des réponses HTTP** : un nouveau problème d'UTF-8, cette fois 
  pas dans un fichier mais dans l'écriture de la réponse réseau — résolu 
  en précisant explicitement l'encodage avec `getBytes("UTF-8")`.
- **Construction manuelle de JSON** : utiliser `StringBuilder` pour 
  assembler une réponse JSON sans bibliothèque externe, avec la gestion 
  de la virgule entre éléments (variable `premier`).
- **Asynchrone en JavaScript** (`async`/`await`, `fetch`, `Promise`) : 
  comprendre qu'une requête réseau ne revient pas instantanément, et 
  comment écrire du code qui "attend" une réponse sans bloquer la page.
- **DOM dynamique** : générer du HTML depuis JavaScript (`createElement`, 
  `innerHTML`, `appendChild`) plutôt que d'écrire des éléments fixes à la main.
- **Design intentionnel** : construire une palette et une hiérarchie 
  visuelle réfléchies (couleurs sémantiques pour le statut de remboursement, 
  typographie différenciée) plutôt que des styles par défaut.
- **Débogage réseau** : apprendre à lire les erreurs dans l'onglet Console 
  des DevTools (erreurs CORS, `Failed to fetch`) pour diagnostiquer un 
  problème de communication client/serveur.
>>>>>>> df7a09f7ec54f4ea75b70aa8009ef07672090a8a
