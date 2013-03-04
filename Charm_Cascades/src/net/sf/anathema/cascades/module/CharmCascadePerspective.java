package net.sf.anathema.cascades.module;

import net.sf.anathema.cascades.presenter.CascadePresenter;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

@PerspectiveAutoCollector
@Weight(weight = 6000)
public class CharmCascadePerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("toolbar/TaskBarCharms24.png");
    toggle.setTooltip("ItemType.CharmCascades.PrintName");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, IResources resources) {
    CharmCascadeModuleView view = new CharmCascadeModuleView();
    ICharacterGenerics characterGenerics = CharacterGenericsExtractor.getGenerics(applicationModel);
    MagicDescriptionProvider magicDescriptionProvider = getCharmDescriptionProvider(applicationModel, resources);
    new CascadePresenter(resources, characterGenerics, view, magicDescriptionProvider).initPresentation();
    container.setSwingContent(view.getComponent());
  }

  private MagicDescriptionProvider getCharmDescriptionProvider(IApplicationModel model, IResources resources) {
    return CharmDescriptionProviderExtractor.CreateFor(model, resources);
  }
}
