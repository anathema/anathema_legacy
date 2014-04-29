package net.sf.anathema.character.equipment.creation.presenter;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArmourStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.swing.IconToggleButton;
import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.layout.SwingLayoutUtils;
import net.sf.anathema.lib.gui.widgets.IIntegerSpinner;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArmourStatisticsPresenter {
  private final EquipmentStatsView view;
  private final ArmourStatisticsProperties properties;
  private final IArmourStatisticsModel armourModel;


  public ArmourStatisticsPresenter(IArmourStatisticsModel armourModel, EquipmentStatsView view, Resources resources) {
    this.view = view;
    this.properties = new ArmourStatisticsProperties(resources);
    this.armourModel = armourModel;
  }

  public void initPresentation() {
    addSpinner(properties.getBashingSoakLabel(), armourModel.getBashingSoakModel());
    addSpinner(properties.getBashingHardnessLabel(), armourModel.getBashingHardnessModel());

    IIntValueModel lethalSoakModel = armourModel.getLethalSoakModel();
    addSpinner(properties.getLethalSoakLabel(), lethalSoakModel);
    addSpinner(properties.getLethalHardnessLabel(), armourModel.getLethalHardnessModel());

    final IconToggleButton linkToggleButton = new IconToggleButton(
            new ImageProvider().getImageIcon(new CharacterUI().getLinkIconPath()));
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

    addSpinner(properties.getMobilityPenaltyLabel(), armourModel.getMobilityPenaltyModel());
    addSpinner(properties.getFatigueLabel(), armourModel.getFatigueModel());

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

  private void addSpinner(String label, IIntValueModel model) {
    IIntegerSpinner spinner = view.addIntegerSpinner(label, model.getValue());
    new IntValuePresentation().initPresentation(spinner, model);
  }
}