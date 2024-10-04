# Docker Management Guide for User-Product API

## 🔄 Common Docker Commands

### Daily Development Commands
```bash
# Start the application and see the logs
docker-compose up

# Start the application in detached mode (background)
docker-compose up -d

# Stop the application
docker-compose down

# Restart a specific service
docker-compose restart app    # For the Spring Boot app
docker-compose restart mongodb # For MongoDB

# View logs
docker-compose logs -f        # Follow all logs
docker-compose logs -f app    # Follow only app logs
docker-compose logs -f mongodb # Follow only MongoDB logs
```

### Maintenance Commands
```bash
# Remove all containers and volumes (clean start)
docker-compose down -v

# Rebuild the application
docker-compose build
# or
docker-compose up --build

# Check container status
docker-compose ps

# Check container resources usage
docker stats
```

### MongoDB Data Management
```bash
# Access MongoDB shell inside the container
docker-compose exec mongodb mongosh

# Create database backup
docker-compose exec mongodb mongodump --out /data/backup/

# Restore database
docker-compose exec mongodb mongorestore /data/backup/
```

## 🚀 Best Practices

### 1. Development Workflow
- Always use `docker-compose down` to stop the application properly
- Use `docker-compose up --build` after making changes to the application code
- Check logs frequently during development using `docker-compose logs -f`

### 2. Database Management
- Regularly backup your MongoDB data
- Monitor disk space used by volumes
- Don't delete volumes unless you intend to delete all data

### 3. Troubleshooting Steps
If you encounter issues:

1. Check container status:
   ```bash
   docker-compose ps
   ```

2. View detailed logs:
   ```bash
   docker-compose logs -f
   ```

3. Restart services:
   ```bash
   docker-compose restart
   ```

4. Clean restart if needed:
   ```bash
   docker-compose down
   docker-compose up --build
   ```

### 4. Port Management
If you need to switch back to local MongoDB:
1. Stop the Docker containers
2. Start local MongoDB service:
   ```bash
   # Ubuntu/Debian
   sudo systemctl start mongodb

   # macOS
   brew services start mongodb
   ```
3. Update application.properties to use localhost:27017

## 🔍 Monitoring

### Check Resource Usage
```bash
# View container resource usage
docker stats

# View disk space used by Docker
docker system df
```

### Container Health Checks
```bash
# Check container logs for errors
docker-compose logs | grep -i error

# Check container health status
docker inspect --format='{{.State.Health.Status}}' container_id
```

## 🛡️ Security Reminders

1. Never commit sensitive data in Docker files
2. Regularly update base images
3. Monitor container resource usage
4. Use non-root users in containers
5. Keep MongoDB port closed to external access when in production
