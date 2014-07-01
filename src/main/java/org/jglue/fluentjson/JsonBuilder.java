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

import com.google.gson.stream.JsonWriter;

/**
 * Base type for fluent builders.
 * @author Bryn Cooke
 *
 */
public interface JsonBuilder {
    
	/**
     * Write the JSON to a writer.
     * @param out The output writer.
     * @throws IOException if there was a problem
     */
    public void write(Writer out) throws IOException;
    
	/**
     * Write the JSON to a writer.
     * @param out The output writer.
     * @throws IOException if there was a problem
     */
    public void write(JsonWriter out) throws IOException;
    
    /**
     * @return The serialized JSON as a string.
     */
    @Override
    public String toString();
}