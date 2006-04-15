package net.sf.anathema.character.generic.impl.magic.persistence.writer.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.persistence.writer.KeywordWriter;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class KeywordWriterTest extends BasicTestCase {

  private KeywordWriter writer;
  private Element element;
  private List<ICharmAttribute> keywords;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.writer = new KeywordWriter();
    this.element = DocumentFactory.getInstance().createElement("charm"); //$NON-NLS-1$    
    this.keywords = new ArrayList<ICharmAttribute>();
  }

  public void testWriteNoKeywords() throws Exception {
    writer.write(keywords, element);
    assertNull(element.element("charmAttribute")); //$NON-NLS-1$
  }

  public void testWriteKeyword() throws Exception {
    keywords.add(new CharmAttribute("Test1", false)); //$NON-NLS-1$
    writer.write(keywords, element);
    assertEquals("Test1", element.element("charmAttribute").attributeValue("attribute")); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }

  public void testVisibilityCorrect() throws Exception {
    keywords.add(new CharmAttribute("Test1", true)); //$NON-NLS-1$
    writer.write(keywords, element);
    assertTrue(ElementUtilities.getBooleanAttribute(element.element("charmAttribute"), "visualize", false)); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }

  public void testWriteTwoKeywords() throws Exception {
    keywords.add(new CharmAttribute("Test1", false)); //$NON-NLS-1$
    keywords.add(new CharmAttribute("Test2", true)); //$NON-NLS-1$
    writer.write(keywords, element);
    assertEquals(2, element.elements("charmAttribute").size()); //$NON-NLS-1$
  }
}