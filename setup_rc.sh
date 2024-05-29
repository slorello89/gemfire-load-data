#!/bin/bash

cp ./target/Trades2Geode-1.0.jar ./jars
chmod +w ./jars/Trades2Geode-1.0.jar

docker run \
-it --rm --privileged=true \
--name redis-connect-$(hostname) \
-v $(pwd)/config:/opt/redislabs/redis-connect/config \
-v $(pwd)/jars:/opt/redislabs/redis-connect/extlib \
--net host \
redislabs/redis-connect start