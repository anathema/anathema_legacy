package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.model.CharacterStatisticsConfiguration;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.core.ISwingFrameOrDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.ItemTemplateFactory;

public class CharacterCreationTemplateFactory implements ItemTemplateFactory {

  private final Resources resources;
  private final ICharacterGenerics generics;

  public CharacterCreationTemplateFactory(ICharacterGenerics generics, Resources resources) {
    this.generics = generics;
    this.resources = resources;
  }

  @Override
  public IDialogModelTemplate createTemplate() {
    CharacterStatisticsConfiguration template = new CharacterStatisticsConfiguration();
    IDialogPage page = createPage(template);
    boolean canceled = showDialog(page);
    if (canceled) {
      return NO_TEMPLATE;
    }
    return template;
  }

  private IDialogPage createPage(IDialogModelTemplate template) {
    CharacterItemCreationModel model = new CharacterItemCreationModel(generics, (CharacterStatisticsConfiguration) template);
    CharacterItemCreationView view = new CharacterItemCreationView();
    CharacterCreationPageProperties properties = new CharacterCreationPageProperties(resources);
    return new CharacterCreationDialogPage(model, view, properties);
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