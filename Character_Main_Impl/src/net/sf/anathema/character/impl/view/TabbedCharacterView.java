package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.impl.view.concept.CharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.impl.view.magic.MagicViewFactory;
import net.sf.anathema.character.impl.view.overview.OverviewView;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.view.item.AbstractItemView;
import net.sf.anathema.framework.view.util.OptionalViewBar;
import net.sf.anathema.lib.gui.swing.IDisposable;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class TabbedCharacterView extends AbstractItemView implements ICharacterView {

  private final IntegerViewFactory integerDisplayFactory;
  private OverviewView creationOverviewView;
  private OverviewView experienceOverviewView;
  private OverviewView overviewView;
  private final List<IDisposable> disposables = new ArrayList<IDisposable>();
  private final IntegerViewFactory integerViewFactoryWithoutMarker;
  private final CharacterPane characterPane;
  private final OptionalViewBar optionalViewPane = new OptionalViewBar();
  private JPanel content;

  public TabbedCharacterView(IntegerViewFactory factory, String name, Icon icon, IntegerViewFactory factoryWithoutMarker) {
    this(new TaskedCharacterPane(), factory, name, icon, factoryWithoutMarker);
  }

  public TabbedCharacterView(CharacterPane characterPane, IntegerViewFactory factory, String name, Icon icon,
                             IntegerViewFactory factoryWithoutMarker) {
    super(name, icon);
    this.characterPane = characterPane;
    this.integerDisplayFactory = factory;
    this.integerViewFactoryWithoutMarker = factoryWithoutMarker;
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
  public MultipleContentView addMultipleContentView(String header) {
    return characterPane.addMultipleContentView(header);
  }

  @Override
  public IAdvantageViewFactory createAdvantageViewFactory() {
    return new AdvantageViewFactory(integerDisplayFactory);
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
    return new GroupedFavorableTraitViewFactory(integerDisplayFactory, integerViewFactoryWithoutMarker);
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
      content = new JPanel(new BorderLayout());
      content.add(characterPane.getComponent(), BorderLayout.CENTER);
      content.add(optionalViewPane.getComponent(), BorderLayout.EAST);
      showOverview();
    }
    return content;
  }

  @Override
  public void toggleOverviewView(boolean experienced) {
    this.overviewView = experienced ? experienceOverviewView : creationOverviewView;
    showOverview();
  }

  private void showOverview() {
    if (overviewView == null) {
      return;
    }
    JComponent component = overviewView.getComponent();
    optionalViewPane.setView("Overview", component);
  }
}