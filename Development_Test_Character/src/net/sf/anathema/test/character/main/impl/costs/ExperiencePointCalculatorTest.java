package net.sf.anathema.test.character.main.impl.costs;

import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.impl.model.advance.ExperiencePointCostCalculator;
import net.sf.anathema.dummy.character.trait.DummyBasicTrait;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.easymock.EasyMock;

public class ExperiencePointCalculatorTest extends BasicTestCase {

  private ExperiencePointCostCalculator calculator;
  private IExperiencePointCosts experienceCosts;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.experienceCosts = EasyMock.createStrictMock(IExperiencePointCosts.class);
    this.calculator = new ExperiencePointCostCalculator(experienceCosts);
  }

  public void testAbilityCosts() throws Exception {
    EasyMock.expect(experienceCosts.getAbilityCosts(true)).andReturn(new MultiplyRatingCosts(2)).atLeastOnce();
    EasyMock.expect(experienceCosts.getAbilityCosts(false)).andReturn(new MultiplyRatingCosts(3)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    assertEquals(4, calculator.getAbilityCosts(new DummyBasicTrait(2, 3), true));
    assertEquals(6, calculator.getAbilityCosts(new DummyBasicTrait(2, 3), false));
  }

  public void testAttributeCosts() throws Exception {
    EasyMock.expect(experienceCosts.getAttributeCosts()).andReturn(new MultiplyRatingCosts(3)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    assertEquals(6, calculator.getAttributeCosts(new DummyBasicTrait(2, 3)));
    assertEquals(15, calculator.getAttributeCosts(new DummyBasicTrait(2, 4)));
  }

  public void testEssenceCosts() throws Exception {
    EasyMock.expect(experienceCosts.getEssenceCosts()).andReturn(new MultiplyRatingCosts(8)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    assertEquals(16, calculator.getEssenceCosts(new DummyBasicTrait(2, 3)));
  }

  public void testVirtueCosts() throws Exception {
    EasyMock.expect(experienceCosts.getVirtueCosts()).andReturn(new MultiplyRatingCosts(5)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    assertEquals(15, calculator.getVirtueCosts(new DummyBasicTrait(3, 4)));
  }

  public void testWillpowerCosts() throws Exception {
    EasyMock.expect(experienceCosts.getWillpowerCosts()).andReturn(new MultiplyRatingCosts(1)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    assertEquals(3, calculator.getWillpowerCosts(new DummyBasicTrait(3, 4)));
  }
}