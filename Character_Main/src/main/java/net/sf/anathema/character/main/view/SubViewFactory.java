package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.type.CharacterType;

public interface SubViewFactory {
  <T> T create(CharacterType type);
}