package net.sf.anathema.character.meritsflaws.presenter.view;

import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.presenter.IPerkListener;

public interface IPerkView {

  public void setAvailablePerks(IPerk[] perks);

  public void addPerkListener(IPerkListener listener);

  public void setPerkDetails(IPerkDetailsView view);

  public void setAddEnabled(boolean enabled);

  public void setSelectedPerks(IQualitySelection<IPerk>[] selectedPerks);

  public void setRemoveEnabled(boolean enabled);

  public void setAvailableListSelection(IPerk perk);
}