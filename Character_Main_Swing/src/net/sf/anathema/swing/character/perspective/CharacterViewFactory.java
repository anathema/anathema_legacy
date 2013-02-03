package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.registry.IRegistry;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterViewFactory {

  private IAnathemaModel model;

  public CharacterViewFactory(IAnathemaModel model) {
    this.model = model;
  }

  public IView createView(IItem item) {
    IItemType characterItemType = retrieveCharacterItemType(model);
    IRegistry<IItemType,IItemViewFactory> registry = model.getViewFactoryRegistry();
    IItemViewFactory factory = registry.get(characterItemType);
    return factory.createView(item);
  }
}
