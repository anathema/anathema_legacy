package net.sf.anathema.character.main.persistence;

import net.sf.anathema.character.generic.data.IExtensibleDataSetProvider;
import net.sf.anathema.character.generic.framework.CharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.magic.persistence.ISpellCache;
import net.sf.anathema.character.generic.impl.magic.persistence.SpellCache;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.impl.persistence.DummyObjectFactory;
import net.sf.anathema.character.impl.persistence.ExaltedCharacterPersister;
import net.sf.anathema.character.main.testing.dummy.DummyExaltCharacterType;
import net.sf.anathema.character.main.testing.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class ExaltedCharacterPersisterTest {
  private IMessaging messaging = mock(IMessaging.class);
  private IItemType type;
  private SimpleDummyCharacterTemplate template;
  private CharacterStatisticsConfiguration configuration;

  @Before
  public void createPersistableItemType() throws Exception {
    type = mock(IItemType.class);
    when(type.supportsRepository()).thenReturn(true);
  }

  @Before
  public void createCharacterConfiguration() {
    template = new SimpleDummyCharacterTemplate(new DummyExaltCharacterType(), null);
    configuration = new CharacterStatisticsConfiguration();
    configuration.setTemplate(template);
  }

  @Test
  public void createsFullyLoadedCharacter() throws Exception {
    ICharacterGenerics generics = createCharacterGenerics();
    ExaltedCharacterPersister persister = new ExaltedCharacterPersister(type, generics, messaging);
    ICharacter character = createNewCharacter(persister);
    assertThat(character.getCharacterContext().isFullyLoaded(), is(true));
  }

  private ICharacterGenerics createCharacterGenerics() {
    IExtensibleDataSetProvider dataSetProvider = mock(IExtensibleDataSetProvider.class);
    when(dataSetProvider.getDataSet(ICharmCache.class)).thenReturn(new CharmCache());
    when(dataSetProvider.getDataSet(ISpellCache.class)).thenReturn(new SpellCache());
    ICharacterGenerics generics = new CharacterGenerics(null, new DummyObjectFactory(), dataSetProvider);
    generics.getTemplateRegistry().register(template);
    return generics;
  }

  private ICharacter createNewCharacter(ExaltedCharacterPersister persister) throws PersistenceException {
    IItem item = persister.createNew(configuration);
    return (ICharacter) item.getItemData();
  }
}
