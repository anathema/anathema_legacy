package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.*;

public class MarkerIntValueDisplayFactory implements IIntValueDisplayFactory {

  private final Icon activeBallIcon;
  private final Icon passiveBallIcon;
  private final Icon blockedIcon;

  public MarkerIntValueDisplayFactory(IResources resources, ICharacterType type) {
    CharacterUI characterUI = new CharacterUI(resources);
    this.activeBallIcon = characterUI.getMediumBallResource(type);
    this.passiveBallIcon = characterUI.getUnselectedBallResource();
    this.blockedIcon = characterUI.getUnselectableBallResource();
  }

  public IIntValueDisplay createIntValueDisplay(int maxValue, int value, TwoUpperBounds bounds) {
    IIntValueDisplay intValueDisplay = IntValueDisplay.createMarkerDisplay(blockedIcon, passiveBallIcon, activeBallIcon, maxValue, bounds);
    intValueDisplay.setValue(value);
    return intValueDisplay;
  }
}