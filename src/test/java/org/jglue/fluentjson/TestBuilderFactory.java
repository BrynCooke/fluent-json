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
import java.io.StringWriter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.jglue.fluentjson.AbstractMapper;
import org.jglue.fluentjson.JsonBuilder;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.jglue.fluentjson.JsonObjectBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

public class TestBuilderFactory {
	@Test
	public void testBuildObject() throws IOException, ParseException {

		JsonObject jsonObject = JsonBuilderFactory.buildObject()
				.addNull("null").add("string", "1")
				.add("stringNull", (String) null).add("number", 2)
				.add("numberNull", (Float) null).add("boolean", true)
				.add("booleanNull", (Boolean) null).add("character", 'c')
				.add("characterNull", (Character) null).addObject("obj")
				.add("NP1", 4).end().addArray("arr").addObject().end()
				.add("AE1").end().getJson();

		JsonElement test = new JsonParser()
				.parse("{\"null\":null, \"string\":\"1\",\"stringNull\":null,\"number\":2,\"numberNull\":null,\"boolean\":true,\"booleanNull\":null,\"character\":'c',\"characterNull\":null,\"obj\":{\"NP1\":4},\"arr\":[{},\"AE1\"]}");
		Assert.assertEquals(test, jsonObject);

	}

	@Test
	public void testSerialization() throws IOException, ParseException {

		JsonObjectBuilder<?, JsonObject> builder = JsonBuilderFactory
				.buildObject().add("Prop1", "1").add("Prop2", 2)
				.addNull("Prop3").add("Prop4", (String) null)
				.addObject("Prop5").add("NP1", 4).end().addArray("Foo")
				.addObject().end().add("AE1").end();

		StringWriter writer = new StringWriter();
		StringWriter writer2 = new StringWriter();
		JsonWriter jsonWriter = new JsonWriter(writer2);
		builder.write(writer);
		builder.write(jsonWriter);
		Assert.assertEquals(
				"{\"Prop1\":\"1\",\"Prop2\":2,\"Prop3\":null,\"Prop4\":null,\"Prop5\":{\"NP1\":4},\"Foo\":[{},\"AE1\"]}",
				builder.toString());
		Assert.assertEquals(
				"{\"Prop1\":\"1\",\"Prop2\":2,\"Prop3\":null,\"Prop4\":null,\"Prop5\":{\"NP1\":4},\"Foo\":[{},\"AE1\"]}",
				writer.toString());
		Assert.assertEquals(
				"{\"Prop1\":\"1\",\"Prop2\":2,\"Prop3\":null,\"Prop4\":null,\"Prop5\":{\"NP1\":4},\"Foo\":[{},\"AE1\"]}",
				writer2.toString());

	}

	@Test
	public void testBuildArray() throws IOException, ParseException {

		JsonArray jsonObject = JsonBuilderFactory.buildArray().addNull()
				.add("1").add((String) null).add(2).add((Float) null).add('c')
				.add((Character) null).add(true).add((Boolean) null)
				.addObject().add("NP1", 4).end().addArray().addObject().end()
				.add("AE1").end().getJson();

		JsonElement test = new JsonParser()
				.parse("[null, \"1\", null, 2, null, 'c', null, true, null,{\"NP1\":4},[{},\"AE1\"]]");
		Assert.assertEquals(test, jsonObject);
	}

	public static class A {
		String b = "hello";
		String c = "world";
	}

	@Test
	public void testTransformationObject() throws IOException, ParseException {
		A a = new A();

		JsonObject jsonObject = JsonBuilderFactory.buildObject()
				.add("value", a, new AbstractMapper<A>() {

					@Override
					public JsonBuilder map(A t) {
						return buildObject().add("b", t.b).add("c", t.c);
					}
				}).getJson();
		JsonElement test = new JsonParser()
				.parse("{\"value\":{\"b\":\"hello\",\"c\":\"world\"}}");
		Assert.assertEquals(test, jsonObject);
	}

	@Test
	public void testTransformationArrayInObject() throws IOException,
			ParseException {
		List<A> aList = Arrays.asList(new A[] { new A(), new A() });

		JsonObject jsonObject = JsonBuilderFactory.buildObject()
				.add("value", aList, new AbstractMapper<A>() {

					@Override
					public JsonBuilder map(A t) {
						return buildObject().add("b", t.b).add("c", t.c);
					}
				}).getJson();
		JsonElement test = new JsonParser()
				.parse("{\"value\":[{\"b\":\"hello\",\"c\":\"world\"}, {\"b\":\"hello\",\"c\":\"world\"}]}");
		Assert.assertEquals(test, jsonObject);
	}

