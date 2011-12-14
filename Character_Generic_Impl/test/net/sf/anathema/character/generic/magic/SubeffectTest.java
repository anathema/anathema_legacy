package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.dummy.DummyBasicCharacterData;
import net.sf.anathema.character.generic.dummy.DummyCondition;
import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;

import org.junit.Assert;
import org.junit.Test;

public class SubeffectTest {

  @Test
  public void testIsCorrectlyLearned() throws Exception {
    DummyCondition condition = new DummyCondition();
    Subeffect subeffect = new Subeffect("Effective", new DummyBasicCharacterData(), condition); //$NON-NLS-1$
    subeffect.setLearned(true);
    Assert.assertFalse(subeffect.isLearned());
    condition.setValue(true);
    subeffect.setLearned(true);
    Assert.assertTrue(subeffect.isLearned());
    condition.setValue(false);
    subeffect.setLearned(false);
    Assert.assertFalse(subeffect.isLearned());
  }
}