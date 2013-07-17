package net.sf.anathema.hero.magic.model.charms;

import com.google.common.collect.Lists;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharm;
import net.sf.anathema.hero.charms.compiler.CharmCacheImpl;
import net.sf.anathema.hero.charms.compiler.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharmCacheTest {

  private ReflectionSpecialCharmBuilder specialCharmBuilderMock = mock(ReflectionSpecialCharmBuilder.class);
  private CharmCacheImpl cache = new CharmCacheImpl(specialCharmBuilderMock);

  @Test
  public void matchesCharacterTypesToIdentificatesForSpecialCharmLookup() throws Exception {
    SpecialCharmDto specialCharmDto = mock(SpecialCharmDto.class);
    ISpecialCharm specialCharm = mock(ISpecialCharm.class);
    SimpleIdentifier solar = new SimpleIdentifier("Dummy");
    addSpecialCharmForSolar(specialCharmDto, solar);
    when(specialCharmBuilderMock.readCharm(specialCharmDto)).thenReturn(specialCharm);
    ISpecialCharm[] charmData = cache.getSpecialCharmData(new DummyExaltCharacterType());
    assertThat(charmData[0], is(specialCharm));
  }

  @Test
  public void matchesCharacterTypesToIdentificatesForCharmLookup() throws Exception {
    Charm charm = mock(Charm.class);
    SimpleIdentifier solar = new SimpleIdentifier("Dummy");
    cache.addCharm(solar, charm);
    Charm[] charmData = cache.getCharms(new DummyExaltCharacterType());
    assertThat(charmData[0], is(charm));
  }

  private void addSpecialCharmForSolar(SpecialCharmDto specialCharm, SimpleIdentifier solar) {
    ArrayList<SpecialCharmDto> data = Lists.newArrayList(specialCharm);
    cache.addSpecialCharmData(solar, data);
  }
}
