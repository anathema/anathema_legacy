package net.sf.anathema.cascades.module;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.cascades.presenter.CharmCascadesPresenterImpl;
import net.sf.anathema.cascades.presenter.CharmTreeIdentifierMap;
import net.sf.anathema.character.magic.description.MagicDescriptionProvider;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.display.view.FxCharmView;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.lib.file.RelativePath;
import org.tbee.javafx.scene.layout.MigPane;

@PerspectiveAutoCollector
@Weight(weight = 6000)
public class CharmCascadePerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/toolbar/TaskBarCharms24.png"));
    toggle.setTooltip("ItemType.CharmCascades.PrintName");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Environment environment, UiEnvironment uiEnvironment) {
    HeroEnvironment characterGenerics = HeroEnvironmentExtractor.getGenerics(applicationModel);
    MagicDescriptionProvider magicDescriptionProvider = getCharmDescriptionProvider(applicationModel, environment);
    FxCharmView cascadeView = new FxCharmView();
    new CharmCascadesPresenterImpl(environment, characterGenerics, cascadeView, magicDescriptionProvider,
            new CharmTreeIdentifierMap()).initPresentation();
    MigPane content = createContentPane(cascadeView);
    container.setContent(content);
  }

  private MigPane createContentPane(FxCharmView cascadeView) {
    Node node = cascadeView.getNode();
    MigPane content = new MigPane(new LC().fill());
    content.add(node, new CC().grow().push());
    return content;
  }

  private MagicDescriptionProvider getCharmDescriptionProvider(IApplicationModel model, Environment environment) {
    return CharmDescriptionProviderExtractor.CreateFor(model, environment);
  }
}