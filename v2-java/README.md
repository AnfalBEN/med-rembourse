# MedRembourse — Version Java

Réécriture orientée objet de la version C, pratiquant l'encapsulation, 
l'héritage, la généricité et la gestion d'exceptions.

## Contexte
Version Java du projet, pensée pour pratiquer la Programmation Orientée 
Objet (POO2) : encapsulation, composition, héritage, classes génériques.

## Fonctionnalités
- [x] Recherche d'un médicament par nom
- [x] Chargement et jointure de deux fichiers de données via composition (`RechercheService`)
- [x] Gestion des cas particuliers (non trouvé, taux non communiqué)
- [x] Repository générique (`Repository<T>`) partagé par `MedicamentRepository` et `PresentationRepository`

## Architecture
-Medicament.java → modèle (encapsulation)
-Presentation.java → modèle (encapsulation)
-Repository.java → classe abstraite générique (chargement de fichier)
-MedicamentRepository.java → hérite de Repository<Medicament>
-PresentationRepository.java → hérite de Repository<Presentation>
-RechercheService.java → composition des deux repositories, logique de recherche
-Main.java → point d'entrée


## Stack technique
- Langage : Java (sans framework)
- Données : mêmes fichiers BDPM que la version C

## Installation
```bash
cd v2-java
javac Repository.java Main.java Medicament.java MedicamentRepository.java Presentation.java PresentationRepository.java RechercheService.java
java Main
```

## Auteur
Anfal Bensaou — étudiante ingénieure, Polytech Nice Sophia (Sciences Informatiques)

---

## English

Object-oriented rewrite of the C version, practicing encapsulation, 
composition, inheritance and generics — reusing the same BDPM datasets 
and reproducing the same search/reimbursement logic in idiomatic Java.

**Tech stack**: Java (no framework), `ArrayList`, `BufferedReader`, checked exceptions.

---

## Difficultés rencontrées et ce que j'ai appris

- **Encapsulation** : passer d'un accès direct aux champs (`struct` en C) à 
  des attributs `private` accessibles via des getters — comprendre pourquoi 
  un getter doit être `public`.
- **Enchaînement d'appels de méthodes** (`liste.get(i).getNom()`) : passer 
  d'un accès plat (`tableau[i].nom` en C) à plusieurs appels successifs, 
  chacun renvoyant un objet différent.
- **Exceptions checked** (`try/catch`, `throws IOException`) : comprendre 
  que Java interrompt l'exécution à la différence du C, où une erreur 
  (`fopen` renvoyant `NULL`) devait être vérifiée manuellement.
- **`ArrayList` vs tableau statique** : plus besoin de fixer une taille 
  maximale à l'avance, `.size()` remplace le compteur manuel du C.
- **`String.split()` et ses pièges** : découvrir que `split("\t")` supprime 
  les colonnes vides en fin de ligne par défaut, et qu'il faut `split("\t", -1)` 
  pour les conserver — équivalent du problème rencontré avec `strtok` en C.
- **Classes génériques et abstraites** (`Repository<T>`) : factoriser deux 
  classes très similaires (`MedicamentRepository`, `PresentationRepository`) 
  en une seule classe parente paramétrée par un type, avec une méthode 
  abstraite (`creerElement`) que chaque sous-classe implémente différemment.
- **Résolution de faux problèmes d'IDE** : apprendre à distinguer une vraie 
  erreur de compilation (`javac`) d'un faux positif de l'extension Java de 
  VS Code (nécessitant parfois un nettoyage du "Language Server").
