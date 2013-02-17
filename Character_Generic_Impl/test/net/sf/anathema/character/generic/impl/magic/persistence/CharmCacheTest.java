package net.sf.anathema.character.generic.impl.magic.persistence;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.util.Identificate;
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
    Identificate solar = new Identificate("Dummy");
    addSpecialCharmForSolar(specialCharm, solar);
    ISpecialCharm[] charmData = cache.getSpecialCharmData(new DummyExaltCharacterType());
    assertThat(charmData[0], is(specialCharm));
  }

  @Test
  public void matchesCharacterTypesToIdentificatesForCharmLookup() throws Exception {
    ICharm charm = Mockito.mock(ICharm.class);
    Identificate solar = new Identificate("Dummy");
    cache.addCharm(solar, charm);
    ICharm[] charmData = cache.getCharms(new DummyExaltCharacterType());
    assertThat(charmData[0], is(charm));
  }

  private void addSpecialCharmForSolar(ISpecialCharm specialCharm, Identificate solar) {
    ArrayList<ISpecialCharm> data = Lists.newArrayList(specialCharm);
    cache.addSpecialCharmData(solar, data);
  }
}
