# Commandes Docker pour SonarQube

## 🚀 Démarrage rapide

### 1. Créer les volumes Docker

```powershell
docker volume create sonarqube_data
docker volume create sonarqube_logs
docker volume create sonarqube_extensions
```

### 2. Lancer SonarQube (Windows PowerShell)

```powershell
docker run -d --name sonarqube -p 9000:9000 `
  -v sonarqube_data:/opt/sonarqube/data `
  -v sonarqube_logs:/opt/sonarqube/logs `
  -v sonarqube_extensions:/opt/sonarqube/extensions `
  sonarqube:lts-community
```

### 2b. Lancer SonarQube (Command Prompt / cmd)

```cmd
docker run -d --name sonarqube -p 9000:9000 ^
  -v sonarqube_data:/opt/sonarqube/data ^
  -v sonarqube_logs:/opt/sonarqube/logs ^
  -v sonarqube_extensions:/opt/sonarqube/extensions ^
  sonarqube:lts-community
```

---

## 📋 Commandes de gestion

### Vérifier l'état du conteneur

```powershell
docker ps
docker ps -a  # Inclut les conteneurs arrêtés
```

### Voir les logs

```powershell
docker logs sonarqube
docker logs -f sonarqube  # Suivi en temps réel
```

### Arrêter SonarQube

```powershell
docker stop sonarqube
```

### Redémarrer SonarQube

```powershell
docker start sonarqube
```

### Supprimer le conteneur (les volumes restent)

```powershell
docker rm sonarqube
```

---

## 🔧 Dépannage

### SonarQube ne démarre pas

1. Vérifier si le port 9000 est disponible :
```powershell
netstat -ano | findstr :9000
```

2. Si occupé, utiliser un autre port :
```powershell
docker run -d --name sonarqube -p 9001:9000 ...
```

### Supprimer complètement (reset total)

```powershell
docker stop sonarqube
docker rm sonarqube
docker volume rm sonarqube_data sonarqube_logs sonarqube_extensions
```

### Vérifier l'utilisation des ressources

```powershell
docker stats sonarqube
```

---

## 📊 Commande Maven pour l'analyse

### Windows PowerShell

```powershell
mvn clean verify sonar:sonar `
  -Dsonar.projectKey=Student_class `
  -Dsonar.host.url=http://localhost:9000 `
  -Dsonar.login=VOTRE_TOKEN
```

### Windows Command Prompt (cmd)

```cmd
mvn clean verify sonar:sonar ^
  -Dsonar.projectKey=Student_class ^
  -Dsonar.host.url=http://localhost:9000 ^
  -Dsonar.login=VOTRE_TOKEN
```

---

## ✅ Checklist avant analyse

- [ ] Docker Desktop est démarré
- [ ] Le conteneur `sonarqube` est en cours d'exécution (`docker ps`)
- [ ] L'interface web est accessible (http://localhost:9000)
- [ ] Un projet est créé dans SonarQube
- [ ] Un token a été généré et copié
- [ ] Le `pom.xml` est présent dans le dossier courant
