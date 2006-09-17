package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.presenter.SimpleViewTabContent;
import net.sf.anathema.character.view.magic.IMagicViewFactory;

public interface IMagicSubPresenter {

  public SimpleViewTabContent init(IMagicViewFactory view);
}