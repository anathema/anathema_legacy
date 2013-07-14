package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmSpecialist;
import net.sf.anathema.character.main.magic.model.charm.special.ArraySubEffects;
import net.sf.anathema.character.main.magic.model.charm.special.IMultipleEffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.MultipleEffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.SubEffect;
import net.sf.anathema.character.main.magic.model.charm.special.SubEffectImpl;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.hero.dummy.DummyCondition;
import net.sf.anathema.hero.experience.ExperienceModel;
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
    SubEffectImpl effect = new SubEffectImpl("id", specialist.getExperience(), condition);
    IMultipleEffectCharm charm = createCharm(effect);
    MultipleEffectCharmConfiguration configuration = new MultipleEffectCharmConfiguration(specialist, null, charm, null);
    effect.setExperienceLearned(true);
    configuration.learn(true);
    assertTrue(effect.isLearned());
    assertFalse(effect.isCreationLearned());
  }

  private IMultipleEffectCharm createCharm(SubEffectImpl effect) {
    IMultipleEffectCharm charm = mock(IMultipleEffectCharm.class);
    when(charm.buildSubeffects(isA(CharmSpecialist.class), (ICharmLearnableArbitrator) isNull(), (Charm) isNull()))
            .thenReturn(new ArraySubEffects(new SubEffect[]{effect}));
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