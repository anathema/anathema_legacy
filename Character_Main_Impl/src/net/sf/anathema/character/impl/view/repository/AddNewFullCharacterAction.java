package net.sf.anathema.character.impl.view.repository;

import java.awt.Component;

import javax.swing.Action;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.AbstractAddNewItemAction;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public class AddNewFullCharacterAction extends AbstractAddNewItemAction<CharacterStatisticsConfiguration> {

  private final ICharacterGenerics generics;
  private final IResources resources;

  public static Action createMenuAction(
      ICharacterGenerics generics,
      IResources resources,
      IAnathemaModel anathemaModel,
      String nameResource) {
    SmartAction action = new AddNewFullCharacterAction(generics, anathemaModel, resources);
    action.setName(resources.getString(nameResource));
    return action;
  }

  public static Action createToolAction(ICharacterGenerics generics, IResources resources, IAnathemaModel anathemaModel) {
    SmartAction action = new AddNewFullCharacterAction(generics, anathemaModel, resources);
    action.setIcon(resources.getImageIcon("toolbar/TaskBarNew24.png")); //$NON-NLS-1$
    action.setToolTipText(resources.getString("CharacterGenerator.NewCharacter.ToolTip.Text")); //$NON-NLS-1$
    return action;
  }

  private AddNewFullCharacterAction(ICharacterGenerics generics, IAnathemaModel anathemaModel, IResources resources) {
    super(anathemaModel, resources, ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID);
    this.generics = generics;
    this.resources = resources;
  }

  @Override
  protected CharacterStatisticsConfiguration createTemplate(Component parentComponent) {
    CharacterTemplateTree tree = new CharacterTemplateTree(generics, resources);
    IExaltedRuleSet preferredRuleSet = AnathemaCharacterPreferences.getDefaultPreferences().getPreferredRuleset();
    RuleSetSelectionView rulesView = new RuleSetSelectionView(resources, preferredRuleSet);
    NewCharacterDialogPage page = new NewCharacterDialogPage(tree, rulesView, resources);
    UserDialog userDialog = new UserDialog(parentComponent, page);
    userDialog.show();
    if (userDialog.isCanceled()) {
      return null;
    }
    IExaltedRuleSet ruleSet = rulesView.getSelectedRules();
    ICharacterTemplate template = generics.getTemplateRegistry().getTemplate(
        tree.getSelectedTemplate().getTemplateType(),
        ruleSet.getEdition());
    return new CharacterStatisticsConfiguration(template, ruleSet);
  }

  @Override
  protected final IItem createNewItem(CharacterStatisticsConfiguration configuration, IItemType itemType)
      throws AnathemaException {
    ExaltedCharacter character = new ExaltedCharacter();
    character.createCharacterStatistics(configuration.getTemplate(), generics, configuration.getRuleSet());
    return new AnathemaItem(itemType, character);
  }
}