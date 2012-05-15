package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.character.generic.framework.resources.CharacterIntValueGraphics;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.value.MarkerIntValueDisplayFactory;
import net.sf.anathema.framework.value.MarkerLessIntValueDisplayFactory;
import net.sf.anathema.lib.resources.IResources;

public class IntValueDisplayFactoryPrototype {
  public static IntegerViewFactory createWithoutMarkerForCharacterType(IResources resources, ICharacterType type) {
    CharacterIntValueGraphics graphics = createGraphics(resources, type);
    return new MarkerLessIntValueDisplayFactory(graphics);
  }

  public static IntegerViewFactory createWithMarkerForCharacterType(IResources resources, ICharacterType type) {
    CharacterIntValueGraphics graphics = createGraphics(resources, type);
    return new MarkerIntValueDisplayFactory(graphics);
  }

  private static CharacterIntValueGraphics createGraphics(IResources resources, ICharacterType type) {
    return new CharacterIntValueGraphics(resources, type);
  }
}