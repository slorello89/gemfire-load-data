#!/bin/bash

cp ./target/stream-data-to-geode-1.0-SNAPSHOT.jar ./jars
chmod +w ./jars/stream-data-to-geode-1.0-SNAPSHOT.jar

docker run \
-it --rm --privileged=true \
--name redis-connect-$(hostname)-1 \
-v $(pwd)/config:/opt/redislabs/redis-connect/config \
-v $(pwd)/jars:/opt/redislabs/redis-connect/extlib \
--network redis-connect \
-p 8281:8282 \
-d \
redislabs/redis-connect start

docker run \
-it --rm --privileged=true \
--name redis-connect-$(hostname)-2 \
-v $(pwd)/config:/opt/redislabs/redis-connect/config \
-v $(pwd)/jars:/opt/redislabs/redis-connect/extlib \
--network redis-connect \
-p 8282:8282 \
-d \
redislabs/redis-connect start