package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterTypes;
import net.sf.anathema.character.main.testing.dummy.DummyMundaneCharacterType;
import net.sf.anathema.character.main.testing.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.character.model.CharacterStatisticsConfiguration;
import net.sf.anathema.lib.control.ChangeListener;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class CharacterItemCreationModelTest {

  @Test
  public void comparesNewlySetCharacterTypeViaEqualsNotIdentity() throws Exception {
    ICharacterGenerics generics = createGenericsWithCharacterType(new DummyMundaneCharacterType());
    CharacterStatisticsConfiguration configuration = mock(CharacterStatisticsConfiguration.class);
    CharacterItemCreationModel model = new CharacterItemCreationModel(generics, configuration);
    ChangeListener listener = mock(ChangeListener.class);
    model.addListener(listener);
    model.setCharacterType(new DummyMundaneCharacterType());
    verifyZeroInteractions(listener);
  }

  private ICharacterGenerics createGenericsWithCharacterType(ICharacterType characterType) {
    ICharacterGenerics generics = mock(ICharacterGenerics.class);
    DummyCharacterTypes characterTypes = new DummyCharacterTypes();
    characterTypes.add(characterType);
    when(generics.getCharacterTypes()).thenReturn(characterTypes);
    ITemplateRegistry registry = mock(ITemplateRegistry.class);
    SimpleDummyCharacterTemplate characterTemplate = new SimpleDummyCharacterTemplate(characterType, null);
    when(registry.getDefaultTemplate(characterType)).thenReturn(characterTemplate);
    when(registry.getAllSupportedTemplates(characterType)).thenReturn(
            new ICharacterExternalsTemplate[]{characterTemplate});
    when(generics.getTemplateRegistry()).thenReturn(registry);
    return generics;
  }
}
