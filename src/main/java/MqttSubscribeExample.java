
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
 
public class MqttSubscribeExample {
    public static void main(String[] args) {
        String broker       = "tcp://138.138.0.112:1883"; // MQTT broker URL
        String clientId     = "JavaSample"; // MQTT client ID, must be unique on broker
        String topic        = "testtopic/3"; // MQTT topic to subscribe to
        String content      = "Message from Java client"; // Test message content
        int qos             = 1; // Quality of Service (0, 1, 2)
 
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, new MemoryPersistence());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
 
            // Set callback handler
            sampleClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost! " + cause);
                }
 
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Message arrived! QoS " + message.getQos() + " on topic '" + topic + "' with content: " + new String(message.getPayload()));
                }
 
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });
 
            System.out.println("Subscribing to topic \"" + topic + "\"");
            sampleClient.subscribe(topic, qos);
            System.out.println("Subscribed");
            Thread.sleep(10000); // Sleep for 10 seconds to receive messages (you can use a loop instead)
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}