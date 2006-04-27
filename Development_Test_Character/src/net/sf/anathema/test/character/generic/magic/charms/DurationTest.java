package net.sf.anathema.test.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.lib.testing.BasicTestCase;

public class DurationTest extends BasicTestCase {

  public void testInstantDuration() throws Exception {
    Duration duration = Duration.getDuration("Instant"); //$NON-NLS-1$
    assertEquals(Duration.INSTANT_DURATION, duration);
  }

  public void testOtherDuration() throws Exception {
    Duration duration = Duration.getDuration("Anything"); //$NON-NLS-1$
    assertEquals(DurationType.Other, duration.getType());
  }
}