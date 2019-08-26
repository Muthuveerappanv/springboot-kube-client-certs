# springboot-kube-client-certs
project to maintain spring boot client certificates as kubernetes secrets

## Create Secret (for private key)

```kubectl create secret sample-sec --from-file=client_pavel.p12```
(file is located at the client project resoruce folder)

## Create configmap

```kubectl create configmap --from-literal=secure-rest-ip=secure-rest-server --from-literal=secure-rest-port=8443```

## Build project with command

```mvn clean install```

    #### cd into sb-secure-rest-server
    
    ```docker build -t secure-rest-server .```
    
    ```kubecl create -f secure-rest-deployment.yaml```
    
    
    #### cd into sb-secure-rest-client
    
    ```docker build -t secure-rest-client .```
    
    ```kubecl create -f secure-rest-client-deployment.yml```
    
## Access the client REST API at - 

```http://<minikube-ip>:<port>/https/{custId} ```
