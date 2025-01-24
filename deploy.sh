#!/bin/bash

# Configuration
APP_NAME="nba-players-rater"
REPO_URL="https://github.com/EmilKulka/nba-players-rater-app.git"
FRONTEND_DIR="frontend/nba-player-rater-frontend"
BACKEND_DIR="backend/nba-players-rater-api"
FRONTEND_BUILD_DEST="/var/www/${APP_NAME}/frontend"
NGINX_CONF_DEST="/etc/nginx/conf.d/${APP_NAME}.conf"

# Update system and install dependencies
sudo dnf update -y
sudo dnf install -y \
    docker \
    git \
    nodejs \
    npm \
    wget \
    nginx

# Install Docker Compose
sudo mkdir -p /usr/local/bin
sudo curl -SL https://github.com/docker/compose/releases/download/v2.20.2/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Start Docker
sudo systemctl enable docker
sudo systemctl start docker

# Clone repository
if [ -d "nba-players-rater-app" ]; then
  echo "Repository exists. Updating..."
  cd nba-players-rater-app
  git pull origin main || { echo "Failed to update repository"; exit 1; }
else
  echo "Klonowanie repozytorium..."
  git clone ${REPO_URL} || { echo "Failed to clone repository"; exit 1; }
  cd nba-players-rater-app
fi

# Create .env files (DON'T JUDGE PLS)
cat > .env << EOF
POSTGRES_USER=postgres
POSTGRES_PASSWORD=foo
POSTGRES_DB=nba-players-rater
PGADMIN_DEFAULT_EMAIL=foo@gmail.com
PGADMIN_DEFAULT_PASSWORD=foo
SPRING_DATASOURCE_URL=jdbc:postgresql://local_pg:5432/nba-players-rater
EOF

cat > ${FRONTEND_DIR}/.env << EOF
VITE_REACT_APP_API_BASE_URL=http://<WELL YEAH>:8080/api/v1
EOF

# Build frontend
cd ${FRONTEND_DIR}
npm install
npm run build
sudo mkdir -p ${FRONTEND_BUILD_DEST}
sudo cp -r dist/* ${FRONTEND_BUILD_DEST}
cd ../..

# Install and configure Nginx
sudo systemctl enable nginx
sudo systemctl start nginx

# Nginx configuration
NGINX_CONFIG="server {
    listen 80;
    server_name _;

    location / {
        root ${FRONTEND_BUILD_DEST};
        index index.html;
        try_files \$uri /index.html;
    }

    location /api/ {
        proxy_pass http://localhost:8080/;
        proxy_set_header Host \$host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
    }
}
"
echo "${NGINX_CONFIG}" | sudo tee ${NGINX_CONF_DEST} > /dev/null
sudo nginx -t
sudo systemctl restart nginx

# Run Docker Compose
cd ${BACKEND_DIR}/..
sudo docker-compose down
sudo docker-compose up -d --build --remove-orphans

echo "Deployment completed successfully!"
