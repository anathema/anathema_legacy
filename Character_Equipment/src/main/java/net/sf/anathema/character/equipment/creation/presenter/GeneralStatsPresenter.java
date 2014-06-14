package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class GeneralStatsPresenter {
  private final EquipmentStatsView view;
  private EquipmentStatsDialog dialog;
  private final IEquipmentStatisticsModel model;
  private IEquipmentStatisticsCreationModel overallModel;
  private Resources resources;

  public GeneralStatsPresenter(EquipmentStatsView view, EquipmentStatsDialog dialog, IEquipmentStatisticsModel model,
                               IEquipmentStatisticsCreationModel overallModel, Resources resources) {
    this.view = view;
    this.dialog = dialog;
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
    dialog.setMessage(getDefaultMessage());
    dialog.setTitle(getPageDescription());
  }

  private void initCompletionPresentation() {
    model.addValidListener(GeneralStatsPresenter.this::updateValidity);
    updateValidity();
  }

  private void updateValidity() {
    if (model.isValid() && isNameUnique()) {
      dialog.setCanFinish();
    } else {
      dialog.setCannotFinish();
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

  public Message getDefaultMessage() {
    return new Message(resources.getString("Equipment.Creation.Stats.DefaultMessage"), MessageType.NORMAL);
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