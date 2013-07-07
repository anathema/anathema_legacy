package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterTypes;
import net.sf.anathema.character.main.testing.dummy.DummyMundaneCharacterType;
import net.sf.anathema.character.main.testing.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.character.main.CharacterStatisticsConfiguration;
import net.sf.anathema.lib.control.ChangeListener;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class CharacterItemCreationModelTest {

  @Test
  public void comparesNewlySetCharacterTypeViaEqualsNotIdentity() throws Exception {
    HeroEnvironment generics = createGenericsWithCharacterType(new DummyMundaneCharacterType());
    CharacterStatisticsConfiguration configuration = mock(CharacterStatisticsConfiguration.class);
    CharacterItemCreationModel model = new CharacterItemCreationModel(generics, configuration);
    ChangeListener listener = mock(ChangeListener.class);
    model.addListener(listener);
    model.setCharacterType(new DummyMundaneCharacterType());
    verifyZeroInteractions(listener);
  }

  private HeroEnvironment createGenericsWithCharacterType(ICharacterType characterType) {
    HeroEnvironment generics = mock(HeroEnvironment.class);
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
