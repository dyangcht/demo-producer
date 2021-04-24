# demo-producer

### Producer
```
oc new-app --name demo 'java:11~https://github.com/dyangcht/demo-producer.git#main' --as-deployment-config=true
oc delete bc demo
oc delete dc demo
oc delete svc demo

oc new-app --name demo2 'java:11~https://github.com/dyangcht/demo-producer.git#main' --as-deployment-config=true
oc delete bc demo2
oc delete dc demo2
oc delete svc demo2

```

### Consumer
```
oc run kafka-consumer -ti --image=registry.redhat.io/amq7/amq-streams-kafka-27-rhel7:1.7.0 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --topic my-topic --from-beginning
```
