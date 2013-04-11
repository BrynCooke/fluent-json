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
package org.jglue.jsonfluent;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestBuilderFactory {
	@Test
	public void testBuildObject() throws IOException, ParseException {

		JsonObject jsonObject = JsonBuilderFactory.buildObject()
				.add("Prop1", "1").add("Prop2", 2).addNull("Prop3")
				.add("Prop4", (String) null).addObject("Prop5").add("NP1", 4)
				.end().addArray("Foo").addObject().end().add("AE1").end()
				.getJson();

		JsonElement test = new JsonParser()
				.parse("{\"Prop1\":\"1\",\"Prop2\":2,\"Prop3\":null,\"Prop4\":null,\"Prop5\":{\"NP1\":4},\"Foo\":[{},\"AE1\"]}");
		Assert.assertEquals(test, jsonObject);
	}

	@Test
	public void testBuildArray() throws IOException, ParseException {

		JsonArray jsonObject = JsonBuilderFactory.buildArray().add("1").add(2)
				.addNull().add((String) null).addObject().add("NP1", 4).end()
				.addArray().addObject().end().add("AE1").end().getJson();

		JsonElement test = new JsonParser()
				.parse("[\"1\",2,null,null,{\"NP1\":4},[{},\"AE1\"]]");
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
				.add("value", a, new AbstractTransform<A>() {

					@Override
					public JsonBuilder transform(A t) {
						return buildObject().add("b", t.b)
								.add("c", t.c);
					}
				}).getJson();
		JsonElement test = new JsonParser()
				.parse("{\"value\":{\"b\":\"hello\",\"c\":\"world\"}}");
		Assert.assertEquals(test, jsonObject);
	}
	
	@Test
	public void testTransformationArrayInObject() throws IOException, ParseException {
		List<A> aList = Arrays.asList(new A[]{new A(), new A()});

		JsonObject jsonObject = JsonBuilderFactory.buildObject()
				.add("value", aList, new AbstractTransform<A>() {

					@Override
					public JsonBuilder transform(A t) {
						return buildObject().add("b", t.b)
								.add("c", t.c);
					}
				}).getJson();
		JsonElement test = new JsonParser()
				.parse("{\"value\":[{\"b\":\"hello\",\"c\":\"world\"}, {\"b\":\"hello\",\"c\":\"world\"}]}");
		Assert.assertEquals(test, jsonObject);
	}
	
	@Test
	public void testTransformationArray() throws IOException, ParseException {
		List<A> aList = Arrays.asList(new A[]{new A(), new A()});

		JsonArray jsonArray = JsonBuilderFactory.buildArray(aList, new AbstractTransform<A>() {

					@Override
					public JsonBuilder transform(A t) {
						return buildObject().add("b", t.b)
								.add("c", t.c);
					}
				}).getJson();
		JsonElement test = new JsonParser()
				.parse("[{\"b\":\"hello\",\"c\":\"world\"}, {\"b\":\"hello\",\"c\":\"world\"}]");
		Assert.assertEquals(test, jsonArray);
	}
}
