package net.sf.anathema.character.generic.impl.magic.persistence.writer.test;

import net.sf.anathema.character.generic.impl.magic.persistence.writer.CharmTypeWriter;
import net.sf.anathema.character.generic.impl.magic.test.DummyCharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class CharmTypeWriterTest extends BasicTestCase {
  private CharmTypeWriter writer;
  private Element element;
  private DummyCharm charm;

  @Override
  protected void setUp() throws Exception {
    writer = new CharmTypeWriter();
    element = DocumentFactory.getInstance().createElement("charm"); //$NON-NLS-1$    
    charm = new DummyCharm();
  }

  public void testWriteType() throws Exception {
    CharmType expectedType = CharmType.ExtraAction;
    charm.setCharmType(expectedType);
    writer.write(charm, element);
    assertEquals(expectedType.getId(), element.element("charmtype").attributeValue("type")); //$NON-NLS-1$//$NON-NLS-2$
  }

  public void testWriteAnotherType() throws Exception {
    CharmType expectedType = CharmType.Simple;
    charm.setCharmType(expectedType);
    writer.write(charm, element);
    assertEquals(expectedType.getId(), element.element("charmtype").attributeValue("type")); //$NON-NLS-1$//$NON-NLS-2$
  }
}
