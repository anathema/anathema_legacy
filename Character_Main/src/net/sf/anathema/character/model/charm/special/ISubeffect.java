package net.sf.anathema.character.model.charm.special;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface ISubeffect extends IIdentificate {

  public boolean isLearned();

  public boolean isCreationLearned();

  public void addChangeListener(IChangeListener listener);

  public void setLearned(boolean learned);
}