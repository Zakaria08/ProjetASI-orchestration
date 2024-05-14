# ProjetASI Orchestrateur

Ce projet est une application FastAPI orchestrée avec Docker Compose. L'application offre plusieurs points de terminaison pour gérer les clients et les reçus.

## Structure du Projet

### Fichiers et Dossiers

- `docker-compose.yml`: Fichier de configuration pour Docker Compose.
- `Dockerfile`: Fichier pour construire l'image Docker.
- `main.py`: Point d'entrée principal de l'application FastAPI.
- `requirements.txt`: Fichier listant les dépendances Python nécessaires.
- `routers/`: Dossier contenant les routeurs FastAPI pour différents points de terminaison.
- `schemas.py`: Fichier définissant les modèles de données.
- `services/`: Dossier contenant les services pour la récupération et le traitement des données.

## Prérequis

Assurez-vous d'avoir les logiciels suivants installés sur votre machine :
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Installation

Clonez le dépôt Git :

```bash
git clone https://github.com/Zakaria08/ProjetASI-orchestration.git
cd ProjetASI-orchestration
```
Renommez le fichier **.env.example** en **.env** et modifiez-le avec les valeurs appropriées.

## Utilisation

### Construction et Déploiement

Pour construire et déployer le conteneur localement, exécutez :


```bash
docker-compose up
```

Ajoutez le paramètre **-d** pour exécuter en arrière-plan.

## Test

### Pour vérifier que le conteneur fonctionne, exécutez :

```bash
docker-compose ps
```

### Pour afficher les logs de sortie, exécutez :

```bash
docker-compose logs
```

Pour surveiller continuellement les logs, exécutez :

```bash
docker-compose logs --tail 100 -f
```

### Accédez à l'application via http://localhost:5000 ou exécutez :

```bash
curl http://localhost:5000
```

## Points de Terminaison de l'API

-   **GET** **/all_clients**: Retourne tous les clients.
-   **GET** **/all_receipts_per_client**: Retourne tous les reçus par client.
-   **GET** **/average_age**: Retourne l'âge moyen des clients.
-   **GET** **/ticket**: Retourne le reçu du jour pour une date d'achat donnée.
-   **GET** **/customer/{customer_cardnumber}**: Retourne les informations d'un client spécifique par numéro de carte.
-   **GET** **/ticket/{receipt_id}**: Retourne les informations d'un reçu spécifique par ID.




## Auteur

Zakaria08