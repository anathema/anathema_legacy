package net.sf.anathema.character.generic.framework.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.resources.IResources;

public class MagicDisplayLabeler {
	private IResources resources;
	
	public MagicDisplayLabeler(IResources resources) {
		this.resources = resources;
	}
	
	public String getLabelForMagic(IMagic magic) {
		if (magic instanceof ICharm && ((ICharm)magic).isInstanceOfGenericCharm()) {
			ITraitType charmType = ((ICharm)magic).getPrimaryTraitType();
			String baseCharmId = getGenericCharmBaseId((ICharm) magic);
			return resources.getString(baseCharmId, resources.getString(charmType.getId()));
		}
		return resources.getString(magic.getId());
	}
	
	public String getGenericLabelForMagic(IMagic magic) {
		if (magic instanceof ICharm && ((ICharm)magic).isInstanceOfGenericCharm()) {
			FavoringTraitType charmType = ((ICharm)magic).getCharacterType().getFavoringTraitType();
			String traitString = "(" + resources.getString(charmType.getId()) + ")";
			String baseCharmId = getGenericCharmBaseId((ICharm) magic);
			return resources.getString(baseCharmId, traitString);
		}
		return resources.getString(magic.getId());
	}
	
	public boolean supportsMagic(IMagic magic) {
		if (magic == null) return false;
		if (magic instanceof ICharm && ((ICharm)magic).isInstanceOfGenericCharm()) {
			String baseCharmId = getGenericCharmBaseId((ICharm) magic);
			return resources.supportsKey(baseCharmId);
		}
		return resources.supportsKey(magic.getId());
	}
	
	private String getGenericCharmBaseId(ICharm charm) {
		return charm.getId().substring(0, charm.getId().lastIndexOf('.'));
	}
}
