package net.sf.anathema.character.impl.module;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.KeyStroke;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.impl.view.repository.AddNewFullCharacterAction;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.AbstractSelectedItemEnabler;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.presenter.action.ItemTypeLoadAction;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class CharacterModulePresenter {

  private final ICharacterGenerics generics;

  public CharacterModulePresenter(
      final IAnathemaModel anathemaModel,
      IAnathemaView view,
      final IResources resources,
      IItemType typeCharacter,
      ICharacterGenerics generics) {
    this.generics = generics;
    view.getToolbar().addTools(createTools(anathemaModel, resources, typeCharacter));
    view.getMenuBar().addMenu(createCharacterMenu(anathemaModel, resources));
  }

  private JMenu createCharacterMenu(final IAnathemaModel anathemaModel, final IResources resources) {
    JMenu characterMenu = new JMenu(resources.getString("CharacterMenu.Title")); //$NON-NLS-1$
    characterMenu.setMnemonic('C');
    characterMenu.add(new SmartAction(resources.getString("CharacterMenu.ToExperienced.Title")) { //$NON-NLS-1$
          {
            setAcceleratorKey(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
            new AbstractSelectedItemEnabler(anathemaModel.getItemManagement(), this) {
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
                return character.hasStatistics() && !character.getStatistics().isExperienced();
              }
            };
          }

          @Override
          protected void execute(Component parentComponent) {
            ICharacter character = (ICharacter) anathemaModel.getItemManagement().getSelectedItem().getItemData();
            character.getStatistics().setExperienced(true);
            setEnabled(false);
          }
        });
    return characterMenu;
  }

  private Action[] createTools(final IAnathemaModel anathemaModel, final IResources resources, IItemType typeCharacter) {
    return new Action[] {
        AddNewFullCharacterAction.createToolAction(generics, resources, anathemaModel),
        ItemTypeLoadAction.createToolAction(
            anathemaModel,
            typeCharacter,
            resources,
            resources.getImageIcon("toolbar/Add24.gif"), //$NON-NLS-1$
            resources.getString("CharacterGenerator.LoadAction.Tooltip")) //$NON-NLS-1$
    };
  }
}