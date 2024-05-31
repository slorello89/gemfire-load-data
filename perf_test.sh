#!/bin/bash

# Start time
start_time=$(date +%s)

# Kick off the job with the curl command
curl -X POST "http://localhost:8282/connect/api/v1/job/transition/start/cdc-job/load" -H "accept: */*"

# Wait for the Redis key to exist
while true; do
  # Check if the Redis key exists
  exists=$(redis-cli -p 14000 EXISTS test:entry:999999)

  # If the key exists, break the loop
  if [ "$exists" -eq 1 ]; then
    break
  fi

  # Sleep for a short interval before checking again
  sleep 1
done

# End time
end_time=$(date +%s)

# Calculate elapsed time
elapsed_time=$((end_time - start_time))

# Print the elapsed time
echo "Elapsed time: $elapsed_time seconds"
