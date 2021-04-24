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

After create kafka cluster using operator in OCP4, you need to create the configmap of the metric.
. Goto "Installed Operators"
. Click "Red Hat Integration - AMQ Streams" and the click on the tab "Kafka"
. Click on the "my-cluster" which you created before
. Click on the tab "YAML"
. Put this statement as following
```
    metricsConfig:
      type: jmxPrometheusExporter
      valueFrom:
        configMapKeyRef:
          key: kafka-metrics-config.yml
          name: kafka-metrics
```

Reference: https://github.com/strimzi/strimzi-kafka-operator/tree/main/examples <br/>

