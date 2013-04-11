package org.jglue.jsonfluent;



/**
 * Fluent builder for JSON objects.
 * @author Bryn Cooke
 *
 * @param <P> The parent type.
 * @param <R> The root type.
 */
public interface JsonObjectBuilder<P, R> extends JsonBuilder {
    /**
     * Add a new object to this object using the specified key. 
     * @param key The key.
     * @return The builder for the new object.
     */
    public JsonObjectBuilder<JsonObjectBuilder<P, R>, R> addObject(String key);

    /**
     * Add a new array to this object using the specified key.
     * @param key The key.
     * @return A builder for the new object.
     */
    public JsonArrayBuilder<JsonObjectBuilder<P, R>, R> addArray(String key);

    /**
     * End the current object and return to building the parent element.
     * @return the builder
     */
    public P end();

    
    /**
     * Add an array of elements assigned to a key.
     * 
     * @param key The key for the new element.
     * @param builders The builders for the elements.
     * @return The current builder.
     */
    public JsonObjectBuilder<P, R> add(String key, Iterable<JsonBuilder> builders);
    
    

    /**
     * Add a single element assigned to a key.
     * 
     * @param key The key for the new element.
     * @param builder The builder for the element.
     * @return The current builder.
     */
    public JsonObjectBuilder<P, R> add(String key, JsonBuilder builder);

    /**
     * Add a simple property
     * 
     * @param key The key for the property.
     * @param value the value to assign
     * @return the current builder.
     */
    public JsonObjectBuilder<P, R> add(String key, Boolean value);
    
    /**
     * Add a simple property
     * 
     * @param key The key for the property.
     * @param value the value to assign
     * @return the current builder.
     */
    public JsonObjectBuilder<P, R> add(String key, Character value);
    
    
    /**
     * Add a simple property
     * 
     * @param key The key for the property.
     * @param value the value to assign
     * @return the current builder.
     */
    public JsonObjectBuilder<P, R> add(String key, Number value);
    
    /**
     * Add a null property. Note that other add methods also accept null. 
     * 
     * @param key The key for the property.
     * @param value the value to assign
     * @return the current builder.
     */
    public JsonObjectBuilder<P, R> addNull(String key);
    
    /**
     * Add a simple property
     * 
     * @param key The key for the property.
     * @param value the value to assign
     * @return the current builder.
     */
    public JsonObjectBuilder<P, R> add(String key, String value);

	/**
	 * @return the underlying JSONSimple object.
	 */
	public R getJson();
}