package net.sf.anathema.test.character.generic.persistence.magic.load;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostListBuilder;
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
}