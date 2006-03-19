package net.sf.anathema.character.generic.framework.xml.rules.test;

import org.dom4j.Element;

import net.sf.anathema.character.generic.framework.xml.registry.test.DummyXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.rules.AdditionalRulesTemplateParser;
import net.sf.anathema.character.generic.framework.xml.rules.GenericAdditionalRules;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

public class AdditionalRulesTemplateParserTest extends BasicTestCase {
  private static final String ORIGINAL_TEMPLATE_ID = "original"; //$NON-NLS-1$
  private DummyXmlTemplateRegistry<GenericAdditionalRules> registry;
  private AdditionalRulesTemplateParser parser;
  private GenericAdditionalRules originalTemplate;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.registry = new DummyXmlTemplateRegistry<GenericAdditionalRules>();
    this.parser = new AdditionalRulesTemplateParser(registry);
    originalTemplate = new GenericAdditionalRules();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
  }

  public void testNoCompulsiveMagic() throws Exception {
    String xml = "<rules/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = parser.parseTemplate(rootElement);
    assertEquals(0, template.getCompulsiveCharmIDs().length);
  }

  public void testRequiredCharm() throws Exception {
    String xml = "<rules><requiredMagic><magic type=\"charm\" id=\"Charm\" /></requiredMagic> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = parser.parseTemplate(rootElement);
    assertEquals("Charm", template.getCompulsiveCharmIDs()[0]); //$NON-NLS-1$
  }
}
