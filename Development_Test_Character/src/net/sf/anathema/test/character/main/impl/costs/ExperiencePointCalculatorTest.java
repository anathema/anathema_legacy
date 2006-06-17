package net.sf.anathema.test.character.main.impl.costs;

import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.impl.model.advance.ExperiencePointCostCalculator;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.test.character.dummy.DummyBasicTrait;

import org.easymock.MockControl;

public class ExperiencePointCalculatorTest extends BasicTestCase {

  private ExperiencePointCostCalculator calculator;
  private IExperiencePointCosts experienceCosts;
  private MockControl experienceCostsControl;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.experienceCostsControl = MockControl.createStrictControl(IExperiencePointCosts.class);
    this.experienceCosts = (IExperiencePointCosts) experienceCostsControl.getMock();
    this.calculator = new ExperiencePointCostCalculator(experienceCosts);
  }

  public void testAbilityCosts() throws Exception {
    experienceCosts.getAbilityCosts(true);
    experienceCostsControl.setReturnValue(new MultiplyRatingCosts(2), MockControl.ONE_OR_MORE);
    experienceCosts.getAbilityCosts(false);
    experienceCostsControl.setReturnValue(new MultiplyRatingCosts(3), MockControl.ONE_OR_MORE);
    experienceCostsControl.replay();
    assertEquals(4, calculator.getAbilityCosts(new DummyBasicTrait(2, 3), true));
    assertEquals(6, calculator.getAbilityCosts(new DummyBasicTrait(2, 3), false));
  }

  public void testAttributeCosts() throws Exception {
    experienceCosts.getAttributeCosts();
    experienceCostsControl.setReturnValue(new MultiplyRatingCosts(3), MockControl.ONE_OR_MORE);
    experienceCostsControl.replay();
    assertEquals(6, calculator.getAttributeCosts(new DummyBasicTrait(2, 3)));
    assertEquals(15, calculator.getAttributeCosts(new DummyBasicTrait(2, 4)));
  }

  public void testEssenceCosts() throws Exception {
    experienceCosts.getEssenceCosts();
    experienceCostsControl.setReturnValue(new MultiplyRatingCosts(8), MockControl.ONE_OR_MORE);
    experienceCostsControl.replay();
    assertEquals(16, calculator.getEssenceCosts(new DummyBasicTrait(2, 3)));
  }

  // todo NOW vom (09.04.2005) (sieroux): Soll das getestet werden?
  // public void testRatingExperiencedCostsBelowOrEqualsToCreationValue() throws Exception {
  // MultiplyRatingCosts ratingCosts = new MultiplyRatingCosts(3, 1);
  // assertEquals(0, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(0, 0), ratingCosts));
  // assertEquals(0, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(1, 1), ratingCosts));
  // assertEquals(0, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(2, 0), ratingCosts));
  // }
  //
  // public void testRatingExperiencedCostsFromHigherValue() throws Exception {
  // MultiplyRatingCosts ratingCosts = new MultiplyRatingCosts(3, 1);
  // assertEquals(0, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(1, 1), ratingCosts));
  // assertEquals(3, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(1, 2), ratingCosts));
  // assertEquals(9, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(1, 3), ratingCosts));
  // assertEquals(18, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(1, 4), ratingCosts));
  // }
  //
  // public void testRatingExperiencedCostsFromZero() throws Exception {
  // MultiplyRatingCosts ratingCosts = new MultiplyRatingCosts(3, 1);
  // assertEquals(1, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(0, 1), ratingCosts));
  // assertEquals(4, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(0, 2), ratingCosts));
  // assertEquals(10, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(0, 3), ratingCosts));
  // assertEquals(19, ExperiencePointCalculator.getTraitRatingCosts(new DummyBasicTrait(0, 4), ratingCosts));
  // }

  public void testVirtueCosts() throws Exception {
    experienceCosts.getVirtueCosts();
    experienceCostsControl.setReturnValue(new MultiplyRatingCosts(5), MockControl.ONE_OR_MORE);
    experienceCostsControl.replay();
    assertEquals(15, calculator.getVirtueCosts(new DummyBasicTrait(3, 4)));
  }

  public void testWillpowerCosts() throws Exception {
    experienceCosts.getWillpowerCosts();
    experienceCostsControl.setReturnValue(new MultiplyRatingCosts(1), MockControl.ONE_OR_MORE);
    experienceCostsControl.replay();
    assertEquals(3, calculator.getWillpowerCosts(new DummyBasicTrait(3, 4)));
  }
}