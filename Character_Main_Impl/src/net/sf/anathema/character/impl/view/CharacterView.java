package net.sf.anathema.character.impl.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.sf.anathema.character.impl.view.concept.CharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.impl.view.magic.MagicViewFactory;
import net.sf.anathema.character.impl.view.overview.OverviewView;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.framework.presenter.view.IMultiTabView;
import net.sf.anathema.framework.view.item.AbstractTabbedItemView;
import net.sf.anathema.framework.view.util.MultiTabView;
import net.sf.anathema.lib.gui.IDisposable;

public class CharacterView extends AbstractTabbedItemView implements ICharacterView {

  private final IIntValueDisplayFactory intValueDisplayFactory;
  private OverviewView creationOverviewView;
  private OverviewView experienceOverviewView;
  private OverviewView overviewView;
  private final List<IDisposable> disposables = new ArrayList<IDisposable>();
  private final IIntValueDisplayFactory intValueDisplayFactoryWithoutMarker;

  public CharacterView(
      IIntValueDisplayFactory factory,
      String name,
      Icon icon,
      IIntValueDisplayFactory factoryWithoutMarker) {
    super(name, icon);
    this.intValueDisplayFactory = factory;
    this.intValueDisplayFactoryWithoutMarker = factoryWithoutMarker;
  }

  public IGroupedFavorableTraitViewFactory createGroupedFavorableTraitViewFactory() {
    return new GroupedFavorableTraitViewFactory(intValueDisplayFactory, intValueDisplayFactoryWithoutMarker);
  }

  public ICharacterDescriptionView createCharacterDescriptionView() {
    return new CharacterDescriptionView();
  }

  public IMultiTabView addMultiTabView(String header) {
    IMultiTabView multiTabView = new MultiTabView();
    addTab(multiTabView, header);
    return multiTabView;
  }

  public IOverviewView addCreationOverviewView() {
    OverviewView newView = new OverviewView();
    this.creationOverviewView = newView;
    return newView;
  }

  public IExperienceConfigurationView createExperienceConfigurationView() {
    return new ExperienceConfigurationView();
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

  public IOverviewView addExperienceOverviewView() {
    OverviewView newView = new OverviewView();
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