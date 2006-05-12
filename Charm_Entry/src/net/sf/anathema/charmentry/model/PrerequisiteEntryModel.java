package net.sf.anathema.charmentry.model;

import net.disy.commons.core.util.ISimpleBlock;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.IPrerequisitesModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;

public class PrerequisiteEntryModel implements IPrerequisitesModel {

  private final IConfigurableCharmData charmData;
  private final ChangeControl control = new ChangeControl();

  public PrerequisiteEntryModel(IHeaderDataModel headerModel, IConfigurableCharmData charmData) {
    this.charmData = charmData;
    headerModel.addModelListener(new CheckInputListener(new ISimpleBlock() {
      public void execute() {
        control.fireChangedEvent();
      }
    }));
  }

  public void addModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public void setEssenceMinimum(int minimum) {
    if (charmData.getEssence().getCurrentValue() == minimum) {
      return;
    }
    charmData.setEssencePrerequisite(new ValuedTraitType(OtherTraitType.Essence, minimum));
    control.fireChangedEvent();
  }

  public int getEssenceMinimum() {
    return charmData.getEssence().getCurrentValue();
  }

  public ITraitType[] getPrimaryPrerequisiteTypes() {
    if (charmData.getEdition() == null) {
      return new ITraitType[0];
    }
    if (charmData.getCharacterType() == CharacterType.LUNAR) {
      return AttributeType.values();
    }
    return AbilityType.getAbilityTypes(charmData.getEdition());
  }

  public IGenericTrait getPrimaryPrerequisite() {
    return charmData.getPrimaryPrerequiste();
  }

  public void setPrimaryPrerequisite(ITraitType type, int value) {
    if (type == null) {
      return;
    }
    if (getPrimaryPrerequisite() != null
        && type == getPrimaryPrerequisite().getType()
        && value == getPrimaryPrerequisite().getCurrentValue()) {
      return;
    }
    charmData.setPrimaryPrerequisite(new ValuedTraitType(type, value));
    charmData.setGroupId(type.getId());
    control.fireChangedEvent();
  }

  public boolean isPermanentCharm() {
    return charmData.getCharmTypeModel().getCharmType() == CharmType.Permanent;
  }

  public IExaltedEdition getEdition() {
    return charmData.getEdition();
  }
}