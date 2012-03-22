package net.sf.anathema.character.lunar.beastform.model;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractCombatStats;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.MutationVisitorAdapter;
import net.sf.anathema.character.mutations.model.types.SoakProvidingMutation;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.List;

public class BeastformNaturalSoak extends AbstractCombatStats implements IArmourStats {
  private final IQualityModel<?> model;
  private final IGenericTraitCollection collection;

  public BeastformNaturalSoak(IGenericTraitCollection traitCollection, IQualityModel<?> model) {
    this.collection = traitCollection;
    this.model = model;
  }

  @Override
  public Integer getFatigue() {
    return null;
  }

  @Override
  public Integer getMobilityPenalty() {
    return null;
  }

  private int getStaminaValue() {
    return collection.getTrait(AttributeType.Stamina).getCurrentValue();
  }

  private int getNaturalSoak(HealthType healthType) {
    switch (healthType) {
      case Aggravated: {
        return 0;
      }
      case Lethal: {
        return getStaminaValue() / 2;
      }
      case Bashing: {
        return getStaminaValue();
      }
      default: {
        throw new UnreachableCodeReachedException("Illegal Health Type"); //$NON-NLS-1$
      }
    }
  }

  private int getUncappedSoak(HealthType type) {
    Ensure.ensureTrue("Aggravated Soak not supported", type != HealthType.Aggravated); //$NON-NLS-1$
    int staminaValue = getStaminaValue();
    return doMutations(type, staminaValue);
  }

  @SuppressWarnings("unchecked")
  private int doMutations(HealthType type, int staminaValue) {
    IQualityModel<IMutation> mutationModel = (IQualityModel<IMutation>) this.model;
    final List<SoakProvidingMutation> mutationList = new ArrayList<SoakProvidingMutation>();
    for (IQualitySelection<IMutation> selection : mutationModel.getSelectedQualities()) {
      selection.getQuality().accept(new MutationVisitorAdapter() {
        @Override
        public void acceptSoakProvidingMutation(SoakProvidingMutation mutation) {
          mutation.adjustActiveMutationList(mutationList);
        }
      });
    }
    if (mutationList.size() == 0) {
      return getNaturalSoak(type);
    }
    float soakStaminaModifier = 0;
    for (SoakProvidingMutation mutation : mutationList) {
      float currentStaminaModifier = mutation.getSoakStaminaModifier(type);
      soakStaminaModifier = Math.max(soakStaminaModifier, currentStaminaModifier);
    }
    int soakValue = (int) Math.floor(staminaValue * soakStaminaModifier);
    for (SoakProvidingMutation mutation : mutationList) {
      soakValue += mutation.getBonus();
    }
    return soakValue;
  }

  @Override
  public Integer getSoak(HealthType type) {
    switch (type) {
      case Aggravated: {
        return null;
      }
      default: {
        return Math.min(getUncappedSoak(type), 12);
      }
    }
  }

  @Override
  public Integer getHardness(HealthType type) {
    switch (type) {
      case Aggravated: {
        return null;
      }
      default: {
        int uncappedHardness = Math.max(getUncappedSoak(type) - 12, 0);
        return Math.min(uncappedHardness, 12);
      }
    }
  }

  @Override
  public IIdentificate getName() {
    return new Identificate("NaturalSoak"); //$NON-NLS-1$
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}