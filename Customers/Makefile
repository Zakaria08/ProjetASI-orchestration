.PHONY: *
.DEFAULT_GOAL:=help

# Internal
ROOT_DIR=$(shell dirname $(realpath $(firstword $(MAKEFILE_LIST))))
VERSION=$(shell cat $(ROOT_DIR)/VERSION)
CLJ=clojure -J-Dclojure.main.report=stderr

# Detect OS for compatible sleep command
ifeq ($(OS),Windows_NT)
    SLEEP_CMD = timeout /t 5 /nobreak
else
    SLEEP_CMD = sleep 5
endif

##@ Commands

run: ## Start application
	clojure -M -m app

compose-up: ## Start containers
	echo "Starting containers..."
	docker compose up --build -d --remove-orphans
	echo "Containers started."
	echo "Waiting 5 seconds to ensure all the containers are started..."
	$(SLEEP_CMD)

compose-down: ## Stop containers
	docker-compose down --remove-orphans

help:  ## Display this help
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m\033[0m\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)
