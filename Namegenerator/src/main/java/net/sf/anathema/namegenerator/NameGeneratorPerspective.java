package net.sf.anathema.namegenerator;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.namegenerator.exalted.ExaltedNameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.NameGeneratorPresenter;
import net.sf.anathema.namegenerator.presenter.model.INameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.view.NameGeneratorView;
import net.sf.anathema.namegenerator.view.FxNameGeneratorView;

@PerspectiveAutoCollector
@Weight(weight = 7000)
public class NameGeneratorPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setTooltip("ItemType.NameGenerator.PrintName");
    toggle.setIcon(new RelativePath("icons/NameGeneratorPerspective.png"));
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, Resources resources) {
    NameGeneratorView view = new FxNameGeneratorView();
    INameGeneratorModel generatorModel = new ExaltedNameGeneratorModel();
    new NameGeneratorPresenter(resources, view, generatorModel).initPresentation();
    container.setSwingContent(view.getComponent());
  }
}
