package net.sf.anathema.hero.template;

import net.sf.anathema.hero.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.template.points.FixedValueRatingCosts;
import net.sf.anathema.hero.dummy.DummyBonusPointCosts;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultBonusPointsTest {

  private DummyBonusPointCosts costs = new DummyBonusPointCosts();

  @Test
  public void testAbilityCost() throws Exception {
    CurrentRatingCosts unfavoredAbilityCosts = costs.getAbilityCosts(false);
    assertEquals(new FixedValueRatingCosts(2), unfavoredAbilityCosts);
    CurrentRatingCosts favoredAbilityCosts = costs.getAbilityCosts(true);
    assertEquals(new FixedValueRatingCosts(1), favoredAbilityCosts);
  }
}