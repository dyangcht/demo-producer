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
### Using config map for server definition
Create the config map
```
kubectl create configmap spring-app-config --from-file=src/main/resources/application.properties
```
Change the deployment. Add the volumes and volumeMounts
```
          volumeMounts:
          - name: application-config 
            mountPath: "/deployments/config" 
            readOnly: true
      volumes:
      - name: application-config
        configMap:
          name: spring-app-config 
          items:
          - key: application.properties 
            path: application.properties
```

### Consumer
```
oc run kafka-consumer -ti --image=registry.redhat.io/amq7/amq-streams-kafka-27-rhel7:1.7.0 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server my-cluster-kafka-bootstrap:9092 --topic my-topic --from-beginning
```

After create kafka cluster using operator in OCP4, you need to create the configmap of the metric.
- Goto "Installed Operators"
- Click "Red Hat Integration - AMQ Streams" and the click on the tab "Kafka"
- Click on the "my-cluster" which you created before
- Click on the tab "YAML"
- Put this statement as following
```
    metricsConfig:
      type: jmxPrometheusExporter
      valueFrom:
        configMapKeyRef:
          key: kafka-metrics-config.yml
          name: kafka-metrics
```

Reference: 
- [Kafka Samples, kafka cluster, Metrics](https://github.com/strimzi/strimzi-kafka-operator/tree/main/examples)
- [Programming Sample 1](https://developers.redhat.com/blog/2019/06/17/building-apache-kafka-streams-applications-using-red-hat-amq-streams-part-1/)
- [Programming Sample 2](https://developers.redhat.com/blog/2019/06/06/accessing-apache-kafka-in-strimzi-part-1-introduction/)
- [Installation Guide](https://access.redhat.com/documentation/en-us/red_hat_amq/2021.q2/html-single/deploying_and_upgrading_amq_streams_on_openshift/index#operator-hub-str)
