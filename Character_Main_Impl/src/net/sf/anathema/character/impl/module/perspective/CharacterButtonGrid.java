package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridLayout;

import static net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID;

public class CharacterButtonGrid {

  private final JPanel buttonGrid = new JPanel(new GridLayout(0, 1));
  private final JPanel panel = new JPanel();

  public CharacterButtonGrid() {
    panel.add(buttonGrid);
  }

  public void fillFromRepository(IAnathemaModel model, CharacterStack characterStack) {
    for (PrintNameFile printNameFile : collectCharacterPrintNameFiles(model)) {
      ShowCharacterAction showAction = new ShowCharacterAction(printNameFile, model, characterStack);
      buttonGrid.add(new JButton(showAction));
    }
  }

  public JComponent getContent() {
    return panel;
  }

  private PrintNameFile[] collectCharacterPrintNameFiles(IAnathemaModel model) {
    IItemType characterItemType = collectCharacterItemType(model);
    IPrintNameFileAccess access = model.getRepository().getPrintNameFileAccess();
    return access.collectAllPrintNameFiles(characterItemType);
  }

  private IItemType collectCharacterItemType(IAnathemaModel model) {
    return model.getItemTypeRegistry().getById(CHARACTER_ITEM_TYPE_ID);
  }

}
