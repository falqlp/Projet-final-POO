# Projet de Traitement de Fichiers Batch en Java

[![Coverage Status](https://codecov.io/gh/falqlp/Projet-final-POO/branch/master/graph/badge.svg)](https://codecov.io/gh/falqlp/Projet-final-POO)
## Description du Projet

Ce projet consiste à développer un programme batch en Java pour :
- Scruter un dossier spécifique à la recherche de fichiers `users_<YYYYMMDDHHmmSS>.csv`.
- Parser ces fichiers CSV.
- Peupler une base de données relationnelle avec les entrées des fichiers.
- Déplacer les fichiers traités dans un autre dossier.

## Structure du Projet

Le projet est organisé en plusieurs classes principales :

1. **`CSVParser`** : Cette classe utilise Apache Commons CSV pour parser les fichiers CSV et créer des objets `User`.
2. **`DatabaseManager`** : Cette classe gère les interactions avec la base de données PostgreSQL. Elle insère ou met à jour les enregistrements dans la table `users`.
3. **`DirectoryWatcher`** : Cette classe utilise le `WatchService` de Java pour surveiller un dossier pour de nouveaux fichiers et les traiter. CONTIENT LE MAIN !
4. **`FileMover`** : Cette classe déplace les fichiers traités vers un dossier d'archive.
5. **`User`** : Cette classe représente les utilisateurs avec leurs informations respectives.

## Installation et Configuration

### Prérequis

- Java 21
- Maven
- PostgreSQL

### Configuration de PostgreSQL

Vous pouvez utiliser Docker pour configurer une instance PostgreSQL
Le docker compose se trouve dans **`/docker`**. La table sera généré automatiquement.

## Github Actions

Il existe une pipeline github qui execute tout les tests a chaque push sur la branche principale