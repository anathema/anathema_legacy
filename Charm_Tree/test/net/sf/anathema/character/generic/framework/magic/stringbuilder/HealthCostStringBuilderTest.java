package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.magic.general.IHealthCost;
import net.sf.anathema.lib.dummy.DummyResources;

public class HealthCostStringBuilderTest extends TestCase {
  private HealthCostStringBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("Bashing", "Bashing");
    resources.putString("Lethal", "Lethal");
    resources.putString("CharmTreeView.ToolTip.HealthLevel", "health level");
    resources.putString("CharmTreeView.ToolTip.HealthLevels", "health levels");
    builder = new HealthCostStringBuilder(
        resources,
        "CharmTreeView.ToolTip.HealthLevel", "CharmTreeView.ToolTip.HealthLevels");
  }

  public void testNoCost() throws Exception {
    String costString = getDisplayString(HealthCost.NULL_HEALTH_COST);
    assertEquals("", costString);
  }

  private String getDisplayString(IHealthCost cost) {
    return builder.getCostString(cost);
  }

  public void testValueOnly() throws Exception {
    String costString = getDisplayString(new HealthCost(2, null, false, HealthType.Lethal));
    assertEquals("2 Lethal health levels", costString);
  }

  public void testValueAndText() throws Exception {
    String costString = getDisplayString(new HealthCost(3, "or more", false, HealthType.Bashing));
    assertEquals("3 Bashing health levels or more", costString);
  }

  public void testSingleHealthLevel() throws Exception {
    String costString = getDisplayString(new HealthCost(1, null, false, HealthType.Bashing));
    assertEquals("1 Bashing health level", costString);
  }

  public void testTextOnly() throws Exception {
    String costString = getDisplayString(new HealthCost(0, "Special", false, HealthType.Aggravated));
    assertEquals(" Special", costString);
  }
}
