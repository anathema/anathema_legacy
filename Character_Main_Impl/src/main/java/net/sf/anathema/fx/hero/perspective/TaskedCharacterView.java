package net.sf.anathema.fx.hero.perspective;

import net.sf.anathema.hero.advance.overview.view.CategorizedOverview;
import net.sf.anathema.character.main.view.CharacterView;
import net.sf.anathema.character.main.view.CharacterViewSection;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.character.main.view.SubViewRegistry;
import net.sf.anathema.framework.view.util.OptionalViewBar;
import net.sf.anathema.swing.hero.overview.DefaultCategorizedOverview;
import net.sf.anathema.swing.hero.overview.NullOverviewContainer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class TaskedCharacterView implements CharacterView {

  private CategorizedOverview creationOverviewView;
  private CategorizedOverview experienceOverviewView;
  private CategorizedOverview overviewView = new NullOverviewContainer();
  private final TaskedCharacterPane characterPane = new TaskedCharacterPane();
  private final OptionalViewBar optionalViewPane = new OptionalViewBar();
  private JPanel content;
  private final SubViewRegistry subViewFactory;

  public TaskedCharacterView(SubViewRegistry viewFactory) {
    this.subViewFactory = viewFactory;
  }

  @Override
  public CategorizedOverview addCreationOverviewView() {
    DefaultCategorizedOverview newView = new DefaultCategorizedOverview();
    this.creationOverviewView = newView;
    return newView;
  }

  @Override
  public CategorizedOverview addExperienceOverviewView() {
    DefaultCategorizedOverview newView = new DefaultCategorizedOverview();
    this.experienceOverviewView = newView;
    return newView;
  }

  @Override
  public SectionView addSection(String title) {
    return new CharacterViewSection(characterPane, title, subViewFactory);
  }

  @Override
  public final JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new BorderLayout());
      content.add(characterPane.getComponent(), BorderLayout.CENTER);
      content.add(optionalViewPane.getComponent(), BorderLayout.EAST);
      overviewView.showIn(characterPane);
    }
    return content;
  }

  @Override
  public void toggleOverviewView(boolean experienced) {
    this.overviewView = experienced ? experienceOverviewView : creationOverviewView;
    optionalViewPane.setView("Overview", characterPane.getOverview());
    overviewView.showIn(characterPane);
  }
}