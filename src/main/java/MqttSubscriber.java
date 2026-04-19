import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttSubscriber {
	private static final String BROKER_URL = "tcp://138.138.0.112:1883";
	private static final String TOPIC = "testtopic/3";

	public static void main(String[] args) {
		try {
			MqttClient client = new MqttClient(BROKER_URL, MqttClient.generateClientId());
			client.connect();
			// 订阅主题
			client.subscribe(TOPIC, (topic, message) -> {
				System.out.println("Message received: " + new String(message.getPayload()));
			});
			System.out.println("Subscribed to topic: " + TOPIC);
			// 保持应用运行，以便接收消息
			Thread.sleep(10000);
			client.disconnect();
			System.out.println("Disconnected from broker.");
		} catch (MqttException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}	