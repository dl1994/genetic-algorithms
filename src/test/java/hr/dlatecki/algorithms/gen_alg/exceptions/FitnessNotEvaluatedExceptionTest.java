/* 
 * The MIT License (MIT)
 * 
 * Copyright © 2016 Domagoj Latečki
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hr.dlatecki.algorithms.gen_alg.exceptions;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

/**
 * Class which contains tests for <code>FitnessNotEvaluatedException</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see FitnessNotEvaluatedException
 */
public class FitnessNotEvaluatedExceptionTest {
    
    /**
     * Message used in tests.
     */
    private static final String MESSAGE = "testMessage";
    
    /**
     * Tests the constructors.
     */
    @Test
    public void testConstructors() {
        
        FitnessNotEvaluatedException a = new FitnessNotEvaluatedException();
        Assert.assertNull(a.getMessage());
        FitnessNotEvaluatedException b = new FitnessNotEvaluatedException(MESSAGE);
        Assert.assertEquals(MESSAGE, b.getMessage());
    }
    
    /**
     * Tests the serialization.
     * 
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        
        FitnessNotEvaluatedException toSend = new FitnessNotEvaluatedException(MESSAGE);
        Object recieved = TestUtilities.serializeDeserialize(toSend);
        Assert.assertTrue(recieved instanceof FitnessNotEvaluatedException);
        Assert.assertEquals(MESSAGE, ((FitnessNotEvaluatedException) recieved).getMessage());
    }
}
