package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.core.ISwingFrameOrDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.workflow.wizard.selection.CharacterTemplateCreator;
import net.sf.anathema.lib.workflow.wizard.selection.IItemOperator;

import javax.swing.SwingUtilities;

public class SwingCharacterTemplateCreator implements CharacterTemplateCreator {

  private final Resources resources;

  public SwingCharacterTemplateCreator(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void createTemplate(final IItemOperator operator, final ICharacterItemCreationModel creationModel) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        doIt(operator, creationModel);
      }
    });
  }

  private void doIt(IItemOperator operator, ICharacterItemCreationModel creationModel) {
    IDialogPage page = createPage(creationModel);
    boolean canceled = showDialog(page);
    if (canceled) {
      return;
    }
    operator.operate(creationModel.getSelectedTemplate());
  }

  private IDialogPage createPage(ICharacterItemCreationModel creationModel) {
    CharacterItemCreationView view = new CharacterItemCreationView();
    CharacterCreationPageProperties properties = new CharacterCreationPageProperties(resources);
    return new CharacterCreationDialogPage(creationModel, view, properties);
  }

  private boolean showDialog(IDialogPage page) {
    UserDialog dialog = new UserDialog(SwingApplicationFrame.getParentComponent(), page);
    ISwingFrameOrDialog configuredDialog = dialog.getDialog();
    configuredDialog.setResizable(false);
    GuiUtilities.centerToParent(configuredDialog.getWindow());
    DialogResult result = dialog.show();
    return result.isCanceled();
  }
}