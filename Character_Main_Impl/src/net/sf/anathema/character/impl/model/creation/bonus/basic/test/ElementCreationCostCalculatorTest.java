package net.sf.anathema.character.impl.model.creation.bonus.basic.test;

import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCost;
import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.basic.SimpleCostElement;
import net.sf.anathema.lib.testing.BasicTestCase;

public class ElementCreationCostCalculatorTest extends BasicTestCase {

  private ElementCreationCostCalculator costCalculator;

  private void assertCalculatedCosts(
      ElementCreationCost expectedCosts,
      SimpleCostElement element,
      int freeDots,
      int bonusPointCostFactor) {
    assertEquals(expectedCosts, costCalculator.calculateElementCreationCost(element, freeDots, bonusPointCostFactor));
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.costCalculator = new ElementCreationCostCalculator();
  }

  public void testUnraisedElement() throws Exception {
    assertCalculatedCosts(new ElementCreationCost(0, 0), new SimpleCostElement(3, 3), 4, 2);
  }

  public void testRaiseElementWithFreeDots() throws Exception {
    assertCalculatedCosts(new ElementCreationCost(2, 0), new SimpleCostElement(1, 3), 2, 3);
  }

  public void testRaiseElementWithBonusPoints() throws Exception {
    assertCalculatedCosts(new ElementCreationCost(0, 6), new SimpleCostElement(1, 4), 0, 2);
  }

  public void testRaiseElementWithFreeDotsAndBonusPoints() throws Exception {
    assertCalculatedCosts(new ElementCreationCost(1, 4), new SimpleCostElement(1, 4), 1, 2);
  }
}