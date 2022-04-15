#!/bin/bash

mvn clean \
&& docker rmi "$(docker images -a -q)" -f \
&& docker volume prune