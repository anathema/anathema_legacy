package net.sf.anathema.character.main.library.intvalue;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.value.MarkerIntValueDisplayFactory;

public class IntValueDisplayFactoryPrototype {

  public static IntegerViewFactory createWithMarkerForCharacterType(ICharacterType type) {
    CharacterIntValueGraphics graphics = new CharacterIntValueGraphics(type);
    return new MarkerIntValueDisplayFactory(graphics);
  }
}