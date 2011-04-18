package net.sf.anathema.character.infernal.template;

import net.sf.anathema.character.generic.impl.magic.charm.special.CharmTier;
import net.sf.anathema.character.generic.impl.magic.charm.special.MultipleEffectCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.StaticMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TieredMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.charm.special.TraitDependentMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.infernal.generic.InfernalFirstExcellency;

public interface IInfernalSpecialCharms
{
	public static final IMultiLearnableCharm MALFEAS_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.Malfeas", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm CECELYNE_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.Cecelyne", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm SWLIHN_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.SheWhoLivesInHerName", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm ADORJAN_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.Adorjan", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm EBON_DRAGON_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.EbonDragon", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm KIMBERY_EXCELLENCY = new InfernalFirstExcellency(
		      "Infernal.1stExcellency.Kimbery", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	
	public static final IMultiLearnableCharm WINDBORN_STRIDE = new TraitDependentMultiLearnableCharm(
		      "Infernal.Wind-BornStride", //$NON-NLS-1$
		      EssenceTemplate.SYSTEM_ESSENCE_MAX,
		      OtherTraitType.Essence);
	public static final IMultiLearnableCharm RUNNING_TO_FOREVER = new StaticMultiLearnableCharm(
		      "Infernal.RunningtoForever", //$NON-NLS-1$
		      2);
	
	public static final IMultipleEffectCharm INTOLERABLE_BURNING_TRUTHS = new MultipleEffectCharm(
			"Infernal.IntolerableBurningTruths", new String[] { "MotherBeforeDaughter", "TrustIsNaive", "ExistenceisAgony", "NeverForgive", "HateSpringsEternal" });
	
	public static final IMultiLearnableCharm VIRIDIAN_LEGEND_EXOSKELETON = new TieredMultiLearnableCharm(
		      "Infernal.ViridianLegendExoskeleton", //$NON-NLS-1$
		      new CharmTier[] { new CharmTier(3), new CharmTier(4) });
	
	
}