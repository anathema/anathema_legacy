package net.sf.anathema.test.character.generic.framework.xml;

import java.awt.Color;

import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericCharmPresentationProperties;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.framework.xml.presentation.PresentationPropertiesParser;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.AbilityGroupType;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.dummy.character.template.DummyCharacterTemplateResourceProvider;
import net.sf.anathema.dummy.character.template.DummyXmlTemplateRegistry;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class PresentationPropertiesParserTest extends BasicTestCase {

  private DummyXmlTemplateRegistry<GenericPresentationTemplate> templateRegistry;
  private DummyCharacterTemplateResourceProvider resourceProvider;
  private PresentationPropertiesParser parser;

  private GenericPresentationTemplate parseXml(String xmlCode) throws AnathemaException, PersistenceException {
    Element templateElement = DocumentUtilities.read(xmlCode).getRootElement();
    return parser.parseTemplate(templateElement);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.templateRegistry = new DummyXmlTemplateRegistry<GenericPresentationTemplate>();
    DummyXmlTemplateRegistry<GenericCharmPresentationProperties> charmRegistry = new DummyXmlTemplateRegistry<GenericCharmPresentationProperties>();
    this.resourceProvider = new DummyCharacterTemplateResourceProvider();
    this.parser = new PresentationPropertiesParser(templateRegistry, resourceProvider, charmRegistry);
  }

  public void testParseXmlWithoutColor() throws Exception {
    String xml = "<presentation />"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertNull(presentationProperties.getColor());
  }

  public void testColorUpdateWithParsing() throws Exception {
    String xml = "<presentation><color red=\"111\" green=\"133\" blue=\"255\" /></presentation>"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertEquals(new Color(111, 133, 255), presentationProperties.getColor());
  }

  public void testParseXmlWithoutBallResource() throws Exception {
    String xml = "<presentation />"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertNull(presentationProperties.getBallResource());
  }

  public void testUpdateBallResourceWithParsing() throws Exception {
    String xml = "<presentation><ballResource type=\"Mortal\" /></presentation>"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertEquals(DummyCharacterTemplateResourceProvider.MORTAL_BALL_RESOURCE, presentationProperties.getBallResource());
  }

  public void testParseXmlWithoutAbilityBallResources() throws Exception {
    String xml = "<presentation />"; //$NON-NLS-1$
    GenericPresentationTemplate presentationProperties = parseXml(xml);
    GenericCharacterTemplate parentTemplate = new GenericCharacterTemplate();
    parentTemplate.setTemplateType(new TemplateType(CharacterType.SOLAR));
    presentationProperties.setParentTemplate(parentTemplate);
    assertEquals(
        "SolarButtonLifeFirstEdition20.png", presentationProperties.getMediumCasteIconResource(AbilityGroupType.Life.getId(), ExaltedEdition.FirstEdition.getId())); //$NON-NLS-1$
  }

  public void testParseXmlWithoutNewActionResource() throws Exception {
    String xml = "<presentation />"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertNull(presentationProperties.getNewActionResource());
  }

  public void testUpdateNewActionResourceWithParsing() throws Exception {
    String xml = "<presentation><newResources><newAction>NewActionResource</newAction></newResources></presentation>"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertEquals("NewActionResource", presentationProperties.getNewActionResource()); //$NON-NLS-1$
  }

  public void testParseXmlWithoutCharmPresentationProperties() throws Exception {
    String xml = "<presentation/>"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertNull(presentationProperties.getCharmPresentationProperties());
  }

  public void testUpdateCharmPresentationProperties() throws Exception {
    String xml = "<presentation><charmPresentation>" //$NON-NLS-1$
        + "<polygon>157.07742,9.777771 153.65161,19.199997</polygon>" //$NON-NLS-1$
        + "<charmDimension width=\"190\" height=\"72\"/>+" //$NON-NLS-1$
        + "<gapDimension width=\"25\" height=\"50\"/>" //$NON-NLS-1$
        + "</charmPresentation>   </presentation>"; //$NON-NLS-1$
    IPresentationProperties presentationProperties = parseXml(xml);
    assertNotNull(presentationProperties.getCharmPresentationProperties());
  }
}