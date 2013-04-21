package net.sf.anathema.character.impl.costs;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.traits.DummyTraitTemplateFactory;
import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.impl.model.creation.bonus.virtue.VirtueCostCalculator;
import net.sf.anathema.character.impl.model.traits.VirtueTemplateFactory;
import net.sf.anathema.character.impl.model.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.testing.BasicCharacterTestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VirtueCostCalculatorTest {

  private VirtueCostCalculator calculator;
  private IDefaultTrait[] virtues;

  @Before
  public void setUp() throws Exception {
    BonusPointCosts cost = new DefaultBonusPointCosts();
    ITraitContext traitContext = new BasicCharacterTestCase().createModelContextWithEssence2(new CreationTraitValueStrategy()).getTraitContext();
    this.virtues = new DefaultTraitFactory(traitContext, new NullAdditionalRules(), new VirtueTemplateFactory(new DummyTraitTemplateFactory())).createTraits(VirtueType.values());
    this.calculator = new VirtueCostCalculator(virtues, 5, cost);
  }

  @Test
  public void testNothingSpent() throws Exception {
    calculator.calculateVirtuePoints();
    assertEquals(0, calculator.getBonusPointsSpent());
    assertEquals(0, calculator.getVirtueDotsSpent());
  }

  @Test
  public void testVirtueAtThreeNoBonusPointsSpent() throws Exception {
    virtues[0].setCreationValue(3);
    calculator.calculateVirtuePoints();
    assertEquals(0, calculator.getBonusPointsSpent());
    assertEquals(2, calculator.getVirtueDotsSpent());
  }

  @Test
  public void testVirtueAtFourOneBonusPointsSpent() throws Exception {
    virtues[0].setCreationValue(4);
    calculator.calculateVirtuePoints();
    assertEquals(3, calculator.getBonusPointsSpent());
    assertEquals(2, calculator.getVirtueDotsSpent());
  }

  @Test
  public void testVirtueDotsSpentNoBonusPointsSpent() throws Exception {
    virtues[0].setCreationValue(3);
    virtues[1].setCreationValue(3);
    virtues[2].setCreationValue(2);
    calculator.calculateVirtuePoints();
    assertEquals(0, calculator.getBonusPointsSpent());
    assertEquals(5, calculator.getVirtueDotsSpent());
  }

  @Test
  public void testMoreThanFiveDotsOnValuesLessFour() throws Exception {
    virtues[0].setCreationValue(3);
    virtues[1].setCreationValue(3);
    virtues[2].setCreationValue(3);
    calculator.calculateVirtuePoints();
    assertEquals(3, calculator.getBonusPointsSpent());
    assertEquals(5, calculator.getVirtueDotsSpent());
  }

  @Test
  public void testMoreThanMaximumAndHighValues() throws Exception {
    virtues[0].setCreationValue(5);
    virtues[1].setCreationValue(3);
    virtues[2].setCreationValue(3);
    calculator.calculateVirtuePoints();
    assertEquals(9, calculator.getBonusPointsSpent());
    assertEquals(5, calculator.getVirtueDotsSpent());
  }

  @Test
  public void testAllVirtuesMaximal() throws Exception {
    for (IDefaultTrait virtue : virtues) {
      virtue.setCreationValue(5);
    }
    calculator.calculateVirtuePoints();
    assertEquals(5, calculator.getVirtueDotsSpent());
    assertEquals(33, calculator.getBonusPointsSpent());
  }
}