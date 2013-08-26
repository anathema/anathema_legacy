package net.sf.anathema.character.main.library.util;

import net.sf.anathema.character.main.type.CharacterType;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static java.text.MessageFormat.format;

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
    String skinFolder = characterType.getId().toLowerCase();
    return format("skin/{0}/trait.css", skinFolder);
  }
}