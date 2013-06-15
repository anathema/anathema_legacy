package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.charmtree.builder.stringbuilder.CostStringBuilder;
import net.sf.anathema.lib.dummy.DummyResources;

public class WillpowerCostStringBuilderTest extends TestCase {

  private CostStringBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("WillpowerType.Name", "Willpower");
    resources.putString("Magic.Cost.Permanent", "permanent");
    builder = new CostStringBuilder(resources, "WillpowerType.Name");
  }

  public void testNoWillpowerCost() throws Exception {
    String costString = getDisplayString(Cost.NULL_COST);
    assertEquals("", costString);
  }

  private String getDisplayString(ICost cost) {
    return builder.getCostString(cost);
  }

  public void testValueOnly() throws Exception {
    String costString = getDisplayString(new Cost("2", null, false));
    assertEquals("2 Willpower", costString);
  }

  public void testValueAndText() throws Exception {
    String costString = getDisplayString(new Cost("2", "or more", false));
    assertEquals("2 Willpower or more", costString);
  }

  public void testTextOnly() throws Exception {
    String costString = getDisplayString(new Cost(null, "Special", false));
    assertEquals(" Special", costString);
  }

  public void testPermanentCost() throws Exception {
    String costString = getDisplayString(new Cost("2", null, true));
    assertEquals("2 permanent Willpower", costString);
  }
}