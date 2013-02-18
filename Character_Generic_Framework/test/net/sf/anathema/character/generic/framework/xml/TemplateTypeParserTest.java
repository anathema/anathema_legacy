package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.dummy.DummyCharacterTypes;
import net.sf.anathema.character.generic.dummy.DummyMundaneCharacterType;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
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
    String xml = "<template characterType=\"Dummy\" />"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals(type, templateType.getCharacterType());
  }


  @Test
  public void testParseWithoutSubtemplateSpecification() throws Exception {
    String xml = "<template characterType=\"Dummy\" />"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals(TemplateType.DEFAULT_SUB_TYPE, templateType.getSubType());
  }

  @Test
  public void testParseWithSpecifiedSubtemplate() throws Exception {
    String xml = "<template characterType=\"Dummy\" subtemplate=\"special\"/>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals("special", templateType.getSubType().getId()); //$NON-NLS-1$
  }

  @Test
  public void testParseWithSpecifiedDefaultSubtemplate() throws Exception {
    String xml = "<template characterType=\"Dummy\" subtemplate=\"default\"/>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals(TemplateType.DEFAULT_SUB_TYPE, templateType.getSubType());
  }
}