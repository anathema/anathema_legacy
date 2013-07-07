package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.type.ICharacterType;

public interface SubViewFactory {
  <T> T create(ICharacterType type);
}