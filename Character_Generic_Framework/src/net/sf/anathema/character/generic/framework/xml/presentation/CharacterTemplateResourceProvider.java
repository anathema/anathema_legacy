package net.sf.anathema.character.generic.framework.xml.presentation;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharacterTemplateResourceProvider implements ICharacterTemplateResourceProvider {

  private final Map<CharacterType, String> resourcesByCharacterType = new HashMap<CharacterType, String>() {
    {
      put(CharacterType.MORTAL, IIconConstants.MORTAL_BALL);
      put(CharacterType.LUNAR, IIconConstants.LUNAR_BALL);
      put(CharacterType.SOLAR, IIconConstants.SOLAR_BALL);
    }
  };

  public String getBallResource(CharacterType characterType) {
    return resourcesByCharacterType.get(characterType);
  }

  public String getUnselectedBallResource() {
    return IIconConstants.UNSELECTED_BALL;
  }
}