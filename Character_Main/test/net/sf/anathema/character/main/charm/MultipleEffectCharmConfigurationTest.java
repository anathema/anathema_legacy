package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.generic.impl.magic.charm.special.ArraySubEffects;
import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.testing.dummy.DummyCondition;
import net.sf.anathema.character.model.charm.CharmSpecialist;
import net.sf.anathema.character.model.charm.special.MultipleEffectCharmConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MultipleEffectCharmConfigurationTest {

  @Test
  public void learnsEffects() throws Exception {
    CharmSpecialist specialist = createExperiencedSpecialist();
    DummyCondition condition = createCondition();
    Subeffect effect = new Subeffect("id", specialist.getExperience(), condition);
    IMultipleEffectCharm charm = createCharm(effect);
    MultipleEffectCharmConfiguration configuration = new MultipleEffectCharmConfiguration(specialist, null, charm, null);
    effect.setExperienceLearned(true);
    configuration.learn(true);
    assertTrue(effect.isLearned());
    assertFalse(effect.isCreationLearned());
  }

  private IMultipleEffectCharm createCharm(Subeffect effect) {
    IMultipleEffectCharm charm = mock(IMultipleEffectCharm.class);
    when(charm.buildSubeffects(isA(CharmSpecialist.class), (ICharmLearnableArbitrator) isNull(), (ICharm) isNull()))
            .thenReturn(new ArraySubEffects(new ISubeffect[]{effect}));
    return charm;
  }

  private DummyCondition createCondition() {
    DummyCondition condition = new DummyCondition();
    condition.setValue(true);
    return condition;
  }

  private CharmSpecialist createExperiencedSpecialist() {
    ExperienceModel model = mock(ExperienceModel.class);
    when(model.isExperienced()).thenReturn(true);
    CharmSpecialist specialist = mock(CharmSpecialist.class);
    when(specialist.getExperience()).thenReturn(model);
    return specialist;
  }
}