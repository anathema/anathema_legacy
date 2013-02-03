package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.view.IItemView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

public class CharacterStack {
  private final CardLayout stack = new CardLayout();
  private final JPanel viewPanel = new JPanel(stack);
  private final List<String> knownCharacters = new ArrayList<>();

  public void showCharacter(IAnathemaModel model, String repositoryId) {
    if (!knownCharacters.contains(repositoryId)) {
      addCharacter(model, repositoryId);
    }
    stack.show(viewPanel, repositoryId);
  }

  public void addCharacter(IAnathemaModel model, String repositoryId) {
    IItemView itemView = loadItemView(model, repositoryId);
    viewPanel.add(itemView.getComponent(), repositoryId);
    viewPanel.revalidate();
    viewPanel.repaint();
  }

  private IItemView loadItemView(IAnathemaModel model, String repositoryId) {
    IItemType itemType = model.getItemTypeRegistry().getById("ExaltedCharacter");
    IRepositoryReadAccess readAccess = model.getRepository().openReadAccess(itemType, repositoryId);
    IItem item = model.getPersisterRegistry().get(itemType).load(readAccess);
    return model.getViewFactoryRegistry().get(itemType).createView(item);
  }

  public JComponent getContent() {
    return viewPanel;
  }
}
