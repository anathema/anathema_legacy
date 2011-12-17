package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArmourStatisticsProperties;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.framework.value.IconToggleButton;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public class ArmourStatisticsPresenterPage extends
    AbstractEquipmentStatisticsPresenterPage<IArmourStatisticsModel, ArmourStatisticsProperties> {

  public ArmourStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, new ArmourStatisticsProperties(resources), model, model.getArmourStatisticsModel(), viewFactory);
  }

  @Override
  protected void addAdditionalContent() {

    addLabelledComponentRow(new String[] {
        getProperties().getBashingSoakLabel(),
        getProperties().getBashingHardnessLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getBashingSoakModel()).getComponent(),
        initIntegerSpinner(getPageModel().getBashingHardnessModel()).getComponent() });
    final IIntValueModel lethalSoakModel = getPageModel().getLethalSoakModel();
    addLabelledComponentRow(new String[] {
        getProperties().getLethalSoakLabel(),
        getProperties().getLethalHardnessLabel() }, new Component[] {
        initIntegerSpinner(lethalSoakModel).getComponent(),
        initIntegerSpinner(getPageModel().getLethalHardnessModel()).getComponent() });
    final IconToggleButton linkToggleButton = new IconToggleButton(new CharacterUI(getResources()).getLinkIcon());
    final IIntValueModel aggravatedSoakModel = getPageModel().getSoakModel(HealthType.Aggravated);
    final JComponent aggravatedSoakSpinner = initIntegerSpinner(aggravatedSoakModel).getComponent();
    getPageContent().addDialogComponent(new IDialogComponent() {
      public void fillInto(JPanel panel, int columnCount) {
        panel.add(new JLabel(getProperties().getAggravatedSoakLabel()));
        panel.add(aggravatedSoakSpinner, GridDialogLayoutData.FILL_HORIZONTAL);
        JPanel internalPanel = new JPanel(new GridDialogLayout(2, false));
        internalPanel.add(linkToggleButton.getComponent());
        internalPanel.add(new JLabel(getProperties().getLinkSoakLabel()));
        panel.add(internalPanel, GridDialogLayoutDataFactory.createHorizontalSpanData(columnCount - 2));
      }

      public int getColumnCount() {
        return 4;
      }
    });
    addLabelledComponentRow(
        new String[] { getProperties().getMobilityPenaltyLabel(), getProperties().getFatigueLabel() },
        new Component[] {
            initIntegerSpinner(getPageModel().getMobilityPenaltyModel()).getComponent(),
            initIntegerSpinner(getPageModel().getFatigueModel()).getComponent() });
    linkToggleButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        boolean isLinkToggled = !linkToggleButton.isSelected();
        if (isLinkToggled) {
          aggravatedSoakModel.setValue(lethalSoakModel.getValue());
        }
        aggravatedSoakSpinner.setEnabled(!isLinkToggled);
        linkToggleButton.setSelected(isLinkToggled);
      }
    });
    boolean linked = lethalSoakModel.getValue() == aggravatedSoakModel.getValue();
    linkToggleButton.setSelected(linked);
    aggravatedSoakSpinner.setEnabled(!linked);
    lethalSoakModel.addIntValueChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        if (linkToggleButton.isSelected()) {
          aggravatedSoakModel.setValue(lethalSoakModel.getValue());
        }
      }
    });
  }

  @Override
  protected boolean isTagsSupported() {
    return false;
  }
}