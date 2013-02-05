package net.sf.anathema.character.perspective.model;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.control.IChangeListener;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CharacterSystemModelTest {
  private CharacterPersistenceModel mockPersistence = mock(CharacterPersistenceModel.class);
  private final IChangeListener mockListener = mock(IChangeListener.class);
  private final IItem mockItem = Mockito.mock(IItem.class);
  private CharacterSystemModel systemModel = new CharacterSystemModel(mockPersistence);

  @Test
  public void notifiesListenerWhenSelectingFirstCharacter() {
    CharacterIdentifier identifier = new CharacterIdentifier("Loaded");
    systemModel.whenGetsSelection(mockListener);
    systemModel.setCurrentCharacter(identifier);
    verify(mockListener, atLeastOnce()).changeOccurred();
  }
}
