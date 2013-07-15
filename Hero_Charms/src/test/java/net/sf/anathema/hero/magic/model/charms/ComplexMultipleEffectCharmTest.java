package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.charms.display.special.CharmSpecialistImpl;
import net.sf.anathema.character.main.magic.model.charm.special.ComplexMultipleEffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IMultipleEffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.SubEffect;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.hero.dummy.DummyHero;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ComplexMultipleEffectCharmTest {
  private CharmSpecialistImpl specialist = new CharmSpecialistImpl(new DummyHero());
  private ICharmLearnableArbitrator arbitrator = mock(ICharmLearnableArbitrator.class);
  private Charm baseCharm = mock(Charm.class);
  private IMultipleEffectCharm charmWithThreeEffects =
          new ComplexMultipleEffectCharm("Solar.TestCharm", new String[]{"A", "B", "C"}, new HashMap<String, String>());

  @Test
  public void instantiatesSubeffects() throws Exception {
    SubEffect[] subeffects = charmWithThreeEffects.buildSubeffects(specialist, arbitrator, baseCharm).getEffects();
    assertThat(subeffects.length, is(3));
  }

  @Test
  public void instantiatesSubeffectsOnlyOnce() throws Exception {
    charmWithThreeEffects.buildSubeffects(specialist, arbitrator, baseCharm);
    SubEffect[] subeffectsAgain = charmWithThreeEffects.buildSubeffects(specialist, arbitrator, baseCharm).getEffects();
    assertThat(subeffectsAgain.length, is(3));
  }
}