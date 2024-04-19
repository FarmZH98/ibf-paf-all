package sg.edu.nus.iss.workshop25.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public record MessageObject(String id, String message) { 
	public static MessageObject toMessageObject(JsonObject json) {
		return new MessageObject(json.getString("id", "not-set"), json.getString("message", "not-set"));
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("id", id())
			.add("message", message())
			.build();
	}
}
