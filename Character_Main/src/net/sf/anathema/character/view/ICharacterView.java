package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.framework.presenter.view.IMultiTabView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.gui.IDisposable;

public interface ICharacterView extends IItemView {

  public IGroupedFavorableTraitConfigurationView addGroupedFavorableTraitConfigurationView(
      String header,
      int columnCount);

  public IGroupedFavorableTraitConfigurationView addGroupedFavorableTraitConfigurationTab(String header, int columnCount);

  public ICharacterDescriptionView addCharacterDescriptionView(String header);

  public IOverviewView addCreationOverviewView();

  public IOverviewView addExperienceOverviewView();

  public IExperienceConfigurationView addExperienceConfigurationView(String header);

  public void toogleOverviewView(boolean experienced);

  public IMultiTabView addMultiTabView(String header);

  public IIntValueDisplayFactory getIntValueDisplayFactory();

  public void addDisposable(IDisposable disposable);

  public ICharacterConceptAndRulesViewFactory createConceptViewFactory();

  public IAdvantageViewFactory createAdvantageViewFactory();

  public IMagicViewFactory createMagicViewFactory();
}