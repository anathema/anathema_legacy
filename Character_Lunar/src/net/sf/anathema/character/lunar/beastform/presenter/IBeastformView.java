package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.generic.framework.magic.view.IMagicLearnView;
import net.sf.anathema.character.lunar.beastform.view.IBeastformOverviewView;
import net.sf.anathema.character.lunar.beastform.view.IBeastformOverviewViewProperties;
import net.sf.anathema.character.lunar.beastform.view.IGiftLearnViewProperties;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.IView;

public interface IBeastformView extends IView {

  public IBeastformOverviewView addOverviewView(IBeastformOverviewViewProperties properties);

  public IIntValueView addAttributeValueView(String string, int currentValue, int maximalValue);

  public IIntValueView addCharmValueView(String string, int currentValue, int maximalValue);

  public IMagicLearnView addGiftsView(IGiftLearnViewProperties giftViewProperties);
  
  public void addMutationsView(IMutationsView mutationView);
}