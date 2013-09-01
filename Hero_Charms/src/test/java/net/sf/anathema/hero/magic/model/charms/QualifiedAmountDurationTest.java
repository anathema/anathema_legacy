package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.hero.utilities.NullResources;
import net.sf.anathema.character.main.magic.charm.duration.QualifiedAmountDuration;
import net.sf.anathema.framework.environment.Resources;
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