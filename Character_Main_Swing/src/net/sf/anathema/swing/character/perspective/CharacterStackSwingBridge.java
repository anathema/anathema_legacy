package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.character.perspective.CharacterStackBridge;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;

public class CharacterStackSwingBridge implements CharacterStackBridge {

  private final StackView stackView;
  private final CharacterViewFactory viewFactory;

  public CharacterStackSwingBridge(IApplicationModel model, StackView stackView) {
    this.viewFactory = new CharacterViewFactory(model);
    this.stackView = stackView;
  }

  @Override
  public void addViewForCharacter(CharacterIdentifier identifier, IItem item) {
    IView itemView = viewFactory.createView(item);
    stackView.addView(identifier, itemView);
  }

  @Override
  public void showCharacterView(CharacterIdentifier identifier) {
    stackView.showView(identifier);
  }

  public JComponent getComponent() {
    return stackView.getComponent();
  }
}
