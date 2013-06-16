package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.main.library.NullResources;
import net.sf.anathema.lib.resources.Resources;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QualifiedAmountDurationTest {

  @Test
  public void testSingularKey() throws Exception {
    Resources resources = new NullResources();
    final QualifiedAmountDuration duration = new QualifiedAmountDuration("1", "unit");
    assertEquals("Charm.QualifiedAmount,Charm.Amount.1,Charm.Unit.unit.Singular,", duration.getText(resources));
  }
}