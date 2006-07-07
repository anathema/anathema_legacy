package net.sf.anathema.character.impl.module;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.KeyStroke;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.generic.framework.configuration.ICharacterPreferencesConstants;
import net.sf.anathema.character.impl.module.preferences.RulesetPreferenceElement;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.item.AbstractSelectedItemEnabler;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.PreferencesElementsExtensionPoint;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class CharacterModule extends AbstractAnathemaModule {

  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    view.getMenuBar().addMenu(createCharacterMenu(model, resources));
    new CharacterPerformanceTuner(model, resources).startTuning();
  }

  @Override
  public void fillPresentationExtensionPoints(
      IRegistry<String, IAnathemaExtension> extensionPointRegistry,
      IAnathemaModel model,
      IResources resources,
      IAnathemaView view) {
    super.fillPresentationExtensionPoints(extensionPointRegistry, model, resources, view);
    PreferencesElementsExtensionPoint preferencesPoint = (PreferencesElementsExtensionPoint) extensionPointRegistry.get(PreferencesElementsExtensionPoint.ID);
    preferencesPoint.register(ICharacterPreferencesConstants.RULESET_PREFERENCE, new RulesetPreferenceElement());
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