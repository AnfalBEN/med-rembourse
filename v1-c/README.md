# MedRembourse

Application en ligne de commande (C) permettant de rechercher un médicament 
par son nom et de savoir s'il est remboursé par la Sécurité sociale, et à 
quel taux.

## Contexte
Projet personnel réalisé en autonomie, dans le but de m'entraîner sur des 
notions de Programmation Système (fichiers, pointeurs, structures, chaînes 
de caractères en C) tout en répondant à un besoin concret du quotidien.

## Objectif
Permettre à un utilisateur de taper le nom d'un médicament et d'obtenir 
instantanément :
- son taux de remboursement (100%, 65%, 30%, 15%, ou non communiqué)
- le Code CIS et le nom complet de chaque présentation correspondante

## Fonctionnalités
- [x] Recherche d'un médicament par nom (recherche partielle, insensible à la casse non implémentée pour l'instant)
- [x] Chargement et jointure de deux fichiers de données (spécialités + présentations)
- [x] Gestion des cas particuliers : médicament non trouvé, taux non communiqué

## Fonctionnalités futures (bonus)
- [ ] Recherche insensible à la casse
- [ ] Recherche par substance active
- [ ] Version Java (pratique orientée objet)
- [ ] Interface web simple

## Source des données
Base de Données Publique des Médicaments (BDPM) — ANSM / HAS / Assurance Maladie
https://base-donnees-publique.medicaments.gouv.fr
(données réutilisables librement, source citée ici)

## Stack technique
- Langage : C
- Données : fichiers texte BDPM (`CIS_bdpm.txt`, `CIS_CIP_bdpm.txt`), séparés par tabulations
- Encodage : conversion Latin-1 → UTF-8 via `iconv`

## Installation
```bash
git clone https://github.com/ton-pseudo/med-rembourse.git
cd med-rembourse
iconv -f ISO-8859-1 -t UTF-8 CIS_bdpm.txt > CIS_bdpm_utf8.txt
gcc main.c -o med-rembourse
./med-rembourse
```

## Auteur
Anfal Bensaou — étudiante ingénieure, Polytech Nice Sophia (Systèmes Informatiques)

---

## English

**MedRembourse** is a C command-line tool to search for a medication by name 
and check whether it is reimbursed by French social security, and at what rate.

It parses two official public datasets from the French Public Medication 
Database (BDPM), joins them manually on a shared medication ID (Code CIS), 
and handles edge cases such as missing reimbursement data or unmatched 
records. This project was built to practice low-level C concepts (file I/O, 
pointers, structs, string manipulation) using a real, messy, real-world 
dataset rather than a toy example.

**Tech stack**: C, public government data (BDPM), manual file parsing (no external libraries).

---

## Difficultés rencontrées et ce que j'ai appris

Ce projet m'a servi à consolider des notions de Programmation Système que 
j'avais du mal à maîtriser. Voici les principaux points sur lesquels j'ai 
buté puis progressé :

- **Format des données réelles** : comprendre qu'un fichier `.txt` n'est pas 
  toujours simple à parser — colonnes séparées par tabulations (`\t`), et 
  non par espaces, avec parfois des colonnes vides consécutives.
- **`strtok` et ses limites** : découvrir que `strtok` "saute" les colonnes 
  vides (deux délimiteurs consécutifs sont traités comme un seul), ce qui 
  m'a obligée à réécrire le parsing avec `strchr` pour un découpage plus 
  robuste, colonne par colonne, même vide.
- **Encodage de caractères** : comprendre la différence entre Latin-1 
  (ISO-8859-1) et UTF-8, pourquoi les caractères accentués s'affichaient mal 
  (`È` au lieu de `é`), et comment convertir un fichier avec `iconv`.
- **Gestion des chaînes de caractères en C** : la nécessité du caractère de 
  fin de chaîne `\0`, pourquoi `strncpy` ne le garantit pas toujours, et le 
  risque de dépassement de tampon (buffer overflow) si on oublie une taille.
- **Comparaison de chaînes** : comprendre pourquoi on ne peut jamais comparer 
  deux chaînes avec `==` en C (ça compare des adresses mémoire, pas le 
  contenu), et qu'il faut systématiquement `strcmp`.
- **Erreurs "hors limites" (off-by-one)** : une boucle `for` avec `<=` au 
  lieu de `<` peut accéder à une case de tableau qui n'existe pas — un 
  classique que j'ai mieux compris en le vivant concrètement.
- **Organisation du code en C** : la nécessité des prototypes de fonctions 
  quand une fonction en utilise une autre définie plus bas dans le fichier, 
  et l'ordre des déclarations (structures avant leur utilisation).
- **Nettoyage de la saisie utilisateur** : `fgets` garde le retour à la 
  ligne tapé par l'utilisateur, qu'il faut retirer avec `strcspn` avant de 
  l'utiliser dans une recherche.
- **"Jointure" de données sans base de données** : reproduire à la main la 
  logique d'un `JOIN SQL` en C, en reliant deux tableaux via un identifiant 
  commun.

Ce projet reprend et consolide des notions vues en cours de Programmation 
Système (fichiers, pointeurs, structures) que je repasse cette année.