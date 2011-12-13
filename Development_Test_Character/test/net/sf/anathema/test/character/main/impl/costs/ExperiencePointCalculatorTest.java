package net.sf.anathema.test.character.main.impl.costs;

import net.sf.anathema.character.dummy.trait.DummyBasicTrait;
import net.sf.anathema.character.generic.impl.template.points.MultiplyRatingCosts;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.impl.model.advance.ExperiencePointCostCalculator;
import org.easymock.EasyMock;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExperiencePointCalculatorTest {

  private IExperiencePointCosts experienceCosts = EasyMock.createStrictMock(IExperiencePointCosts.class);
  private ExperiencePointCostCalculator calculator = new ExperiencePointCostCalculator(experienceCosts);

  @Test
  @Ignore
  public void testAbilityCosts() throws Exception {
    EasyMock.expect(experienceCosts.getAbilityCosts(true)).andReturn(new MultiplyRatingCosts(2)).atLeastOnce();
    EasyMock.expect(experienceCosts.getAbilityCosts(false)).andReturn(new MultiplyRatingCosts(3)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    // TODO 09.08.2006 (sieroux): Test anpassen an ITrait
    //assertEquals(4, calculator.getAbilityCosts(new DummyBasicTrait(2, 3), true));
    //assertEquals(6, calculator.getAbilityCosts(new DummyBasicTrait(2, 3), false));
  }

  @Test
  @Ignore
  public void testAttributeCosts() throws Exception {
    EasyMock.expect(experienceCosts.getAttributeCosts(true)).andReturn(new MultiplyRatingCosts(2)).atLeastOnce();
    EasyMock.expect(experienceCosts.getAttributeCosts(false)).andReturn(new MultiplyRatingCosts(3)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    /*EasyMock.expect(experienceCosts.getAttributeCosts(false)).andReturn(new MultiplyRatingCosts(3)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    assertEquals(6, calculator.getAttributeCosts(new DummyBasicTrait(2, 3), false));
    assertEquals(15, calculator.getAttributeCosts(new DummyBasicTrait(2, 4), false));*/
  }

  @Test
  public void testEssenceCosts() throws Exception {
    EasyMock.expect(experienceCosts.getEssenceCosts()).andReturn(new MultiplyRatingCosts(8)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    assertEquals(16, calculator.getEssenceCosts(new DummyBasicTrait(2, 3)));
  }

  @Test
  public void testVirtueCosts() throws Exception {
    EasyMock.expect(experienceCosts.getVirtueCosts()).andReturn(new MultiplyRatingCosts(5)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    assertEquals(15, calculator.getVirtueCosts(new DummyBasicTrait(3, 4)));
  }

  @Test
  public void testWillpowerCosts() throws Exception {
    EasyMock.expect(experienceCosts.getWillpowerCosts()).andReturn(new MultiplyRatingCosts(1)).atLeastOnce();
    EasyMock.replay(experienceCosts);
    assertEquals(3, calculator.getWillpowerCosts(new DummyBasicTrait(3, 4)));
  }
}