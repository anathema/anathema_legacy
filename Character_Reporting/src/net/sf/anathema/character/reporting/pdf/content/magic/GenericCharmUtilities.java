package net.sf.anathema.character.reporting.pdf.content.magic;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;

public class GenericCharmUtilities
{
	public static boolean shouldShowCharm(final IMagicStats stats, IGenericCharacter character)
	{
		return shouldShowCharm(stats, character, getGenericCharmTraits(character));
	}
	
	public static boolean shouldShowCharm(final IMagicStats stats, IGenericCharacter character, List<ITraitType> traits)
	{
		if (AnathemaCharacterPreferences.getDefaultPreferences().printAllGenerics())
			return true;
		List<IMagic> allLearnedMagic = character.getAllLearnedMagic();
		    
		for (final ITraitType trait : traits)
		{
			final String charmId = stats.getName().getId() + "." + trait.getId();
			boolean isLearned = CollectionUtilities.getFirst(allLearnedMagic, new IPredicate<IMagic>() {
			    public boolean evaluate(IMagic value) {
			      return charmId.equals(value.getId());
			    }
			  }) != null;
			if (isLearned)
				return true;
		}
		return false;
	}
	  
	public static int getDisplayedGenericCharmCount(IGenericCharacter character)
	{
		List<ITraitType> traits = getGenericCharmTraits(character);
		int count = 0;
		
		for (IMagicStats stats : character.getGenericCharmStats())
			if (shouldShowCharm(stats, character, traits))
				count++;
		return count;
	}
	
	public static boolean hasDisplayedGenericCharms(ReportContent content)
	{
		return getDisplayedGenericCharmCount(content.getCharacter()) > 0;
	}
	
	public static List<ITraitType> getGenericCharmTraits(IGenericCharacter character) {
	    FavoringTraitType type = character.getTemplate().getMagicTemplate().getFavoringTraitType();
	    List<ITraitType> traits = new ArrayList<ITraitType>();
	    IIdentifiedTraitTypeGroup[] list = null;
	    if (type == FavoringTraitType.AbilityType) {
	      list = character.getAbilityTypeGroups();
	    }
	    if (type == FavoringTraitType.AttributeType) {
	      list = character.getAttributeTypeGroups();
	    }
	    if (type == FavoringTraitType.YoziType) {
	      list = character.getYoziTypeGroups();
	    }

	    for (ITraitTypeGroup group : list) {
	      for (ITraitType trait : group.getAllGroupTypes()) {
	        traits.add(trait);
	      }
	    }

	    return traits;
	  }
}
