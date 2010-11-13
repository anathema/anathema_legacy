package net.sf.anathema.test.character.generic.template;

import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.template.points.FixedValueRatingCosts;
import net.sf.anathema.character.generic.template.experience.ICurrentRatingCosts;
import net.sf.anathema.lib.testing.BasicTestCase;

public class DefaultBonusPointsTest extends BasicTestCase {

  private DefaultBonusPointCosts costs;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    costs = new DefaultBonusPointCosts();
  }

  public void testAbilityCost() throws Exception {
    ICurrentRatingCosts unfavoredAbilityCosts = costs.getAbilityCosts(false);
    assertEquals(new FixedValueRatingCosts(2), unfavoredAbilityCosts);
    ICurrentRatingCosts favoredAbilityCosts = costs.getAbilityCosts(true);
    assertEquals(new FixedValueRatingCosts(1), favoredAbilityCosts);
  }
}