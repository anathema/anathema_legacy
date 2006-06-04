package net.sf.anathema.test.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostListBuilder;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.ExceptionConvertingBlock;

public class CostListBuilderTest extends net.sf.anathema.lib.testing.BasicTestCase {

  private CostListBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    builder = new CostListBuilder();
  }

  public void testTemporaryNullElement() throws Exception {
    assertThrowsException(PersistenceException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        builder.buildTemporaryCostList(null);
      }
    });
  }

  public void testPermanentNullElement() throws Exception {
    IPermanentCostList list = builder.buildPermanentCostList(null);
    assertEquals(list.getEssenceCost(), Cost.NULL_COST);
    assertEquals(list.getWillpowerCost(), Cost.NULL_COST);
    assertEquals(list.getXPCost(), Cost.NULL_COST);
    assertEquals(list.getHealthCost(), HealthCost.NULL_HEALTH_COST);
  }
}