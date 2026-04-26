FROM node:18-alpine AS build
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .

FROM nginx:1.27-alpine
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app /usr/share/nginx/html
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]
