package controllers;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.Controller;
import play.mvc.WebSocket;

public class SocketController extends Controller {
	
	
	public static  WebSocket.Out<JsonNode> outter;

	public static WebSocket<JsonNode> index() {
		return new WebSocket<JsonNode>() {

			// Called when the Websocket Handshake is done.
			public void onReady(WebSocket.In<JsonNode> in,
					WebSocket.Out<JsonNode> out) {

				// For each event received on the socket,
				in.onMessage(new Callback<JsonNode>() {
					public void invoke(JsonNode event) {

						// Log events to the console
						System.out.println(event);

					}
				});

				// When the socket is closed.
				in.onClose(new Callback0() {
					public void invoke() {

						System.out.println("Disconnected");

					}
				});

				outter = out;
				// Send a single 'Hello!' message
				
				  ObjectNode notifier = Json.newObject();
				  notifier.put("msg", "Hello");
				  Map<String,String> map =  new HashMap<String, String>();
				  map.put("msg", "Hello");
				out.write(notifier);

			}

		};
	}

}
