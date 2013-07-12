package net.sf.anathema.character.main.templateparser;

import net.sf.anathema.character.main.persistence.SecondEdition;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.template.abilities.AbilityGroupType;
import net.sf.anathema.character.main.template.presentation.IPresentationProperties;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.main.testing.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.GenericCharacterTemplate;
import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.main.xml.presentation.PresentationPropertiesParser;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PresentationPropertiesParserTest {

  private PresentationPropertiesParser parser;

  private GenericPresentationTemplate parseXml(String xmlCode) throws AnathemaException {
    Element templateElement = DocumentUtilities.read(xmlCode).getRootElement();
    return parser.parseTemplate(templateElement);
  }

  @Before
  public void setUp() throws Exception {
    DummyXmlTemplateRegistry<GenericPresentationTemplate> templateRegistry = new DummyXmlTemplateRegistry<>();
    this.parser = new PresentationPropertiesParser(templateRegistry);
  }

  @Test
  public void testParseXmlWithoutAbilityBallResources() throws Exception {
    String xml = "<presentation />";
    GenericPresentationTemplate presentationProperties = parseXml(xml);
    GenericCharacterTemplate parentTemplate = new GenericCharacterTemplate();
    parentTemplate.setTemplateType(new TemplateType(new DummyExaltCharacterType()));
    presentationProperties.setParentTemplate(parentTemplate);
    assertEquals(new RelativePath("icons/DummyButtonLifeSecondEdition16.png"),
            presentationProperties.getSmallCasteIconResource(AbilityGroupType.Life.getId(), SecondEdition.SECOND_EDITION));
  }

  @Test
  public void testSetsColorOnCharmPresentationProperties() throws Exception {
    String xml = "<presentation>" + "\t<color red=\"139\" green=\"123\" blue=\"255\" />" + "</presentation>";
    IPresentationProperties presentationProperties = parseXml(xml);
    assertEquals(new RGBColor(139, 123, 255), presentationProperties.getCharmPresentationProperties().getColor());
  }

  @Test
  public void testDefaultsToWhiteIfNoColorIsSet() throws Exception {
    String xml = "<presentation/>";
    IPresentationProperties presentationProperties = parseXml(xml);
    assertEquals(RGBColor.White, presentationProperties.getCharmPresentationProperties().getColor());
  }
}