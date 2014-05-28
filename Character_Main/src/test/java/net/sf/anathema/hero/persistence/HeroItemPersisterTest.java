package net.sf.anathema.hero.persistence;

import net.sf.anathema.character.framework.Character;
import net.sf.anathema.character.framework.item.Item;
import net.sf.anathema.character.framework.persistence.HeroItemPersister;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.hero.dummy.DummyExaltCharacterType;
import net.sf.anathema.hero.dummy.DummyObjectFactory;
import net.sf.anathema.hero.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentImpl;
import net.sf.anathema.hero.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.lib.exception.PersistenceException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class HeroItemPersisterTest {
  private IMessaging messaging = mock(IMessaging.class);
  private SimpleDummyCharacterTemplate template = new SimpleDummyCharacterTemplate(new DummyExaltCharacterType(), null);

  @Test
  public void createsFullyLoadedCharacter() throws Exception {
    HeroEnvironment generics = createEnvironment();
    HeroItemPersister persister = new HeroItemPersister(generics, messaging);
    Character character = createNewCharacter(persister);
    assertThat(character.isFullyLoaded(), is(true));
  }

  private HeroEnvironment createEnvironment() {
    IExtensibleDataSetProvider dataSetProvider = mock(IExtensibleDataSetProvider.class);
    final DummyObjectFactory objectFactory = new DummyObjectFactory();
    HeroEnvironment generics = new HeroEnvironmentImpl(null, objectFactory, dataSetProvider);
    generics.getTemplateRegistry().register(template);
    return generics;
  }

  private Character createNewCharacter(HeroItemPersister persister) throws PersistenceException {
    Item item = persister.createNew(template);
    return (Character) item.getItemData();
  }
}
