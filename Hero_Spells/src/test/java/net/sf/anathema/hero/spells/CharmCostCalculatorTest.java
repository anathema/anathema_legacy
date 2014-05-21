package net.sf.anathema.hero.spells;

import com.google.common.collect.ImmutableList;
import net.sf.anathema.character.main.library.trait.DefaultTrait;
import net.sf.anathema.character.main.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.ValueChangeChecker;
import net.sf.anathema.character.main.library.trait.favorable.FavorableState;
import net.sf.anathema.character.main.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.main.library.trait.rules.TraitRules;
import net.sf.anathema.character.main.library.trait.rules.TraitRulesImpl;
import net.sf.anathema.character.main.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.character.main.magic.spells.Spell;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.SimpleTraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.context.CreationTraitValueStrategy;
import net.sf.anathema.character.main.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.BasicCharacterTestCase;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzerImpl;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostCalculator;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelImpl;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.magic.DummySpell;
import net.sf.anathema.hero.magic.dummy.DummyCharmsModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.SimpleTraitRules;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CharmCostCalculatorTest {

  private static void addAbilityAndEssence(TraitModel traitModel, DummyHero hero) {
    traitModel.addTraits(createTrait(hero, OtherTraitType.Essence, new TraitTemplate()));
    for (final AbilityType traitType : AbilityType.values()) {
      traitModel.addTraits(createFavorableTrait(hero, traitType, new TraitTemplate()));
    }
  }

  public static Trait createTrait(Hero hero, TraitType traitType, TraitTemplate traitTemplate) {
    ValueChangeChecker checker = new FriendlyValueChangeChecker();
    TraitRules rules = new SimpleTraitRules(traitType, traitTemplate, hero);
    return new DefaultTrait(hero, rules, checker);
  }

  public static Trait createFavorableTrait(Hero hero, TraitType traitType, TraitTemplate traitTemplate) {
    ValueChangeChecker checker = new FriendlyValueChangeChecker();
    TraitRules rules = new SimpleTraitRules(traitType, traitTemplate, hero);
    return new DefaultTrait(hero, rules, new CasteType[0], checker, new FriendlyIncrementChecker());
  }

  private static Trait createTrait(Hero hero, TypedTraitTemplateFactory factory, TraitType traitType) {
    ITraitTemplate traitTemplate = factory.create(traitType);
    ValueChangeChecker checker = new FriendlyValueChangeChecker();
    TraitRulesImpl rules = new TraitRulesImpl(traitType, traitTemplate, hero);
    return new DefaultTrait(hero, rules, checker);
  }

  private MagicCreationCostCalculator calculator;
  private DummySpellsModel spells = new DummySpellsModel();
  private DummyCharmsModel charms = new DummyCharmsModel();
  private TraitModel traitModel;

  @Before
  public void setUp() throws Exception {
    CharmsModel charmModel = new CharmsModelImpl(new CharmsTemplate());
    spells.initializeMagicModel(charmModel);
    DummyHero hero = new BasicCharacterTestCase().createModelContextWithEssence2(new CreationTraitValueStrategy());
    traitModel = TraitModelFetcher.fetch(hero);
    addAbilityAndEssence(traitModel, hero);
    hero.addModel(charmModel);
    hero.addModel(charms);
    hero.addModel(spells);
    MagicPointsTemplate template = new MagicPointsTemplate();
    template.generalCreationPoints.freePicks = 3;
    template.generalCreationPoints.costs = 5;
    template.favoredCreationPoints.freePicks = 2;
    template.favoredCreationPoints.costs = 4;
    MagicCreationCostEvaluator magicCostEvaluator = charmModel.getMagicCostEvaluator();
    calculator = new MagicCreationCostCalculator(magicCostEvaluator, template, MartialArtsLevel.Celestial, new CostAnalyzerImpl(hero));
  }

  @Test
  public void testNoSpellsLearned() {
    assertEquals(0, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointCost());
  }

  @Test
  public void testOneSpellLearnedOccultUnfavored() {
    spells.addSpells(Collections.<Spell>singletonList(new DummySpell()));
    calculator.calculateMagicCosts();
    assertEquals(1, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointCost());
  }

  @Test
  public void testOneSpellLearnedOccultFavored() {
    setOccultFavored();
    spells.addSpells(Collections.<Spell>singletonList(new DummySpell()));
    calculator.calculateMagicCosts();
    assertEquals(0, calculator.getGeneralCharmPicksSpent());
    assertEquals(1, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointCost());
  }

  private void setOccultFavored() {
    traitModel.getTrait(AbilityType.Occult).getFavorization().setFavorableState(FavorableState.Favored);
  }

  @Test
  public void testUnfavoredSpellsOverflowToBonus() {
    DummySpell dummySpell = new DummySpell();
    spells.addSpells(ImmutableList.<Spell>of(dummySpell, dummySpell, dummySpell, dummySpell));
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(5, calculator.getBonusPointCost());
  }

  @Test
  public void testUnfavoredSpellsOverflowToBonusAndAreReset() {
    DummySpell dummySpell = new DummySpell();
    DummySpell dummySpellToRemove = new DummySpell();
    spells.addSpells(ImmutableList.<Spell>of(dummySpell, dummySpell, dummySpell, dummySpellToRemove));
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(5, calculator.getBonusPointCost());
    spells.removeSpells(Collections.<Spell>singletonList(dummySpellToRemove), false);
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
  }

  @Test
  public void removalRemovesBonusPointCost() {
    DummySpell dummySpell = new DummySpell();
    DummySpell dummySpellToRemove = new DummySpell();
    spells.addSpells(ImmutableList.<Spell>of(dummySpell, dummySpell, dummySpell, dummySpellToRemove), false);
    spells.removeSpells(Collections.<Spell>singletonList(dummySpellToRemove), false);
    calculator.calculateMagicCosts();
    assertThat(calculator.getBonusPointCost(), is(0));
  }

  @Test
  public void testFavoredSpellsOverflowToGeneralAndBonus() {
    setOccultFavored();
    DummySpell dummySpell = new DummySpell();
    spells.addSpells(ImmutableList.<Spell>of(dummySpell, dummySpell, dummySpell, dummySpell, dummySpell, dummySpell));
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(2, calculator.getFavoredCharmPicksSpent());
    assertEquals(4, calculator.getBonusPointCost());
  }
}