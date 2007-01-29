package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.backgrounds.BackgroundRegistry;
import net.sf.anathema.character.generic.framework.xml.rules.AdditionalRulesTemplateParser;
import net.sf.anathema.character.generic.framework.xml.rules.GenericAdditionalRules;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.ITraitTypeVisitor;
import net.sf.anathema.dummy.character.magic.DummyMagicCollection;
import net.sf.anathema.dummy.character.magic.DummySpell;
import net.sf.anathema.dummy.character.template.DummyXmlTemplateRegistry;
import net.sf.anathema.dummy.character.trait.DummyGenericTraitCollection;
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
  private AdditionalRulesTemplateParser parser;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.registry = new DummyXmlTemplateRegistry<GenericAdditionalRules>();
    originalTemplate = new GenericAdditionalRules();
    registry.register(ORIGINAL_TEMPLATE_ID, originalTemplate);
    parser = new AdditionalRulesTemplateParser(registry, new ISpecialCharm[0], new BackgroundRegistry());
  }

  public void testNoCompulsiveMagic() throws Exception {
    GenericAdditionalRules template = parseEmptyRuleset();
    assertEquals(0, template.getCompulsiveCharmIDs().length);
  }

  private GenericAdditionalRules parseEmptyRuleset() throws AnathemaException, PersistenceException {
    String xml = "<rules/>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = parser.parseTemplate(rootElement);
    return template;
  }

  public void testRequiredCharm() throws Exception {
    String xml = "<rules><requiredMagic><magic type=\"charm\" id=\"Charm\" /></requiredMagic> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = parser.parseTemplate(rootElement);
    assertEquals("Charm", template.getCompulsiveCharmIDs()[0]); //$NON-NLS-1$
  }

  public void testNoAdditionalPools() throws Exception {
    GenericAdditionalRules template = parseEmptyRuleset();
    assertEquals(0, template.getAdditionalEssencePools().length);
  }

  @Test
  public void testCharmMultiLearnableEssencePool() throws Exception {
    final StaticMultiLearnableCharm charm = new StaticMultiLearnableCharm("Charm", 3); //$NON-NLS-1$
    AdditionalRulesTemplateParser ownParser = new AdditionalRulesTemplateParser(
        registry,
        new ISpecialCharm[] { charm },
        new BackgroundRegistry());
    String xml = "<rules><additionalPools><multilearnablePool><charmReference id=\"Charm\"/><personalPool multiplier=\"5\"/><peripheralPool multiplier=\"10\"/></multilearnablePool></additionalPools> </rules>"; //$NON-NLS-1$
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
  public void testBackgroundMultiLearnableEssencePool() throws Exception {
    IBackgroundTemplate type = new CustomizedBackgroundTemplate("Background"); //$NON-NLS-1$
    BackgroundRegistry backgroundRegistry = new BackgroundRegistry();
    backgroundRegistry.add(type);
    AdditionalRulesTemplateParser ownParser = new AdditionalRulesTemplateParser(
        registry,
        new ISpecialCharm[0],
        backgroundRegistry);
    String xml = "<rules><additionalPools><multilearnablePool><backgroundReference id=\"Background\"/><personalPool multiplier=\"1\"/><peripheralPool><fixedValue value=\"0\" pool=\"0\"/><fixedValue value=\"1\" pool=\"2\"/><fixedValue value=\"2\" pool=\"3\"/></peripheralPool></multilearnablePool></additionalPools> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = ownParser.parseTemplate(rootElement);
    assertEquals(1, template.getAdditionalEssencePools().length);
    DummyGenericTraitCollection collection = new DummyGenericTraitCollection();
    IAdditionalEssencePool pool = template.getAdditionalEssencePools()[0];
    collection.setValue(type, 0);
    assertEquals(0, pool.getAdditionalPersonalPool(collection, null));
    assertEquals(0, pool.getAdditionalPeripheralPool(collection, null));
    collection.setValue(type, 1);
    assertEquals(1, pool.getAdditionalPersonalPool(collection, null));
    assertEquals(2, pool.getAdditionalPeripheralPool(collection, null));
    collection.setValue(type, 2);
    assertEquals(2, pool.getAdditionalPersonalPool(collection, null));
    assertEquals(3, pool.getAdditionalPeripheralPool(collection, null));
  }

  @Test
  public void testNoAdditionalMagicPools() throws Exception {
    GenericAdditionalRules template = parseEmptyRuleset();
    assertEquals(0, template.getAdditionalMagicLearnPools().length);
  }

  @Test
  public void testAdditionalMagicPool() throws Exception {
    IBackgroundTemplate type = new CustomizedBackgroundTemplate("Background"); //$NON-NLS-1$
    BackgroundRegistry backgroundRegistry = new BackgroundRegistry();
    backgroundRegistry.add(type);
    AdditionalRulesTemplateParser ownParser = new AdditionalRulesTemplateParser(
        registry,
        new ISpecialCharm[0],
        backgroundRegistry);
    String xml = "<rules><additionalMagic><magicPool><backgroundReference id=\"Background\"/></magicPool></additionalMagic> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = ownParser.parseTemplate(rootElement);
    DummyGenericTraitCollection collection = new DummyGenericTraitCollection();
    collection.setValue(type, 0);
    assertEquals(0, template.getAdditionalMagicLearnPools()[0].getAdditionalMagicCount(collection));
    assertTrue(template.getAdditionalMagicLearnPools()[0].isAllowedFor(collection, new DummySpell(
        CircleType.Terrestrial)));
    collection.setValue(type, 2);
    assertEquals(2, template.getAdditionalMagicLearnPools()[0].getAdditionalMagicCount(collection));
    assertTrue(template.getAdditionalMagicLearnPools()[0].isAllowedFor(collection, new DummySpell(
        CircleType.Terrestrial)));
  }

  @Test
  public void testDenyingAdditionalMagicPool() throws Exception {
    IBackgroundTemplate type = new CustomizedBackgroundTemplate("Background"); //$NON-NLS-1$
    BackgroundRegistry backgroundRegistry = new BackgroundRegistry();
    backgroundRegistry.add(type);
    AdditionalRulesTemplateParser ownParser = new AdditionalRulesTemplateParser(
        registry,
        new ISpecialCharm[0],
        backgroundRegistry);
    String xml = "<rules><additionalMagic><magicPool defaultResponse=\"false\"><backgroundReference id=\"Background\"/><spellReference id=\"Expected\"/></magicPool></additionalMagic> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = ownParser.parseTemplate(rootElement);
    DummyGenericTraitCollection collection = new DummyGenericTraitCollection();
    DummySpell dummySpell = new DummySpell("id"); //$NON-NLS-1$
    dummySpell.setCircleType(CircleType.Terrestrial);
    assertFalse(template.getAdditionalMagicLearnPools()[0].isAllowedFor(collection, dummySpell));
    DummySpell expectedSpell = new DummySpell("Expected"); //$NON-NLS-1$
    expectedSpell.setCircleType(CircleType.Terrestrial);
    assertFalse(template.getAdditionalMagicLearnPools()[0].isAllowedFor(
        collection,
        new DummySpell(CircleType.Celestial)));
    assertTrue(template.getAdditionalMagicLearnPools()[0].isAllowedFor(collection, expectedSpell));
  }

  @Test
  public void testAdditionalBonusCostForBackground() throws Exception {
    IBackgroundTemplate type = new CustomizedBackgroundTemplate("Background"); //$NON-NLS-1$
    BackgroundRegistry backgroundRegistry = new BackgroundRegistry();
    backgroundRegistry.add(type);
    AdditionalRulesTemplateParser ownParser = new AdditionalRulesTemplateParser(
        registry,
        new ISpecialCharm[0],
        backgroundRegistry);
    String xml = "<rules><additionalCost><costModifier><backgroundReference id=\"Background\"/><bonusModification thresholdLevel=\"1\" multiplier=\"2\"/></costModifier></additionalCost> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = ownParser.parseTemplate(rootElement);
    assertEquals(0, template.getCostModifier(type).getAdditionalDotsToSpend(5));
    assertEquals(8, template.getCostModifier(type).getAdditionalBonusPointsToSpend(5));
  }
  
  @Test
  public void testAdditionalDotsForBackground() throws Exception {
    IBackgroundTemplate type = new CustomizedBackgroundTemplate("Background"); //$NON-NLS-1$
    BackgroundRegistry backgroundRegistry = new BackgroundRegistry();
    backgroundRegistry.add(type);
    AdditionalRulesTemplateParser ownParser = new AdditionalRulesTemplateParser(
        registry,
        new ISpecialCharm[0],
        backgroundRegistry);
    String xml = "<rules><additionalCost><costModifier><backgroundReference id=\"Background\"/><dotCostModification thresholdLevel=\"1\" multiplier=\"1\"/></costModifier></additionalCost> </rules>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    GenericAdditionalRules template = ownParser.parseTemplate(rootElement);
    assertEquals(2, template.getCostModifier(type).getAdditionalDotsToSpend(3));
    assertEquals(0, template.getCostModifier(type).getAdditionalBonusPointsToSpend(5));
  }

  @Test
  public void testStandardCostForBackground() throws Exception {
    IBackgroundTemplate type = new CustomizedBackgroundTemplate("Background"); //$NON-NLS-1$
    GenericAdditionalRules template = parseEmptyRuleset();
    assertEquals(0, template.getCostModifier(type).getAdditionalDotsToSpend(5));
    assertEquals(0, template.getCostModifier(type).getAdditionalBonusPointsToSpend(5));
  }

  @Test
  public void testForbiddenBackgrounds() throws Exception {
    String xml = "<rules><forbiddenBackgrounds><backgroundReference id=\"forbidden\" /></forbiddenBackgrounds> </rules>"; //$NON-NLS-1$
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