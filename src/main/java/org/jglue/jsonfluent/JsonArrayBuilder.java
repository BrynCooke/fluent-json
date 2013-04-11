package org.jglue.jsonfluent;

import javax.lang.model.type.NullType;


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
	 * Add a single element.
	 * 
	 * @param builder
	 * @return the current builder.
	 */
	public JsonArrayBuilder<P, R> add(JsonBuilder builder);

	
	/**
	 * @return the underlying JSONSimple object.
	 */
	public R getJson();
}