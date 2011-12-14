package net.sf.anathema.character.generic.persistence.load.load;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.SourceBuilder;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SourceBuilderTest {

  @Test
  public void testCasteBookAir() throws Exception {
    String xml = "<parent><source source=\"ABAir\" /></parent>";//$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    SourceBuilder sourceBuilder = new SourceBuilder();
    IExaltedSourceBook[] sources = sourceBuilder.buildSourceList(rootElement);
    assertArrayEquals(new IExaltedSourceBook[]{ExaltedSourceBook.ABAir}, sources);
  }
}