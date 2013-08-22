package net.sf.anathema.cascades.module;

import net.sf.anathema.cascades.presenter.CharmCascadesPresenterImpl;
import net.sf.anathema.cascades.presenter.CharmTreeIdentifierMap;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.display.view.FxCharmView;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

@PerspectiveAutoCollector
@Weight(weight = 6000)
public class CharmCascadePerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/toolbar/TaskBarCharms24.png"));
    toggle.setTooltip("ItemType.CharmCascades.PrintName");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Resources resources) {
    HeroEnvironment characterGenerics = HeroEnvironmentExtractor.getGenerics(applicationModel);
    MagicDescriptionProvider magicDescriptionProvider = getCharmDescriptionProvider(applicationModel, resources);
    FxCharmView cascadeView = new FxCharmView();
    new CharmCascadesPresenterImpl(resources, characterGenerics, cascadeView, magicDescriptionProvider,
            new CharmTreeIdentifierMap()).initPresentation();
    container.setContent(cascadeView.getNode());
  }

  private MagicDescriptionProvider getCharmDescriptionProvider(IApplicationModel model, Resources resources) {
    return CharmDescriptionProviderExtractor.CreateFor(model, resources);
  }
}