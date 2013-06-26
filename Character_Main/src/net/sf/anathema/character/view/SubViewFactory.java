package net.sf.anathema.character.view;

import net.sf.anathema.character.generic.type.ICharacterType;

public interface SubViewFactory {
  <T> T create(ICharacterType type);
}