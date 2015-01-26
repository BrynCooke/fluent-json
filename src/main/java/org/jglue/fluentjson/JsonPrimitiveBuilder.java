package org.jglue.fluentjson;

import java.io.IOException;
import java.io.Writer;

import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;

public class JsonPrimitiveBuilder implements JsonBuilder {
	private JsonPrimitive primitive;
	public JsonPrimitiveBuilder(Number n) {
		primitive = new JsonPrimitive(n);
	}

	public JsonPrimitiveBuilder(Character c) {
		primitive = new JsonPrimitive(c);
	}

	public JsonPrimitiveBuilder(Boolean b) {
		primitive = new JsonPrimitive(b);
	}

	public JsonPrimitiveBuilder(String s) {
		primitive = new JsonPrimitive(s);
	}

	JsonPrimitive getPrimitive() {
		return primitive;
	}
	
	@Override
	public void write(Writer out) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void write(JsonWriter out) throws IOException {
		throw new UnsupportedOperationException();
	}

}
