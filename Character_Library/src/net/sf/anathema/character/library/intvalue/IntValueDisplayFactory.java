package net.sf.anathema.character.library.intvalue;

import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.lib.resources.IResources;

public class IntValueDisplayFactory implements IIntValueDisplayFactory {

  private final Icon activeBallIcon;
  private final Icon passiveBallIcon;

  public IntValueDisplayFactory(IResources resources, Icon ballIcon) {
    this.activeBallIcon = ballIcon;
    this.passiveBallIcon = new CharacterUI(resources).getUnselectedBallResource();
  }

  public IIntValueDisplay createIntValueDisplay(int maxValue, int value) {
    IntValueDisplay intValueDisplay = new IntValueDisplay(passiveBallIcon, activeBallIcon, maxValue);
    intValueDisplay.setValue(value);
    return intValueDisplay;
  }
}