package net.sf.anathema.test.character.generic.persistence.magic.load;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.SourceBuilder;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class SourceBuilderTest extends BasicTestCase {

  public void testCasteBookAir() throws Exception {
    String xml = "<parent><source source=\"ABAir\" /></parent>";//$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    SourceBuilder sourceBuilder = new SourceBuilder();
    IExaltedSourceBook[] sources = sourceBuilder.buildSourceList(rootElement);
    assertEquals(new IExaltedSourceBook[] { ExaltedSourceBook.ABAir }, sources);
  }
}