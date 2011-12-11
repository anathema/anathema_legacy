package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.generic.dummy.DummyCondition;
import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MultipleEffectCharmConfigurationTest {

  @Test
  public void learnsEffects() throws Exception {
    IBasicCharacterData data = createData();
    DummyCondition condition = createCondition();
    Subeffect effect = new Subeffect("id", data, condition); //$NON-NLS-1$
    IMultipleEffectCharm charm = createCharm(effect);
    MultipleEffectCharmConfiguration configuration = new MultipleEffectCharmConfiguration(
      new DummyCharacterModelContext(),
      null,
      charm,
      null);
    effect.setExperienceLearned(true);
    configuration.learn(true);
    assertTrue(effect.isLearned());
    assertFalse(effect.isCreationLearned());
  }

  private IMultipleEffectCharm createCharm(Subeffect effect) {
    IMultipleEffectCharm charm = mock(IMultipleEffectCharm.class);
    when(
      charm.buildSubeffects(isA(IBasicCharacterData.class), any(IGenericTraitCollection.class), (ICharmLearnableArbitrator) isNull(), (ICharm) isNull()))
      .thenReturn(new ISubeffect[]{effect});
    return charm;
  }

  private DummyCondition createCondition() {
    DummyCondition condition = new DummyCondition();
    condition.setValue(true);
    return condition;
  }

  private IBasicCharacterData createData() {
    IBasicCharacterData data = mock(IBasicCharacterData.class);
    when(data.isExperienced()).thenReturn(true);
    return data;
  }
}