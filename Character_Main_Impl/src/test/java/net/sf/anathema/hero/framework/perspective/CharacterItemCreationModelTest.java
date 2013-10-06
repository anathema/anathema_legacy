package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.character.main.HeroTemplateHolder;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.dummy.DummyCharacterTypes;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.control.ChangeListener;

import org.junit.Test;
import org.mockito.Mockito;

public class CharacterItemCreationModelTest {

  @Test
  public void comparesNewlySetCharacterTypeViaEqualsNotIdentity() throws Exception {
    HeroEnvironment generics = createGenericsWithCharacterType(new DummyMundaneCharacterType());
    HeroTemplateHolder configuration = Mockito.mock(HeroTemplateHolder.class);
    CharacterItemCreationModel model = new CharacterItemCreationModel(generics);
    ChangeListener listener = Mockito.mock(ChangeListener.class);
    model.addListener(listener);
    model.setCharacterType(new DummyMundaneCharacterType());
    Mockito.verifyZeroInteractions(listener);
  }

  private HeroEnvironment createGenericsWithCharacterType(CharacterType characterType) {
    HeroEnvironment generics = Mockito.mock(HeroEnvironment.class);
    DummyCharacterTypes characterTypes = new DummyCharacterTypes();
    characterTypes.add(characterType);
    Mockito.when(generics.getCharacterTypes()).thenReturn(characterTypes);
    ITemplateRegistry registry = Mockito.mock(ITemplateRegistry.class);
    SimpleDummyCharacterTemplate characterTemplate = new SimpleDummyCharacterTemplate(characterType, null);
    Mockito.when(registry.getDefaultTemplate(characterType)).thenReturn(characterTemplate);
    Mockito.when(registry.getAllSupportedTemplates(characterType)).thenReturn(
            new HeroTemplate[]{characterTemplate});
    Mockito.when(generics.getTemplateRegistry()).thenReturn(registry);
    return generics;
  }
}
