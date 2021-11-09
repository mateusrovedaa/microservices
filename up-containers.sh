#!/bin/bash

docker run --rm -p 8000:8000 -d --network db_app --name gateway gateway
docker run --rm -p 127.0.0.1:8200:8000 -d --network db_app --name register register-service
docker run --rm -p 127.0.0.1:8300:8000 -d --network db_app --name login login-service
docker run --rm -p 127.0.0.1:8400:8000 -d --network db_app --name checkin checkin-service
docker run --rm -p 127.0.0.1:8500:8000 -d --network db_app --name event event-service
docker run --rm -p 127.0.0.1:8600:8000 -d --network db_app --name email email-service