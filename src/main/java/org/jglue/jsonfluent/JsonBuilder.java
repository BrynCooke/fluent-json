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
import java.io.Writer;

/**
 * Base type for fluent builders.
 * @author Bryn Cooke
 *
 */
public interface JsonBuilder {
    
	/**
     * Write the JSON to a writer. Not that order of elements is not guaranteed.
     * @param out The outout writer.
     * @throws IOException
     */
    public void write(Writer out) throws IOException;
    
    /**
     * @return The serialized JSON as a string.
     */
    @Override
    public String toString();
}