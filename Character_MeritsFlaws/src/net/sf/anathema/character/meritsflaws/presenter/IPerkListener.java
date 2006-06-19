package net.sf.anathema.character.meritsflaws.presenter;

import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.presenter.view.IPerkDetailsView;

public interface IPerkListener {

  public void perkAdded(IPerk selectedPerk, IPerkDetailsView detailsView);

  public void perkSelected(IPerk perk);

  public void filterChanged(Object type, Object category);

  public void perkRemoved(IQualitySelection<IPerk> perkSelection);

  public void selectionSelected(IQualitySelection<IPerk> perkSelection);
}