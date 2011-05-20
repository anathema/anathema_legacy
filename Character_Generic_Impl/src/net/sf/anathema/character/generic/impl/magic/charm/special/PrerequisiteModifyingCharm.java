package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.traits.ITraitType;

public class PrerequisiteModifyingCharm implements IPrerequisiteModifyingCharm
{
	private final String charmId;
	private final ITraitType traitType;
	private final int modifier;
	
	public PrerequisiteModifyingCharm(
		      String charmId,
		      ITraitType traitType,
		      int modifier) {
		    this.traitType = traitType;
		    this.modifier = modifier;
		    this.charmId = charmId;
		  }
	@Override
	public int getTraitModifier(ICharm charm, ITraitType trait, int value)
	{
		//assuming modification of all traits in applicable
		//charms by the same value. Can create a more elaborate
		//constructor in the future if necessary.
		if (value == 10)
			return value;
		try
		{
		if (charm.getPrimaryTraitType() == traitType)
			return value + modifier;
		}
		catch (Exception e) { }
		return value;
	}

	@Override
	public void accept(ISpecialCharmVisitor visitor)
	{
		visitor.visitPrerequisiteModifyingCharm(this);
	}

	@Override
	public String getCharmId()
	{
		return charmId;
	}

}
