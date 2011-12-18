package net.sf.anathema.character.mutations.view;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.lib.gui.IView;

public interface IMutationsView extends IView {

  public IMagicLearnView addMutationsView(IMutationLearnViewProperties giftViewProperties);

  public IOverviewCategory createOverview(String borderLabel);
}
