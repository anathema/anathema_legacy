package net.sf.anathema.character.meritsflaws.presenter.view;

import net.sf.anathema.character.meritsflaws.presenter.IPerkListener;

public interface IPerkView {

  public void setAvailablePerks(Object[] perks);

  public void addPerkListener(IPerkListener listener);

  public void setPerkDetails(IPerkDetailsView view);

  public void setAddEnabled(boolean enabled);

  public void setSelectedPerks(Object[] selectedPerks);

  public void setRemoveEnabled(boolean enabled);

  public void setAvailableListSelection(Object perk);
}