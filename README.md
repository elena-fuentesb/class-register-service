## Running the sample code

1. Start a node:

    ```
    sbt -Dconfig.resource=local1.conf run
    ```

2. Check for service readiness

    ```
    curl http://localhost:9301/ready
    ```
