package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArmourStatisticsProperties;
import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.layout.SwingLayoutUtils;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArmourStatisticsPresenterPage extends AbstractEquipmentStatisticsPresenterPage<IArmourStatisticsModel, ArmourStatisticsProperties> {
  public ArmourStatisticsPresenterPage(Resources resources, IEquipmentStatisticsCreationModel model,
                                       IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, new ArmourStatisticsProperties(resources), model, model.getArmourStatisticsModel(), viewFactory);
  }

  @Override
  protected void addAdditionalContent() {
    addLabelledComponentRow(
            new String[]{getProperties().getBashingSoakLabel(), getProperties().getBashingHardnessLabel()},
            new Component[]{initIntegerSpinner(getPageModel().getBashingSoakModel()).getComponent(), initIntegerSpinner(
                    getPageModel().getBashingHardnessModel()).getComponent()});
    final IIntValueModel lethalSoakModel = getPageModel().getLethalSoakModel();
    addLabelledComponentRow(
            new String[]{getProperties().getLethalSoakLabel(), getProperties().getLethalHardnessLabel()},
            new Component[]{initIntegerSpinner(lethalSoakModel).getComponent(), initIntegerSpinner(
                    getPageModel().getLethalHardnessModel()).getComponent()});
    final IconToggleButton linkToggleButton = new IconToggleButton(new ImageProvider().getImageIcon(new CharacterUI().getLinkIconPath()));
    final IIntValueModel aggravatedSoakModel = getPageModel().getSoakModel(HealthType.Aggravated);
    final JComponent aggravatedSoakSpinner = initIntegerSpinner(aggravatedSoakModel).getComponent();
    getPageContent().addView(new AdditiveView() {
      @Override
      public void addTo(JPanel panel, CC data) {
        panel.add(new JLabel(getProperties().getAggravatedSoakLabel()));
        panel.add(aggravatedSoakSpinner, new CC().growX());
        JComponent button = linkToggleButton.getComponent();
        panel.add(button, SwingLayoutUtils.constraintsForImageButton(button).split(2).spanX());
        panel.add(new JLabel(getProperties().getLinkSoakLabel()));
      }
    }, new CC());
    addLabelledComponentRow(new String[]{getProperties().getMobilityPenaltyLabel(), getProperties().getFatigueLabel()},
            new Component[]{initIntegerSpinner(
                    getPageModel().getMobilityPenaltyModel()).getComponent(), initIntegerSpinner(
                    getPageModel().getFatigueModel()).getComponent()});
    linkToggleButton.addActionListener(new ActionListener() {
      @Override
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
    lethalSoakModel.addIntValueChangeListener(new IntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        if (linkToggleButton.isSelected()) {
          aggravatedSoakModel.setValue(lethalSoakModel.getValue());
        }
      }
    });
  }

}