package net.sf.anathema.character.impl.model.concept;

import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class MotivationModel implements ICharacterMotivationModel {

  private final ISimpleTextualDescription motivation = new SimpleTextualDescription();

  public String getMainEntry() {
    return motivation.getText();
  }

  public boolean hasSeparateWillpowerCondition() {
    return false;
  }

  public String getWillpowerCondition() {
    throw new UnsupportedOperationException("Willpower condition not supported by motivation."); //$NON-NLS-1$
  }

}