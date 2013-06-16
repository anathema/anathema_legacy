package net.sf.anathema.character.main.item;

import net.sf.anathema.character.generic.framework.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.generic.impl.additional.NullAdditionalRules;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.main.essencepool.model.EssencePoolModelImpl;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.main.testing.dummy.template.DummyCharacterTemplate;
import net.sf.anathema.character.model.ICharacter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenericCharacterTest {
  ICharacter statistics = mock(ICharacter.class);

  @Before
  public void createEssenceUserWithoutPeripheralPool() throws Exception {
    DummyCharacterTemplate characterTemplate = new DummyCharacterTemplate();
    GenericEssenceTemplate essenceTemplate = new GenericEssenceTemplate();
    essenceTemplate.setEssenceUser(true);
    characterTemplate.setEssenceTemplate(essenceTemplate);
    when(statistics.getCharacterTemplate()).thenReturn(characterTemplate);
    when(statistics.getEssencePool())
            .thenReturn(new EssencePoolModelImpl(essenceTemplate, new NullAdditionalRules(), new DummyCharacterModelContext()));
  }

  @Test
  public void returnsNullWhenEssenceUserHasNoPeripheralPool() throws Exception {
    GenericCharacter character = new GenericCharacter(statistics);
    String peripheralPool = character.getPeripheralPool();
    assertThat(peripheralPool, is(nullValue()));
  }
}
