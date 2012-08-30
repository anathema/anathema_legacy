package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.ResourceFile;

public abstract class CharacterTypeModule extends NullObjectCharacterModuleAdapter {

	@Override
	public void addCharacterTemplates(ICharacterTemplateResourceProvider provider, ICharacterGenerics characterGenerics) {
		for (ResourceFile templateResource : provider.getTemplateResourcesForType(getType().getId())) {
			registerParsedTemplate(characterGenerics, templateResource); //$NON-NLS-1$
		}
	}	
	
	protected abstract ICharacterType getType();
}
