mvn clean
docker rmi "$(docker images -a -q)" -f
docker volume prune
docker image prune -a
pause