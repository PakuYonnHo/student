import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublisher {
	private static final String BROKER_URL = "tcp://138.138.0.112:1883";
	private static final String TOPIC = "testtopic/3";

	public static void main(String[] args) {
		try {
			MqttClient client = new MqttClient(BROKER_URL, MqttClient.generateClientId());
			MqttConnectOptions options = new MqttConnectOptions();
			options.setCleanSession(true);
			client.connect(options);
			// 发布消息
			MqttMessage message = new MqttMessage("Hello MQTT!".getBytes());
			message.setQos(2); // 消息服务质量等级（QoS）
			client.publish(TOPIC, message);
			System.out.println("Message published: " + message);
			client.disconnect();
			System.out.println("Disconnected from broker.");
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}