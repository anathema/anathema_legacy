package net.sf.anathema.character.library.intvalue;

import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.lib.resources.IResources;

public class MarkerLessIntValueDisplayFactory implements IIntValueDisplayFactory {

  private final Icon activeBallIcon;
  private final Icon passiveBallIcon;

  public MarkerLessIntValueDisplayFactory(IResources resources, ICharacterType type) {
    CharacterUI characterUI = new CharacterUI(resources);
    this.activeBallIcon = characterUI.getMediumBallResource(type);
    this.passiveBallIcon = characterUI.getUnselectedBallResource();
  }

  public IIntValueDisplay createIntValueDisplay(int maxValue, int value) {
    IIntValueDisplay intValueDisplay = IntValueDisplay.createMarkerLessDisplay(
        passiveBallIcon,
        activeBallIcon,
        maxValue);
    intValueDisplay.setValue(value);
    return intValueDisplay;
  }
}