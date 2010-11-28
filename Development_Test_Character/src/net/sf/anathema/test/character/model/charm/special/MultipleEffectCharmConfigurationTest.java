package net.sf.anathema.test.character.model.charm.special;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.impl.magic.charm.special.Subeffect;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.impl.model.charm.special.MultipleEffectCharmConfiguration;
import net.sf.anathema.dummy.character.DummyCharacterModelContext;
import net.sf.anathema.test.character.generic.magic.charms.DummyCondition;

import org.easymock.EasyMock;
import org.junit.Test;

public class MultipleEffectCharmConfigurationTest {

  @Test
  public void testname() throws Exception {
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
    IMultipleEffectCharm charm = EasyMock.createMock(IMultipleEffectCharm.class);
    EasyMock.expect(
        charm.buildSubeffects(isA(IBasicCharacterData.class), (ICharmLearnableArbitrator) isNull(), (ICharm) isNull()))
        .andReturn(new ISubeffect[] { effect });
    EasyMock.replay(charm);
    return charm;
  }

  private DummyCondition createCondition() {
    DummyCondition condition = new DummyCondition();
    condition.setValue(true);
    return condition;
  }

  private IBasicCharacterData createData() {
    IBasicCharacterData data = EasyMock.createMock(IBasicCharacterData.class);
    EasyMock.expect(data.isExperienced()).andReturn(true).anyTimes();
    EasyMock.replay(data);
    return data;
  }
}