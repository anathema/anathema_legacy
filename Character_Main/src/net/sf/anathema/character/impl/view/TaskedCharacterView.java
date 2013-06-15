package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.impl.view.overview.OverviewContainer;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.overview.CategorizedOverview;
import net.sf.anathema.character.view.overview.NullOverviewContainer;
import net.sf.anathema.framework.view.util.OptionalViewBar;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class TaskedCharacterView implements CharacterView {

  private CategorizedOverview creationOverviewView;
  private CategorizedOverview experienceOverviewView;
  private CategorizedOverview overviewView = new NullOverviewContainer();
  private final TaskedCharacterPane characterPane;
  private final OptionalViewBar optionalViewPane = new OptionalViewBar();
  private JPanel content;
  private final SubViewRegistry subViewFactory;

  public TaskedCharacterView(SubViewRegistry viewFactory) {
    this.characterPane = new TaskedCharacterPane();
    this.subViewFactory = viewFactory;
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
  public SectionView addSection(String title) {
    return new CharacterViewSection(characterPane, title, subViewFactory);
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