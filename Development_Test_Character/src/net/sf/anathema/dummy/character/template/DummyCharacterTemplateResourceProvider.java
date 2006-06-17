package net.sf.anathema.dummy.character.template;

import net.sf.anathema.character.generic.framework.xml.presentation.ICharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.type.CharacterType;

public class DummyCharacterTemplateResourceProvider implements ICharacterTemplateResourceProvider {

  public static final String MORTAL_BALL_RESOURCE = "MortalBallResource"; //$NON-NLS-1$
  public static final String UNSELECTED_BALL = "UnselectedBall"; //$NON-NLS-1$

  public String getBallResource(CharacterType characterType) {
    if (characterType == CharacterType.MORTAL) {
      return MORTAL_BALL_RESOURCE;
    }
    throw new UnsupportedOperationException("Only mortals for test supported"); //$NON-NLS-1$
  }

  public String getUnselectedBallResource() {
    return UNSELECTED_BALL;
  }
}