	@Test
	public void testTransformationObjectInArray() throws IOException,
			ParseException {

		JsonArray jsonArray = JsonBuilderFactory.buildArray()
				.add(new A(), new AbstractMapper<A>() {

					@Override
					public JsonBuilder map(A t) {
						return buildObject().add("b", t.b).add("c", t.c);
					}
				}).getJson();
		JsonElement test = new JsonParser()
				.parse("[{\"b\":\"hello\",\"c\":\"world\"}]");
		Assert.assertEquals(test, jsonArray);
	}

	@Test
	public void testTransformationArrayInArray() throws IOException,
			ParseException {
		List<A> aList = Arrays.asList(new A[] { new A(), new A() });

		JsonArray jsonArray = JsonBuilderFactory.buildArray()
				.add(aList, new AbstractMapper<A>() {

					@Override
					public JsonBuilder map(A t) {
						return buildObject().add("b", t.b).add("c", t.c);
					}
				}).getJson();
		JsonElement test = new JsonParser()
				.parse("[[{\"b\":\"hello\",\"c\":\"world\"}, {\"b\":\"hello\",\"c\":\"world\"}]]");
		Assert.assertEquals(test, jsonArray);
	}

	@Test
	public void testTransformationArray() throws IOException, ParseException {
		List<A> aList = Arrays.asList(new A[] { new A(), new A() });

		JsonArray jsonArray = JsonBuilderFactory.buildArray(aList,
				new AbstractMapper<A>() {

					@Override
					public JsonBuilder map(A t) {
						return buildObject().add("b", t.b).add("c", t.c);
					}
				}).getJson();
		JsonElement test = new JsonParser()
				.parse("[{\"b\":\"hello\",\"c\":\"world\"}, {\"b\":\"hello\",\"c\":\"world\"}]");
		Assert.assertEquals(test, jsonArray);
	}
	
	@Test
	public void testAddJsonBuildersOnArray() throws IOException, ParseException {
		List<? extends JsonBuilder> aList = Arrays.asList(JsonBuilderFactory.buildObject().add("b", "hello").add("c", "world"), JsonBuilderFactory.buildObject().add("b", "hello").add("c", "world"));

		JsonArray jsonArray = JsonBuilderFactory.buildArray().add(aList).getJson();
		JsonElement test = new JsonParser()
				.parse("[[{\"b\":\"hello\",\"c\":\"world\"}, {\"b\":\"hello\",\"c\":\"world\"}]]");
		Assert.assertEquals(test, jsonArray);
	}
	@Test
	public void testAddJsonBuildersOnObject() throws IOException, ParseException {
		List<? extends JsonBuilder> aList = Arrays.asList(JsonBuilderFactory.buildObject().add("b", "hello").add("c", "world"), JsonBuilderFactory.buildObject().add("b", "hello").add("c", "world"));

		JsonObject jsonArray = JsonBuilderFactory.buildObject().add("val", aList).getJson();
		JsonElement test = new JsonParser()
				.parse("{\"val\":[{\"b\":\"hello\",\"c\":\"world\"}, {\"b\":\"hello\",\"c\":\"world\"}]}");
		Assert.assertEquals(test, jsonArray);
	}
	
	@Test
	public void testAddAllJsonBuildersOnArray() throws IOException, ParseException {
		List<? extends JsonBuilder> aList = Arrays.asList(JsonBuilderFactory.buildObject().add("b", "hello").add("c", "world"), JsonBuilderFactory.buildObject().add("b", "hello").add("c", "world"));

		JsonArray jsonArray = JsonBuilderFactory.buildArray().addAll(aList).getJson();
		JsonElement test = new JsonParser()
				.parse("[{\"b\":\"hello\",\"c\":\"world\"}, {\"b\":\"hello\",\"c\":\"world\"}]");
		Assert.assertEquals(test, jsonArray);
	}
	
	@Test
	public void testTransformationArrayAddAll() throws IOException,
			ParseException {
		List<A> aList = Arrays.asList(new A[] { new A(), new A() });

		JsonArray jsonArray = JsonBuilderFactory.buildArray()
				.addAll(aList, new AbstractMapper<A>() {

					@Override
					public JsonBuilder map(A t) {
						return buildObject().add("b", t.b).add("c", t.c);
					}
				}).getJson();
		JsonElement test = new JsonParser()
				.parse("[{\"b\":\"hello\",\"c\":\"world\"}, {\"b\":\"hello\",\"c\":\"world\"}]");
		Assert.assertEquals(test, jsonArray);
	}
}
