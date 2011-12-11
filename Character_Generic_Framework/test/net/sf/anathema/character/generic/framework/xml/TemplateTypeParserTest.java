package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;

public class TemplateTypeParserTest extends BasicTestCase {

  public void testParseWithoutSubtemplateSpecification() throws Exception {
    String xml = "<template characterType=\"Mortal\" />"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser().parse(element);
    assertEquals(CharacterType.MORTAL, templateType.getCharacterType());
    assertEquals(TemplateType.DEFAULT_SUB_TYPE, templateType.getSubType());
  }

  public void testParseWithSpecifiedSubtemplate() throws Exception {
    String xml = "<template characterType=\"Solar\" subtemplate=\"special\"/>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser().parse(element);
    assertEquals(CharacterType.SOLAR, templateType.getCharacterType());
    assertEquals("special", templateType.getSubType().getId()); //$NON-NLS-1$
  }

  public void testParseWithSpecifiedDefaultSubtemplate() throws Exception {
    String xml = "<template characterType=\"Lunar\" subtemplate=\"default\"/>"; //$NON-NLS-1$
    Element element = DocumentUtilities.read(xml).getRootElement();
    ITemplateType templateType = new TemplateTypeParser().parse(element);
    assertEquals(CharacterType.LUNAR, templateType.getCharacterType());
    assertEquals(TemplateType.DEFAULT_SUB_TYPE, templateType.getSubType());
  }
}