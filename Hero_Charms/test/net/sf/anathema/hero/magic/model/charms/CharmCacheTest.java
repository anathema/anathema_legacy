package net.sf.anathema.hero.magic.model.charms;

import com.google.common.collect.Lists;
import net.sf.anathema.character.main.magic.persistence.CharmCache;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CharmCacheTest {

  private CharmCache cache = new CharmCache();

  @Test
  public void matchesCharacterTypesToIdentificatesForSpecialCharmLookup() throws Exception {
    ISpecialCharm specialCharm = Mockito.mock(ISpecialCharm.class);
    SimpleIdentifier solar = new SimpleIdentifier("Dummy");
    addSpecialCharmForSolar(specialCharm, solar);
    ISpecialCharm[] charmData = cache.getSpecialCharmData(new DummyExaltCharacterType());
    assertThat(charmData[0], is(specialCharm));
  }

  @Test
  public void matchesCharacterTypesToIdentificatesForCharmLookup() throws Exception {
    ICharm charm = Mockito.mock(ICharm.class);
    SimpleIdentifier solar = new SimpleIdentifier("Dummy");
    cache.addCharm(solar, charm);
    ICharm[] charmData = cache.getCharms(new DummyExaltCharacterType());
    assertThat(charmData[0], is(charm));
  }

  private void addSpecialCharmForSolar(ISpecialCharm specialCharm, SimpleIdentifier solar) {
    ArrayList<ISpecialCharm> data = Lists.newArrayList(specialCharm);
    cache.addSpecialCharmData(solar, data);
  }
}
