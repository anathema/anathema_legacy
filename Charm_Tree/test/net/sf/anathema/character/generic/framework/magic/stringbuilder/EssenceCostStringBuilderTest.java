package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.lib.dummy.DummyResources;

public class EssenceCostStringBuilderTest extends TestCase {

  private CostStringBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("CharmTreeView.ToolTip.Mote", "mote");
    resources.putString("CharmTreeView.ToolTip.Motes", "motes");
    builder = new CostStringBuilder(resources, "CharmTreeView.ToolTip.Mote", "CharmTreeView.ToolTip.Motes");
  }

  public void testNoEssenceCost() throws Exception {
    String costString = getCostString(Cost.NULL_COST);
    assertEquals("", costString);
  }

  private String getCostString(ICost cost) {
    return builder.getCostString(cost);
  }

  public void testPluralValue() throws Exception {
    String costString = getCostString(new Cost("2", null, false));
    assertEquals("2 motes", costString);
  }

  public void testSingularValue() throws Exception {
    String costString = getCostString(new Cost("1", null, false));
    assertEquals("1 mote", costString);
  }

  public void testValueAndText() throws Exception {
    String costString = getCostString(new Cost("4", "or more", false));
    assertEquals("4 motes or more", costString);
  }

  public void testTextOnly() throws Exception {
    String costString = getCostString(new Cost(null, "Special", false));
    assertEquals(" Special", costString);
  }
}