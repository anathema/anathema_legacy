package net.sf.anathema.character.library.util;

import net.sf.anathema.character.generic.type.ICharacterType;

import java.util.ArrayList;
import java.util.List;

public class CssSkinner {

  public String[] getSkins(ICharacterType characterType) {
    List<String> skins = new ArrayList<>();
    skins.add("skin/platform/dotselector.css");
    skins.add("skin/character/favorable.css");
    skins.add(chooseSkinForCharacterType(characterType));
    return skins.toArray(new String[skins.size()]);
  }

  private String chooseSkinForCharacterType(ICharacterType characterType) {
    if (characterType == null) {
      return "skin/character/trait.css";
    }
    if (characterType.getId().equals("Solar")) {
      return "skin/solar/trait.css";
    } else if (characterType.getId().equals("Mortal")) {
      return "skin/mortal/trait.css";
    } else {
      return "skin/character/trait.css";
    }
  }
}
