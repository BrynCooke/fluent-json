package org.jglue.jsonfluent;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;

/**
 * Factory for builders.
 * 
 * @author Bryn Cooke
 * 
 */
public class JsonBuilderFactory {

	/**
	 * @return Start building a new json object.
	 */
	public JsonObjectBuilder<?, JsonObject> buildObject() {
		JsonObject o = new JsonObject();
		return new Impl(o, o);
	}

	/**
	 * @return Start building new json array.
	 */
	public JsonArrayBuilder<?, JsonArray> buildArray() {
		JsonArray o = new JsonArray();
		return new Impl(o, o);
	}

	public class Impl<P, R> implements JsonObjectBuilder<P, R>,
			JsonArrayBuilder<P, R> {

		private JsonElement context;
		private JsonElement root;
		private P parent;

		public Impl(JsonElement root, P parent, JsonElement context) {
			this.parent = parent;
			this.root = root;
			this.context = context;
		}

		public Impl(JsonElement root, JsonElement context) {
			this.parent = (P) this;
			this.root = root;
			this.context = context;
		}

		@Override
		public JsonObjectBuilder addObject(String key) {
			JsonObject o = new JsonObject();
			((JsonObject) context).add(key, o);
			return new Impl(root, this, o);
		}

		@Override
		public JsonArrayBuilder addArray(String key) {
			JsonArray o = new JsonArray();
			((JsonObject) context).add(key, o);
			return new Impl(root, this, o);
		}

		@Override
		public JsonArrayBuilder add(Iterable<? extends JsonBuilder> builders) {
			JsonArray array = (JsonArray) context;
			for (JsonBuilder builder : builders) {
				Impl i = ((Impl) builder);
				array.add(i.context);

			}
			return this;
		}

		@Override
		public JsonObjectBuilder add(String key, Iterable<JsonBuilder> builders) {
			JsonObject obj = (JsonObject) context;
			for (JsonBuilder builder : builders) {
				Impl i = ((Impl) builder);
				obj.add(key, i.context);

			}
			return this;
		}

		@Override
		public JsonObjectBuilder add(String key, Boolean value) {
			JsonObject obj = (JsonObject) context;
			if (value == null) {
				obj.add(key, JsonNull.INSTANCE);
			} else {
				obj.add(key, new JsonPrimitive(value));
			}
			return this;
		}

		@Override
		public JsonObjectBuilder add(String key, Character value) {
			JsonObject obj = (JsonObject) context;
			if (value == null) {
				obj.add(key, JsonNull.INSTANCE);
			} else {
				obj.add(key, new JsonPrimitive(value));
			}
			return this;
		}

		@Override
		public JsonObjectBuilder add(String key, Number value) {
			JsonObject obj = (JsonObject) context;
			if (value == null) {
				obj.add(key, JsonNull.INSTANCE);
			} else {
				obj.add(key, new JsonPrimitive(value));
			}
			return this;
		}

		@Override
		public JsonObjectBuilder add(String key, String value) {
			JsonObject obj = (JsonObject) context;
			if (value == null) {
				obj.add(key, JsonNull.INSTANCE);
			} else {
				obj.add(key, new JsonPrimitive(value));
			}

			return this;
		}

		@Override
		public JsonArrayBuilder add(JsonBuilder builder) {
			JsonArray array = (JsonArray) context;
			array.add(((Impl) builder).context);
			return this;
		}

		@Override
		public JsonObjectBuilder add(String key, JsonBuilder builder) {
			JsonObject obj = (JsonObject) context;
			obj.add(key, ((Impl) builder).context);
			return this;
		}

		@Override
		public String toString() {
			return root.toString();
		}

		@Override
		public P end() {
			return parent;
		}

		@Override
		public JsonObjectBuilder<JsonArrayBuilder<P, R>, R> addObject() {
			JsonObject o = new JsonObject();
			((JsonArray) context).add(o);
			return new Impl(root, this, o);
		}

		@Override
		public JsonArrayBuilder<JsonArrayBuilder<P, R>, R> addArray() {
			JsonArray o = new JsonArray();
			((JsonArray) context).add(o);
			return new Impl(root, this, o);
		}

		@Override
		public void write(Writer out) throws IOException {
			JsonWriter jsonWriter = new JsonWriter(out);
			jsonWriter.setLenient(true);
			Streams.write(root, jsonWriter);
		}

		@Override
		public R getJson() {
			return (R) root;
		}

		@Override
		public JsonArrayBuilder<P, R> add(Boolean value) {
			JsonArray array = ((JsonArray) context);
			if (value == null) {
				array.add(JsonNull.INSTANCE);
			} else {
				array.add(new JsonPrimitive(value));
			}
			return this;
		}

		@Override
		public JsonArrayBuilder<P, R> add(Character value) {
			JsonArray array = ((JsonArray) context);
			if (value == null) {
				array.add(JsonNull.INSTANCE);
			} else {
				array.add(new JsonPrimitive(value));
			}
			return this;
		}

		@Override
		public JsonArrayBuilder<P, R> add(Number value) {
			JsonArray array = ((JsonArray) context);
			if (value == null) {
				array.add(JsonNull.INSTANCE);
			} else {
				array.add(new JsonPrimitive(value));
			}
			return this;
		}

		@Override
		public JsonArrayBuilder<P, R> add(String value) {
			JsonArray array = ((JsonArray) context);
			if (value == null) {
				array.add(JsonNull.INSTANCE);
			} else {
				array.add(new JsonPrimitive(value));
			}
			return this;
		}

		@Override
		public JsonArrayBuilder<P, R> addNull() {
			JsonArray array = ((JsonArray) context);
			array.add(JsonNull.INSTANCE);
			return this;
		}

		@Override
		public JsonObjectBuilder<P, R> addNull(String key) {
			JsonObject obj = ((JsonObject) context);
			obj.add(key, JsonNull.INSTANCE);
			return this;
		}

	}

}
