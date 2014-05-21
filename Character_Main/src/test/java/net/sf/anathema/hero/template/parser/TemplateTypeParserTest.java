package net.sf.anathema.hero.template.parser;

import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.character.framework.xml.TemplateTypeParser;
import net.sf.anathema.hero.dummy.DummyCharacterTypes;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TemplateTypeParserTest {

  private DummyMundaneCharacterType type = new DummyMundaneCharacterType();
  private DummyCharacterTypes types = new DummyCharacterTypes();

  @Before
  public void setUp() throws Exception {
    types.add(type);
  }

  @Test
  public void testParseWithSpecifiedSubtemplate() throws Exception {
    String xml = "<template characterType=\"Dummy\" subtemplate=\"special\"/>";
    Element element = DocumentUtilities.read(xml).getRootElement();
    TemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals("special", templateType.getSubType().getId());
  }
}