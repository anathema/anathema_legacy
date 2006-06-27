package net.sf.anathema.demo.character.impl.view;

import javax.swing.JOptionPane;

import net.disy.commons.swing.dialog.userdialog.IDialogPage;
import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.character.generic.framework.CharacterModuleContainerInitializer;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.impl.view.repository.CharacterTemplateTree;
import net.sf.anathema.character.impl.view.repository.NewCharacterDialogPage;
import net.sf.anathema.character.impl.view.repository.RuleSetSelectionView;
import net.sf.anathema.framework.resources.AnathemaResources;
import de.jdemo.extensions.SwingDemoCase;

public class NewCharacterDialogDemo extends SwingDemoCase {

  public void demoAdvancedNewCharacterDialog() {
    ICharacterGenerics characterGenerics = new CharacterModuleContainerInitializer().initContainer(
        new AnathemaResources()).getCharacterGenerics();
    AnathemaResources anathemaResources = new AnathemaResources();
    IDialogPage page = new NewCharacterDialogPage(
        new CharacterTemplateTree(characterGenerics, anathemaResources),
        new RuleSetSelectionView(anathemaResources, ExaltedRuleSet.CoreRules),
        anathemaResources);
    UserDialog container = new UserDialog(JOptionPane.getRootFrame(), page);
    show(container.getDialog().getWindow());
  }
}