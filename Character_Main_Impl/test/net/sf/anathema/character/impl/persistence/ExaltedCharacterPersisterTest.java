package net.sf.anathema.character.impl.persistence;

import net.sf.anathema.character.generic.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.character.generic.framework.CharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.character.generic.impl.rules.ExaltedEdition.SecondEdition;
import static net.sf.anathema.character.generic.type.CharacterType.DB;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExaltedCharacterPersisterTest {
  private IAnathemaMessaging messaging = mock(IAnathemaMessaging.class);
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
    template = new SimpleDummyCharacterTemplate(DB, null, SecondEdition);
    configuration = new CharacterStatisticsConfiguration();
    configuration.setRuleSet(ExaltedRuleSet.SecondEdition);
    configuration.setTemplate(template);
  }

  @Test
  public void createsFullyLoadedCharacter() throws Exception {
    ICharacterGenerics generics = createCharacterGenerics();
    ExaltedCharacterPersister persister = new ExaltedCharacterPersister(type, generics, messaging);
    ICharacter character = createNewCharacter(persister);
    assertThat(character.getStatistics().getCharacterContext().isFullyLoaded(), is(true));
  }

  private ICharacterGenerics createCharacterGenerics() {
    ICharacterGenerics generics = new CharacterGenerics(null);
    generics.getTemplateRegistry().register(template);
    return generics;
  }

  private ICharacter createNewCharacter(ExaltedCharacterPersister persister) throws PersistenceException {
    IItem item = persister.createNew(configuration);
    return (ICharacter) item.getItemData();
  }
}
