package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.DurationBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.testing.ExceptionConvertingBlock;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

public class DurationBuilderTest extends BasicTestCase {

  private DurationBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    builder = new DurationBuilder();
  }

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

  public void testInstantDuration() throws Exception {
    Element durationElement = getDurationElement();
    String text = "Instant"; //$NON-NLS-1$
    durationElement.addAttribute("duration", text); //$NON-NLS-1$
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(SimpleDuration.INSTANT_DURATION, duration);
  }

  public void testPermanentDuration() throws Exception {
    Element durationElement = getDurationElement();
    String text = "Permanent"; //$NON-NLS-1$
    durationElement.addAttribute("duration", text); //$NON-NLS-1$
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(SimpleDuration.PERMANENT_DURATION, duration);
  }

  public void testQualifiedAmountDuration() throws Exception {
    Element durationElement = getDurationElement();
    String amount = "this amount"; //$NON-NLS-1$
    String unit = "that unit"; //$NON-NLS-1$
    durationElement.addAttribute("amount", amount); //$NON-NLS-1$
    durationElement.addAttribute("unit", unit); //$NON-NLS-1$
    IDuration duration = builder.buildDuration(durationElement);
    assertEquals(new QualifiedAmountDuration(amount, unit), duration);
  }

  public void testNoDurationElement() throws Exception {
    assertThrowsException(CharmException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        builder.buildDuration(null);
      }
    });
  }

  public void testNoUsefullDurationAttribute() throws Exception {
    assertThrowsException(PersistenceException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        builder.buildDuration(getDurationElement());
      }
    });
  }
}