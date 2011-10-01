package net.sf.anathema.test.character.main.impl.costs;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.character.generic.impl.template.points.AttributeCreationPoints;
import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.context.BasicCharacterContext;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.AttributeCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.util.TraitGroupCost;
import net.sf.anathema.character.impl.model.traits.creation.AdditionRulesTraitValueChangeChecker;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.dummy.character.DummyAdditionalBonusPointManagment;
import net.sf.anathema.dummy.character.DummyGenericCharacter;
import net.sf.anathema.dummy.character.template.DummyCharacterTemplate;
import net.sf.anathema.dummy.character.trait.DummyCoreTraitConfiguration;
import net.sf.anathema.dummy.character.trait.DummyTraitContext;
import net.sf.anathema.test.character.BasicCharacterTestCase;

public class AttributeCostCalculatorTest extends BasicCharacterTestCase {

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
    ITrait[] groupAttributes = traitConfiguration.getAllTraits(groupType);
    int perAttribute = (int) Math.ceil((double) pointsToSpent / groupAttributes.length);
    int remainingPointsToSpent = pointsToSpent;
    for (ITrait attribute : groupAttributes) {
      int toSpent = Math.min(perAttribute, remainingPointsToSpent);
      ((IDefaultTrait) attribute).setCreationValue(1 + toSpent);
      remainingPointsToSpent -= toSpent;
    }
  }

  private void assertAllPointsToSpendUsed(int[] dotsToSpend) {
    List<Integer> dotsToSpendList = new ArrayList<Integer>();
    for (int dot : dotsToSpend) {
      dotsToSpendList.add(dot);
    }
    for (AttributeGroupType attributeGroupType : AttributeGroupType.values()) {
      TraitGroupCost attributePoints = calculator.getAttributePoints(attributeGroupType);
      Integer groupDotsToSpend = attributePoints.getPointsToSpend();
      assertTrue("Not contains dotCount " + groupDotsToSpend, dotsToSpendList.contains(groupDotsToSpend)); //$NON-NLS-1$
      dotsToSpendList.remove(groupDotsToSpend);
    }
    assertTrue(dotsToSpendList.isEmpty());
  }

  private IAttributeCreationPoints creationPoint;
    private AttributeCostCalculator calculator;
  private DummyCoreTraitConfiguration traitConfiguration;

    @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.creationPoint = new AttributeCreationPoints(6, 4, 2);
    this.traitConfiguration = new DummyCoreTraitConfiguration();
        DummyAdditionalBonusPointManagment additionalBonusPointManagement = new DummyAdditionalBonusPointManagment();
    addAttributesAndEssence(traitConfiguration);
      DefaultBonusPointCosts cost = new DefaultBonusPointCosts();
    this.calculator = new AttributeCostCalculator(traitConfiguration,
    		creationPoint,
            cost,
            additionalBonusPointManagement);
  }

  private void addAttributesAndEssence(DummyCoreTraitConfiguration coreTraits) {
    IAdditionalRules additionalRules = new NullAdditionalRules();
    TraitTemplateCollection templateCollection = new TraitTemplateCollection(new ExaltTraitTemplateFactory());
    DummyTraitContext traitContext = new DummyTraitContext(coreTraits);
    ITraitTemplate essenceTraitTemplate = templateCollection.getTraitTemplate(OtherTraitType.Essence);
    ILimitationContext limitationContext = traitContext.getLimitationContext();
    TraitRules essenceRules = new TraitRules(OtherTraitType.Essence, essenceTraitTemplate, limitationContext);
    coreTraits.addTestTrait(new DefaultTrait(essenceRules, traitContext, new FriendlyValueChangeChecker()));
    CharacterListening listening = new CharacterListening();
    GrumpyIncrementChecker incrementChecker = new GrumpyIncrementChecker();
    for (AttributeType traitType : AttributeType.values()) {
      ITraitTemplate traitTemplate = templateCollection.getTraitTemplate(traitType);
      IValueChangeChecker checker = new AdditionRulesTraitValueChangeChecker(
          traitType,
          limitationContext,
          additionalRules.getAdditionalTraitRules());
      coreTraits.addTestTrait(new DefaultTrait(
          new FavorableTraitRules(traitType, traitTemplate, limitationContext),
          null,
          traitContext,
          new BasicCharacterContext(new DummyGenericCharacter(new DummyCharacterTemplate())),
          listening,
          checker,
          incrementChecker));
    }
  }

  public void testNoAttributesLearned() throws Exception {
    calculator.calculateAttributeCosts();
    assertEmptyPoints(AttributeGroupType.Physical);
    assertEmptyPoints(AttributeGroupType.Social);
    assertEmptyPoints(AttributeGroupType.Mental);
    assertAllPointsToSpendUsed(creationPoint.getCounts());
  }

  public void testFirstGroupIsPrimaryGroup() throws Exception {
    spendPoints(AttributeGroupType.Physical, creationPoint.getPrimaryCount());
    calculator.calculateAttributeCosts();
    assertEmptyPoints(AttributeGroupType.Social);
    assertEmptyPoints(AttributeGroupType.Mental);
    assertFullyLearned(AttributeGroupType.Physical, creationPoint.getPrimaryCount());
    assertAllPointsToSpendUsed(creationPoint.getCounts());
  }

  public void testLastGroupIsPrimaryGroup() throws Exception {
    spendPoints(AttributeGroupType.Mental, creationPoint.getPrimaryCount());
    calculator.calculateAttributeCosts();
    assertEmptyPoints(AttributeGroupType.Physical);
    assertEmptyPoints(AttributeGroupType.Social);
    assertFullyLearned(AttributeGroupType.Mental, creationPoint.getPrimaryCount());
    assertAllPointsToSpendUsed(creationPoint.getCounts());
  }

  public void testAllDotsSpentWithoutBonusPoints() throws Exception {
    spendPoints(AttributeGroupType.Physical, creationPoint.getPrimaryCount());
    spendPoints(AttributeGroupType.Mental, creationPoint.getSecondaryCount());
    spendPoints(AttributeGroupType.Social, creationPoint.getTertiaryCount());
    calculator.calculateAttributeCosts();
    assertFullyLearned(AttributeGroupType.Physical, creationPoint.getPrimaryCount());
    assertFullyLearned(AttributeGroupType.Mental, creationPoint.getSecondaryCount());
    assertFullyLearned(AttributeGroupType.Social, creationPoint.getTertiaryCount());
  }

  public void testPrimaryGroupLearnedOneWithBonusPoints() throws Exception {
    spendPoints(AttributeGroupType.Physical, creationPoint.getPrimaryCount() + 1);
    spendPoints(AttributeGroupType.Mental, creationPoint.getSecondaryCount());
    spendPoints(AttributeGroupType.Social, creationPoint.getTertiaryCount());
    calculator.calculateAttributeCosts();
    assertOverlearned(AttributeGroupType.Physical, creationPoint.getPrimaryCount(), ATTRIBUTE_BONUS_POINT_COST);
    assertFullyLearned(AttributeGroupType.Mental, creationPoint.getSecondaryCount());
    assertFullyLearned(AttributeGroupType.Social, creationPoint.getTertiaryCount());
  }

  public void testAllGroupsLearnedOneWithBonusPoints() throws Exception {
    spendPoints(AttributeGroupType.Physical, creationPoint.getPrimaryCount() + 1);
    spendPoints(AttributeGroupType.Mental, creationPoint.getSecondaryCount() + 1);
    spendPoints(AttributeGroupType.Social, creationPoint.getTertiaryCount() + 1);
    calculator.calculateAttributeCosts();
    assertOverlearned(AttributeGroupType.Physical, creationPoint.getPrimaryCount(), ATTRIBUTE_BONUS_POINT_COST);
    assertOverlearned(AttributeGroupType.Mental, creationPoint.getSecondaryCount(), ATTRIBUTE_BONUS_POINT_COST);
    assertOverlearned(AttributeGroupType.Social, creationPoint.getTertiaryCount(), ATTRIBUTE_BONUS_POINT_COST);
  }
}