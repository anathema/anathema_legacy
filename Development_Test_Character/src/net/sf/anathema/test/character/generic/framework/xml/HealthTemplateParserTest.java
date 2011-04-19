package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.generic.framework.xml.health.HealthTemplateParser;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.dummy.character.template.DummyXmlTemplateRegistry;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class HealthTemplateParserTest extends BasicTestCase {

  private static final String ORIGINAL_TEMPLATE_ID = "original"; //$NON-NLS-1$
  private DummyXmlTemplateRegistry<GenericHealthTemplate> registry;
  private HealthTemplateParser parser;
  private GenericHealthTemplate originalTemplate;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.registry = new DummyXmlTemplateRegistry<GenericHealthTemplate>();
    this.parser = new HealthTemplateParser(registry);
    originalTemplate = new GenericHealthTemplate();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
  }

  public void testNoToughnessTraitDefined() throws Exception {
    String xml = "<healthTemplate/>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericHealthTemplate template = parser.parseTemplate(templateElement);
    assertEquals(AbilityType.Endurance, template.getToughnessControllingTraits()[0]);
  }

  public void testSetToughnessControllingTrait() throws Exception {
    String xml = "<healthTemplate><toughnessControllingTrait type=\"Stamina\"/></healthTemplate>"; //$NON-NLS-1$
    Element templateElement = DocumentUtilities.read(xml).getRootElement();
    GenericHealthTemplate template = parser.parseTemplate(templateElement);
    assertEquals(AttributeType.Stamina, template.getToughnessControllingTraits()[0]);
  }
}