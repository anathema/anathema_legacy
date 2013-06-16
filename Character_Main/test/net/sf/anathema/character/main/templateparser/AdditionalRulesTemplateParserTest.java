package net.sf.anathema.character.main.templateparser;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.framework.xml.rules.AdditionalRulesTemplateParser;
import net.sf.anathema.character.generic.framework.xml.rules.GenericAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterTypes;
import net.sf.anathema.character.main.testing.dummy.DummyMagicCollection;
import net.sf.anathema.character.main.testing.dummy.template.DummyXmlTemplateRegistry;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdditionalRulesTemplateParserTest {

  private static final String ORIGINAL_TEMPLATE_ID = "original";
  private DummyXmlTemplateRegistry<GenericAdditionalRules> registry;
  private final CharacterTypes characterTypes = new DummyCharacterTypes();
  private AdditionalRulesTemplateParser parser;

  @Before
  public void setUp() throws Exception {
    this.registry = new DummyXmlTemplateRegistry<>();
    GenericAdditionalRules originalTemplate = new GenericAdditionalRules();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
    parser = new AdditionalRulesTemplateParser(registry, new ISpecialCharm[0], characterTypes);
  }

  @Test
  public void testNoCompulsiveMagic() throws Exception {
    GenericAdditionalRules template = parseEmptyRuleset();
    assertEquals(0, template.getCompulsiveCharmIDs().length);
  }

  private GenericAdditionalRules parseEmptyRuleset() throws AnathemaException {
    String xml = "<rules/>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    return parser.parseTemplate(rootElement);
  }

  @Test
  public void testRequiredCharm() throws Exception {
    String xml = "<rules><requiredMagic><magic type=\"charm\" id=\"Charm\" /></requiredMagic> </rules>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = parser.parseTemplate(rootElement);
    assertEquals("Charm", template.getCompulsiveCharmIDs()[0]);
  }

  @Test
  public void testNoAdditionalPools() throws Exception {
    GenericAdditionalRules template = parseEmptyRuleset();
    assertEquals(0, template.getAdditionalEssencePools().length);
  }

  @Test
  public void testCharmMultiLearnableEssencePool() throws Exception {
    final StaticMultiLearnableCharm charm = new StaticMultiLearnableCharm("Charm", 3);
    AdditionalRulesTemplateParser ownParser = new AdditionalRulesTemplateParser(registry, new ISpecialCharm[]{charm}, characterTypes);
    String xml =
            "<rules><additionalPools><multilearnablePool><charmReference id=\"Charm\"/><personalPool multiplier=\"5\"/><peripheralPool multiplier=\"10\"/></multilearnablePool></additionalPools> </rules>";
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = ownParser.parseTemplate(rootElement);
    assertEquals(1, template.getAdditionalEssencePools().length);
    DummyMagicCollection collection = new DummyMagicCollection();
    collection.setLearnCount(charm, 2);
    IAdditionalEssencePool pool = template.getAdditionalEssencePools()[0];
    assertEquals(10, pool.getAdditionalPersonalPool(null, collection));
    assertEquals(20, pool.getAdditionalPeripheralPool(null, collection));
  }

  @Test
  public void testNoAdditionalMagicPools() throws Exception {
    GenericAdditionalRules template = parseEmptyRuleset();
    assertEquals(0, template.getAdditionalMagicLearnPools().length);
  }
}