package org.jglue.jsonfluent;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestBuilderFactory {
	@Test
	public void testBuildObject() throws IOException, ParseException {

		JsonBuilderFactory s = new JsonBuilderFactory();

		JsonObject jsonObject = s.buildObject()
					.add("Prop1", "1")
					.add("Prop2", 2)
					.addNull("Prop3")
					.add("Prop4", (String)null)
					.addObject("Prop5")
						.add("NP1", 4)
						.end()
					.addArray("Foo")
						.addObject()
						.end()
						.add("AE1")
					.end()
				.getJson();
		
		JsonElement test = new JsonParser().parse("{\"Prop1\":\"1\",\"Prop2\":2,\"Prop3\":null,\"Prop4\":null,\"Prop5\":{\"NP1\":4},\"Foo\":[{},\"AE1\"]}");
		Assert.assertEquals(test, jsonObject);
	}
	
	@Test
	public void testBuildArray() throws IOException, ParseException {

		JsonBuilderFactory s = new JsonBuilderFactory();

		JsonArray jsonObject = s.buildArray()
					.add("1")
					.add(2)
					.addNull()
					.add((String)null)
					.addObject()
						.add("NP1", 4)
						.end()
					.addArray()
						.addObject()
						.end()
						.add("AE1")
					.end()
				.getJson();
		
		JsonElement test = new JsonParser().parse("[\"1\",2,null,null,{\"NP1\":4},[{},\"AE1\"]]");
		Assert.assertEquals(test, jsonObject);
	}

}
