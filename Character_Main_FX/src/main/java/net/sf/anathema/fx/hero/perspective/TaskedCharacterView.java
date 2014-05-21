package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.framework.display.CharacterView;
import net.sf.anathema.hero.display.CharacterViewSection;
import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.character.framework.display.SubViewRegistry;
import net.sf.anathema.framework.view.util.OptionalViewBar;
import net.sf.anathema.fx.hero.overview.DefaultCategorizedOverview;
import net.sf.anathema.fx.hero.overview.NullOverviewContainer;
import net.sf.anathema.hero.advance.overview.view.CategorizedOverview;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.Stylesheet;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class TaskedCharacterView implements CharacterView, NodeHolder {

  private CategorizedOverview creationOverviewView;
  private CategorizedOverview experienceOverviewView;
  private CategorizedOverview overviewView = new NullOverviewContainer();
  private final TaskedCharacterPane characterPane = new TaskedCharacterPane();
  private final OptionalViewBar optionalViewPane = new OptionalViewBar();
  private MigPane content;
  private final SubViewRegistry subViewFactory;
  private final Stylesheet[] stylesheets;

  public TaskedCharacterView(SubViewRegistry viewFactory, Stylesheet... stylesheets) {
    this.subViewFactory = viewFactory;
    this.stylesheets = stylesheets;
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
  public Node getNode() {
    if (content == null) {
      content = new MigPane(fillWithoutInsets(), new AC().index(0).shrink().shrinkPrio(200));
      for (Stylesheet stylesheet : stylesheets) {
        stylesheet.applyToParent(content);
      }
      content.add(characterPane.getNode(), new CC().grow().push());
      content.add(optionalViewPane.getNode(), new CC().alignY("top").grow().dockEast());
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