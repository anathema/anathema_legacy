package net.sf.anathema.character.equipment.creation.view.fx;

import javafx.event.ActionEvent;
import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsDialog;
import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsView;
import net.sf.anathema.framework.environment.fx.DialogFactory;
import net.sf.anathema.lib.gui.dialog.core.StaticOperationResult;
import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.message.Message;
import org.controlsfx.control.action.AbstractAction;
import org.controlsfx.dialog.Dialog;

public class FxEditStatsDialog implements EquipmentStatsDialog {
  private final AbstractAction okayAction = new AbstractAction(Dialog.Actions.OK.textProperty().get()){
    @Override
    public void handle(ActionEvent actionEvent) {
      dialog.hide();
      handler.handleOperationResult(StaticOperationResult.Confirmed());
    }
  };
  private final AbstractAction cancelAction = new AbstractAction(Dialog.Actions.CANCEL.textProperty().get()){
    @Override
    public void handle(ActionEvent actionEvent) {
      dialog.hide();
      handler.handleOperationResult(StaticOperationResult.Canceled());
    }
  };
  private final FxEquipmentStatsView view = new FxEquipmentStatsView();
  private Dialog dialog;
  private String title;
  private OperationResultHandler handler;
  private String messageText;
  private DialogFactory dialogFactory;

  public FxEditStatsDialog(DialogFactory dialogFactory) {
    this.dialogFactory = dialogFactory;
  }

  public void setCanFinish() {
    okayAction.disabledProperty().setValue(false);
  }

  public void setCannotFinish() {
    okayAction.disabledProperty().setValue(true);
  }

  @Override
  public void setMessage(Message message) {
    this.messageText = message.getText();
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void show(OperationResultHandler handler) {
    this.handler = handler;
    this.dialog = dialogFactory.createDialog(title);
    dialog.setMasthead(messageText);
    dialog.setContent(view.getNode());
    dialog.getActions().setAll(okayAction, cancelAction);
    dialog.show();
  }

  @Override
  public EquipmentStatsView getEquipmentStatsView() {
    return view;
  }
}
