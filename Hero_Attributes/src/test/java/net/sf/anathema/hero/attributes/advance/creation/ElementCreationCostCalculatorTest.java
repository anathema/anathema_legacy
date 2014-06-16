package net.sf.anathema.hero.attributes.advance.creation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementCreationCostCalculatorTest {

  private ElementCreationCostCalculator costCalculator = new ElementCreationCostCalculator();

  @Test
  public void calculatesNoCostForATraitAtBaseValue() throws Exception {
    ElementCreationCost actual = calculateActualCost(new SimpleCostElement(1, 1), 1, 2);
    int expectedDots = 0;
    int expectedBonusPoints = 0;
    assertThatCostEqualsExpectation(actual, expectedDots, expectedBonusPoints);
  }

  @Test
  public void prefersFreeDotsOverBonusPoints() throws Exception {
    ElementCreationCost actual = calculateActualCost(new SimpleCostElement(1, 2), 1, 2);
    assertThatCostEqualsExpectation(actual, 1, 0);
  }

  @Test
  public void usesBonusPointsIfNoFreeDotsAvailable() throws Exception {
    ElementCreationCost actual = calculateActualCost(new SimpleCostElement(1, 2), 0, 2);
    assertThatCostEqualsExpectation(actual, 0, 2);
  }

  @Test
  public void usesBonusPointsIfAllFreeDotsAreSpent() throws Exception {
    ElementCreationCost actual = calculateActualCost(new SimpleCostElement(1, 3), 1, 2);
    assertThatCostEqualsExpectation(actual, 1, 2);
  }

  private ElementCreationCost calculateActualCost(SimpleCostElement trait, int freeDots, int bonusPointCost) {
    return costCalculator.calculateElementCreationCost(trait, freeDots, bonusPointCost);
  }

  private void assertThatCostEqualsExpectation(ElementCreationCost actual, int expectedDots, int expectedBonusPoints) {
    assertEquals(new ElementCreationCost(expectedDots, expectedBonusPoints), actual);
  }

}