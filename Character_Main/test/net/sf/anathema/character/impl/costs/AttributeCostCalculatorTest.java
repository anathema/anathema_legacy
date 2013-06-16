package net.sf.anathema.character.impl.costs;

import net.sf.anathema.character.dummy.generic.DummyAdditionalBonusPointManagment;
import net.sf.anathema.character.dummy.trait.DummyCoreTraitConfiguration;
import net.sf.anathema.character.dummy.trait.DummyTraitContext;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.dummy.DummyGenericCharacter;
import net.sf.anathema.character.generic.dummy.template.DummyCharacterTemplate;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.traits.DummyTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.impl.model.context.BasicCharacterContext;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.AttributeCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.util.TraitGroupCost;
import net.sf.anathema.character.impl.model.traits.creation.AdditionRulesTraitValueChangeChecker;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.traits.types.AttributeGroupType.Mental;
import static net.sf.anathema.character.generic.traits.types.AttributeGroupType.Physical;
import static net.sf.anathema.character.generic.traits.types.AttributeGroupType.Social;
import static net.sf.anathema.character.generic.traits.types.OtherTraitType.Essence;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AttributeCostCalculatorTest {

  private static final int ATTRIBUTE_BONUS_POINT_COST = 4;

  private void assertEmptyPoints(AttributeGroupType groupType) {
    TraitGroupCost costs = calculator.getAttributePoints(groupType);
    assertEquals(0, costs.getBonusPointsSpent());
    assertEquals(0, costs.getDotsSpent());
    assertTrue(traitConfiguration.containsAllTraits(groupType, costs.getTraits()));
  }

  private void assertFullyLearned(AttributeGroupType groupType, int allowedDotCount) {
    TraitGroupCost costs = calculator.getAttributePoints(groupType);
    assertEquals(0, costs.getBonusPointsSpent());
    assertEquals(allowedDotCount, costs.getDotsSpent());
    assertEquals(allowedDotCount, costs.getPointsToSpend());
    assertTrue(traitConfiguration.containsAllTraits(groupType, costs.getTraits()));
  }

  private void assertOverlearned(AttributeGroupType groupType, int allowedDotCount, int bonusPoints) {
    TraitGroupCost costs = calculator.getAttributePoints(groupType);
    assertEquals(allowedDotCount, costs.getDotsSpent());
    assertEquals(allowedDotCount, costs.getPointsToSpend());
    assertEquals(bonusPoints, costs.getBonusPointsSpent());
    assertTrue(traitConfiguration.containsAllTraits(groupType, costs.getTraits()));
  }

  public void spendPoints(AttributeGroupType groupType, int pointsToSpent) {
    Trait[] groupAttributes = traitConfiguration.getAllTraits(groupType);
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
  private AttributeCostCalculator calculator;
  private DummyCoreTraitConfiguration traitConfiguration;

  @Before
  public void setUp() throws Exception {
    this.creationPoint = new AttributeCreationPoints(6, 4, 2);
    this.traitConfiguration = new DummyCoreTraitConfiguration();
    DummyAdditionalBonusPointManagment additionalBonusPointManagement = new DummyAdditionalBonusPointManagment();
    addAttributesAndEssence(traitConfiguration);
    DefaultBonusPointCosts cost = new DefaultBonusPointCosts();
    this.calculator = new AttributeCostCalculator(traitConfiguration.getAttributeConfiguration(), creationPoint, cost, additionalBonusPointManagement);
  }

  private void addAttributesAndEssence(DummyCoreTraitConfiguration coreTraits) {
    IAdditionalRules additionalRules = new NullAdditionalRules();
    TraitTemplateCollection templateCollection = new TraitTemplateCollection(new DummyTraitTemplateFactory());
    DummyTraitContext traitContext = new DummyTraitContext(coreTraits);
    ITraitTemplate essenceTraitTemplate = templateCollection.getTraitTemplate(Essence);
    ILimitationContext limitationContext = traitContext.getLimitationContext();
    TraitRules essenceRules = new TraitRules(Essence, essenceTraitTemplate, limitationContext);
    coreTraits.addTestTrait(new DefaultTrait(essenceRules, traitContext, new FriendlyValueChangeChecker()));
    CharacterListening listening = new CharacterListening();
    GrumpyIncrementChecker incrementChecker = new GrumpyIncrementChecker();
    for (AttributeType traitType : AttributeType.values()) {
      ITraitTemplate traitTemplate = templateCollection.getTraitTemplate(traitType);
      IValueChangeChecker checker = new AdditionRulesTraitValueChangeChecker(traitType, limitationContext, additionalRules.getAdditionalTraitRules());
      coreTraits.addTestTrait(new DefaultTrait(new FavorableTraitRules(traitType, traitTemplate, limitationContext), new ICasteType[0], traitContext,
              new BasicCharacterContext(new DummyGenericCharacter(new DummyCharacterTemplate())), listening, checker, incrementChecker));
    }
  }

  @Test
  public void testNoAttributesLearned() throws Exception {
    calculator.calculateAttributeCosts();
    assertEmptyPoints(Physical);
    assertEmptyPoints(Social);
    assertEmptyPoints(Mental);
    assertAllPointsToSpendUsed(creationPoint.getCounts());
  }

  @Test
  public void testFirstGroupIsPrimaryGroup() throws Exception {
    spendPoints(Physical, creationPoint.getPrimaryCount());
    calculator.calculateAttributeCosts();
    assertEmptyPoints(Social);
    assertEmptyPoints(Mental);
    assertFullyLearned(Physical, creationPoint.getPrimaryCount());
    assertAllPointsToSpendUsed(creationPoint.getCounts());
  }

  @Test
  public void testLastGroupIsPrimaryGroup() throws Exception {
    spendPoints(Mental, creationPoint.getPrimaryCount());
    calculator.calculateAttributeCosts();
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
    calculator.calculateAttributeCosts();
    assertFullyLearned(Physical, creationPoint.getPrimaryCount());
    assertFullyLearned(Mental, creationPoint.getSecondaryCount());
    assertFullyLearned(Social, creationPoint.getTertiaryCount());
  }

  @Test
  public void testPrimaryGroupLearnedOneWithBonusPoints() throws Exception {
    spendPoints(Physical, creationPoint.getPrimaryCount() + 1);
    spendPoints(Mental, creationPoint.getSecondaryCount());
    spendPoints(Social, creationPoint.getTertiaryCount());
    calculator.calculateAttributeCosts();
    assertOverlearned(Physical, creationPoint.getPrimaryCount(), ATTRIBUTE_BONUS_POINT_COST);
    assertFullyLearned(Mental, creationPoint.getSecondaryCount());
    assertFullyLearned(Social, creationPoint.getTertiaryCount());
  }

  @Test
  public void testAllGroupsLearnedOneWithBonusPoints() throws Exception {
    spendPoints(Physical, creationPoint.getPrimaryCount() + 1);
    spendPoints(Mental, creationPoint.getSecondaryCount() + 1);
    spendPoints(Social, creationPoint.getTertiaryCount() + 1);
    calculator.calculateAttributeCosts();
    assertOverlearned(Physical, creationPoint.getPrimaryCount(), ATTRIBUTE_BONUS_POINT_COST);
    assertOverlearned(Mental, creationPoint.getSecondaryCount(), ATTRIBUTE_BONUS_POINT_COST);
    assertOverlearned(Social, creationPoint.getTertiaryCount(), ATTRIBUTE_BONUS_POINT_COST);
  }
}