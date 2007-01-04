package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.xml.rules.AdditionalRulesTemplateParser;
import net.sf.anathema.character.generic.framework.xml.rules.GenericAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.dummy.character.magic.DummyMagicCollection;
import net.sf.anathema.dummy.character.template.DummyXmlTemplateRegistry;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;
import org.junit.Test;

public class AdditionalRulesTemplateParserTest extends BasicTestCase {
  private static final String ORIGINAL_TEMPLATE_ID = "original"; //$NON-NLS-1$
  private DummyXmlTemplateRegistry<GenericAdditionalRules> registry;
  private GenericAdditionalRules originalTemplate;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.registry = new DummyXmlTemplateRegistry<GenericAdditionalRules>();
    originalTemplate = new GenericAdditionalRules();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
  }

  public void testNoCompulsiveMagic() throws Exception {
    GenericAdditionalRules template = parseEmptyRuleset();
    assertEquals(0, template.getCompulsiveCharmIDs().length);
  }

  private GenericAdditionalRules parseEmptyRuleset() throws AnathemaException, PersistenceException {
    AdditionalRulesTemplateParser parser = new AdditionalRulesTemplateParser(registry, new ISpecialCharm[0]);
    String xml = "<rules/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = parser.parseTemplate(rootElement);
    return template;
  }

  public void testRequiredCharm() throws Exception {
    AdditionalRulesTemplateParser parser = new AdditionalRulesTemplateParser(registry, new ISpecialCharm[0]);
    String xml = "<rules><requiredMagic><magic type=\"charm\" id=\"Charm\" /></requiredMagic> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = parser.parseTemplate(rootElement);
    assertEquals("Charm", template.getCompulsiveCharmIDs()[0]); //$NON-NLS-1$
  }

  public void testNoAdditionalPools() throws Exception {
    GenericAdditionalRules template = parseEmptyRuleset();
    assertEquals(0, template.getAdditionalEssencePools().length);
  }

  public void testAdditionalMultiLearnablePool() throws Exception {
    final StaticMultiLearnableCharm charm = new StaticMultiLearnableCharm("Charm", 3); //$NON-NLS-1$
    AdditionalRulesTemplateParser parser = new AdditionalRulesTemplateParser(registry, new ISpecialCharm[] { charm });
    String xml = "<rules><additionalPools><multilearnablePool><charmReference id=\"Charm\"/><personalPool multiplier=\"5\"/><peripheralPool multiplier=\"10\"/></multilearnablePool></additionalPools> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = parser.parseTemplate(rootElement);
    assertEquals(1, template.getAdditionalEssencePools().length);
    DummyMagicCollection collection = new DummyMagicCollection();
    collection.setLearnCount(charm, 2);
    IAdditionalEssencePool pool = template.getAdditionalEssencePools()[0];
    assertEquals(10, pool.getAdditionaPersonalPool(null, collection));
    assertEquals(20, pool.getAdditionaPeripheralPool(null, collection));
  }

  @Test
  public void testForbiddenBackgrounds() throws Exception {
    AdditionalRulesTemplateParser parser = new AdditionalRulesTemplateParser(registry, new ISpecialCharm[0]);
    String xml = "<rules><forbiddenBackgrounds><background id=\"forbidden\" /></forbiddenBackgrounds> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = parser.parseTemplate(rootElement);
    assertTrue(template.isRejected(getDummyBackgroundTemplate()));
  }

  @Test
  public void testNoForbiddenBackgrounds() throws Exception {
    GenericAdditionalRules template = parseEmptyRuleset();
    assertFalse(template.isRejected(getDummyBackgroundTemplate()));
  }

  private IBackgroundTemplate getDummyBackgroundTemplate() {
    return new IBackgroundTemplate() {
      public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
        return false;
      }

      public LowerableState getExperiencedState() {
        return null;
      }

      public void accept(ITraitTypeVisitor visitor) {
        // nothing to do
      }

      public String getId() {
        return "forbidden"; //$NON-NLS-1$
      }
    };
  }
}