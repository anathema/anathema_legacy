package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;
import net.sf.anathema.character.main.testing.dummy.DummyBasicCharacterData;
import net.sf.anathema.character.main.testing.dummy.DummyCondition;
import org.junit.Assert;
import org.junit.Test;

public class SubeffectTest {

  @Test
  public void testIsCorrectlyLearned() throws Exception {
    DummyCondition condition = new DummyCondition();
    Subeffect subeffect = new Subeffect("Effective", new DummyBasicCharacterData(), condition);
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