package net.sf.anathema.hero.magic.costs;

import com.google.common.collect.ImmutableList;
import net.sf.anathema.character.main.library.trait.favorable.FavorableState;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.traits.context.CreationTraitValueStrategy;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.BasicCharacterTestCase;
import net.sf.anathema.hero.advance.AbstractBonusPointTestCase;
import net.sf.anathema.hero.advance.CostAnalyzerImpl;
import net.sf.anathema.hero.charms.advance.creation.MagicBonusPointCalculator;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelImpl;
import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.dummy.DummyBonusPointCosts;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.magic.DummyCharmsModel;
import net.sf.anathema.hero.dummy.magic.DummySpell;
import net.sf.anathema.hero.magic.testing.DummySpellsModel;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CharmCostCalculatorTest extends AbstractBonusPointTestCase {

  private MagicBonusPointCalculator calculator;
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
    hero.template.creationPoints.favoredCreationCharmCount = 2;
    hero.template.creationPoints.defaultCreationCharmCount = 3;
    ICreationPoints creationPoints = hero.getTemplate().getCreationPoints();
    DummyBonusPointCosts costs = new DummyBonusPointCosts();
    costs.magicCostsTto.general.charmCost = 5;
    costs.magicCostsTto.favored.charmCost = 4;
    calculator = new MagicBonusPointCalculator(charmModel.getMagicCostEvaluator(), creationPoints, costs, new CostAnalyzerImpl(hero));
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