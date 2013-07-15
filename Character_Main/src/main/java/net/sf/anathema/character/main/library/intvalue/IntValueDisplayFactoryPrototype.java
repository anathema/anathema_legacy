package net.sf.anathema.character.main.library.intvalue;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.value.MarkerIntValueDisplayFactory;

public class IntValueDisplayFactoryPrototype {

  public static IntegerViewFactory createWithMarkerForCharacterType(CharacterType type) {
    CharacterIntValueGraphics graphics = new CharacterIntValueGraphics(type);
    return new MarkerIntValueDisplayFactory(graphics);
  }
}