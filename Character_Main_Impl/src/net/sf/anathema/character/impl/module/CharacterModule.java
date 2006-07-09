package net.sf.anathema.character.impl.module;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.KeyStroke;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.AbstractSelectedItemEnabler;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.module.IAnathemaModule;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.resources.IResources;

public class CharacterModule implements IAnathemaModule {

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    view.getMenuBar().addMenu(createCharacterMenu(model, resources));
    new CharacterPerformanceTuner(model, resources).startTuning();
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
}