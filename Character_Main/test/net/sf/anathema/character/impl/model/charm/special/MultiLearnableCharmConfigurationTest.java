package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.dummy.generic.DummyCharacterModelContext;
import net.sf.anathema.character.dummy.generic.magic.DummyCharmConfiguration;
import net.sf.anathema.character.dummy.generic.magic.DummyLearnableArbitrator;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.impl.model.context.trait.ExperiencedTraitValueStrategy;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultiLearnableCharmConfigurationTest {

  private ICharacterModelContext context = new DummyCharacterModelContext(new ExperiencedTraitValueStrategy());
  private ICharmConfiguration config = new DummyCharmConfiguration();
  private ICharm charm = new DummyCharm("id");
  private IMultiLearnableCharm specialCharm = new StaticMultiLearnableCharm("id", 5);
  private ICharmLearnableArbitrator arbitrator = new DummyLearnableArbitrator("id");
  private MultiLearnableCharmConfiguration configuration = new MultiLearnableCharmConfiguration(context, config, charm, specialCharm, arbitrator);

  @Test
  public void learnsExperiencedIfCurrentlyExperienced() throws Exception {
    configuration.learn(true);
    assertThat(configuration.getCategory().getExperiencedValue(), is(1));
  }

  @Test
  public void doesNotChangeCreationValueIfAlreadyLearnedAndCurrentlyExperienced() throws Exception {
    configuration.setCurrentLearnCount(1);
    configuration.learn(true);
    assertThat(configuration.getCategory().getCreationValue(), is(0));
  }
}
