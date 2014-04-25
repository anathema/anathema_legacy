package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.AbstractEquipmentStatisticsProperties;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class GeneralStatsPresenter {
  private final EquipmentStatsView view;
  private final IEquipmentStatisticsModel model;
  private final AbstractEquipmentStatisticsProperties properties;
  private IEquipmentStatisticsCreationModel overallModel;

  public GeneralStatsPresenter(EquipmentStatsView view, IEquipmentStatisticsModel model,
                               AbstractEquipmentStatisticsProperties properties,
                               IEquipmentStatisticsCreationModel overallModel) {
    this.view = view;
    this.model = model;
    this.properties = properties;
    this.overallModel = overallModel;
  }

  public void initPresentation() {
    initBasicPresentation();
    initNamePresentation();
    initCompletionPresentation();
  }

  private void initBasicPresentation() {
    view.setMessage(properties.getDefaultMessage());
    view.setTitle(properties.getPageDescription());
    view.setDescription(properties.getPageDescription());
  }

  private void initCompletionPresentation() {
    model.addValidListener(() -> {
      updateValidity();
    });
    updateValidity();
  }

  private void updateValidity() {
    if (model.isValid() && isNameUnique()) {
      view.setCanFinish();
    } else {
      view.setCannotFinish();
    }
  }

  private void initNamePresentation() {
    ITextualDescription name = model.getName();
    if (name.isEmpty()) {
      name.setText(properties.getDefaultName());
    }
    ITextView textView = view.addLineTextView(properties.getNameLabel());
    new TextualPresentation().initView(textView, name);
  }


  private boolean isNameUnique() {
    return overallModel.isNameUnique(model.getName().getText());
  }
}