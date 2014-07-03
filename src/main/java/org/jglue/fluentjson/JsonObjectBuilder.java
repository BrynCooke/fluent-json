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

import java.time.temporal.Temporal;
import java.util.Date;



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
    public JsonObjectBuilder<P, R> add(String key, Iterable<? extends JsonBuilder> builders);
    

    /**
     * Add an array of elements assigned to a key.
     * 
     * @param key The key for the new element.
     * @param transform The transformer for the object.
     * @param objects The objects to add.
     * @param <T> The type of the objects
     * @return The current builder.
     */
    public <T> JsonObjectBuilder<P, R> add(String key, Mapper<T> transform, Iterable<T> objects);
    
    

    /**
     * Add a single element assigned to a key.
     * 
     * @param key The key for the new element.
     * @param builder The builder for the element.
     * @return The current builder.
     */
    public JsonObjectBuilder<P, R> add(String key, JsonBuilder builder);
    
    /**
     * Add a single element assigned to a key.
     * 
     * @param key The key for the new element.
     * @param transform The transformer for the object.
     * @param objects The objects to add.
     * @param <T> The objects type.
     * @return The current builder.
     */
    public <T> JsonObjectBuilder<P, R> add(String key, Mapper<T> transform, T... objects);

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
     * Add a simple property
     * 
     * @param key The key for the property.
     * @param value the value to assign
     * @return the current builder.
     */
    public JsonObjectBuilder<P, R> add(String key, Temporal value);

    
    /**
     * Add a simple property
     * 
     * @param key The key for the property.
     * @param value the value to assign
     * @return the current builder.
     */
    public JsonObjectBuilder<P, R> add(String key, Date value);



	/**
	 * @return the underlying JSONSimple object.
	 */
	public R getJson();
}