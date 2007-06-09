package net.sf.anathema.test.charm.entry.persistence;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.charmentry.persistence.KeywordWriter;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class KeywordWriterTest extends BasicTestCase {

  private KeywordWriter writer;
  private Element element;
  private DummyCharm charm;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.writer = new KeywordWriter();
    this.element = DocumentFactory.getInstance().createElement("charm"); //$NON-NLS-1$    
    this.charm = new DummyCharm();
  }

  public void testWriteNoKeywords() throws Exception {
    writer.write(charm, element);
    assertNull(element.element("charmAttribute")); //$NON-NLS-1$
  }

  public void testWriteKeyword() throws Exception {
    charm.addKeyword(new CharmAttribute("Test1", false)); //$NON-NLS-1$
    writer.write(charm, element);
    assertEquals("Test1", element.element("charmAttribute").attributeValue("attribute")); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }

  public void testVisibilityCorrect() throws Exception {
    charm.addKeyword(new CharmAttribute("Test1", true)); //$NON-NLS-1$
    writer.write(charm, element);
    assertTrue(ElementUtilities.getBooleanAttribute(element.element("charmAttribute"), "visualize", false)); //$NON-NLS-1$//$NON-NLS-2$
  }

  public void testWriteTwoKeywords() throws Exception {
    charm.addKeyword(new CharmAttribute("Test1", false)); //$NON-NLS-1$
    charm.addKeyword(new CharmAttribute("Test2", true)); //$NON-NLS-1$
    writer.write(charm, element);
    assertEquals(2, element.elements("charmAttribute").size()); //$NON-NLS-1$
  }
}