package net.sf.anathema.cascades.module;

import net.sf.anathema.cascades.presenter.CascadePresenter;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

@PerspectiveAutoCollector
@Weight(weight = 6000)
public class CharmCascadePerspective implements Perspective {
  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("toolbar/TaskBarCharms24.png");
    toggle.setTooltip("ItemType.CharmCascades.PrintName");
  }

  @Override
  public void initContent(Container container, IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    CharmCascadeModuleView view = new CharmCascadeModuleView();
    ICharacterGenerics characterGenerics = CharacterGenericsExtractor.getGenerics(model);
    MagicDescriptionProvider magicDescriptionProvider = getCharmDescriptionProvider(model, resources);
    new CascadePresenter(resources, characterGenerics, view, magicDescriptionProvider).initPresentation();
    container.setSwingContent(view.getComponent());
  }

  private MagicDescriptionProvider getCharmDescriptionProvider(IAnathemaModel model, IResources resources) {
    return CharmDescriptionProviderExtractor.CreateFor(model, resources);
  }
}
