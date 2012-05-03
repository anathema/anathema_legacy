package net.sf.anathema.character.generic.framework.xml;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericCharmPresentationProperties;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.framework.xml.presentation.PresentationPropertiesParser;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.AbilityGroupType;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.persistence.SecondEdition;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;

public class PresentationPropertiesParserTest extends TestCase {

  private PresentationPropertiesParser parser;

  private GenericPresentationTemplate parseXml(String xmlCode) throws AnathemaException {
    Element templateElement = DocumentUtilities.read(xmlCode).getRootElement();
    return parser.parseTemplate(templateElement);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyXmlTemplateRegistry<GenericPresentationTemplate> templateRegistry = new DummyXmlTemplateRegistry<GenericPresentationTemplate>();
    DummyXmlTemplateRegistry<GenericCharmPresentationProperties> charmRegistry = new DummyXmlTemplateRegistry<GenericCharmPresentationProperties>();
    this.parser = new PresentationPropertiesParser(templateRegistry, charmRegistry);
  }

  public void testParseXmlWithoutAbilityBallResources() throws Exception {
    String xml = "<presentation />"; //$NON-NLS-1$
    GenericPresentationTemplate presentationProperties = parseXml(xml);
    GenericCharacterTemplate parentTemplate = new GenericCharacterTemplate();
    parentTemplate.setTemplateType(new TemplateType(CharacterType.SOLAR));
    presentationProperties.setParentTemplate(parentTemplate);
    assertEquals(
        "SolarButtonLifeSecondEdition16.png", presentationProperties.getSmallCasteIconResource(AbilityGroupType.Life.getId(), new SecondEdition().getId())); //$NON-NLS-1$
  }

  public void testParseXmlWithoutCharmPresentationProperties() throws Exception {
    String xml = "<presentation/>"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertNull(presentationProperties.getCharmPresentationProperties());
  }

  public void testUpdateCharmPresentationProperties() throws Exception {
    String xml = "<presentation><charmPresentation>" //$NON-NLS-1$
        + "\t<color red=\"139\" green=\"0\" blue=\"0\" />" //$NON-NLS-1$
        + "</charmPresentation>   </presentation>"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertNotNull(presentationProperties.getCharmPresentationProperties());
  }
}