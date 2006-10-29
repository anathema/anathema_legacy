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
import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.framework.view.item.AbstractItemView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.view.util.MultiTabContentView;
import net.sf.anathema.framework.view.util.TabbedView;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;

public class CharacterView extends AbstractItemView implements ICharacterView {

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

  private final TabbedView tabbedView = new TabbedView();
  private JComponent content;

  protected final void addTab(IView viewContent, final String name) {
    tabbedView.addTab(viewContent, new ContentProperties(name));
  }

  public final JComponent getComponent() {
    if (content == null) {
      setTabAreaComponents(getTabAreaComponents());
      content = tabbedView.getComponent();
    }
    return content;
  }

  protected final void setTabAreaComponents(JComponent[] components) {
    tabbedView.setTabAreaComponents(components);
  }

  public IGroupedFavorableTraitViewFactory createGroupedFavorableTraitViewFactory() {
    return new GroupedFavorableTraitViewFactory(intValueDisplayFactory, intValueDisplayFactoryWithoutMarker);
  }

  public ICharacterDescriptionView createCharacterDescriptionView() {
    return new CharacterDescriptionView();
  }

  public IMultiContentView addMultiContentView(String header) {
    IMultiContentView multiTabView = new MultiTabContentView();
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

  private JComponent[] getTabAreaComponents() {
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