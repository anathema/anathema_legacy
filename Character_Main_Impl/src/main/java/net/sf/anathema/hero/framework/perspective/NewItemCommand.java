package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.ItemTemplateFactory;

import javax.swing.SwingUtilities;

public class NewItemCommand implements Command {

  private final ItemCreator itemCreator;
  private final ItemTemplateFactory templateFactory;
  private final Environment environment;

  public NewItemCommand(ItemTemplateFactory templateFactory, Environment environment, ItemReceiver itemReceiver,
                        RepositoryItemPersister persister) {
    this.templateFactory = templateFactory;
    this.environment = environment;
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
      environment.handle(e, environment.getString("CharacterSystem.NewCharacter.Error"));
    }
  }
}