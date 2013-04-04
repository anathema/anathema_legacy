package net.sf.anathema.character.generic.persistence.load.load;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DurationBuilderTest{

  private DurationBuilder builder= new DurationBuilder();

  @Test
  public void testAnyDuration() throws Exception {
    Element durationElement = getDurationElement();
    String text = "One Tudeldu";
    durationElement.addAttribute("duration", text);
    IDuration duration = builder.buildDuration(durationElement);
    assertFalse(duration == SimpleDuration.INSTANT_DURATION);
    assertFalse(duration == SimpleDuration.PERMANENT_DURATION);
    assertEquals(text, ((SimpleDuration) duration).getText());
  }

  private Element getDurationElement() {
    return new DefaultElement("duration");
  }
  @Test
  public void testInstantDuration() throws Exception {
    Element durationElement = getDurationElement();
    String text = "Instant";
    durationElement.addAttribute("duration", text);
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(SimpleDuration.INSTANT_DURATION, duration);
  }
  @Test
  public void testPermanentDuration() throws Exception {
    Element durationElement = getDurationElement();
    String text = "Permanent";
    durationElement.addAttribute("duration", text);
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(SimpleDuration.PERMANENT_DURATION, duration);
  }
  @Test
  public void testQualifiedAmountDuration() throws Exception {
    Element durationElement = getDurationElement();
    String amount = "this amount";
    String unit = "that unit";
    durationElement.addAttribute("amount", amount);
    durationElement.addAttribute("unit", unit);
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(new QualifiedAmountDuration(amount, unit), duration);
  }
  @Test
  public void testUntilEventDuration() throws Exception {
    Element durationElement = getDurationElement();
    String event = "an event";
    durationElement.addAttribute("event", event);
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