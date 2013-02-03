package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.character.perspective.CharacterIdentifier;
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
  public void addViewForExistingCharacter(IItem item) {
    IView itemView = viewFactory.createView(item);
    String repositoryId = item.getRepositoryLocation().getId();
    stackView.addView(new CharacterIdentifier(repositoryId), itemView);
  }

  @Override
  public void showCharacterView(CharacterIdentifier identifier) {
    stackView.showView(identifier);
  }

  public JComponent getComponent() {
    return stackView.getComponent();
  }
}
