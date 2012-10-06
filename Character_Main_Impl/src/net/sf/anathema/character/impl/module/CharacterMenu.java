package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.AbstractSelectedItemEnabler;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.presenter.menu.IAnathemaMenu;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IMenuBar;
import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.initialization.Menu;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;

@Menu
@Weight(weight = 5)
public class CharacterMenu implements IAnathemaMenu {

  @Override
  public void add(IResources resources, IAnathemaModel model, IMenuBar menubar) {
    IMenu menu = menubar.addMenu(resources.getString("CharacterMenu.Title")); //$NON-NLS-1$
    menu.setMnemonic('C');
    menu.addMenuItem(new MakeCharacterExperienced(resources, model));
  }

  private static class MakeCharacterExperienced extends SmartAction {
    private final IAnathemaModel model;

    public MakeCharacterExperienced(IResources resources, IAnathemaModel model) {
      super(resources.getString("CharacterMenu.ToExperienced.Title"));
      this.model = model;
      new AbstractSelectedItemEnabler(model.getItemManagement(), this) {
        @Override
        protected boolean isEnabled(IItem selectedItem) {
          if (selectedItem == null) {
            return false;
          }
          IItemData itemData = selectedItem.getItemData();
          if (!(itemData instanceof ICharacter)) {
            return false;
          }
          ICharacter character = (ICharacter) itemData;
          return !character.isExperienced();
        }
      };
    }

    @Override
    protected void execute(Component parentComponent) {
      ICharacter character = (ICharacter) model.getItemManagement().getSelectedItem().getItemData();
      character.setExperienced(true);
      setEnabled(false);
    }
  }
}