package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.impl.view.concept.CharacterConceptAndRulesView;
import net.sf.anathema.character.impl.view.magic.MagicViewFactory;
import net.sf.anathema.character.impl.view.overview.OverviewContainer;
import net.sf.anathema.character.view.BackgroundView;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.character.view.overview.CategorizedOverview;
import net.sf.anathema.character.view.overview.NullOverviewContainer;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.view.util.OptionalViewBar;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class TaskedCharacterView implements CharacterView {

  private final IntegerViewFactory integerDisplayFactory;
  private CategorizedOverview creationOverviewView;
  private CategorizedOverview experienceOverviewView;
  private CategorizedOverview overviewView = new NullOverviewContainer();
  private final IntegerViewFactory integerViewFactoryWithoutMarker;
  private final TaskedCharacterPane characterPane;
  private final OptionalViewBar optionalViewPane = new OptionalViewBar();
  private JPanel content;

  public TaskedCharacterView(IntegerViewFactory factory, IntegerViewFactory factoryWithoutMarker) {
    this.characterPane = new TaskedCharacterPane();
    this.integerDisplayFactory = factory;
    this.integerViewFactoryWithoutMarker = factoryWithoutMarker;
  }

  @Override
  public CategorizedOverview addCreationOverviewView() {
    OverviewContainer newView = new OverviewContainer();
    this.creationOverviewView = newView;
    return newView;
  }

  @Override
  public CategorizedOverview addExperienceOverviewView() {
    OverviewContainer newView = new OverviewContainer();
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
  public ICharacterConceptAndRulesView createConceptView() {
    return new CharacterConceptAndRulesView();
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
  public BackgroundView createBackgroundView() {
    return new SeparateBackgroundView(integerDisplayFactory);
  }

  @Override
  public IMagicViewFactory createMagicViewFactory() {
    return new MagicViewFactory(integerDisplayFactory);
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
    optionalViewPane.setView("Overview", characterPane.getOverview());
    showOverview();
  }

  private void showOverview() {
    overviewView.showIn(characterPane);
  }
}