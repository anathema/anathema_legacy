package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.testing.dummy.DummyCondition;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubeffectTest {

  @Test
  public void testIsCorrectlyLearned() throws Exception {
    ExperienceModel experience = mock(ExperienceModel.class);
    when(experience.isExperienced()).thenReturn(false);
    DummyCondition condition = new DummyCondition();
    Subeffect subeffect = new Subeffect("Effective", experience, condition);
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