package net.sf.anathema.character.main.costs;

import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.impl.model.context.BasicCharacterContext;
import net.sf.anathema.character.impl.model.creation.bonus.magic.MagicCostCalculator;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.main.testing.dummy.DummyAdditionalBonusPointManagment;
import net.sf.anathema.character.main.testing.dummy.DummyAdditionalSpellPointManagement;
import net.sf.anathema.character.main.testing.dummy.DummyGenericCharacter;
import net.sf.anathema.character.main.testing.dummy.magic.DummyCharmModel;
import net.sf.anathema.character.main.testing.dummy.magic.DummySpell;
import net.sf.anathema.character.main.testing.dummy.magic.DummySpellConfiguration;
import net.sf.anathema.character.main.testing.dummy.template.DummyHeroTemplate;
import net.sf.anathema.character.main.testing.dummy.trait.DummyCoreTraitConfiguration;
import net.sf.anathema.character.main.model.traits.GenericTraitCollectionFacade;
import net.sf.anathema.character.model.ISpellConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharmCostCalculatorTest extends AbstractBonusPointTestCase {

  private MagicCostCalculator calculator;
  private ISpellConfiguration spells;
  private DummyCoreTraitConfiguration traitConfiguration;

  @Before
  public void setUp() throws Exception {
    DummyCharmModel charms = new DummyCharmModel();
    spells = new DummySpellConfiguration();
    traitConfiguration = new DummyCoreTraitConfiguration();
    addAbilityAndEssence(traitConfiguration);
    BonusPointCosts cost = new DefaultBonusPointCosts();
    DummyGenericCharacter genericCharacter = new DummyGenericCharacter(new DummyHeroTemplate());
    calculator = new MagicCostCalculator(genericCharacter.getTemplate().getMagicTemplate(), charms, spells, 2, 3, cost,
            new DummyAdditionalBonusPointManagment(), new DummyAdditionalSpellPointManagement(), new BasicCharacterContext(genericCharacter),
            new GenericTraitCollectionFacade(traitConfiguration));
  }

  @Test
  public void testNoSpellsLearned() {
    assertEquals(0, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(0, calculator.getBonusPointsSpentForSpells());
  }

  @Test
  public void testOneSpellLearnedOccultUnfavored() {
    spells.addSpells(new ISpell[]{new DummySpell()});
    calculator.calculateMagicCosts();
    assertEquals(1, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(0, calculator.getBonusPointsSpentForSpells());
  }

  @Test
  public void testOneSpellLearnedOccultFavored() {
    setOccultFavored();
    spells.addSpells(new ISpell[]{new DummySpell()});
    calculator.calculateMagicCosts();
    assertEquals(0, calculator.getGeneralCharmPicksSpent());
    assertEquals(1, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(0, calculator.getBonusPointsSpentForSpells());
  }

  private void setOccultFavored() {
    traitConfiguration.getTrait(AbilityType.Occult).getFavorization().setFavorableState(FavorableState.Favored);
  }

  @Test
  public void testUnfavoredSpellsOverflowToBonus() {
    DummySpell dummySpell = new DummySpell();
    spells.addSpells(new ISpell[]{dummySpell, dummySpell, dummySpell, dummySpell});
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(5, calculator.getBonusPointsSpentForSpells());
  }

  @Test
  public void testUnfavoredSpellsOverflowToBonusAndAreReset() {
    DummySpell dummySpell = new DummySpell();
    DummySpell dummySpellToRemove = new DummySpell();
    spells.addSpells(new ISpell[]{dummySpell, dummySpell, dummySpell, dummySpellToRemove});
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(5, calculator.getBonusPointsSpentForSpells());
    spells.removeSpells(new ISpell[]{dummySpellToRemove}, false);
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForSpells());
  }

  @Test
  public void testFavoredSpellsOverflowToGeneralAndBonus() {
    setOccultFavored();
    DummySpell dummySpell = new DummySpell();
    spells.addSpells(new ISpell[]{dummySpell, dummySpell, dummySpell, dummySpell, dummySpell, dummySpell,});
    calculator.calculateMagicCosts();
    assertEquals(3, calculator.getGeneralCharmPicksSpent());
    assertEquals(2, calculator.getFavoredCharmPicksSpent());
    assertEquals(0, calculator.getBonusPointsSpentForCharms());
    assertEquals(4, calculator.getBonusPointsSpentForSpells());
  }
}