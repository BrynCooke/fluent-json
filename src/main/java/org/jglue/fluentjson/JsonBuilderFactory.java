/*
 *    Copyright 2013 Bryn Cooke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jglue.fluentjson;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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
	public static JsonObjectBuilder<?, JsonObject> buildObject() {
		JsonObject o = new JsonObject();
		return new Impl(o, o);
	}

	/**
	 * @return Start building new json array.
	 */
	public static JsonArrayBuilder<?, JsonArray> buildArray() {
		JsonArray o = new JsonArray();
		return new Impl(o, o);
	}

	/**
	 * @param transform
	 *            The transformer for the objects
	 * @param objects
	 *            The objects to build
	 * @param <T>
	 *            The type of the objects
	 * @return Start building new json array.
	 */
	public static <T> JsonArrayBuilder<?, JsonArray> buildArray(Mapper<T> transform, Iterable<T> objects) {
		JsonArray a = new JsonArray();
		Impl impl = new Impl(a, a);
		for (T o : objects) {
			impl.add(transform.map(o));
		}
		return impl;
	}

	private static class Impl<P, R> implements JsonObjectBuilder<P, R>, JsonArrayBuilder<P, R> {

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
			JsonArrayBuilder<?, JsonArray> array = createArray(builders);
			add(array);
			return this;
		}

		private JsonArrayBuilder<?, JsonArray> createArray(Iterable<? extends JsonBuilder> builders) {
			JsonArrayBuilder<?, JsonArray> array = JsonBuilderFactory.buildArray();
			for (JsonBuilder b : builders) {
				array.add(b);
			}
			return array;
		}

		@Override
		public JsonObjectBuilder add(String key, Iterable<? extends JsonBuilder> builders) {
			JsonArrayBuilder<?, JsonArray> array = createArray(builders);
			add(key, array);

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
			if (builder instanceof JsonPrimitiveBuilder) {
				array.add(((JsonPrimitiveBuilder) builder).getPrimitive());
			} else {
				array.add(((Impl) builder).context);
			}
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
			write(jsonWriter);
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

		@Override
		public void write(JsonWriter out) throws IOException {
			write(out, root);
		}

		@Override
		public <T> JsonObjectBuilder<P, R> add(String key, Mapper<T> transform, Iterable<T> objects) {
			JsonArrayBuilder<?, JsonArray> array = createArray(objects, transform);
			add(key, array);
			return this;
		}

		private <T> JsonArrayBuilder<?, JsonArray> createArray(Iterable<T> objects, Mapper<T> transform) {
			JsonArrayBuilder<?, JsonArray> array = JsonBuilderFactory.buildArray();
			for (T o : objects) {
				array.add(transform.map(o));
			}
			return array;
		}

		@Override
		public <T> JsonObjectBuilder<P, R> add(String key, Mapper<T> transform, T... objects) {
			for (T object : objects) {
				add(key, transform.map(object));
			}
			return this;
		}

		@Override
		public <T> JsonArrayBuilder<P, R> add(Mapper<T> transform, Iterable<T> objects) {
			JsonArrayBuilder<?, JsonArray> array = createArray(objects, transform);
			add(array);
			return this;
		}

		@Override
		public <T> JsonArrayBuilder<P, R> add(Mapper<T> transform, T... objects) {
			for (T object : objects) {
				add(transform.map(object));
			}
			return this;
		}

		/**
		 * Serialization code copied from GSON
		 */
		private void write(JsonWriter out, JsonElement value) throws IOException {
			if (value == null || value.isJsonNull()) {
				out.nullValue();
			} else if (value.isJsonPrimitive()) {
				JsonPrimitive primitive = value.getAsJsonPrimitive();
				if (primitive.isNumber()) {
					out.value(primitive.getAsNumber());
				} else if (primitive.isBoolean()) {
					out.value(primitive.getAsBoolean());
				} else {
					out.value(primitive.getAsString());
				}

			} else if (value.isJsonArray()) {
				out.beginArray();
				for (JsonElement e : value.getAsJsonArray()) {
					write(out, e);
				}
				out.endArray();

			} else if (value.isJsonObject()) {
				out.beginObject();
				for (Map.Entry<String, JsonElement> e : value.getAsJsonObject().entrySet()) {
					out.name(e.getKey());
					write(out, e.getValue());
				}
				out.endObject();

			} else {
				throw new IllegalArgumentException("Couldn't write " + value.getClass());
			}
		}

		@Override
		public JsonArrayBuilder<P, R> addAll(Iterable<? extends JsonBuilder> builders) {
			for (JsonBuilder o : builders) {
				add(o);
			}
			return this;
		}

		@Override
		public <T> JsonArrayBuilder<P, R> addAll(Mapper<T> transform, Iterable<T> objects) {
			for (T o : objects) {
				add(transform.map(o));
			}
			return this;
		}

		@Override
		public JsonObjectBuilder<P, R> add(String key, Temporal value) {
			JsonObject obj = (JsonObject) context;
			if (value == null) {
				obj.add(key, JsonNull.INSTANCE);
			} else {
				obj.add(key, new JsonPrimitive(value.toString()));
			}
			return this;
		}

		@Override
		public JsonObjectBuilder<P, R> add(String key, Date value) {
			JsonObject obj = (JsonObject) context;
			if (value == null) {
				obj.add(key, JsonNull.INSTANCE);
			} else {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
				dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
				String isoDate = dateFormat.format(value);
				obj.add(key, new JsonPrimitive(isoDate));
			}
			return this;
		}

		@Override
		public JsonArrayBuilder<P, R> add(Date value) {
			JsonArray array = ((JsonArray) context);
			if (value == null) {
				array.add(JsonNull.INSTANCE);
			} else {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
				dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
				String isoDate = dateFormat.format(value);
				array.add(new JsonPrimitive(isoDate));
			}
			return this;
		}

		@Override
		public JsonArrayBuilder<P, R> add(Temporal value) {
			JsonArray array = ((JsonArray) context);
			if (value == null) {
				array.add(JsonNull.INSTANCE);
			} else {
				array.add(new JsonPrimitive(value.toString()));
			}
			return this;
		}
	}

	/**
	 * Build a primitive for use in arrays
	 * @param o The value
	 * @return the new builder
	 */
	public static JsonBuilder buildPrimitive(Number o) {
		return new JsonPrimitiveBuilder(o);
	}

	/**
	 * Build a primitive for use in arrays
	 * @param o The value
	 * @return the new builder
	 */
	public static JsonBuilder buildPrimitive(Character o) {

		return new JsonPrimitiveBuilder(o);
	}

	/**
	 * Build a primitive for use in arrays
	 * @param o The value
	 * @return the new builder
	 */
	public static JsonBuilder buildPrimitive(Boolean o) {

		return new JsonPrimitiveBuilder(o);
	}

	/**
	 * Build a primitive for use in arrays
	 * @param o The value
	 * @return the new builder
	 */
	public static JsonBuilder buildPrimitive(String o) {

		return new JsonPrimitiveBuilder(o);
	}

}
