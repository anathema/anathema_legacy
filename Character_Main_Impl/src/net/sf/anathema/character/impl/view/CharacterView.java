package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.impl.view.concept.CharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.impl.view.magic.MagicViewFactory;
import net.sf.anathema.character.impl.view.overview.OverviewView;
import net.sf.anathema.framework.value.IIntValueDisplayFactory;
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
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.lib.gui.IDisposable;

import javax.swing.Icon;
import javax.swing.JComponent;
import java.util.ArrayList;
import java.util.List;

public class CharacterView extends AbstractItemView implements ICharacterView {

  private final IIntValueDisplayFactory intValueDisplayFactory;
  private OverviewView creationOverviewView;
  private OverviewView experienceOverviewView;
  private OverviewView overviewView;
  private final List<IDisposable> disposables = new ArrayList<IDisposable>();
  private final IIntValueDisplayFactory intValueDisplayFactoryWithoutMarker;
  private final IMultiContentView contentView = new MultiTabContentView();

  private JComponent content;

  public CharacterView(IIntValueDisplayFactory factory, String name, Icon icon,
                       IIntValueDisplayFactory factoryWithoutMarker) {
    super(name, icon);
    this.intValueDisplayFactory = factory;
    this.intValueDisplayFactoryWithoutMarker = factoryWithoutMarker;
  }

  @Override
  public IOverviewView addCreationOverviewView() {
    OverviewView newView = new OverviewView();
    this.creationOverviewView = newView;
    return newView;
  }

  @Override
  public void addDisposable(IDisposable disposable) {
    disposables.add(disposable);
  }

  @Override
  public IOverviewView addExperienceOverviewView() {
    OverviewView newView = new OverviewView();
    this.experienceOverviewView = newView;
    return newView;
  }

  @Override
  public IMultiContentView addMultiContentView(String header) {
    IMultiContentView multiTabView = new MultiTabContentView(TabDirection.Up);
    contentView.addView(multiTabView, new ContentProperties(header));
    return multiTabView;
  }

  @Override
  public IAdvantageViewFactory createAdvantageViewFactory() {
    return new AdvantageViewFactory(intValueDisplayFactory);
  }

  @Override
  public ICharacterDescriptionView createCharacterDescriptionView() {
    return new CharacterDescriptionView();
  }

  @Override
  public ICharacterConceptAndRulesViewFactory createConceptViewFactory() {
    return new CharacterConceptAndRulesViewFactory();
  }

  @Override
  public IExperienceConfigurationView createExperienceConfigurationView() {
    return new ExperienceConfigurationView();
  }

  @Override
  public IGroupedFavorableTraitViewFactory createGroupedFavorableTraitViewFactory() {
    return new GroupedFavorableTraitViewFactory(intValueDisplayFactory, intValueDisplayFactoryWithoutMarker);
  }

  @Override
  public IMagicViewFactory createMagicViewFactory() {
    return new MagicViewFactory();
  }

  @Override
  public void dispose() {
    for (IDisposable disposable : disposables) {
      disposable.dispose();
    }
  }

  @Override
  public final JComponent getComponent() {
    if (content == null) {
      showOverviewInTabArea();
      content = contentView.getComponent();
    }
    return content;
  }

  @Override
  public void toogleOverviewView(boolean experienced) {
    this.overviewView = experienced ? experienceOverviewView : creationOverviewView;
    showOverviewInTabArea();
  }

  private void showOverviewInTabArea() {
    if (overviewView == null) {
      return;
    }
    JComponent component = overviewView.getComponent();
    contentView.setAdditionalComponent(component);
  }
}