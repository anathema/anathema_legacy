package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.HeroTemplateHolder;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.hero.framework.HeroEnvironment;
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
  public void createTemplate(final HeroEnvironment heroEnvironment, final IItemOperator operator) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        doIt(heroEnvironment, operator);
      }
    });
  }

  private void doIt(HeroEnvironment heroEnvironment, IItemOperator operator) {
    CharacterItemCreationModel model = new CharacterItemCreationModel(heroEnvironment);
    IDialogPage page = createPage(model);
    boolean canceled = showDialog(page);
    if (canceled) {
      return;
    }
    operator.operate(model.getSelectedTemplate());
  }

  private IDialogPage createPage(CharacterItemCreationModel creationModel) {
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