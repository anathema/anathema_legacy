package net.sf.anathema.hero.spiritual.advance;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.hero.BasicCharacterTestCase;
import net.sf.anathema.hero.dummy.DummyBonusPointCosts;
import net.sf.anathema.hero.dummy.DummyHero;
import net.sf.anathema.hero.dummy.template.DummyTraitTemplateFactory;
import net.sf.anathema.character.main.traits.VirtueTemplateFactory;
import net.sf.anathema.character.main.traits.context.CreationTraitValueStrategy;
import net.sf.anathema.character.main.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.hero.spiritual.advance.creation.SpiritualCreationData;
import net.sf.anathema.hero.spiritual.advance.creation.VirtueBonusCostCalculator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class VirtueBonusCostCalculatorTest {

  private VirtueBonusCostCalculator calculator;
  private Trait[] virtues;

  @Before
  public void setUp() throws Exception {
    BonusPointCosts cost = new DummyBonusPointCosts();
    DummyHero hero = new BasicCharacterTestCase().createModelContextWithEssence2(new CreationTraitValueStrategy());
    this.virtues = new DefaultTraitFactory(hero, new VirtueTemplateFactory(new DummyTraitTemplateFactory())).createTraits(VirtueType.values());
    SpiritualCreationData creationData = Mockito.mock(SpiritualCreationData.class);
    when(creationData.getMaximumFreeVirtueRank()).thenReturn(3);
    when(creationData.getVirtueCost()).thenReturn(cost.getVirtueCosts());
    when(creationData.getFreeVirtueCreationDots()).thenReturn(5);
    this.calculator = new VirtueBonusCostCalculator(virtues, creationData);
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
    for (Trait virtue : virtues) {
      virtue.setCreationValue(5);
    }
    calculator.calculateVirtuePoints();
    assertEquals(5, calculator.getVirtueDotsSpent());
    assertEquals(33, calculator.getBonusPointsSpent());
  }
}