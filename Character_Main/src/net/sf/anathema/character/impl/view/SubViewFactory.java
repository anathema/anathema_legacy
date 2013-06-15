package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.generic.type.ICharacterType;

public interface SubViewFactory {
  <T> T create(ICharacterType type);
}