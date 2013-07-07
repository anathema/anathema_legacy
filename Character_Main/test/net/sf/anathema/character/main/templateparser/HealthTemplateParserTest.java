package net.sf.anathema.character.main.templateparser;

import net.sf.anathema.character.main.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.main.xml.health.HealthTemplateParser;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.testing.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HealthTemplateParserTest {

  private static final String ORIGINAL_TEMPLATE_ID = "original";
  private DummyXmlTemplateRegistry<GenericHealthTemplate> registry;
  private HealthTemplateParser parser;
  private GenericHealthTemplate originalTemplate;

  @Before
  public void setUp() throws Exception {
    this.registry = new DummyXmlTemplateRegistry<>();
    this.parser = new HealthTemplateParser(registry);
    originalTemplate = new GenericHealthTemplate();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
  }

  @Test
  public void testSetToughnessControllingTrait() throws Exception {
    String xml = "<healthTemplate><toughnessControllingTrait type=\"Stamina\"/></healthTemplate>";
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericHealthTemplate template = parser.parseTemplate(templateElement);
    assertEquals(AttributeType.Stamina, template.getToughnessControllingTraits()[0]);
  }
}