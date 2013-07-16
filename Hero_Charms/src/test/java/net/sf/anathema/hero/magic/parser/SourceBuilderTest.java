package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.main.magic.model.source.SourceBook;
import net.sf.anathema.character.main.magic.model.source.SourceBookImpl;
import net.sf.anathema.character.main.magic.parser.magic.SourceBuilder;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SourceBuilderTest {

  @Test
  public void testCasteBookAir() throws Exception {
    String xml = "<parent><source source=\"ABAir\" /></parent>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    SourceBuilder sourceBuilder = new SourceBuilder();
    SourceBook[] sources = sourceBuilder.buildSourceList(rootElement);
    assertArrayEquals(new SourceBook[]{new SourceBookImpl("ABAir")}, sources);
  }
}