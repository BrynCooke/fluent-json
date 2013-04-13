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



/**
 * Fluent builder for json arrays.
 * @author Bryn Cooke
 *
 * @param <P> The parent type.
 * @param <R> The root type.
 */
public interface JsonArrayBuilder<P, R> extends JsonBuilder {
	/**
	 * Add a new object as the next element in this array.
	 * @return the builder for the new object.
	 */
	public JsonObjectBuilder<JsonArrayBuilder<P, R>, R> addObject();

	
	/**
	 * Add a new array as the next element in this array.
	 * @return the builder for the new array.
	 */
	public JsonArrayBuilder<JsonArrayBuilder<P, R>, R> addArray();

	/**
     * End the current object and return to building the parent element.
     * @return the builder
     */
	public P end();

	/**
	 * Add a single value to this array.
	 * 
	 * @param value the value to add.
	 * @return the current builder.
	 */
	public JsonArrayBuilder<P, R> add(Boolean value);
	
	/**
	 * Add a single value to this array.
	 * 
	 * @param value the value to add.
	 * @return the current builder.
	 */
	public JsonArrayBuilder<P, R> add(Character value);
	
	
	/**
	 * Add a single value to this array.
	 * 
	 * @param value the value to add.
	 * @return the current builder.
	 */
	public JsonArrayBuilder<P, R> add(Number value);
	
	
	/**
	 * Add a single value to this array.
	 * 
	 * @param value the value to add.
	 * @return the current builder.
	 */
	public JsonArrayBuilder<P, R> add(String value);
	/**
	 * Add a single value to this array.
	 * 
	 * @param value the value to add.
	 * @return the current builder.
	 */
	public JsonArrayBuilder<P, R> addNull();
	
	/**
	 * Add an array of elements.
	 * 
	 * @param builders the builders to get the elements from.
	 * @return the current builder.
	 */
	public JsonArrayBuilder<P, R> add(Iterable<? extends JsonBuilder> builders);
	
	/**
	 * Add a collection of elements to the current array.
	 * 
	 * @param builders the builders to get the elements from.
	 * @return the current builder.
	 */
	public JsonArrayBuilder<P, R> addAll(Iterable<? extends JsonBuilder> builders);

	/**
	 * Add a single element.
	 * 
	 * @param builder the builder for the element to add.
	 * @return the current builder.
	 */
	public JsonArrayBuilder<P, R> add(JsonBuilder builder);

	
	/**
	 * Add an array of elements.
	 * 
	 * @param builders the builders to get the elements from.
	 * @return the current builder.
	 */
	public <T> JsonArrayBuilder<P, R> add(Iterable<T> objects, Mapper<T> transform);
	
	/**
	 * Add a collection of elements to the current array.
	 * 
	 * @param builders the builders to get the elements from.
	 * @return the current builder.
	 */
	public <T> JsonArrayBuilder<P, R> addAll(Iterable<T> objects, Mapper<T> transform);

	/**
	 * Add a single element.
	 * 
	 * @param object the object to add.
	 * @return the current builder.
	 */
	public <T> JsonArrayBuilder<P, R> add(T object, Mapper<T> transform);

	
	/**
	 * @return the underlying JSONSimple object.
	 */
	public R getJson();
}