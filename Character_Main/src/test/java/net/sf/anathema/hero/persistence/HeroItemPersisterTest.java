package net.sf.anathema.hero.persistence;

import net.sf.anathema.character.main.Character;
import net.sf.anathema.character.main.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.main.persistence.HeroItemPersister;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.hero.dummy.DummyObjectFactory;
import net.sf.anathema.hero.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentImpl;
import net.sf.anathema.lib.exception.PersistenceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroItemPersisterTest {
  private IMessaging messaging = mock(IMessaging.class);
  private IItemType type;
  private SimpleDummyCharacterTemplate template = new SimpleDummyCharacterTemplate(new DummyExaltCharacterType(), null);

  @Before
  public void createPersistableItemType() throws Exception {
    type = mock(IItemType.class);
    when(type.supportsRepository()).thenReturn(true);
  }

  @Test
  public void createsFullyLoadedCharacter() throws Exception {
    HeroEnvironment generics = createEnvironment();
    HeroItemPersister persister = new HeroItemPersister(type, generics, messaging);
    Character character = createNewCharacter(persister);
    assertThat(character.isFullyLoaded(), is(true));
  }

  private HeroEnvironment createEnvironment() {
    IExtensibleDataSetProvider dataSetProvider = mock(IExtensibleDataSetProvider.class);
    HeroEnvironment generics = new HeroEnvironmentImpl(null, new DummyObjectFactory(), dataSetProvider);
    generics.getTemplateRegistry().register(template);
    return generics;
  }

  private Character createNewCharacter(HeroItemPersister persister) throws PersistenceException {
    Item item = persister.createNew(template);
    return (Character) item.getItemData();
  }
}
