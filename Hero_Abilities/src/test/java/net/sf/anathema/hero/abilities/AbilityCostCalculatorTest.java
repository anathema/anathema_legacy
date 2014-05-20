package net.sf.anathema.hero.abilities;

import net.sf.anathema.character.main.template.abilities.AbilityGroupType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import net.sf.anathema.hero.traits.template.Group;
import net.sf.anathema.hero.advance.AbstractBonusPointTestCase;
import net.sf.anathema.character.main.library.trait.FavorableTraitCost;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.template.experience.AbilityPointCosts;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.template.points.AbilityCreationPoints;
import net.sf.anathema.character.main.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.main.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.DummyHeroEnvironment;
import net.sf.anathema.hero.dummy.models.DummyHeroConcept;
import net.sf.anathema.hero.dummy.models.DummySpiritualTraitModel;
import net.sf.anathema.hero.dummy.models.DummyTraitModel;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.abilities.advance.creation.AbilityCostCalculatorImpl;
import net.sf.anathema.hero.abilities.model.AbilitiesModelImpl;
import net.sf.anathema.hero.points.PointModelImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AbilityCostCalculatorTest extends AbstractBonusPointTestCase {

  private static class DummyAbilityPointCosts implements AbilityPointCosts {

    private final CurrentRatingCosts defaultCosts = new FixedValueRatingCosts(2);
    private final CurrentRatingCosts favoredCosts = new FixedValueRatingCosts(1);

    @Override
    public CurrentRatingCosts getAbilityCosts(boolean favored) {
      return favored ? favoredCosts : defaultCosts;
    }

    @Override
    public int getDefaultSpecialtyDotsPerPoint() {
      return 1;
    }

    @Override
    public int getFavoredSpecialtyDotsPerPoint() {
      return 2;
    }

    @Override
    public int getMaximumFreeAbilityRank() {
      return 3;
    }
  }

  private static void assertEmptyCosts(AbilityCostCalculatorImpl calculator, Trait ability) {
    FavorableTraitCost[] abilityCost = calculator.getCosts(ability);
    if (ability.getType() == AbilityType.Craft) {
      return;
    }
    assertEquals("Ability " + ability, 0, abilityCost[0].getBonusCost());
    assertEquals("Ability " + ability, 0, abilityCost[0].getFavoredPointCost());
    assertEquals("Ability " + ability, 0, abilityCost[0].getGeneralPointCost());
  }

  private AbilityPointCosts costs;
  private GroupedTraitsTemplate abilitiesTemplate = new GroupedTraitsTemplate();
  private AbilitiesModelImpl abilityModel = new AbilitiesModelImpl(abilitiesTemplate);
  private DummyHero dummyHero = new DummyHero();

  private Trait setFavoredAbilityTo(AbilityType abilityType, int value) {
    Trait trait = abilityModel.getTrait(abilityType);
    trait.getFavorization().updateFavorableStateToCaste();
    trait.getFavorization().setFavored(true);
    trait.setCreationValue(value);
    return trait;
  }

  private Trait setUnfavoredAbilityTo(AbilityType abilityType, int value) {
    Trait ability = abilityModel.getTrait(abilityType);
    ability.getFavorization().updateFavorableStateToCaste();
    ability.getFavorization().setFavored(false);
    ability.setCreationValue(value);
    return ability;
  }

  @Before
  public void setUp() throws Exception {
    this.abilitiesTemplate = new GroupedTraitsTemplate();
    addGroupsToTemplate();
    this.abilityModel = new AbilitiesModelImpl(abilitiesTemplate);
    dummyHero.addModel(abilityModel);
    dummyHero.addModel(new DummyTraitModel());
    dummyHero.addModel(new DummyHeroConcept());
    dummyHero.addModel(new DummySpiritualTraitModel());
    costs = new DummyAbilityPointCosts();
  }

  private AbilityCostCalculatorImpl startCalculation(IFavorableTraitCreationPoints creationPoints) {
    AbilityCostCalculatorImpl calculator = new AbilityCostCalculatorImpl(abilityModel, creationPoints, costs);
    calculator.recalculate();
    return calculator;
  }

  @Test
  public void testAllAbilitiesUnlearned() throws Exception {
    AbilityCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 4);
    initializeModelWith(abilityCreationPoints);
    AbilityCostCalculatorImpl calculator = startCalculation(abilityCreationPoints);
    assertEquals(0, calculator.getFreePointsSpent(true));
    assertEquals(0, calculator.getFreePointsSpent(false));
    for (Trait ability : getAllAbilities()) {
      assertEmptyCosts(calculator, ability);
    }
  }

  private List<Trait> getAllAbilities() {
    List<Trait> abilities = new ArrayList<>();
    for (AbilityType type : AbilityType.values()) {
      Trait trait = abilityModel.getTrait(type);
      abilities.add(trait);
    }
    return abilities;
  }

  @Test
  public void testPreferFavoredForBonusPoint() throws Exception {
    AbilityCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 2);
    initializeModelWith(abilityCreationPoints);
    Trait firstFavored = setFavoredAbilityTo(AbilityType.Melee, 3);
    Trait secondFavored = setFavoredAbilityTo(AbilityType.Ride, 3);
    Trait unfavoredAbility = setUnfavoredAbilityTo(AbilityType.Archery, 3);
    AbilityCostCalculatorImpl calculator = startCalculation(dummyHero.template.creationPoints.abilityCreationPoints);
    assertEquals(3, calculator.getFreePointsSpent(true));
    assertEquals(2, calculator.getFreePointsSpent(false));
    List<Trait> attributesWithoutCost = getAllAbilities();
    attributesWithoutCost.remove(firstFavored);
    attributesWithoutCost.remove(secondFavored);
    attributesWithoutCost.remove(unfavoredAbility);

    FavorableTraitCost firstFavoredCost = calculator.getCosts(firstFavored)[0];
    FavorableTraitCost secondFavoredCost = calculator.getCosts(secondFavored)[0];
    assertFavoredPointCostsSumUpTo(3, firstFavoredCost, secondFavoredCost);
    assertGeneralPointCostsSumUpTo(0, firstFavoredCost, secondFavoredCost);
    assertBonusPointCostsSumUpTo(3, firstFavoredCost, secondFavoredCost);

    FavorableTraitCost[] allAbilityCost = calculator.getCosts(unfavoredAbility);
    FavorableTraitCost abilityCost = allAbilityCost[0];
    assertEquals("Ability " + unfavoredAbility, 2, abilityCost.getBonusCost());
    assertEquals("Ability " + unfavoredAbility, 0, abilityCost.getFavoredPointCost());
    assertEquals("Ability " + unfavoredAbility, 2, abilityCost.getGeneralPointCost());

    for (Trait ability : attributesWithoutCost) {
      assertEmptyCosts(calculator, ability);
    }
  }

  @Test
  public void testGeneralDotsUsedForFavoredAbilitiesOverFavoredDots() throws Exception {
    AbilityCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 4, 5);
    initializeModelWith(abilityCreationPoints);
    Trait firstFavored = setFavoredAbilityTo(AbilityType.Melee, 3);
    Trait secondFavored = setFavoredAbilityTo(AbilityType.Occult, 3);
    AbilityCostCalculatorImpl calculator = startCalculation(abilityCreationPoints);
    assertEquals(4, calculator.getFreePointsSpent(true));
    assertEquals(2, calculator.getFreePointsSpent(false));
    assertGeneralPointCostsSumUpTo(2, calculator.getCosts(firstFavored)[0], calculator.getCosts(secondFavored)[0]);
    assertFavoredPointCostsSumUpTo(4, calculator.getCosts(firstFavored)[0], calculator.getCosts(secondFavored)[0]);
    assertBonusPointCostsSumUpTo(0, calculator.getCosts(firstFavored)[0], calculator.getCosts(secondFavored)[0]);
    List<Trait> allEmptyAbilities = getAllAbilities();
    allEmptyAbilities.remove(firstFavored);
    allEmptyAbilities.remove(secondFavored);
    for (Trait ability : allEmptyAbilities) {
      assertEmptyCosts(calculator, ability);
    }
  }

  @Test
  public void testFavoredDotsForFavoredAbilitiesLessThan3() throws Exception {
    AbilityCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 4);
    initializeModelWith(abilityCreationPoints);
    Trait favoredAbility = setFavoredAbilityTo(AbilityType.Melee, 3);
    AbilityCostCalculatorImpl calculator = startCalculation(abilityCreationPoints);
    assertEquals(3, calculator.getFreePointsSpent(true));
    assertEquals(0, calculator.getFreePointsSpent(false));
    for (Trait ability : getAllAbilities()) {
      if (ability == favoredAbility) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 0, abilityCost.getBonusCost());
        assertEquals("Ability " + ability, 3, abilityCost.getFavoredPointCost());
        assertEquals("Ability " + ability, 0, abilityCost.getGeneralPointCost());
      } else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }

  @Test
  public void testGeneralDotsForUnfavoredAbilityLessThan3() throws Exception {
    AbilityCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 4);
    initializeModelWith(abilityCreationPoints);
    Trait unfavoredAbility = setUnfavoredAbilityTo(AbilityType.Archery, 3);
    AbilityCostCalculatorImpl calculator = startCalculation(abilityCreationPoints);
    assertEquals(0, calculator.getFreePointsSpent(true));
    assertEquals(3, calculator.getFreePointsSpent(false));
    for (Trait ability : getAllAbilities()) {
      if (ability == unfavoredAbility) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 0, abilityCost.getBonusCost());
        assertEquals("Ability " + ability, 0, abilityCost.getFavoredPointCost());
        assertEquals("Ability " + ability, 3, abilityCost.getGeneralPointCost());
      } else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }

  @Test
  public void testBonusPointsForFavoredAbilityAbove3() throws Exception {
    AbilityCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 4);
    initializeModelWith(abilityCreationPoints);
    Trait favoredAbility = setFavoredAbilityTo(AbilityType.Melee, 4);
    AbilityCostCalculatorImpl calculator = startCalculation(abilityCreationPoints);
    assertEquals(3, calculator.getFreePointsSpent(true));
    assertEquals(0, calculator.getFreePointsSpent(false));
    for (Trait ability : getAllAbilities()) {
      if (ability == favoredAbility) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 1, abilityCost.getBonusCost());
        assertEquals("Ability " + ability, 3, abilityCost.getFavoredPointCost());
        assertEquals("Ability " + ability, 0, abilityCost.getGeneralPointCost());
      } else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }

  @Test
  public void testRaiseUnfavoredAbilityAbove3() throws Exception {
    AbilityCreationPoints abilityCreationPoints = new AbilityCreationPoints(2, 3, 4);
    initializeModelWith(abilityCreationPoints);
    Trait unfavoredAbility = setUnfavoredAbilityTo(AbilityType.Archery, 4);
    AbilityCostCalculatorImpl calculator = startCalculation(abilityCreationPoints);
    assertEquals(0, calculator.getFreePointsSpent(true));
    assertEquals(3, calculator.getFreePointsSpent(false));
    for (Trait ability : getAllAbilities()) {
      if (ability == unfavoredAbility) {
        FavorableTraitCost[] allAbilityCost = calculator.getCosts(ability);
        FavorableTraitCost abilityCost = allAbilityCost[0];
        assertEquals("Ability " + ability, 2, abilityCost.getBonusCost());
        assertEquals("Ability " + ability, 0, abilityCost.getFavoredPointCost());
        assertEquals("Ability " + ability, 3, abilityCost.getGeneralPointCost());
      } else {
        assertEmptyCosts(calculator, ability);
      }
    }
  }

  private void initializeModelWith(AbilityCreationPoints abilityCreationPoints) {
    dummyHero.template.creationPoints.abilityCreationPoints = abilityCreationPoints;
    dummyHero.addModel(new PointModelImpl());
    abilityModel.initialize(new DummyHeroEnvironment(), dummyHero);
  }

  private void assertBonusPointCostsSumUpTo(int value, FavorableTraitCost... costs) {
    int sum = 0;
    for (FavorableTraitCost cost : costs) {
      sum += cost.getBonusCost();
    }
    assertEquals("Bonus cost sum " + sum, value, sum);
  }

  private void assertFavoredPointCostsSumUpTo(int value, FavorableTraitCost... costs) {
    int sum = 0;
    for (FavorableTraitCost cost : costs) {
      sum += cost.getFavoredPointCost();
    }
    assertEquals("Favored point cost sum " + sum, value, sum);
  }

  private void assertGeneralPointCostsSumUpTo(int value, FavorableTraitCost... costs) {
    int sum = 0;
    for (FavorableTraitCost cost : costs) {
      sum += cost.getGeneralPointCost();
    }
    assertEquals("General point cost sum " + sum, value, sum);
  }

  private void addGroupsToTemplate() {
    addGroupToTemplate(AbilityGroupType.War.getId(), AbilityType.Archery, AbilityType.Melee, AbilityType.War,
            AbilityType.Thrown, AbilityType.MartialArts, AbilityType.Integrity, AbilityType.Performance,
            AbilityType.Presence, AbilityType.Survival, AbilityType.Resistance);
    addGroupToTemplate(AbilityGroupType.Life.getId(), AbilityType.Occult, AbilityType.Craft, AbilityType.Lore,
            AbilityType.Investigation, AbilityType.Medicine, AbilityType.Awareness, AbilityType.Athletics,
            AbilityType.Dodge, AbilityType.Larceny, AbilityType.Stealth);
    addGroupToTemplate(AbilityGroupType.Wisdom.getId(), AbilityType.Ride, AbilityType.Sail, AbilityType.Bureaucracy,
            AbilityType.Linguistics, AbilityType.Socialize);
  }

  private void addGroupToTemplate(String groupId, TraitType... traitTypes) {
    Group group = new Group();
    group.id = groupId;
    for (TraitType type : traitTypes) {
      group.traits.add(type.getId());
    }
    abilitiesTemplate.groups.add(group);
  }
}

