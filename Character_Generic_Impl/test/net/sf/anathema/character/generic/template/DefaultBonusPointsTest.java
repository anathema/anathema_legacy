package net.sf.anathema.character.generic.template;

import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultBonusPointsTest{

  private DefaultBonusPointCosts costs = new DefaultBonusPointCosts();

  @Test
  public void testAbilityCost() throws Exception {
    ICurrentRatingCosts unfavoredAbilityCosts = costs.getAbilityCosts(false);
    assertEquals(new FixedValueRatingCosts(2), unfavoredAbilityCosts);
    ICurrentRatingCosts favoredAbilityCosts = costs.getAbilityCosts(true);
    assertEquals(new FixedValueRatingCosts(1), favoredAbilityCosts);
  }
}