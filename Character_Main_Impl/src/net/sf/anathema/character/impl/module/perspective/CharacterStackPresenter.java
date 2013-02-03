package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

public class CharacterStackPresenter {
  private final List<String> knownCharacters = new ArrayList<>();
  private final StackView stackView = new StackView();

  public void showCharacter(IAnathemaModel model, String repositoryId) {
    if (!knownCharacters.contains(repositoryId)) {
      addCharacter(model, repositoryId);
    }
    stackView.showView(repositoryId);
  }

  public void addCharacter(IAnathemaModel model, String repositoryId) {
    IView itemView = loadItemView(model, repositoryId);
    stackView.addView(repositoryId, itemView);
  }

  private IView loadItemView(IAnathemaModel model, String repositoryId) {
    IItemType itemType = model.getItemTypeRegistry().getById("ExaltedCharacter");
    IRepositoryReadAccess readAccess = model.getRepository().openReadAccess(itemType, repositoryId);
    IItem item = model.getPersisterRegistry().get(itemType).load(readAccess);
    return model.getViewFactoryRegistry().get(itemType).createView(item);
  }

  public JComponent getContent() {
    return stackView.getContent();
  }
}
