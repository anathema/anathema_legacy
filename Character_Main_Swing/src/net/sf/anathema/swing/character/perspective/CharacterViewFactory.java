package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.ItemViewFactory;
import net.sf.anathema.framework.presenter.SwingItemViewFactory;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.registry.IRegistry;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterViewFactory {

  private IApplicationModel model;

  public CharacterViewFactory(IApplicationModel model) {
    this.model = model;
  }

  public IView createView(IItem item) {
    IItemType characterItemType = retrieveCharacterItemType(model);
    IRegistry<IItemType, ItemViewFactory> registry = model.getViewFactoryRegistry();
    SwingItemViewFactory factory = (SwingItemViewFactory) registry.get(characterItemType);
    return factory.createView(item);
  }
}
