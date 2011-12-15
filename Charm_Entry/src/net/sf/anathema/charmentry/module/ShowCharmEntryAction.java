package net.sf.anathema.charmentry.module;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Action;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.IDialogResult;
import net.disy.commons.swing.dialog.core.ISwingFrameOrDialog;
import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.disy.commons.swing.util.GuiUtilities;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmEntryData;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.charmentry.model.WizardCharmEntryModel;
import net.sf.anathema.charmentry.persistence.CharmEntryPropertiesPersister;
import net.sf.anathema.charmentry.persistence.CharmIO;
import net.sf.anathema.charmentry.presenter.HeaderDataEntryPage;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.resources.IResources;

public class ShowCharmEntryAction extends SmartAction {

  private static final long serialVersionUID = 224753938683474781L;
  private final IResources resources;

  public static Action createMenuAction(IResources resources) {
    return new ShowCharmEntryAction(resources.getString("CharmEntry.Show.Name"), resources); //$NON-NLS-1$
  }

  private ShowCharmEntryAction(String string, IResources resources) {
    this.resources = resources;
    setName(string);
  }

  @Override
  protected void execute(Component parentComponent) {
    parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try {
      ICharmEntryModel model = new WizardCharmEntryModel();
      ICharmEntryViewFactory viewFactory = new CharmEntryViewFactory(resources);
      HeaderDataEntryPage startPage = new HeaderDataEntryPage(resources, model, viewFactory);
      WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
      final ISwingFrameOrDialog configuredDialog = dialog.getConfiguredDialog();
      configuredDialog.setResizable(false);
      GuiUtilities.centerToParent(configuredDialog.getWindow());
      IDialogResult result = dialog.show();
      if (result.isCanceled()) {
        return;
      }
      final ICharmEntryData entryData = model.getCharmData();
      // RLR Best Guess is CharmType
      // Maybe CharacterType
      //CharmCache.getInstance().addCharm(model.getCharmTypeModel().getCharmType(),entryData.getEdition().getDefaultRuleset(), (ICharm) entryData.getCoreData());
      CharmCache.getInstance().addCharm(entryData);

      new CharmIO().writeCharmInternal(entryData);
      final ICharmData coreData = entryData.getCoreData();
      CharmEntryPropertiesPersister charmEntryPropertiesPersister = new CharmEntryPropertiesPersister();
      charmEntryPropertiesPersister.writeCharmNameProperty(
          coreData.getCharacterType(),
          entryData.getEdition(),
          coreData.getId(),
          entryData.getName());
      charmEntryPropertiesPersister.writeCharmPageProperty(
          coreData.getCharacterType(),
          coreData.getId(),
          coreData.getSource(),
          entryData.getPage());
      charmEntryPropertiesPersister.writeDurationProperty(resources, coreData.getDuration());
    }
    catch (Exception e) {
      Message message = new Message("Error occurred while entering charm.", e); //$NON-NLS-1$
      MessageUtilities.indicateMessage(ShowCharmEntryAction.class, parentComponent, message);
    }
    finally {
      parentComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }
}