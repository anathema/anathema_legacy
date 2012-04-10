package net.sf.anathema.character.generic.impl.magic.persistence;

public class CharmCacheTest {

	// is there any way to get access to the cache
	// now that it is not global?
  /*private CharmCache cache = CharmCache.getInstance();

  @Test
  public void matchesCharacterTypesToIdentificatesForSpecialCharmLookup() throws Exception {
    ISpecialCharm specialCharm = Mockito.mock(ISpecialCharm.class);
    Identificate solar = new Identificate("Solar");
    addSpecialCharmForSolar(specialCharm, solar);
    ISpecialCharm[] charmData = cache.getSpecialCharmData(CharacterType.SOLAR);
    assertThat(charmData[0], is(specialCharm));
  }

  @Test
  public void matchesCharacterTypesToIdentificatesForCharmLookup() throws Exception {
    ICharm charm = Mockito.mock(ICharm.class);
    Identificate solar = new Identificate("Solar");
    cache.addCharm(solar, charm);
    ICharm[] charmData = cache.getCharms(CharacterType.SOLAR);
    assertThat(charmData[0], is(charm));
  }

  private void addSpecialCharmForSolar(ISpecialCharm specialCharm, Identificate solar) {
    ArrayList<ISpecialCharm> data = Lists.newArrayList(specialCharm);
    cache.addSpecialCharmData(solar, data);
  }*/
}
