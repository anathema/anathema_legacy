package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.*;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TemplateTypeParserTest {

  public CharacterTypes types = new HardcodedCharacterTypes();

  @Test
  public void testParseWithoutSubtemplateSpecification() throws Exception {
    String xml = "<template characterType=\"Mortal\" />"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals(new MortalCharacterType(), templateType.getCharacterType());
    assertEquals(TemplateType.DEFAULT_SUB_TYPE, templateType.getSubType());
  }

  @Test
  public void testParseWithSpecifiedSubtemplate() throws Exception {
    String xml = "<template characterType=\"Solar\" subtemplate=\"special\"/>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals(new SolarCharacterType(), templateType.getCharacterType());
    assertEquals("special", templateType.getSubType().getId()); //$NON-NLS-1$
  }

  @Test
  public void testParseWithSpecifiedDefaultSubtemplate() throws Exception {
    String xml = "<template characterType=\"Lunar\" subtemplate=\"default\"/>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser(types).parse(element);
    assertEquals(new LunarCharacterType(), templateType.getCharacterType());
    assertEquals(TemplateType.DEFAULT_SUB_TYPE, templateType.getSubType());
  }
}