## Installation

```
# git clone https://github.com/Zakaria08/ProjetASI-orchestration.git
```

Change the .env.example into a .env file

## Run

The following will build and deploy the container locally.

```
# docker-compose up
```

Add the `-d` parameter to run in background.

## Test

Test that the container is running by executing `docker-compose ps`. Check output logs with `docker-compose logs`. To continually monitor output run `docker-compose logs --tail 100 -f`.

Go to http://localhost:8080/ or run `curl http://localhost:8080`.

