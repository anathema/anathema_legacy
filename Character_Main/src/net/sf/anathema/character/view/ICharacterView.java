package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.overview.ICreationOverviewView;
import net.sf.anathema.character.view.overview.IExperienceOverviewView;
import net.sf.anathema.framework.presenter.view.IMultiTabView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.gui.IDisposable;

public interface ICharacterView extends IItemView {

  public IGroupedFavorableTraitConfigurationView addGroupedFavorableTraitConfigurationView(
      String header,
      int columnCount);

  public ICharacterConceptAndRulesView addCharacterConceptView(String header);

  public ICharacterDescriptionView addCharacterDescriptionView(String header);

  public ICreationOverviewView addCreationOverviewView();

  public IExperienceOverviewView addExperienceOverviewView();

  public IExperienceConfigurationView addExperienceConfigurationView(String header);

  public void toogleOverviewView(boolean experienced);

  public IMultiTabView addMultiTabView(String header);

  public IIntValueDisplayFactory getIntValueDisplayFactory();

  public IMagicViewFactory createMagicViewFactory();

  public void addDisposable(IDisposable disposable);

  public IAdvantageViewFactory createAdvantageViewFactory();
}