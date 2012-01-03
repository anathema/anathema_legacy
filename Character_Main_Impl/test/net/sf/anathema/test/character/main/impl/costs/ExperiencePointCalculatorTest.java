package net.sf.anathema.test.character.main.impl.costs;

import net.sf.anathema.character.dummy.trait.DummyBasicTrait;
import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.impl.model.advance.ExperiencePointCostCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExperiencePointCalculatorTest {

  private IExperiencePointCosts experienceCosts = mock(IExperiencePointCosts.class);
  private ExperiencePointCostCalculator calculator = new ExperiencePointCostCalculator(experienceCosts);

  @Test
  public void testEssenceCosts() throws Exception {
    when(experienceCosts.getEssenceCosts()).thenReturn(new MultiplyRatingCosts(8));
    assertEquals(16, calculator.getEssenceCosts(new DummyBasicTrait(2, 3)));
  }

  @Test
  public void testVirtueCosts() throws Exception {
    when(experienceCosts.getVirtueCosts()).thenReturn(new MultiplyRatingCosts(5));
    assertEquals(15, calculator.getVirtueCosts(new DummyBasicTrait(3, 4)));
  }

  @Test
  public void testWillpowerCosts() throws Exception {
    when(experienceCosts.getWillpowerCosts()).thenReturn(new MultiplyRatingCosts(1));
    assertEquals(3, calculator.getWillpowerCosts(new DummyBasicTrait(3, 4)));
  }
}