package net.sf.anathema.hero.attributes;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.template.points.AttributeCreationPoints;
import net.sf.anathema.hero.template.points.IAttributeCreationPoints;
import net.sf.anathema.hero.traits.model.types.AttributeGroupType;
import net.sf.anathema.hero.attributes.advance.creation.AttributeBonusPointCalculator;
import net.sf.anathema.hero.attributes.advance.creation.AttributeCreationData;
import net.sf.anathema.hero.attributes.advance.creation.TraitGroupCost;
import net.sf.anathema.hero.attributes.model.AttributeModelImpl;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.models.DummyHeroConcept;
import net.sf.anathema.hero.dummy.models.DummySpiritualTraitModel;
import net.sf.anathema.hero.dummy.models.DummyTraitModel;
import net.sf.anathema.hero.traits.template.Group;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.sf.anathema.hero.traits.model.types.AttributeGroupType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttributeCostCalculatorTest {

  private static final int ATTRIBUTE_BONUS_POINT_COST = 4;

  private void assertEmptyPoints(AttributeGroupType groupType) {
    TraitGroupCost costs = calculator.getAttributePoints(groupType);
    assertEquals(0, costs.getBonusPointsSpent());
    assertEquals(0, costs.getDotsSpent());
  }

  private void assertFullyLearned(AttributeGroupType groupType, int allowedDotCount) {
    TraitGroupCost costs = calculator.getAttributePoints(groupType);
    assertEquals(0, costs.getBonusPointsSpent());
    assertEquals(allowedDotCount, costs.getDotsSpent());
    assertEquals(allowedDotCount, costs.getPointsToSpend());
  }

  private void assertOverlearned(AttributeGroupType groupType, int allowedDotCount, int bonusPoints) {
    TraitGroupCost costs = calculator.getAttributePoints(groupType);
    assertEquals(allowedDotCount, costs.getDotsSpent());
    assertEquals(allowedDotCount, costs.getPointsToSpend());
    assertEquals(bonusPoints, costs.getBonusPointsSpent());
  }

  public void spendPoints(AttributeGroupType groupType, int pointsToSpent) {
    Trait[] groupAttributes = attributeModel.getAll(groupType);
    int perAttribute = (int) Math.ceil((double) pointsToSpent / groupAttributes.length);
    int remainingPointsToSpent = pointsToSpent;
    for (Trait attribute : groupAttributes) {
      int toSpent = Math.min(perAttribute, remainingPointsToSpent);
      attribute.setCreationValue(1 + toSpent);
      remainingPointsToSpent -= toSpent;
    }
  }

  private void assertAllPointsToSpendUsed(int[] dotsToSpend) {
    List<Integer> dotsToSpendList = new ArrayList<>();
    for (int dot : dotsToSpend) {
      dotsToSpendList.add(dot);
    }
    for (AttributeGroupType attributeGroupType : AttributeGroupType.values()) {
      TraitGroupCost attributePoints = calculator.getAttributePoints(attributeGroupType);
      Integer groupDotsToSpend = attributePoints.getPointsToSpend();
      assertTrue("Not contains dotCount " + groupDotsToSpend, dotsToSpendList.contains(groupDotsToSpend));
      dotsToSpendList.remove(groupDotsToSpend);
    }
    assertTrue(dotsToSpendList.isEmpty());
  }

  private IAttributeCreationPoints creationPoint;
  private AttributeBonusPointCalculator calculator;
  private AttributeModelImpl attributeModel = createAttributeModel();
  private DummyHero dummyHero = new DummyHero();

  @Before
  public void setUp() throws Exception {
    dummyHero.addModel(attributeModel);
    dummyHero.addModel(new DummyTraitModel());
    dummyHero.addModel(new DummyHeroConcept());
    dummyHero.addModel(new DummySpiritualTraitModel());
    attributeModel.initialize(null, dummyHero);
    this.creationPoint = new AttributeCreationPoints(6, 4, 2);
    AttributeCreationData creationData = mock(AttributeCreationData.class);
    when(creationData.getCounts()).thenReturn(new int[] {6, 4, 2});
    when(creationData.getAttributeCosts(any())).thenReturn(ATTRIBUTE_BONUS_POINT_COST);
    when(creationData.getCalculationBase(any())).thenReturn(1);
    this.calculator = new AttributeBonusPointCalculator(attributeModel, creationData);
  }


  private AttributeModelImpl createAttributeModel() {
    GroupedTraitsTemplate template = new GroupedTraitsTemplate();
    template.groups.add(createGroup("Physical", "Strength", "Dexterity", "Stamina"));
    template.groups.add(createGroup("Social", "Charisma", "Manipulation", "Appearance"));
    template.groups.add(createGroup("Mental", "Perception", "Intelligence", "Wits"));
    return new AttributeModelImpl(template);
  }

  private Group createGroup(String id, String... traitIds) {
    Group physicalAttributes = new Group();
    physicalAttributes.id = id;
    physicalAttributes.traits.addAll(Arrays.asList(traitIds));
    return physicalAttributes;
  }


  @Test
  public void testNoAttributesLearned() throws Exception {
    calculator.recalculate();
    assertEmptyPoints(Physical);
    assertEmptyPoints(Social);
    assertEmptyPoints(Mental);
    assertAllPointsToSpendUsed(creationPoint.getCounts());
  }

  @Test
  public void testFirstGroupIsPrimaryGroup() throws Exception {
    spendPoints(Physical, creationPoint.getPrimaryCount());
    calculator.recalculate();
    assertEmptyPoints(Social);
    assertEmptyPoints(Mental);
    assertFullyLearned(Physical, creationPoint.getPrimaryCount());
    assertAllPointsToSpendUsed(creationPoint.getCounts());
  }

  @Test
  public void testLastGroupIsPrimaryGroup() throws Exception {
    spendPoints(Mental, creationPoint.getPrimaryCount());
    calculator.recalculate();
    assertEmptyPoints(Physical);
    assertEmptyPoints(Social);
    assertFullyLearned(Mental, creationPoint.getPrimaryCount());
    assertAllPointsToSpendUsed(creationPoint.getCounts());
  }

  @Test
  public void testAllDotsSpentWithoutBonusPoints() throws Exception {
    spendPoints(Physical, creationPoint.getPrimaryCount());
    spendPoints(Mental, creationPoint.getSecondaryCount());
    spendPoints(Social, creationPoint.getTertiaryCount());
    calculator.recalculate();
    assertFullyLearned(Physical, creationPoint.getPrimaryCount());
    assertFullyLearned(Mental, creationPoint.getSecondaryCount());
    assertFullyLearned(Social, creationPoint.getTertiaryCount());
  }

  @Test
  public void testPrimaryGroupLearnedOneWithBonusPoints() throws Exception {
    spendPoints(Physical, creationPoint.getPrimaryCount() + 1);
    spendPoints(Mental, creationPoint.getSecondaryCount());
    spendPoints(Social, creationPoint.getTertiaryCount());
    calculator.recalculate();
    assertOverlearned(Physical, creationPoint.getPrimaryCount(), ATTRIBUTE_BONUS_POINT_COST);
    assertFullyLearned(Mental, creationPoint.getSecondaryCount());
    assertFullyLearned(Social, creationPoint.getTertiaryCount());
  }

  @Test
  public void testAllGroupsLearnedOneWithBonusPoints() throws Exception {
    spendPoints(Physical, creationPoint.getPrimaryCount() + 1);
    spendPoints(Mental, creationPoint.getSecondaryCount() + 1);
    spendPoints(Social, creationPoint.getTertiaryCount() + 1);
    calculator.recalculate();
    assertOverlearned(Physical, creationPoint.getPrimaryCount(), ATTRIBUTE_BONUS_POINT_COST);
    assertOverlearned(Mental, creationPoint.getSecondaryCount(), ATTRIBUTE_BONUS_POINT_COST);
    assertOverlearned(Social, creationPoint.getTertiaryCount(), ATTRIBUTE_BONUS_POINT_COST);
  }
}