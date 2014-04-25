package net.sf.anathema.character.equipment.creation.presenter;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArmourStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.swing.IconToggleButton;
import net.sf.anathema.character.main.CharacterUI;
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

public class ArmourStatisticsPresenter {
  private final EquipmentStatsView view;
  private final ArmourStatisticsProperties properties;
  private final IArmourStatisticsModel armourModel;


  public ArmourStatisticsPresenter(IArmourStatisticsModel armourModel, EquipmentStatsView view, ArmourStatisticsProperties properties) {
    this.view = view;
    this.properties = properties;
    this.armourModel = armourModel;
  }

  public void initPresentation() {
    view.addLabelledComponentRow(
            new String[]{properties.getBashingSoakLabel(), properties.getBashingHardnessLabel()},
            new Component[]{view.initIntegerSpinner(armourModel.getBashingSoakModel()).getComponent(), view.initIntegerSpinner(
                    armourModel.getBashingHardnessModel()).getComponent()}
    );
    final IIntValueModel lethalSoakModel = armourModel.getLethalSoakModel();
    view.addLabelledComponentRow(
            new String[]{properties.getLethalSoakLabel(), properties.getLethalHardnessLabel()},
            new Component[]{view.initIntegerSpinner(lethalSoakModel).getComponent(), view.initIntegerSpinner(
                    armourModel.getLethalHardnessModel()).getComponent()}
    );
    final IconToggleButton linkToggleButton = new IconToggleButton(new ImageProvider().getImageIcon(new CharacterUI().getLinkIconPath()));
    final IIntValueModel aggravatedSoakModel = armourModel.getSoakModel(HealthType.Aggravated);
    final JComponent aggravatedSoakSpinner = view.initIntegerSpinner(aggravatedSoakModel).getComponent();
    view.addView(new AdditiveView() {
      @Override
      public void addTo(JPanel panel) {
        panel.add(new JLabel(properties.getAggravatedSoakLabel()));
        panel.add(aggravatedSoakSpinner, new CC().growX());
        JComponent button = linkToggleButton.getComponent();
        panel.add(button, SwingLayoutUtils.constraintsForImageButton(button).split(2).spanX());
        panel.add(new JLabel(properties.getLinkSoakLabel()));
      }
    });
    view.addLabelledComponentRow(new String[]{properties.getMobilityPenaltyLabel(), properties.getFatigueLabel()},
            new Component[]{view.initIntegerSpinner(
                    armourModel.getMobilityPenaltyModel()).getComponent(), view.initIntegerSpinner(
                    armourModel.getFatigueModel()).getComponent()}
    );
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
