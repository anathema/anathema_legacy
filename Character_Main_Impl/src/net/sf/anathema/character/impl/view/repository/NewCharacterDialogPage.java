package net.sf.anathema.character.impl.view.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import net.disy.commons.swing.dialog.userdialog.AbstractDialogPage;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.message.BasicMessage;
import net.disy.commons.swing.message.IBasicMessage;
import net.disy.commons.swing.message.MessageType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.view.repository.ICharacterTemplateTree;
import net.sf.anathema.lib.resources.IResources;

public class NewCharacterDialogPage extends AbstractDialogPage {

  private final ICharacterTemplateTree characterTemplateTree;
  private final RuleSetSelectionView rulesView;
  private final IResources resources;

  public NewCharacterDialogPage(
      ICharacterTemplateTree characterTemplateTree,
      RuleSetSelectionView rulesView,
      IResources resources) {
    super(new BasicMessage(resources.getString("CharacterDialog.Message.Select"), MessageType.ERROR)); //$NON-NLS-1$
    this.characterTemplateTree = characterTemplateTree;
    this.rulesView = rulesView;
    this.resources = resources;
    characterTemplateTree.initTemplateTree();
    rulesView.initRulesSelectionView();
  }

  public IBasicMessage createCurrentMessage() {
    if (characterTemplateTree.isTemplateTypeSelected()) {
      return new BasicMessage(resources.getString("CharacterDialog.Message.Confirm"), MessageType.INFORMATION); //$NON-NLS-1$
    }
    return new BasicMessage(resources.getString("CharacterDialog.Message.Select"), MessageType.ERROR); //$NON-NLS-1$
  }

  public JComponent createContent() {
    JPanel panel = new JPanel(new GridDialogLayout(1, false));
    JLabel label = new JLabel(resources.getString("CharacterDialog.Template.Select.Label")); //$NON-NLS-1$
    panel.add(label);
    JComponent templateSelectionComponent = characterTemplateTree.getComponent();
    templateSelectionComponent.setBorder(new BevelBorder(BevelBorder.LOWERED));
    JScrollPane treeScrollPane = new JScrollPane(templateSelectionComponent);
    panel.add(treeScrollPane, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(rulesView.getComponent());
    initListening();
    return panel;
  }

  private void initListening() {
    characterTemplateTree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent e) {
        getCheckInputValidListener().valueChanged(e);
        if (characterTemplateTree.isTemplateTypeSelected()) {
          List<IExaltedRuleSet> rules = new ArrayList<IExaltedRuleSet>();
          for (IExaltedEdition edition : characterTemplateTree.getSelectedTemplate().getSupportedEditions()) {
            Collections.addAll(rules, ExaltedRuleSet.getRuleSetsByEdition(edition));
          }
          rulesView.setAvailableRulesets(rules.toArray(new IExaltedRuleSet[rules.size()]));
        }
      }
    });
  }

  public String getTitle() {
    return resources.getString("CharacterDialog.Title"); //$NON-NLS-1$
  }

  @Override
  public String getDescription() {
    return resources.getString("CharacterDialog.Description"); //$NON-NLS-1$
  }
}