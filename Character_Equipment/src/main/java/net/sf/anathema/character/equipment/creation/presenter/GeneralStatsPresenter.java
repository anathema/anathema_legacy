package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class GeneralStatsPresenter {
  private final EquipmentStatsView view;
  private final IEquipmentStatisticsModel model;
  private IEquipmentStatisticsCreationModel overallModel;
  private Resources resources;

  public GeneralStatsPresenter(EquipmentStatsView view, IEquipmentStatisticsModel model,
                               IEquipmentStatisticsCreationModel overallModel, Resources resources) {
    this.view = view;
    this.model = model;
    this.overallModel = overallModel;
    this.resources = resources;
  }

  public void initPresentation() {
    initBasicPresentation();
    initNamePresentation();
    initCompletionPresentation();
  }

  private void initBasicPresentation() {
    view.setMessage(getDefaultMessage());
    view.setTitle(getPageDescription());
    view.setDescription(getPageDescription());
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
      name.setText(getDefaultName());
    }
    ITextView textView = view.addLineTextView(getNameLabel());
    new TextualPresentation().initView(textView, name);
  }


  private boolean isNameUnique() {
    return overallModel.isNameUnique(model.getName().getText());
  }

  public IBasicMessage getDefaultMessage() {
    return new BasicMessage(resources.getString("Equipment.Creation.Stats.DefaultMessage"));
  }

  public String getPageDescription() {
    return resources.getString("Equipment.Creation.Stats.PageTitle");
  }

  public String getDefaultName() {
    return resources.getString("Equipment.Creation.Stats.DefaultName");
  }

  public String getNameLabel() {
    return resources.getString("Equipment.Creation.Stats.Name") + ":";
  }
}