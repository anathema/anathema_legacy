package net.sf.anathema.character.generic.impl.magic.persistence.writer.test;

import net.sf.anathema.character.generic.impl.magic.MagicSource;
import net.sf.anathema.character.generic.impl.magic.persistence.writer.SourceWriter;
import net.sf.anathema.character.generic.impl.magic.test.DummyCharm;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class SourceWriterTest extends BasicTestCase {

  private SourceWriter writer;
  private Element element;
  private DummyCharm charm;

  @Override
  protected void setUp() throws Exception {
    writer = new SourceWriter();
    element = DocumentFactory.getInstance().createElement("charm"); //$NON-NLS-1$    
    charm = new DummyCharm();
  }

  public void testNullSource() throws Exception {
    charm.setSource(new MagicSource(null, null));
    writer.write(charm, element);
    assertEquals(null, getSourceElement());
  }

  public void testCustomSource() throws Exception {
    charm.setSource(new MagicSource("Custom", null)); //$NON-NLS-1$
    writer.write(charm, element);
    assertEquals(null, getSourceElement());
  }

  public void testFullSource() throws Exception {
    String expectedBook = "Larifari";//$NON-NLS-1$
    String expectedPage = "183";//$NON-NLS-1$ 
    charm.setSource(new MagicSource(expectedBook, expectedPage));
    writer.write(charm, element);
    assertEquals(expectedBook, getSource());
    assertEquals(expectedPage, getPage());
  }

  private String getSource() {
    return getSourceElement().attributeValue("source"); //$NON-NLS-1$
  }

  private Element getSourceElement() {
    return element.element("source"); //$NON-NLS-1$
  }

  private String getPage() {
    return getSourceElement().attributeValue("page"); //$NON-NLS-1$
  }
}