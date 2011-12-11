package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.lib.dummy.DummyResources;
import net.sf.anathema.lib.testing.BasicTestCase;

public class EssenceCostStringBuilderTest extends BasicTestCase {

  private CostStringBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("CharmTreeView.ToolTip.Mote", "mote"); //$NON-NLS-1$ //$NON-NLS-2$
    resources.putString("CharmTreeView.ToolTip.Motes", "motes"); //$NON-NLS-1$ //$NON-NLS-2$     
    builder = new CostStringBuilder(resources, "CharmTreeView.ToolTip.Mote", "CharmTreeView.ToolTip.Motes"); //$NON-NLS-1$//$NON-NLS-2$
  }

  public void testNoEssenceCost() throws Exception {
    String costString = getCostString(Cost.NULL_COST);
    assertEquals("", costString); //$NON-NLS-1$
  }

  private String getCostString(ICost cost) {
    return builder.getCostString(cost);
  }

  public void testPluralValue() throws Exception {
    String costString = getCostString(new Cost("2", null, false)); //$NON-NLS-1$
    assertEquals("2 motes", costString); //$NON-NLS-1$
  }

  public void testSingularValue() throws Exception {
    String costString = getCostString(new Cost("1", null, false)); //$NON-NLS-1$
    assertEquals("1 mote", costString); //$NON-NLS-1$
  }

  public void testValueAndText() throws Exception {
    String costString = getCostString(new Cost("4", "or more", false)); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("4 motes or more", costString); //$NON-NLS-1$
  }

  public void testTextOnly() throws Exception {
    String costString = getCostString(new Cost(null, "Special", false)); //$NON-NLS-1$
    assertEquals(" Special", costString); //$NON-NLS-1$
  }
}