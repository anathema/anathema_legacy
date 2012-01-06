package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ComplexMultipleEffectCharmTest {
  private IBasicCharacterData data = mock(IBasicCharacterData.class);
  private ICharmLearnableArbitrator arbitrator = mock(ICharmLearnableArbitrator.class);
  private ICharm baseCharm = mock(ICharm.class);
  private IMultipleEffectCharm charmWithThreeEffects = new ComplexMultipleEffectCharm("Solar.TestCharm", new String[]{"A", "B", "C"}, new HashMap<String, String>());

  @Test
  public void instantiatesSubeffects() throws Exception {
    ISubeffect[] subeffects = charmWithThreeEffects.buildSubeffects(data, null, arbitrator, baseCharm);
    assertThat(subeffects.length, is(3));
  }

  @Test
  public void instantiatesSubeffectsOnlyOnce() throws Exception {
    charmWithThreeEffects.buildSubeffects(data, null, arbitrator, baseCharm);
    ISubeffect[] subeffectsAgain = charmWithThreeEffects.buildSubeffects(data, null, arbitrator, baseCharm);
    assertThat(subeffectsAgain.length, is(3));
  }
}