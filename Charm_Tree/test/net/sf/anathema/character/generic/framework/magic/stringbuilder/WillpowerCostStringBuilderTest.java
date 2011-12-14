package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.lib.dummy.DummyResources;

public class WillpowerCostStringBuilderTest extends TestCase {

  private CostStringBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("WillpowerType.Name", "Willpower"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("Magic.Cost.Permanent", "permanent"); //$NON-NLS-1$//$NON-NLS-2$
    builder = new CostStringBuilder(resources, "WillpowerType.Name"); //$NON-NLS-1$
  }

  public void testNoWillpowerCost() throws Exception {
    String costString = getDisplayString(Cost.NULL_COST);
    assertEquals("", costString); //$NON-NLS-1$
  }

  private String getDisplayString(ICost cost) {
    return builder.getCostString(cost);
  }

  public void testValueOnly() throws Exception {
    String costString = getDisplayString(new Cost("2", null, false)); //$NON-NLS-1$
    assertEquals("2 Willpower", costString); //$NON-NLS-1$
  }

  public void testValueAndText() throws Exception {
    String costString = getDisplayString(new Cost("2", "or more", false)); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("2 Willpower or more", costString); //$NON-NLS-1$
  }

  public void testTextOnly() throws Exception {
    String costString = getDisplayString(new Cost(null, "Special", false)); //$NON-NLS-1$
    assertEquals(" Special", costString); //$NON-NLS-1$
  }

  public void testPermanentCost() throws Exception {
    String costString = getDisplayString(new Cost("2", null, true)); //$NON-NLS-1$
    assertEquals("2 permanent Willpower", costString); //$NON-NLS-1$
  }
}