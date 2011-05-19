package net.sf.anathema.character.lunar.beastform.model.gift;

import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationRules;

public class DBTMutationRules implements IMutationRules
{
	private final String LARGE_MUTATION = "Large"; 

	@Override
	public boolean acceptMutation(IMutation mutation)
	{
		if (mutation.getId().equals(LARGE_MUTATION))
			return false;
		if (mutation.getCost() < 0)
			return false;
		return true;
	}

}
