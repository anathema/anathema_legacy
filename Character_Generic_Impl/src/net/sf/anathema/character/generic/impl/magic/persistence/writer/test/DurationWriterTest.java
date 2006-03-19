package net.sf.anathema.character.generic.impl.magic.persistence.writer.test;

import junit.framework.TestCase;

import net.sf.anathema.character.generic.impl.magic.persistence.writer.DurationWriter;
import net.sf.anathema.character.generic.impl.magic.test.DummyCharm;
import net.sf.anathema.character.generic.magic.charms.Duration;

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
    charm.setDuration(Duration.getDuration(expectedDuration));
    writer.write(charm, element);
    assertEquals(expectedDuration, element.element("duration").attributeValue("duration")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void testWriteInstantDuration() throws Exception {
    String expectedDuration = "Instant"; //$NON-NLS-1$
    charm.setDuration(Duration.getDuration(expectedDuration));
    writer.write(charm, element);
    assertEquals(expectedDuration, element.element("duration").attributeValue("duration")); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
