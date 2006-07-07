package net.sf.anathema.character.impl.module.view;

import javax.swing.Action;
import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.impl.module.CharacterGenericsExtension;
import net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration;
import net.sf.anathema.character.impl.view.repository.AddNewFullCharacterAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.ItemTypeLoadAction;
import net.sf.anathema.framework.presenter.toolbar.IAnathemaTool;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;
import net.sf.anathema.lib.resources.IResources;

public class CharacterTool implements IAnathemaTool {

  public void add(IResources resources, IAnathemaModel model, IAnathemaToolbar toolbar) {
    IItemType characterItemType = model.getItemTypeRegistry().getById(
        ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID);
    toolbar.addTools(createTools(model, resources, characterItemType));
  }

  private Action[] createTools(final IAnathemaModel anathemaModel, final IResources resources, IItemType typeCharacter) {
    ICharacterGenerics generics = CharacterGenericsExtension.getCharacterGenerics(anathemaModel);
    Icon addIcon = resources.getImageIcon("toolbar/Add24.gif"); //$NON-NLS-1$
    String tooltip = resources.getString("CharacterGenerator.LoadAction.Tooltip"); //$NON-NLS-1$
    return new Action[] {
        AddNewFullCharacterAction.createToolAction(generics, resources, anathemaModel),
        ItemTypeLoadAction.createToolAction(anathemaModel, typeCharacter, resources, addIcon, tooltip) };
  }
}