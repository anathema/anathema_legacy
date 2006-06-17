package net.sf.anathema.test.character.main.impl.costs;

import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.impl.model.context.BasicCharacterContext;
import net.sf.anathema.character.impl.model.creation.bonus.magic.MagicCostCalculator;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.dummy.character.DummyAdditionalBonusPointManagment;
import net.sf.anathema.dummy.character.DummyAdditionalSpellPointManagement;
import net.sf.anathema.dummy.character.DummyGenericCharacter;
import net.sf.anathema.dummy.character.magic.DummyCharmConfiguration;
import net.sf.anathema.dummy.character.magic.DummySpell;
import net.sf.anathema.dummy.character.magic.DummySpellConfiguration;
import net.sf.anathema.dummy.character.template.DummyCharacterTemplate;
import net.sf.anathema.dummy.character.trait.DummyCoreTraitConfiguration;

public class CharmCostCalculatorTest extends AbstractBonusPointTestCase {

  private MagicCostCalculator calculator;
  private DummyCharmConfiguration charms;
  private ISpellConfiguration spells;
  private DummyCoreTraitConfiguration traitConfiguration;

  @Override
  protected void setUp() throws Exception {
    charms = new DummyCharmConfiguration();
    spells = new DummySpellConfiguration();
    traitConfiguration = new DummyCoreTraitConfiguration();
    addAbilityAndEssence(traitConfiguration);
    IBonusPointCosts cost = new DefaultBonusPointCosts();
    DummyGenericCharacter genericCharacter = new DummyGenericCharacter(new DummyCharacterTemplate());
    calculator = new MagicCostCalculator(
        genericCharacter.getTemplate().getMagicTemplate(),
        charms,
        spells,
        2,
        3,
        cost,
        new DummyAdditionalBonusPointManagment(),
        new DummyAdditionalSpellPointManagement(),
        new BasicCharacterContext(genericCharacter),
        traitConfiguration);
  }

  public void testNoSpellsLearned() {
    assertEquals(0, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(0, calculator.getBonusPointsSpentForSpells());
  }

  public void testOneSpellLearnedOccultUnfavored() {
    spells.addSpells(new ISpell[] { new DummySpell() });
    calculator.calculateMagicCosts();
    assertEquals(1, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(0, calculator.getBonusPointsSpentForSpells());
  }

  public void testOneSpellLearnedOccultFavored() {
    setOccultFavored();
    spells.addSpells(new ISpell[] { new DummySpell() });
    calculator.calculateMagicCosts();
    assertEquals(0, calculator.getGeneralCharmPicksSpent());
    assertEquals(1, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(0, calculator.getBonusPointsSpentForSpells());
  }

  private void setOccultFavored() {
    traitConfiguration.getFavorableTrait(AbilityType.Occult).getFavorization().setFavorableState(FavorableState.Favored);
  }

  public void testUnfavoredSpellsOverflowToBonus() {
    DummySpell dummySpell = new DummySpell();
    spells.addSpells(new ISpell[] { dummySpell, dummySpell, dummySpell, dummySpell });
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(5, calculator.getBonusPointsSpentForSpells());
  }

  public void testUnfavoredSpellsOverflowToBonusAndAreReset() {
    DummySpell dummySpell = new DummySpell();
    DummySpell dummySpellToRemove = new DummySpell();
    spells.addSpells(new ISpell[] { dummySpell, dummySpell, dummySpell, dummySpellToRemove });
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(5, calculator.getBonusPointsSpentForSpells());
    spells.removeSpells(new ISpell[] { dummySpellToRemove }, false);
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForSpells());
  }

  public void testFavoredSpellsOverflowToGeneralAndBonus() {
    setOccultFavored();
    DummySpell dummySpell = new DummySpell();
    spells.addSpells(new ISpell[] { dummySpell, dummySpell, dummySpell, dummySpell, dummySpell, dummySpell, });
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(2, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(4, calculator.getBonusPointsSpentForSpells());
  }
}