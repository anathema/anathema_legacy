package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.swing.ExceptionIndicator;
import net.sf.anathema.initialization.FxApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.ItemTemplateFactory;

import javax.swing.SwingUtilities;

public class NewItemCommand implements Command {

  private final ItemCreator itemCreator;
  private final ItemTemplateFactory templateFactory;
  private Resources resources;

  public NewItemCommand(ItemTemplateFactory templateFactory, Resources resources, ItemReceiver itemReceiver,
                        RepositoryItemPersister persister) {
    this.templateFactory = templateFactory;
    this.resources = resources;
    this.itemCreator = new ItemCreator(new NewItemCreator(persister), itemReceiver);
  }

  @Override
  public void execute() {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        doIt();
      }
    });
  }

  private void doIt() {
    IDialogModelTemplate template = templateFactory.createTemplate();
    if (template == ItemTemplateFactory.NO_TEMPLATE) {
      return;
    }
    try {
      itemCreator.operate(template);
    } catch (PersistenceException e) {
      Message message = new Message(resources.getString("AnathemaPersistence.NewMenu.Message.Error"), e);
      ExceptionIndicator.indicate(FxApplicationFrame.getOwner(), message);
    }
  }
}