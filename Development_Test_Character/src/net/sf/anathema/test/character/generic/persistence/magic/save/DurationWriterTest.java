package net.sf.anathema.test.character.generic.persistence.magic.save;

import junit.framework.TestCase;

import net.sf.anathema.character.generic.impl.magic.persistence.writer.DurationWriter;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.dummy.character.magic.DummyCharm;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class DurationWriterTest extends TestCase {
  private DurationWriter writer;
  private Element element;
  private DummyCharm charm;

  @Override
  protected void setUp() throws Exception {
    writer = new DurationWriter();
    element = DocumentFactory.getInstance().createElement("charm"); //$NON-NLS-1$    
    charm = new DummyCharm();
  }

  public void testWriteStandardDuration() throws Exception {
    String expectedDuration = "Expected duration"; //$NON-NLS-1$
    charm.setDuration(SimpleDuration.getDuration(expectedDuration));
    writer.write(charm, element);
    assertEquals(expectedDuration, element.element("duration").attributeValue("duration")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void testWriteInstantDuration() throws Exception {
    String expectedDuration = "Instant"; //$NON-NLS-1$
    charm.setDuration(SimpleDuration.getDuration(expectedDuration));
    writer.write(charm, element);
    assertEquals(expectedDuration, element.element("duration").attributeValue("duration")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void testWriteQualifiedAmountDuration() throws Exception {
    String expectedAmount = "testAmount"; //$NON-NLS-1$
    String expectedUnit = "testUnit"; //$NON-NLS-1$
    charm.setDuration(new QualifiedAmountDuration(expectedAmount, expectedUnit));
    writer.write(charm, element);
    final Element durationElement = element.element("duration"); //$NON-NLS-1$
    assertEquals(expectedAmount, durationElement.attributeValue("amount")); //$NON-NLS-1$
    assertEquals(expectedUnit, durationElement.attributeValue("unit")); //$NON-NLS-1$
  }
}