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