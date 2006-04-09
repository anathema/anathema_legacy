package net.sf.anathema.character.impl.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.sf.anathema.character.impl.view.concept.CharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.impl.view.magic.MagicViewFactory;
import net.sf.anathema.character.impl.view.overview.AbstractOverviewView;
import net.sf.anathema.character.impl.view.overview.CreationOverviewView;
import net.sf.anathema.character.impl.view.overview.ExperienceOverviewView;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.overview.IExperienceOverviewView;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.framework.presenter.view.IMultiTabView;
import net.sf.anathema.framework.presenter.view.MultiTabView;
import net.sf.anathema.framework.view.item.AbstractTabbedItemView;
import net.sf.anathema.lib.gui.IDisposable;

public class CharacterView extends AbstractTabbedItemView implements ICharacterView {

  private final IIntValueDisplayFactory intValueDisplayFactory;
  private CreationOverviewView creationOverviewView;
  private ExperienceOverviewView experienceOverviewView;
  private AbstractOverviewView overviewView;
  private final List<IDisposable> disposables = new ArrayList<IDisposable>();

  public CharacterView(IIntValueDisplayFactory factory, String name, Icon icon) {
    super(name, icon);
    this.intValueDisplayFactory = factory;
  }

  public IGroupedFavorableTraitConfigurationView addGroupedFavorableTraitConfigurationView(
      String header,
      int columnCount) {
    IGroupedFavorableTraitConfigurationView groupedConfigurationView = new GroupedFavorableTraitConfigurationView(
        columnCount,
        header,
        intValueDisplayFactory);
    addTab(groupedConfigurationView, header);
    return groupedConfigurationView;
  }

  public ICharacterDescriptionView addCharacterDescriptionView(String header) {
    ICharacterDescriptionView descriptionView = new CharacterDescriptionView(header);
    addTab(descriptionView, header);
    return descriptionView;
  }

  public IMultiTabView addMultiTabView(String header) {
    IMultiTabView multiTabView = new MultiTabView(null);
    addTab(multiTabView, header);
    return multiTabView;
  }

  public IOverviewView addCreationOverviewView() {
    CreationOverviewView newView = new CreationOverviewView();
    this.creationOverviewView = newView;
    return newView;
  }

  public IExperienceConfigurationView addExperienceConfigurationView(String header) {
    ExperienceConfigurationView newView = new ExperienceConfigurationView(header);
    addTab(newView, header);
    return newView;
  }

  @Override
  public void dispose() {
    for (IDisposable disposable : disposables) {
      disposable.dispose();
    }
  }

  @Override
  protected JComponent[] getTabAreaComponents() {
    if (overviewView == null) {
      return new JComponent[0];
    }
    return new JComponent[] { overviewView.getComponent() };
  }

  public void toogleOverviewView(boolean experienced) {
    this.overviewView = experienced ? experienceOverviewView : creationOverviewView;
    setTabAreaComponents(getTabAreaComponents());
  }

  public IExperienceOverviewView addExperienceOverviewView() {
    ExperienceOverviewView newView = new ExperienceOverviewView();
    this.experienceOverviewView = newView;
    return newView;
  }

  public IIntValueDisplayFactory getIntValueDisplayFactory() {
    return intValueDisplayFactory;
  }

  public IMagicViewFactory createMagicViewFactory() {
    return new MagicViewFactory();
  }

  public IAdvantageViewFactory createAdvantageViewFactory() {
    return new AdvantageViewFactory(intValueDisplayFactory);
  }

  public ICharacterConceptAndRulesViewFactory createConceptViewFactory() {
    return new CharacterConceptAndRulesViewFactory();
  }

  public void addDisposable(IDisposable disposable) {
    disposables.add(disposable);
  }
}