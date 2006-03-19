package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.lunar.beastform.view.IBeastformOverviewView;
import net.sf.anathema.character.lunar.beastform.view.IBeastformOverviewViewProperties;
import net.sf.anathema.character.lunar.beastform.view.IGiftLearnViewProperties;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.framework.value.IIntValueView;

public interface IBeastformView extends ISimpleTabView {

  public IBeastformOverviewView addOverviewView(
      IBeastformOverviewViewProperties properties);

  public IIntValueView addAttributeValueView(String string, int currentValue, int maximalValue);

  public IIntValueView addIntValueView(String string, int currentValue, int maximalValue);

  public IMagicLearnView addGiftsView(IGiftLearnViewProperties giftViewProperties);
}