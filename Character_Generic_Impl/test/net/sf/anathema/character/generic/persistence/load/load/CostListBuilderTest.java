package net.sf.anathema.character.generic.persistence.load.load;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostListBuilder;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.lib.exception.PersistenceException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CostListBuilderTest {

  private CostListBuilder builder= new CostListBuilder();

  @Test
  public void hasNoEssenceCostForNullElement() throws Exception {
    assertCostIsZero(listFromNull().getEssenceCost());
  }

  @Test
  public void hasNoHealthCostForNullElement() throws Exception {
    assertCostIsZero(listFromNull().getHealthCost());
  }

  @Test
  public void hasNoWillpowerCostForNullElement() throws Exception {
    assertCostIsZero(listFromNull().getWillpowerCost());
  }

  @Test
  public void hasNoXpCostForNullElement() throws Exception {
    assertCostIsZero(listFromNull().getXPCost());
  }

  private ICostList listFromNull() throws PersistenceException {
    return builder.buildCostList(null);
  }

  private void assertCostIsZero(ICost cost) {
    assertThat(cost.getCost(), is("0"));
  }
}