package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.character.perspective.CharacterStackBridge;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;

public class CharacterStackSwingBridge implements CharacterStackBridge {

  private final StackView stackView;
  private final CharacterViewFactory viewFactory;

  public CharacterStackSwingBridge(IAnathemaModel model, StackView stackView) {
    this.viewFactory =  new CharacterViewFactory(model);
    this.stackView = stackView;
  }

  @Override
  public void addCharacterView(IItem item) {
    IView itemView = viewFactory.createView(item);
    stackView.addView(item.getRepositoryLocation().getId(), itemView);
  }

  @Override
  public void showCharacterView(String repositoryId) {
    stackView.showView(repositoryId);
  }

  public JComponent getComponent() {
    return stackView.getComponent();
  }
}
