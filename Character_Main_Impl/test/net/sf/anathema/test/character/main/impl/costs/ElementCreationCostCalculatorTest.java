package net.sf.anathema.test.character.main.impl.costs;

import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCost;
import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.basic.SimpleCostElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementCreationCostCalculatorTest {

  private ElementCreationCostCalculator costCalculator = new ElementCreationCostCalculator();

  private void assertCalculatedCosts(
    ElementCreationCost expectedCosts,
    SimpleCostElement element,
    int freeDots,
    int bonusPointCostFactor) {
    assertEquals(expectedCosts, costCalculator.calculateElementCreationCost(element, freeDots, bonusPointCostFactor));
  }

  @Test
  public void testUnraisedElement() throws Exception {
    assertCalculatedCosts(new ElementCreationCost(0, 0), new SimpleCostElement(3, 3), 4, 2);
  }

  @Test
  public void testRaiseElementWithFreeDots() throws Exception {
    assertCalculatedCosts(new ElementCreationCost(2, 0), new SimpleCostElement(1, 3), 2, 3);
  }

  @Test
  public void testRaiseElementWithBonusPoints() throws Exception {
    assertCalculatedCosts(new ElementCreationCost(0, 6), new SimpleCostElement(1, 4), 0, 2);
  }

  @Test
  public void testRaiseElementWithFreeDotsAndBonusPoints() throws Exception {
    assertCalculatedCosts(new ElementCreationCost(1, 4), new SimpleCostElement(1, 4), 1, 2);
  }
}