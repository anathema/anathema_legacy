package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import java.util.ArrayList;
import java.util.List;

public class CharacterStackPresenter {
  private final List<String> knownCharacters = new ArrayList<>();
  private final StackView stackView = new StackView();
  private IAnathemaModel model;
  private CharacterSystemModel systemModel;

  public CharacterStackPresenter(IAnathemaModel model, CharacterSystemModel systemModel) {
    this.model = model;
    this.systemModel = systemModel;
  }

  public void showCharacter(String repositoryId) {
    if (!knownCharacters.contains(repositoryId)) {
      addCharacter(repositoryId);
      knownCharacters.add(repositoryId);
    }
    stackView.showView(repositoryId);
  }

  public void addCharacter(String repositoryId) {
    IView itemView = loadItemView(repositoryId);
    stackView.addView(repositoryId, itemView);
  }

  private IView loadItemView(String repositoryId) {
    IItem item = systemModel.loadItem(repositoryId);
    return model.getViewFactoryRegistry().get(systemModel.getCharacterItemType()).createView(item);
  }

  public JComponent getContent() {
    return stackView.getContent();
  }
}
