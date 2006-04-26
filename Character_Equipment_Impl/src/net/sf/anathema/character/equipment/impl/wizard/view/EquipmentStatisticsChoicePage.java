package net.sf.anathema.character.equipment.impl.wizard.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.wizard.IWizardConfiguration;
import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.toolbar.ToolBarUtilities;
import net.disy.commons.swing.widgets.HorizontalLine;
import net.sf.anathema.character.equipment.impl.model.EquipmentStatisticsType;
import net.sf.anathema.character.equipment.impl.wizard.IAddEquipmentStatisticsProperties;
import net.sf.anathema.character.equipment.impl.wizard.model.AddEquipmentStatisticsWizardModel;

public class EquipmentStatisticsChoicePage extends AbstractAnathemaWizardPage {

  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final AddEquipmentStatisticsWizardModel model;
  private final IAddEquipmentStatisticsProperties properties;
  private JComponent focusComponent;

  public EquipmentStatisticsChoicePage(
      IWizardConfiguration wizard,
      AddEquipmentStatisticsWizardModel model,
      IAddEquipmentStatisticsProperties properties) {
    super(properties.getTypeChoiceTitle(), new BasicMessage(properties.getTypeChoiceMessage()), wizard);
    this.model = model;
    this.properties = properties;
  }

  @Override
  protected IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  @Override
  protected JComponent createContent() {
    model.getStatisticsTypeModel().addChangeListener(getCheckInputValidListener());
    JPanel content = new JPanel(new GridDialogLayout(3, false));
    addStatisticsTypeRow(content, EquipmentStatisticsType.CloseCombat, new JLabel(properties.getOffensiveLabel()));
    addStatisticsTypeRow(content, EquipmentStatisticsType.RangedCombat, new JLabel());
    content.add(new HorizontalLine(), GridDialogLayoutDataUtilities.createHorizontalSpanData(
        3,
        GridDialogLayoutData.FILL_HORIZONTAL));
    addStatisticsTypeRow(content, EquipmentStatisticsType.Armor, new JLabel(properties.getDefensiveLabel()));
    addStatisticsTypeRow(content, EquipmentStatisticsType.Shield, new JLabel());
    return content;
  }

  private void addStatisticsTypeRow(
      JPanel content,
      EquipmentStatisticsType equipmentStatisticsType,
      JLabel startComponent) {
    content.add(startComponent);
    content.add(createToggleButton(equipmentStatisticsType));
    content.add(new JLabel(properties.getLabel(equipmentStatisticsType)));
  }

  private JToggleButton createToggleButton(final EquipmentStatisticsType type) {
    JToggleButton toggleButton = new JToggleButton(properties.getIcon(type));
    if (focusComponent == null) {
      focusComponent = toggleButton;
    }
    ToolBarUtilities.configureToolBarButton(toggleButton);
    buttonGroup.add(toggleButton);
    toggleButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.getStatisticsTypeModel().setValues(new Object[] { type });
      }
    });
    return toggleButton;
  }

  public boolean canFinish() {
    return false;
  }

  public void requestFocus() {
    focusComponent.requestFocus();
  }

  @Override
  public boolean isComplete() {
    return model.getStatisticsTypeModel().getValues().length > 0;
  }
}