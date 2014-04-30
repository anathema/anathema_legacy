package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsDialog;
import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsView;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.IBasicMessage;
import org.jmock.example.announcer.Announcer;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class SwingEditStatsDialog extends AbstractDialogPage implements EquipmentStatsDialog {

  private ExtensibleEquipmentStatsView view = new ExtensibleEquipmentStatsView();
  private final Announcer<ChangeListener> announcer = Announcer.to(ChangeListener.class);
  private boolean canCurrentlyFinish;
  private IBasicMessage message;
  private String title;
  private String description;

  public SwingEditStatsDialog() {
    super("");
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return message;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public final String getDescription() {
    return description;
  }

  @Override
  public boolean canFinish() {
    return canCurrentlyFinish;
  }

  @Override
  public final JComponent createContent() {
    return view.getComponent();
  }

  @Override
  public void setInputValidListener(ChangeListener inputValidListener) {
    announcer.addListener(inputValidListener);
    refreshFinishingState();
  }

  public void setCanFinish() {
    this.canCurrentlyFinish = true;
    refreshFinishingState();
  }

  public void setCannotFinish() {
    this.canCurrentlyFinish = false;
    refreshFinishingState();
  }

  @Override
  public void setMessage(IBasicMessage message) {
    this.message = message;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public void show(OperationResultHandler handler) {
    SwingUtilities.invokeLater(() -> {
      UserDialog dialog = new UserDialog(SwingApplicationFrame.getParentComponent(), this);
      dialog.show(handler);
    });
  }

  @Override
  public EquipmentStatsView getEquipmentStatsView() {
    return view;
  }

  private void refreshFinishingState() {
    announcer.announce().changeOccurred();
  }
}