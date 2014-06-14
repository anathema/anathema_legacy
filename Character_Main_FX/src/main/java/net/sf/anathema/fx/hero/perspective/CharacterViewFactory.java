package net.sf.anathema.fx.hero.perspective;

import net.sf.anathema.character.framework.display.SubViewMap;
import net.sf.anathema.character.framework.display.SubViewRegistry;
import net.sf.anathema.character.framework.library.util.CssSkinner;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.advance.creation.BonusPointManagement;
import net.sf.anathema.hero.advance.creation.IBonusPointManagement;
import net.sf.anathema.hero.advance.experience.ExperiencePointManagement;
import net.sf.anathema.hero.advance.experience.ExperiencePointManagementImpl;
import net.sf.anathema.hero.advance.overview.presenter.OverviewPresenter;
import net.sf.anathema.hero.advance.overview.view.OverviewContainer;
import net.sf.anathema.hero.display.presenter.CharacterPresenter;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.Stylesheet;

import java.util.ArrayList;
import java.util.List;

public class CharacterViewFactory {
  private final Environment environment;
  private final IApplicationModel applicationModel;

  public CharacterViewFactory(Environment environment, IApplicationModel applicationModel) {
    this.environment = environment;
    this.applicationModel = applicationModel;
  }

  public NodeHolder createView(net.sf.anathema.character.framework.Character hero) {
    SubViewRegistry viewFactory = new SubViewMap(environment);
    Stylesheet[] stylesheets = createStylesheets(hero);
    TaskedCharacterView characterView = new TaskedCharacterView(viewFactory, stylesheets);
    new CharacterPresenter(hero, characterView, environment, applicationModel).initPresentation();
    initOverviewPresentation(hero, characterView, environment);
    hero.getChangeManagement().setClean();
    return characterView;
  }

  private Stylesheet[] createStylesheets(Hero hero) {
    String[] skins = new CssSkinner().getSkins(hero.getTemplate().getTemplateType().getCharacterType());
    List<Stylesheet> stylesheets = new ArrayList<>();
    for (String skin : skins) {
      stylesheets.add(new Stylesheet(skin));
    }
    return stylesheets.toArray(new Stylesheet[stylesheets.size()]);
  }

  private void initOverviewPresentation(Hero hero, OverviewContainer container, Resources resources) {
    IBonusPointManagement bonusPointManagement = new BonusPointManagement(hero);
    ExperiencePointManagement experiencePointManagement = new ExperiencePointManagementImpl(hero);
    new OverviewPresenter(resources, hero, container, bonusPointManagement,
            experiencePointManagement, applicationModel.getMessaging()).initPresentation();
  }
}