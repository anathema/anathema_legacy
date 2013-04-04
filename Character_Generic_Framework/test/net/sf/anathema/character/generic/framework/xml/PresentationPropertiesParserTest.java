package net.sf.anathema.character.generic.framework.xml;

import net.sf.anathema.character.generic.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.generic.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.framework.xml.presentation.PresentationPropertiesParser;
import net.sf.anathema.character.generic.impl.persistence.SecondEdition;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.AbilityGroupType;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

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
    assertEquals("icons/DummyButtonLifeSecondEdition16.png",
            presentationProperties.getSmallCasteIconResource(AbilityGroupType.Life.getId(), new SecondEdition().getId()));
  }

  @Test
  public void testSetsColorOnCharmPresentationProperties() throws Exception {
    String xml = "<presentation>"
                 + "\t<color red=\"139\" green=\"123\" blue=\"255\" />"
                 + "</presentation>";
    IPresentationProperties presentationProperties = parseXml(xml);
    assertEquals(new Color(139, 123, 255), presentationProperties.getCharmPresentationProperties().getColor());
  }

  @Test
  public void testDefaultsToWhiteIfNoColorIsSet() throws Exception {
    String xml = "<presentation/>";
    IPresentationProperties presentationProperties = parseXml(xml);
    assertEquals(Color.WHITE, presentationProperties.getCharmPresentationProperties().getColor());
  }
}