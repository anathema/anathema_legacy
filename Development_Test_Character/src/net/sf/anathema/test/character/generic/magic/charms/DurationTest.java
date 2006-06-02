package net.sf.anathema.test.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.lib.testing.BasicTestCase;

public class DurationTest extends BasicTestCase {

  public void testInstantDuration() throws Exception {
    Duration duration = Duration.getDuration("Instant"); //$NON-NLS-1$
    assertEquals(Duration.INSTANT_DURATION, duration);
  }

  public void testOtherDuration() throws Exception {
    Duration duration = Duration.getDuration("OtherDuration"); //$NON-NLS-1$
    assertFalse(Duration.INSTANT_DURATION == duration);
    assertFalse(Duration.PERMANENT_DURATION == duration);
    assertEquals("OtherDuration", duration.getText()); //$NON-NLS-1$
  }

  public void testPermanentDuration() throws Exception {
    Duration duration = Duration.getDuration("Permanent"); //$NON-NLS-1$
    assertEquals(Duration.PERMANENT_DURATION, duration);
  }
}