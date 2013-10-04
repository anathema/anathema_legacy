package net.sf.anathema.charmdatabase.module;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.charmdatabase.management.CharmDatabaseManagement;
import net.sf.anathema.charmdatabase.presenter.CharmDatabasePresenter;
import net.sf.anathema.charmdatabase.view.fx.FxCharmDatabaseView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.lib.file.RelativePath;

@PerspectiveAutoCollector
@Weight(weight = 6000)
public class CharmDatabasePerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/toolbar/TaskBarCharms24.png"));
    toggle.setTooltip("ItemType.CharmCascades.PrintName");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Environment environment) {
	  HeroEnvironment characterGenerics = HeroEnvironmentExtractor.getGenerics(applicationModel);
	  MagicDescriptionProvider magicDescriptionProvider = getCharmDescriptionProvider(applicationModel, environment);
	  CharmDatabaseManagement management = new CharmDatabaseManagement(environment, 
			  characterGenerics, magicDescriptionProvider);
	    initInFx(container, environment, management);
  }
  
  private void initInFx(Container container, Resources resources, CharmDatabaseManagement management) {
	    FxCharmDatabaseView view = new FxCharmDatabaseView(resources);
	    new CharmDatabasePresenter(resources, management, view).initPresentation();
	    container.setContent(view.perspectivePane.getNode());
	  }

  private MagicDescriptionProvider getCharmDescriptionProvider(IApplicationModel model, Environment environment) {
    return CharmDescriptionProviderExtractor.CreateFor(model, environment);
  }
}