package net.sf.anathema.character.mutations.view;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.lib.gui.IView;

public interface IMutationsView extends IView {

  IMagicLearnView addMutationsView(IMutationLearnViewProperties giftViewProperties);

  IOverviewCategory createOverview(String borderLabel);

  void hideOverview();
}
