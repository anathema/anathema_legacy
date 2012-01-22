package net.sf.anathema.namegenerator.presenter;

import java.awt.Component;

import javax.swing.JComponent;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.namegenerator.presenter.model.INameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.view.INameGeneratorView;

public class NameGeneratorPresenter implements IPresenter {

  private final INameGeneratorView view;
  private final INameGeneratorModel model;
  private final IResources resources;
  private final INameGeneratorTypePresentation typePresentation;

  public NameGeneratorPresenter(
      IResources resources,
      INameGeneratorView view,
      INameGeneratorModel model,
      INameGeneratorTypePresentation typePresentation) {
    this.view = view;
    this.model = model;
    this.resources = resources;
    this.typePresentation = typePresentation;
  }

  public void initPresentation() {
    for (IIdentificate generatorType : model.getGeneratorTypes()) {
      JComponent modelPresentation = typePresentation.initGeneratorTypePresentation(generatorType);
      String formattedLabel = resources.getString("NameGeneratorPresenter." + generatorType); //$NON-NLS-1$
      view.addNameGeneratorType(formattedLabel, modelPresentation, generatorType);
    }
    initSelectedGeneratorTypePresentation();
    initGenerationPresentation();
  }

  private void initGenerationPresentation() {
    view.addGenerationAction(new SmartAction(resources.getString("NameGeneratorPresenter.GenerateButtonLabel")) { //$NON-NLS-1$
      private static final long serialVersionUID = 4272323507368472400L;

      @Override
      protected void execute(Component parentComponent) {
        String[] generatedNames = model.generateNames(50);
        view.setResult(AnathemaStringUtilities.concat(generatedNames, "\n")); //$NON-NLS-1$
      }
    });
  }

  private void initSelectedGeneratorTypePresentation() {
    view.addGeneratorTypeChangeListener(new IChangeListener() {
      public void changeOccurred() {
        model.setGeneratorType((IIdentificate) view.getSelectedGeneratorType());
      }
    });
    model.addGeneratorTypeChangeListener(new IChangeListener() {
      public void changeOccurred() {
        view.setSelectedGeneratorType(model.getSelectedGeneratorType());
      }
    });
    view.setSelectedGeneratorType(model.getSelectedGeneratorType());
  }
}