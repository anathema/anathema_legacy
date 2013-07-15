package net.sf.anathema.namegenerator.presenter;

import com.google.common.base.Joiner;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.namegenerator.presenter.model.INameGeneratorModel;
import net.sf.anathema.namegenerator.presenter.view.NameGeneratorView;

public class NameGeneratorPresenter implements Presenter {

  private final NameGeneratorView view;
  private final INameGeneratorModel model;
  private final Resources resources;

  public NameGeneratorPresenter(Resources resources, NameGeneratorView view, INameGeneratorModel model) {
    this.view = view;
    this.model = model;
    this.resources = resources;
  }

  @Override
  public void initPresentation() {
    for (Identifier generatorType : model.getGeneratorTypes()) {
      String formattedLabel = resources.getString(generatorType.getId());
      view.addNameGeneratorType(formattedLabel, generatorType);
    }
    initSelectedGeneratorTypePresentation();
    initGenerationPresentation();
  }

  private void initGenerationPresentation() {
    String label = resources.getString("Namegenerator.GenerateButton.Label");
    view.addGenerationAction(label,
            new Command() {
              @Override
              public void execute() {
                String[] generatedNames = model.generateNames(50);
                view.setResult(Joiner.on("\n").join(generatedNames));
              }
            });
  }

  private void initSelectedGeneratorTypePresentation() {
    view.addGeneratorTypeChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        model.setGeneratorType((Identifier) view.getSelectedGeneratorType());
      }
    });
    model.addGeneratorTypeChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        view.setSelectedGeneratorType(model.getSelectedGeneratorType());
      }
    });
    view.setSelectedGeneratorType(model.getSelectedGeneratorType());
  }
}