package net.sf.anathema.test.character.generic.persistence.magic.load;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.DurationBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.duration.UntilEventDuration;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.junit.Test;

public class DurationBuilderTest{

  private DurationBuilder builder= new DurationBuilder();

  @Test
  public void testAnyDuration() throws Exception {
    Element durationElement = getDurationElement();
    String text = "One Tudeldu"; //$NON-NLS-1$
    durationElement.addAttribute("duration", text); //$NON-NLS-1$
    IDuration duration = builder.buildDuration(durationElement);
    assertFalse(duration == SimpleDuration.INSTANT_DURATION);
    assertFalse(duration == SimpleDuration.PERMANENT_DURATION);
    assertEquals(text, ((SimpleDuration) duration).getText());
  }

  private Element getDurationElement() {
    return new DefaultElement("duration"); //$NON-NLS-1$
  }
  @Test
  public void testInstantDuration() throws Exception {
    Element durationElement = getDurationElement();
    String text = "Instant"; //$NON-NLS-1$
    durationElement.addAttribute("duration", text); //$NON-NLS-1$
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(SimpleDuration.INSTANT_DURATION, duration);
  }
  @Test
  public void testPermanentDuration() throws Exception {
    Element durationElement = getDurationElement();
    String text = "Permanent"; //$NON-NLS-1$
    durationElement.addAttribute("duration", text); //$NON-NLS-1$
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(SimpleDuration.PERMANENT_DURATION, duration);
  }
  @Test
  public void testQualifiedAmountDuration() throws Exception {
    Element durationElement = getDurationElement();
    String amount = "this amount"; //$NON-NLS-1$
    String unit = "that unit"; //$NON-NLS-1$
    durationElement.addAttribute("amount", amount); //$NON-NLS-1$
    durationElement.addAttribute("unit", unit); //$NON-NLS-1$
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(new QualifiedAmountDuration(amount, unit), duration);
  }
  @Test
  public void testUntilEventDuration() throws Exception {
    Element durationElement = getDurationElement();
    String event = "an event"; //$NON-NLS-1$
    durationElement.addAttribute("event", event); //$NON-NLS-1$
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(new UntilEventDuration(event), duration);
  }
  @Test(expected=CharmException.class)
  public void testNoDurationElement() throws Exception {
        builder.buildDuration(null);
  }
  @Test(expected=PersistenceException.class)
  public void testNoUsefullDurationAttribute() throws Exception {
        builder.buildDuration(getDurationElement());
  }
}