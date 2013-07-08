package net.sf.anathema.swing.hero.perspective;

import net.sf.anathema.character.main.perspective.CharacterStackBridge;
import net.sf.anathema.character.main.perspective.model.CharacterIdentifier;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.swing.IView;

import javax.swing.JComponent;

public class CharacterStackSwingBridge implements CharacterStackBridge {

  private final StackView stackView;
  private final CharacterViewFactory viewFactory;

  public CharacterStackSwingBridge(CharacterViewFactory viewFactory, StackView stackView) {
    this.viewFactory = viewFactory;
    this.stackView = stackView;
  }

  @Override
  public void addViewForCharacter(CharacterIdentifier identifier, Item item) {
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
