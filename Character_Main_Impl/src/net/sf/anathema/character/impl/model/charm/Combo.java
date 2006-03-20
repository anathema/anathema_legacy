package net.sf.anathema.character.impl.model.charm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboModelListener;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class Combo implements ICombo {
  private final List<ICharm> charmList = new ArrayList<ICharm>();
  private final List<IComboModelListener> comboModelListeners = new ArrayList<IComboModelListener>();
  private ICharm extraActionCharm = null;
  private ICharm simpleCharm = null;
  private final ISimpleTextualDescription name = new SimpleTextualDescription();
  private final ISimpleTextualDescription description = new SimpleTextualDescription();
  private Integer id = null;

  public ICharm[] getCharms() {
    return charmList.toArray(new ICharm[0]);
  }

  public void addCharm(ICharm charm) {
    if (checkRules(charm)) {
      addCharmNoValidate(charm);
    }
    else {
      throw new IllegalArgumentException("The charm " + charm.getId() + " is illegal in this combo."); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  private void addCharmNoValidate(ICharm charm) {
    charmList.add(charm);
    if (charm.getCharmType() == CharmType.Simple) {
      simpleCharm = charm;
    }
    if (charm.getCharmType() == CharmType.ExtraAction) {
      extraActionCharm = charm;
    }
    fireComboChanged();
  }

  public synchronized void addComboModelListener(IComboModelListener listener) {
    comboModelListeners.add(listener);
  }

  private boolean checkRules(ICharm addedCharm) {
    IComboRestrictions addedComboRules = addedCharm.getComboRules();
    if (!addedComboRules.isComboAllowed(addedCharm.getDuration().getType() == DurationType.Instant)) {
      return false;
    }
    if (charmList.contains(addedCharm)) {
      return false;
    }
    for (ICharm charm : charmList) {
      if (addedComboRules.isRestrictedCharm(charm)) {
        return false;
      }
      if (charm.getComboRules().isRestrictedCharm(addedCharm)) {
        return false;
      }
      for (ITraitType restrictedTraitType : addedComboRules.getRestrictedTraitTypes()) {
        for (IGenericTrait prerequisite : charm.getPrerequisites()) {
          if (prerequisite.getType() == restrictedTraitType) {
            return false;
          }
        }
      }
      for (ITraitType restrictedTraitType : charm.getComboRules().getRestrictedTraitTypes()) {
        for (IGenericTrait prerequisite : addedCharm.getPrerequisites()) {
          if (prerequisite.getType() == restrictedTraitType) {
            return false;
          }
        }
      }
    }
    CharmType addedCharmType = addedCharm.getCharmType();
    if (addedCharmType == CharmType.Simple) {
      if (simpleCharm != null) {
        return false;
      }
      if (extraActionCharm != null) {
        if (!combosAbilityAndAttribute(addedCharm, extraActionCharm)
            && !combosAttributes(addedCharm, extraActionCharm)
            && !addedComboRules.combosAllAbilities()
            && !extraActionCharm.getComboRules().combosAllAbilities()
            && ofDifferentPrimaryPrerequisite(addedCharm, extraActionCharm)) {
          return false;
        }
      }
      if (!samePrerequisiteAsSupplementalCharms(addedCharm)) {
        return false;
      }
    }
    if (addedCharmType == CharmType.ExtraAction) {
      if (extraActionCharm != null) {
        return false;
      }
      if (simpleCharm != null) {
        if (!combosAbilityAndAttribute(addedCharm, simpleCharm)
            && !combosAttributes(addedCharm, simpleCharm)
            && !addedComboRules.combosAllAbilities()
            && !simpleCharm.getComboRules().combosAllAbilities()
            && ofDifferentPrimaryPrerequisite(addedCharm, simpleCharm)) {
          return false;
        }
      }
      if (!samePrerequisiteAsSupplementalCharms(addedCharm)) {
        return false;
      }
    }
    if (addedCharmType == CharmType.Supplemental) {
      if (simpleCharm != null) {
        if (!combosAttributes(addedCharm, simpleCharm)
            && !addedComboRules.combosAllAbilities()
            && !simpleCharm.getComboRules().combosAllAbilities()
            && ofDifferentPrimaryPrerequisite(addedCharm, simpleCharm)) {
          return false;
        }
      }
      if (extraActionCharm != null) {
        if (!combosAbilityAndAttribute(addedCharm, extraActionCharm)
            && !combosAttributes(addedCharm, extraActionCharm)
            && !addedComboRules.combosAllAbilities()
            && !extraActionCharm.getComboRules().combosAllAbilities()
            && ofDifferentPrimaryPrerequisite(addedCharm, extraActionCharm)) {
          return false;
        }
      }
      for (ICharm charm : charmList) {
        if (charm.getCharmType() == CharmType.Supplemental
            && !combosAttributes(addedCharm, charm)
            && !addedComboRules.combosAllAbilities()
            && !charm.getComboRules().combosAllAbilities()
            && ofDifferentPrimaryPrerequisite(addedCharm, charm)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean combosAttributes(ICharm addedCharm, ICharm presentCharm) {
    ITraitType addedType = addedCharm.getPrerequisites()[0].getType();
    ITraitType existingType = presentCharm.getPrerequisites()[0].getType();
    return (addedType instanceof AttributeType && existingType instanceof AttributeType);
  }

  private boolean samePrerequisiteAsSupplementalCharms(ICharm addedCharm) {
    for (ICharm charm : charmList) {
      if (charm.getCharmType() == CharmType.Supplemental
          && !combosAttributes(addedCharm, charm)
          && !combosAbilityAndAttribute(addedCharm, charm)
          && !addedCharm.getComboRules().combosAllAbilities()
          && !charm.getComboRules().combosAllAbilities()
          && ofDifferentPrimaryPrerequisite(addedCharm, charm)) {
        return false;
      }
    }
    return true;
  }

  private boolean combosAbilityAndAttribute(ICharm addedCharm, ICharm presentCharm) {
    ITraitType addedType = addedCharm.getPrerequisites()[0].getType();
    ITraitType existingType = presentCharm.getPrerequisites()[0].getType();
    return (addedType instanceof AbilityType && existingType instanceof AttributeType)
        || (addedType instanceof AttributeType && existingType instanceof AbilityType);
  }

  private synchronized void fireComboChanged() {
    List<IComboModelListener> cloneList = new ArrayList<IComboModelListener>(comboModelListeners);
    for (IComboModelListener listener : cloneList) {
      listener.comboChanged();
    }
  }

  public ICharm[] getComboLegalCharms(ICharm[] learnedCharms) {
    List<ICharm> legalCharms = new ArrayList<ICharm>(Arrays.asList(learnedCharms));
    for (ICharm charm : learnedCharms) {
      if (!checkRules(charm)) {
        legalCharms.remove(charm);
      }
    }
    return legalCharms.toArray(new ICharm[0]);
  }

  private boolean ofDifferentPrimaryPrerequisite(ICharm firstCharm, ICharm secondCharm) {
    return secondCharm.getPrerequisites()[0].getType() != firstCharm.getPrerequisites()[0].getType();
  }

  public void removeCharms(ICharm[] charms) {
    List<ICharm> removal = Arrays.asList(charms);
    charmList.removeAll(removal);
    if (simpleCharm != null && removal.contains(simpleCharm)) {
      simpleCharm = null;
    }
    if (extraActionCharm != null && removal.contains(extraActionCharm)) {
      extraActionCharm = null;
    }
    fireComboChanged();
  }

  @Override
  public ICombo clone() {
    Combo clone = new Combo();
    copyCombo(this, clone);
    return clone;
  }

  public void getValuesFrom(ICombo combo) {
    this.clear();
    copyCombo(combo, this);
  }

  private void copyCombo(ICombo source, Combo destination) {
    for (ICharm charm : source.getCharms()) {
      destination.addCharmNoValidate(charm);
    }
    if (source.getId() != null) {
      destination.setId(source.getId());
    }
    destination.name.setText(source.getName().getText());
    destination.description.setText(source.getDescription().getText());
  }

  public void clear() {
    id = null;
    name.setText(""); //$NON-NLS-1$
    description.setText(""); //$NON-NLS-1$
    removeCharms(charmList.toArray(new ICharm[0]));
  }

  public ISimpleTextualDescription getName() {
    return name;
  }

  public ISimpleTextualDescription getDescription() {
    return description;
  }

  public boolean isComboLegal(ICharm charm) {
    return checkRules(charm);
  }

  public boolean contains(ICharm charm) {
    return charmList.contains(charm);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    Ensure.ensureNull("Id already set.", this.id); //$NON-NLS-1$
    Ensure.ensureNotNull("Id must not be null.", id); //$NON-NLS-1$
    setIdNoValidate(id);
  }

  private void setIdNoValidate(Integer id) {
    this.id = id;
  }

}