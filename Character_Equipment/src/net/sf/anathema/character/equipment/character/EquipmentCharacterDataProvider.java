package net.sf.anathema.character.equipment.character;

import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.FullyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.Unattuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.UnharmoniouslyAttuned;
import static net.sf.anathema.character.generic.type.CharacterType.INFERNAL;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ISpecialtyListChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class EquipmentCharacterDataProvider implements IEquipmentCharacterDataProvider, IEquipmentCharacterOptionProvider {
	
	private final ICharacterModelContext context;
	private final IEquipmentCharacterOptionProvider optionProvider;
	
	public EquipmentCharacterDataProvider(ICharacterModelContext context,
			IEquipmentCharacterOptionProvider optionProvider) {
		this.context = context;
		this.optionProvider = optionProvider;
	}
	
	public INamedGenericTrait[] getSpecialties(ITraitType trait)
	{
		return context.getSpecialtyContext().getSpecialties(trait);
	}

	@Override
	public IEquipmentStatsOption getCharacterSpecialtyOption(String name,
			String type) {
		ITraitType trait = AbilityType.valueOf(type);
		for (INamedGenericTrait specialty : context.getSpecialtyContext().getSpecialties(trait))
			if (specialty.getName().equals(name))
				return new EquipmentSpecialtyOption(specialty, trait);
		return null;
	}

	@Override
	public void enableStatOption(IEquipmentItem item, IEquipmentStats stats,
			IEquipmentStatsOption option) {
		optionProvider.enableStatOption(item, stats, option);
	}

	@Override
	public void disableStatOption(IEquipmentItem item, IEquipmentStats stats,
			IEquipmentStatsOption option) {
		optionProvider.disableStatOption(item, stats, option);
	}

	@Override
	public boolean isStatOptionEnabled(IEquipmentItem item,
			IEquipmentStats stats, IEquipmentStatsOption option) {
		return optionProvider.isStatOptionEnabled(item, stats, option);
	}

	@Override
	public IEquipmentStatsOption[] getEnabledStatOptions(IEquipmentItem item,
			IEquipmentStats stats) {
		return optionProvider.getEnabledStatOptions(item, stats);
	}

	public ArtifactAttuneType[] getAttuneTypes(IEquipmentItem item) {
	    MagicalMaterial material = item.getMaterial();
	    switch (item.getMaterialComposition()) {
		    default:
		    case None:
		        return null;
		    case Fixed:
		    case Variable:
		        return MagicalMaterial.getAttunementTypes(context.getBasicCharacterContext().getCharacterType(), material);
		    case Compound:
		        return new ArtifactAttuneType[]
		                {Unattuned, ArtifactAttuneType.FullyAttuned};
		    case MalfeanMaterials:
		        return createMalfeanMaterialsAttunementOptions();
	    }
	}

  private ArtifactAttuneType[] createMalfeanMaterialsAttunementOptions() {
    if (context.getBasicCharacterContext().getCharacterType() != INFERNAL) {
      return new ArtifactAttuneType[]{Unattuned, UnharmoniouslyAttuned};
    }
    return new ArtifactAttuneType[]{Unattuned, FullyAttuned};
  }

	@Override
	public IEquipmentStatsOption[] getEnabledStatOptions(IEquipmentStats stats) {
		return optionProvider.getEnabledStatOptions(stats);
	}

	@Override
	public void addCharacterSpecialtyListChangeListener(
			ISpecialtyListChangeListener listener) {
		context.getSpecialtyContext().addSpecialtyListChangeListener(listener);
	}
}
