package hr.dlatecki.algorithms.gen_alg.exceptions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.Assert;
import org.junit.Test;

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
     * Tests the serlialization.
     * 
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        
        FitnessNotEvaluatedException toSend = new FitnessNotEvaluatedException(MESSAGE);
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(byteOutput));
        
        out.writeObject(toSend);
        out.flush();
        
        byte[] outputBytes = byteOutput.toByteArray();
        
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(outputBytes)));
        Object recieved = in.readObject();
        Assert.assertTrue(recieved instanceof FitnessNotEvaluatedException);
        Assert.assertEquals(MESSAGE, ((FitnessNotEvaluatedException) recieved).getMessage());
    }
}
