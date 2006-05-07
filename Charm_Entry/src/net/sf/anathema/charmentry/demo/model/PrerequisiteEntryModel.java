package net.sf.anathema.charmentry.demo.model;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmentry.demo.IPrerequisitesModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class PrerequisiteEntryModel implements IPrerequisitesModel {

  private final IConfigurableCharmData charmData;
  private final ChangeControl control = new ChangeControl();

  public PrerequisiteEntryModel(IConfigurableCharmData charmData) {
    this.charmData = charmData;
  }

  public void addModelListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public void setEssenceMinimum(int minimum) {
    charmData.setEssencePrerequisite(new ValuedTraitType(OtherTraitType.Essence, minimum));
    control.fireChangedEvent();
  }

  public int getEssenceMinimum() {
    return charmData.getEssence().getCurrentValue();
  }

  public ITraitType[] getPrimaryPrerequisiteTypes() {
    if (charmData.getCharacterType() == CharacterType.LUNAR) {
      return AttributeType.values();
    }
    return AbilityType.getAbilityTypes(charmData.getEdition());
  }

  public IGenericTrait getPrimaryPrerequisite() {
    return charmData.getPrimaryPrerequiste();
  }

  public void setPrimaryPrerequisite(IGenericTrait trait) {
    charmData.setPrimaryPrerequisite(trait);
  }
}