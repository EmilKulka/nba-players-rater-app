FROM node:20-alpine

WORKDIR /app

ARG VITE_REACT_APP_API_BASE_URL
ENV VITE_REACT_APP_API_BASE_URL=$VITE_REACT_APP_API_BASE_URL

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

EXPOSE 4173

CMD ["npm", "run", "preview"]
