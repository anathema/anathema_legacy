package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICostEntryModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CostEntryModel implements ICostEntryModel {

  private static final String BLANK = ""; //$NON-NLS-1$
  private final IConfigurableCharmData charmData;

  public CostEntryModel(ICharmTypeEntryModel model, final IConfigurableCharmData charmData) {
    this.charmData = charmData;
    model.addModelListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        if (charmData.getCharmTypeModel().getCharmType() == CharmType.Permanent) {
          setEssenceCostValue(BLANK);
          setEssenceCostText(BLANK);
          setWillpowerCostValue(BLANK);
          setWillpowerCostText(BLANK);
          setHealthCostValue(BLANK);
          setHealthCostText(BLANK);
          setXpCostValue(BLANK);
          setXpCostText(BLANK);
        }
      }
    });
  }

  @Override
  public void setEssenceCostValue(String newValue) {
    charmData.getTemporaryCost().getEssenceCost().setValue(newValue);
  }

  @Override
  public void setEssenceCostText(String newValue) {
    charmData.getTemporaryCost().getEssenceCost().setText(newValue);
  }

  @Override
  public void setWillpowerCostValue(String newValue) {
    charmData.getTemporaryCost().getWillpowerCost().setValue(newValue);
  }

  @Override
  public void setWillpowerCostText(String newValue) {
    charmData.getTemporaryCost().getWillpowerCost().setText(newValue);
  }

  @Override
  public void setHealthCostValue(String newValue) {
    charmData.getTemporaryCost().getHealthCost().setValue(newValue);
  }

  @Override
  public void setHealthCostText(String newValue) {
    charmData.getTemporaryCost().getHealthCost().setText(newValue);
  }

  @Override
  public void setXpCostValue(String newValue) {
    charmData.getTemporaryCost().getXPCost().setValue(newValue);
  }

  @Override
  public void setXpCostText(String newValue) {
    charmData.getTemporaryCost().getXPCost().setText(newValue);
  }
}