package net.sf.anathema.character.library.trait.view.fx;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;

public class FxGroupedTraitConfigurationView implements GroupedFavorableTraitConfigurationView {
  private FxGroupedTraitView groupedView;

  @Override
  public void initGui(ColumnCount columnCount) {
    this.groupedView = new FxGroupedTraitView(columnCount);
  }

  @Override
  public void startNewTraitGroup(String groupLabel) {
    groupedView.startNewGroup(groupLabel);
  }

  @Override
  public ExtensibleTraitView addExtensibleTraitView(String string, int currentValue, int maximalValue,
                                                    Trait favorableTrait) {
    return groupedView.addExtensibleTraitView(string, currentValue, maximalValue, favorableTrait);
  }
}
