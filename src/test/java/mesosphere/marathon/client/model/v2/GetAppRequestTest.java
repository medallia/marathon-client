package mesosphere.marathon.client.model.v2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetAppRequestTest {

    private GetAppRequest.Expander instance;

    @Before
    public void setUp() {
        instance = new GetAppRequest.Expander();
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidTypeThrowsException() {
        instance.expand(new Object());
    }

    @Test
    public void nullParams() {
        assertEquals("", instance.expand(new GetAppRequest(null, null)));
    }

    @Test
    public void cmd() {
        assertEquals("cmd=cmd", instance.expand(new GetAppRequest("cmd")));
    }

    @Test
    public void cmdAndSingleEmbed() {
        assertEquals("cmd=cmd&embed=value1", instance.expand(new GetAppRequest("cmd", new String[]{"value1"})));
    }

    @Test
    public void cmdAndTwoEmbeds() {
        assertEquals("cmd=cmd&embed=value1&embed=value2", instance.expand(new GetAppRequest("cmd", new String[]{"value1", "value2"})));
    }

    @Test
    public void noEmbed() {
        assertEquals("", instance.expand(new GetAppRequest(new String[] {})));
    }

    @Test
    public void singleEmbed() {
        assertEquals("embed=value1", instance.expand(new GetAppRequest(new String[] {"value1"})));
    }

    @Test
    public void twoEmbeds() {
        assertEquals("embed=value1&embed=value2", instance.expand(new GetAppRequest(new String[] {"value1", "value2"})));
    }
}
