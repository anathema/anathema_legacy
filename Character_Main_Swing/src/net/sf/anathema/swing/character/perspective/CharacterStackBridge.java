package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.swing.character.perspective.StackView;

import javax.swing.JComponent;

public class CharacterStackBridge {

  private StackView stackView = new StackView();
  private CharacterViewFactory viewFactory;

  public CharacterStackBridge(IAnathemaModel model) {
    this.viewFactory =  new CharacterViewFactory(model);
  }

  public void addCharacterView(IItem item) {
    IView itemView = viewFactory.createView(item);
    stackView.addView(item.getRepositoryLocation().getId(), itemView);
  }

  public void showCharacterView(String repositoryId) {
    stackView.showView(repositoryId);
  }

  public JComponent getComponent() {
    return stackView.getComponent();
  }
}
