package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.EquipmentStatsView;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.dialog.userdialog.OperationResultHandler;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.SwingIntegerSpinner;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import org.jmock.example.announcer.Announcer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SwingEditStatsView extends AbstractDialogPage implements EquipmentStatsView {

  private ExtensibleEquipmentStatsView view = new ExtensibleEquipmentStatsView();
  private final Announcer<ChangeListener> announcer = Announcer.to(ChangeListener.class);
  private boolean canCurrentlyFinish;
  private IBasicMessage message;
  private String title;
  private String description;

  public SwingEditStatsView() {
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

  public final void addView(AdditiveView view) {
    this.view.addView(view);
  }

  public final SwingIntegerSpinner initIntegerSpinner(IIntValueModel intModel) {
    SwingIntegerSpinner spinner = new SwingIntegerSpinner(intModel.getValue());
    new IntValuePresentation().initPresentation(spinner, intModel);
    return spinner;
  }

  public ITextView addLineTextView(String nameLabel) {
    return view.addLineTextView(nameLabel);
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
  public void addElement(String label, JComponent component) {
    addView(panel -> {
      panel.add(new JLabel(label));
      panel.add(component, new CC().growX().pushX());
    });
  }

  private void refreshFinishingState() {
    announcer.announce().changeOccurred();
  }
}