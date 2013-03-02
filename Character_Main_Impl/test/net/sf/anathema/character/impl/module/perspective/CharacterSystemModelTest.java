package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.CharacterPersistenceModel;
import net.sf.anathema.character.perspective.model.model.ItemSystemModel;
import net.sf.anathema.lib.control.IChangeListener;
import org.junit.Test;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CharacterSystemModelTest {

  private CharacterPersistenceModel mockPersistence = mock(CharacterPersistenceModel.class);
  private final IChangeListener mockListener = mock(IChangeListener.class);
  private ItemSystemModel systemModel = new CharacterSystemModel(mockPersistence, null);

  @Test
  public void notifiesListenerWhenSelectingFirstCharacter() {
    CharacterIdentifier identifier = new CharacterIdentifier("Loaded");
    systemModel.whenGetsSelection(mockListener);
    systemModel.setCurrentCharacter(identifier);
    verify(mockListener, atLeastOnce()).changeOccurred();
  }
}
