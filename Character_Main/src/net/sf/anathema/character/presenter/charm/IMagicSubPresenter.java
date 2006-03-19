package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.presenter.TabContent;
import net.sf.anathema.character.view.magic.IMagicViewFactory;

public interface IMagicSubPresenter {

  public TabContent init(IMagicViewFactory view);
}