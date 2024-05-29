#!/bin/bash

cp ./target/Trades2Geode-1.0.jar ./jars
chmod +w ./jars/Trades2Geode-1.0.jar

docker run \
-it --rm --privileged=true \
--name redis-connect-$(hostname)-1 \
-v $(pwd)/config:/opt/redislabs/redis-connect/config \
-v $(pwd)/jars:/opt/redislabs/redis-connect/extlib \
--network redis-connect \
-p 8241:8242 \
-d \
redislabs/redis-connect start

docker run \
-it --rm --privileged=true \
--name redis-connect-$(hostname)-2 \
-v $(pwd)/config:/opt/redislabs/redis-connect/config \
-v $(pwd)/jars:/opt/redislabs/redis-connect/extlib \
--network redis-connect \
-p 8242:8242 \
-d \
redislabs/redis-connect start