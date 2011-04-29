package net.sf.anathema.character.impl.model.charm.special;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmLearnListener;
import net.sf.anathema.character.generic.magic.charms.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.special.ITraitCapModifyingCharmConfiguration;
import net.sf.anathema.lib.control.GenericControl;

public class TraitCapModifyingCharmConfiguration implements ITraitCapModifyingCharmConfiguration
{
	private final GenericControl<ISpecialCharmLearnListener> control = new GenericControl<ISpecialCharmLearnListener>();
	private final ICharacterModelContext context;
	private final ITraitCapModifyingCharm specialCharm;
	private final ICharmConfiguration config;
	private final ICharm charm;
	
	public TraitCapModifyingCharmConfiguration(
	      final ICharacterModelContext context,
	      final ICharmConfiguration config,
	      final ICharm charm,
	      ITraitCapModifyingCharm specialCharm)
	{
		this.specialCharm = specialCharm;
		this.context = context;
		this.config = config;
		this.charm = charm;
	}

	@Override
	public void addSpecialCharmLearnListener(ISpecialCharmLearnListener listener) {
		control.addListener(listener);
	}
	
	@Override
	public void learn(boolean experienced) {
		applyModifier();
	}
	
	public void applyModifier()
	{
		DefaultTrait trait = (DefaultTrait) context.getTraitCollection().getTrait(specialCharm.getTraitType());
		trait.applyCapModifier(specialCharm.getModifier());
	}

	@Override
	public void forget()
	{
		DefaultTrait trait = (DefaultTrait) context.getTraitCollection().getTrait(specialCharm.getTraitType());
		trait.applyCapModifier(-specialCharm.getModifier());
	}

	@Override
	public ICharm getCharm() {
		return charm;
	}

	@Override
	public int getCreationLearnCount() {
		return config.isLearned(charm) ? 1 : 0;
	}

	@Override
	public int getCurrentLearnCount() {
		return config.isLearned(charm) ? 1 : 0;
	}

}
