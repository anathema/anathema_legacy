package net.sf.anathema.character.generic.impl.magic.persistence.writer.test;

import net.sf.anathema.character.generic.impl.magic.persistence.writer.HeadDataWriter;
import net.sf.anathema.character.generic.impl.magic.test.DummyCharm;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class HeadDataWriterTest extends BasicTestCase {

  private HeadDataWriter writer;
  private Element element;

  @Override
  protected void setUp() throws Exception {
    writer = new HeadDataWriter();
    element = DocumentFactory.getInstance().createElement("charm"); //$NON-NLS-1$    
  }

  public void testWriteCompleteData() throws Exception {
    String expectedId = "Charm"; //$NON-NLS-1$
    DummyCharm charm = new DummyCharm(expectedId);
    CharacterType expectedType = CharacterType.ABYSSAL;
    charm.setCharacterType(expectedType);
    String expectedGroup = "CharmGroup"; //$NON-NLS-1$
    charm.setGroupId(expectedGroup);
    writer.write(charm, element);
    assertEquals(expectedId, element.attributeValue("id")); //$NON-NLS-1$
    assertEquals(expectedType.getId(), element.attributeValue("exalt")); //$NON-NLS-1$
    assertEquals(expectedGroup, element.attributeValue("group")); //$NON-NLS-1$
  }
}