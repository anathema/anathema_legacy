package net.sf.anathema.hero.equipment.display.view;

import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentPersonalizationProperties;
import net.sf.anathema.hero.equipment.display.presenter.PersonalizationEditView;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.util.Closure;

public class DialogPersonalizationEditView implements PersonalizationEditView {
  private final EquipmentPersonalizationPresenterPage page;
  private Runnable onConfirmation;

  public DialogPersonalizationEditView(EquipmentPersonalizationProperties properties) {
    this.page = new EquipmentPersonalizationPresenterPage(properties);
  }

  @Override
  public void show() {
    UserDialog dialog = new UserDialog(SwingApplicationFrame.getParentComponent(), page);
    DialogResult result = dialog.show();
    if (!result.isCanceled()) {
      onConfirmation.run();
    }
  }

  @Override
  public void whenChangeIsConfirmed(Runnable runnable) {
    this.onConfirmation = runnable;
  }

  @Override
  public void setTitle(String title) {
    page.setTitle(title);
  }

  @Override
  public void setDescription(String description) {
    page.setDescription(description);
  }

  @Override
  public void whenTitleChanges(Closure<String> closure) {
    page.whenTitleChanges(closure);
  }

  @Override
  public void whenDescriptionChanges(Closure<String> closure) {
    page.whenDescriptionChanges(closure);
  }
}
