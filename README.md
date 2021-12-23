# Community Management Project

## DB

docker run -d -p 6033:3306 --name=community-db --env="POSTGRES_ROOT_PASSWORD=root" --env="POSTGRES_PASSWORD=root" --env="POSTGRES_DATABASE=community" postgres

