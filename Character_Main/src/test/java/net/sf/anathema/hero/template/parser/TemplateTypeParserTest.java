package net.sf.anathema.hero.template.parser;

import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.xml.TemplateTypeParser;
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
  public void discoversType() throws Exception {
    String xml = "<template characterType=\"Dummy\" />";
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals(type, templateType.getCharacterType());
  }

  @Test
  public void testParseWithoutSubtemplateSpecification() throws Exception {
    String xml = "<template characterType=\"Dummy\" />";
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals(TemplateType.DEFAULT_SUB_TYPE, templateType.getSubType());
  }

  @Test
  public void testParseWithSpecifiedSubtemplate() throws Exception {
    String xml = "<template characterType=\"Dummy\" subtemplate=\"special\"/>";
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals("special", templateType.getSubType().getId());
  }

  @Test
  public void testParseWithSpecifiedDefaultSubtemplate() throws Exception {
    String xml = "<template characterType=\"Dummy\" subtemplate=\"default\"/>";
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals(TemplateType.DEFAULT_SUB_TYPE, templateType.getSubType());
  }
}