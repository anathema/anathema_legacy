package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.swing.character.perspective.StackView;

import javax.swing.JComponent;

public class CharacterStackBridge {

  private final StackView stackView;
  private final CharacterViewFactory viewFactory;

  public CharacterStackBridge(IAnathemaModel model, StackView stackView) {
    this.viewFactory =  new CharacterViewFactory(model);
    this.stackView = stackView;
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
