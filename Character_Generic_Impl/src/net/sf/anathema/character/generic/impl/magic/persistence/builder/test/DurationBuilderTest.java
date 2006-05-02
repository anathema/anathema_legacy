package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.DurationBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.DurationType;
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
    Duration duration = builder.buildDuration(durationElement);
    assertEquals(DurationType.Other, duration.getType());
    assertEquals(text, duration.getText(null));
  }

  private Element getDurationElement() {
    return new DefaultElement("duration"); //$NON-NLS-1$
  }

  public void testInstantDuration() throws Exception {
    Element durationElement = getDurationElement();
    String text = "Instant"; //$NON-NLS-1$
    durationElement.addAttribute("duration", text); //$NON-NLS-1$
    Duration duration = builder.buildDuration(durationElement);
    assertEquals(Duration.INSTANT_DURATION, duration);
  }

  public void testNoDurationElement() throws Exception {
    assertThrowsException(CharmException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        builder.buildDuration(null);
      }
    });
  }

  public void testNoDurationAttribute() throws Exception {
    assertThrowsException(PersistenceException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        builder.buildDuration(getDurationElement());
      }
    });
  }
}