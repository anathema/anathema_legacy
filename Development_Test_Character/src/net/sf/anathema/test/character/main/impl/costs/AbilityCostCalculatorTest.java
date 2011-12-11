package net.sf.anathema.test.character.main.impl.costs;

import net.sf.anathema.character.dummy.trait.DummyCoreTraitConfiguration;
import net.sf.anathema.character.generic.dummy.DummyAdditionalBonusPointManagment;
import net.sf.anathema.character.generic.impl.template.points.AbilityCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.generic.template.experience.IAbilityPointCosts;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.impl.model.creation.bonus.ability.AbilityCostCalculator;
import net.sf.anathema.character.library.trait.FavorableTraitCost;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;

public class AbilityCostCalculatorTest extends AbstractBonusPointTestCase {

  private static class DummyAbilityPointCosts implements IAbilityPointCosts {
    private final ICurrentRatingCosts defaultCosts = new FixedValueRatingCosts(2);
    private final ICurrentRatingCosts favoredCosts = new FixedValueRatingCosts(1);

    public ICurrentRatingCosts getAbilityCosts(boolean favored) {
      return favored ? favoredCosts : defaultCosts;
    }

    public int getDefaultSpecialtyDotsPerPoint() {
      return 1;
    }

    public int getFavoredSpecialtyDotsPerPoint() {
      return 2;
    }
    
    public int getMaximumFreeAbilityRank() {
      return 3;
    }
  }

  private static void assertEmptyCosts(AbilityCostCalculator calculator, IFavorableTrait ability) {
    FavorableTraitCost[] abilityCost = calculator.getCosts(ability);
    assertEquals("Ability " + ability, 0, abilityCost[0].getBonusCost()); //$NON-NLS-1$
    assertEquals("Ability " + ability, 0, abilityCost[0].getFavoredPointCost()); //$NON-NLS-1$
    assertEquals("Ability " + ability, 0, abilityCost[0].getGeneralPointCost()); //$NON-NLS-1$
  }

  private DummyCoreTraitConfiguration traitConfiguration;
  private IAbilityPointCosts costs;
  private DummyAdditionalBonusPointManagment additionalBonusPointManagment;

  private IFavorableDefaultTrait setFavoredAbilityTo(AbilityType abilityType, int value) {
    IFavorableDefaultTrait trait = (IFavorableDefaultTrait) traitConfiguration.getFavorableTrait(abilityType);
    trait.getFavorization().updateFavorableStateToCaste();
    trait.getFavorization().setFavored(true);
    trait.setCreationValue(value);
    return trait;
  }

  private IFavorableDefaultTrait setUnfavoredAbilityTo(AbilityType abilityType, int value) {
    IFavorableDefaultTrait ability = (IFavorableDefaultTrait) traitConfiguration.getFavorableTrait(abilityType);
    ability.getFavorization().updateFavorableStateToCaste();
    ability.getFavorization().setFavored(false);
    ability.setCreationValue(value);
    return ability;
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    traitConfiguration = new DummyCoreTraitConfiguration();
    addAbilityAndEssence(traitConfiguration);
    costs = new DummyAbilityPointCosts();
    additionalBonusPointManagment = new DummyAdditionalBonusPointManagment();
  }

  private AbilityCostCalculator startCalculation(IFavorableTraitCreationPoints creationPoints) {
    AbilityCostCalculator calculator = new AbilityCostCalculator(
        traitConfiguration,
        creationPoints,
        0,
        costs,
        additionalBonusPointManagment);
    calculator.calculateCosts();
    return calculator;
  }

  public void testAllAbilitiesUnlearned() throws Exception {
    AbilityCostCalculator calculator = startCalculation(new AbilityCreationPoints(2, 3, 4));
    assertEquals(0, calculator.getFreePointsSpent(true));
    assertEquals(0, calculator.getFreePointsSpent(false));
    for (IFavorableTrait ability : getAllAbilities()) {
      assertEmptyCosts(calculator, ability);
    }
  }

  private IFavorableTrait[] getAllAbilities() {
    return traitConfiguration.getAllAbilities();
  }

