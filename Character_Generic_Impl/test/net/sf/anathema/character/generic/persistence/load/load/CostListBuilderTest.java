package net.sf.anathema.character.generic.persistence.load.load;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostListBuilder;
import net.sf.anathema.lib.exception.PersistenceException;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Wrong fixture - there is something odd with cost lists.")
public class CostListBuilderTest {

  private CostListBuilder builder= new CostListBuilder();

  @Test(expected=PersistenceException.class)
  public void testTemporaryNullElement() throws Exception {
        builder.buildTemporaryCostList(null);
  }
}