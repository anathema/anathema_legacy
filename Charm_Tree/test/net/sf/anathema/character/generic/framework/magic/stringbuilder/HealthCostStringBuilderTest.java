package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.magic.general.IHealthCost;
import net.sf.anathema.lib.dummy.DummyResources;
import net.sf.anathema.lib.testing.BasicTestCase;

public class HealthCostStringBuilderTest extends BasicTestCase {
  private HealthCostStringBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("Bashing", "Bashing"); //$NON-NLS-1$//$NON-NLS-2$
    resources.putString("Lethal", "Lethal"); //$NON-NLS-1$//$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.HealthLevel", "health level"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.HealthLevels", "health levels"); //$NON-NLS-1$ //$NON-NLS-2$
    builder = new HealthCostStringBuilder(
        resources,
        "CharmTreeView.ToolTip.HealthLevel", "CharmTreeView.ToolTip.HealthLevels"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void testNoCost() throws Exception {
    String costString = getDisplayString(HealthCost.NULL_HEALTH_COST);
    assertEquals("", costString); //$NON-NLS-1$
  }

  private String getDisplayString(IHealthCost cost) {
    return builder.getCostString(cost);
  }

  public void testValueOnly() throws Exception {
    String costString = getDisplayString(new HealthCost(2, null, false, HealthType.Lethal));
    assertEquals("2 Lethal health levels", costString); //$NON-NLS-1$
  }

  public void testValueAndText() throws Exception {
    String costString = getDisplayString(new HealthCost(3, "or more", false, HealthType.Bashing)); //$NON-NLS-1$
    assertEquals("3 Bashing health levels or more", costString); //$NON-NLS-1$
  }

  public void testSingleHealthLevel() throws Exception {
    String costString = getDisplayString(new HealthCost(1, null, false, HealthType.Bashing));
    assertEquals("1 Bashing health level", costString); //$NON-NLS-1$
  }

  public void testTextOnly() throws Exception {
    String costString = getDisplayString(new HealthCost(0, "Special", false, HealthType.Aggravated)); //$NON-NLS-1$
    assertEquals(" Special", costString); //$NON-NLS-1$
  }
}