  public void testPreferFavoredForBonusPoint() throws Exception {
    IFavorableTraitCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 2);
    IFavorableDefaultTrait firstFavored = setFavoredAbilityTo(AbilityType.Melee, 3);
    IFavorableDefaultTrait secondFavored = setFavoredAbilityTo(AbilityType.Ride, 3);
    IFavorableDefaultTrait unfavoredAbility = setUnfavoredAbilityTo(AbilityType.Archery, 3);
    AbilityCostCalculator calculator = startCalculation(abilityCreationPoints);
    assertEquals(3, calculator.getFreePointsSpent(true));
    assertEquals(2, calculator.getFreePointsSpent(false));
    for (IFavorableTrait ability : getAllAbilities()) {
      if (ability == firstFavored) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 0, abilityCost.getBonusCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 3, abilityCost.getFavoredPointCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 0, abilityCost.getGeneralPointCost()); //$NON-NLS-1$
      }
      else if (ability == secondFavored) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 3, abilityCost.getBonusCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 0, abilityCost.getFavoredPointCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 0, abilityCost.getGeneralPointCost()); //$NON-NLS-1$
      }
      else if (ability == unfavoredAbility) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 2, abilityCost.getBonusCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 0, abilityCost.getFavoredPointCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 2, abilityCost.getGeneralPointCost()); //$NON-NLS-1$
      }
      else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }

  public void testGeneralDotsUsedForFavoredAbiltiesOverFavoredDots() throws Exception {
    IFavorableTraitCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 4, 5);
    IFavorableDefaultTrait firstFavored = setFavoredAbilityTo(AbilityType.Melee, 3);
    IFavorableDefaultTrait secondFavored = setFavoredAbilityTo(AbilityType.Occult, 3);
    AbilityCostCalculator calculator = startCalculation(abilityCreationPoints);
    assertEquals(4, calculator.getFreePointsSpent(true));
    assertEquals(2, calculator.getFreePointsSpent(false));
    for (IFavorableTrait ability : getAllAbilities()) {
      if (ability == firstFavored) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 0, abilityCost.getBonusCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 3, abilityCost.getFavoredPointCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 0, abilityCost.getGeneralPointCost()); //$NON-NLS-1$
      }
      else if (ability == secondFavored) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 0, abilityCost.getBonusCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 1, abilityCost.getFavoredPointCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 2, abilityCost.getGeneralPointCost()); //$NON-NLS-1$
      }
      else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }

  public void testFavoredDotsForFavoredAbilitesLessThan3() throws Exception {
    IFavorableTraitCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 4);
    IFavorableDefaultTrait favoredAbility = setFavoredAbilityTo(AbilityType.Melee, 3);
    AbilityCostCalculator calculator = startCalculation(abilityCreationPoints);
    assertEquals(3, calculator.getFreePointsSpent(true));
    assertEquals(0, calculator.getFreePointsSpent(false));
    for (IFavorableTrait ability : getAllAbilities()) {
      if (ability == favoredAbility) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 0, abilityCost.getBonusCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 3, abilityCost.getFavoredPointCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 0, abilityCost.getGeneralPointCost()); //$NON-NLS-1$
      }
      else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }

  public void testGeneralDotsForUnfavoredAbiltyLessThan3() throws Exception {
    IFavorableTraitCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 4);
    IFavorableTrait unfavoredAbility = setUnfavoredAbilityTo(AbilityType.Archery, 3);
    AbilityCostCalculator calculator = startCalculation(abilityCreationPoints);
    assertEquals(0, calculator.getFreePointsSpent(true));
    assertEquals(3, calculator.getFreePointsSpent(false));
    for (IFavorableTrait ability : getAllAbilities()) {
      if (ability == unfavoredAbility) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 0, abilityCost.getBonusCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 0, abilityCost.getFavoredPointCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 3, abilityCost.getGeneralPointCost()); //$NON-NLS-1$
      }
      else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }

  public void testBonusPointsForFavoredAbliltyAbove3() throws Exception {
    IFavorableTraitCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 4);
    IFavorableTrait favoredAbility = setFavoredAbilityTo(AbilityType.Melee, 4);
    AbilityCostCalculator calculator = startCalculation(abilityCreationPoints);
    assertEquals(3, calculator.getFreePointsSpent(true));
    assertEquals(0, calculator.getFreePointsSpent(false));
    for (IFavorableTrait ability : getAllAbilities()) {
      if (ability == favoredAbility) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 1, abilityCost.getBonusCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 3, abilityCost.getFavoredPointCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 0, abilityCost.getGeneralPointCost()); //$NON-NLS-1$
      }
      else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }

  public void testRaiseUnfavoredAbliltyAbove3() throws Exception {
    IFavorableTraitCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 4);
    IFavorableTrait unfavoredAbility = setUnfavoredAbilityTo(AbilityType.Archery, 4);
    AbilityCostCalculator calculator = startCalculation(abilityCreationPoints);
    assertEquals(0, calculator.getFreePointsSpent(true));
    assertEquals(3, calculator.getFreePointsSpent(false));
    for (IFavorableTrait ability : getAllAbilities()) {
      if (ability == unfavoredAbility) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 2, abilityCost.getBonusCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 0, abilityCost.getFavoredPointCost()); //$NON-NLS-1$
        assertEquals("Ability " + ability, 3, abilityCost.getGeneralPointCost()); //$NON-NLS-1$
      }
      else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }
}