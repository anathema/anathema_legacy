package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.AbstractEquipmentStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.swing.IWeaponStatisticsView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class StatsNamePresenter {
  private final IWeaponStatisticsView view;
  private final IEquipmentStatisticsModel model;
  private final AbstractEquipmentStatisticsProperties properties;

  public StatsNamePresenter(IWeaponStatisticsView view, IEquipmentStatisticsModel model, AbstractEquipmentStatisticsProperties properties) {
    this.view = view;
    this.model = model;
    this.properties = properties;
  }

  public void initPresentation() {
    ITextualDescription name = model.getName();
    if (name.isEmpty()) {
      name.setText(properties.getDefaultName());
    }
    ITextView textView = view.addLineTextView(properties.getNameLabel());
    new TextualPresentation().initView(textView, name);
  }
}