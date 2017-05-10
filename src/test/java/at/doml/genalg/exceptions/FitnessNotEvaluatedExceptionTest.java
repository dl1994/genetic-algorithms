package at.doml.genalg.exceptions;

import at.doml.genalg.testutils.TestUtilities;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

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
