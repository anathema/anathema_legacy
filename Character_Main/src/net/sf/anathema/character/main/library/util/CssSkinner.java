package net.sf.anathema.character.main.library.util;

import net.sf.anathema.character.main.type.CharacterType;

import java.util.ArrayList;
import java.util.List;

public class CssSkinner {

  public String[] getSkins(CharacterType characterType) {
    List<String> skins = new ArrayList<>();
    skins.add("skin/platform/dotselector.css");
    skins.add("skin/traits/favorable.css");
    skins.add(chooseSkinForCharacterType(characterType));
    return skins.toArray(new String[skins.size()]);
  }

  private String chooseSkinForCharacterType(CharacterType characterType) {
    if (characterType == null) {
      return "skin/traits/trait.css";
    }
    if (characterType.getId().equals("Solar")) {
      return "skin/solar/trait.css";
    } else if (characterType.getId().equals("Mortal")) {
      return "skin/mortal/trait.css";
    } else {
      return "skin/traits/trait.css";
    }
  }
}