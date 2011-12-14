package net.sf.anathema.character.test.generic.magic.charms.duration;

import net.sf.anathema.character.NullResources;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.lib.resources.IResources;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QualifiedAmountDurationTest {

  @Test
  public void testSingularKey() throws Exception {
    IResources resources = new NullResources();
    final QualifiedAmountDuration duration = new QualifiedAmountDuration("1", "unit"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("Charm.QualifiedAmount,Charm.Amount.1,Charm.Unit.unit.Singular,", duration.getText(resources)); //$NON-NLS-1$
  }
}