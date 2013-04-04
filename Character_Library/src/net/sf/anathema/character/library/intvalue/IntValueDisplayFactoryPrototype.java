package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.value.MarkerIntValueDisplayFactory;
import net.sf.anathema.framework.value.MarkerLessIntValueDisplayFactory;

public class IntValueDisplayFactoryPrototype {
  public static IntegerViewFactory createWithoutMarkerForCharacterType(ICharacterType type) {
    CharacterIntValueGraphics graphics = new CharacterIntValueGraphics(type);
    return new MarkerLessIntValueDisplayFactory(graphics);
  }

  public static IntegerViewFactory createWithMarkerForCharacterType(ICharacterType type) {
    CharacterIntValueGraphics graphics = new CharacterIntValueGraphics(type);
    return new MarkerIntValueDisplayFactory(graphics);
  }
}