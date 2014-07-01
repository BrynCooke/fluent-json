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
 * Transform an object in to a builder.
 * @author Bryn Cooke
 *
 * @param <T> The type of the object to map.
 */
public interface Mapper<T> {

	/**
	 * Transform the object in to a builder.
	 * @param o the object to transform.
	 * @return The builder.
	 */
	public JsonBuilder map(T o);
	
}
