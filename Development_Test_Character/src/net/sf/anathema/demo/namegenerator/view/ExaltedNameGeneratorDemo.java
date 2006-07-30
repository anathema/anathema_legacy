package net.sf.anathema.demo.namegenerator.view;

import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.namegenerator.exalted.ExaltedNameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.ExaltedNameGeneratorTypePresentation;
import net.sf.anathema.namegenerator.presenter.INameGeneratorTypePresentation;
import net.sf.anathema.namegenerator.presenter.NameGeneratorPresenter;
import net.sf.anathema.namegenerator.view.NameGeneratorView;
import de.jdemo.extensions.SwingDemoCase;

public class ExaltedNameGeneratorDemo extends SwingDemoCase {

  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    NameGeneratorView view = new NameGeneratorView();
    ExaltedNameGeneratorModel model = new ExaltedNameGeneratorModel();
    INameGeneratorTypePresentation typePresentation = new ExaltedNameGeneratorTypePresentation();
    new NameGeneratorPresenter(resources, view, model, typePresentation).initPresentation();
    show(view.getComponent());
  }
}