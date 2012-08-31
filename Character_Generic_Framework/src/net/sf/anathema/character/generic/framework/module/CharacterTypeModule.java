package net.sf.anathema.character.generic.framework.module;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.ResourceFile;

public abstract class CharacterTypeModule extends NullObjectCharacterModuleAdapter {

	@Override
	public void addCharacterTemplates(ICharacterTemplateResourceProvider provider, ICharacterGenerics characterGenerics) {
		for (ResourceFile templateResource : provider.getTemplateResourcesForType(getType().getId())) {
			GenericCharacterTemplate template = registerParsedTemplate(characterGenerics, templateResource);
			if (template != null) {
				template.setCustomTemplate(provider.isCustomTemplate(templateResource));
			}
		}
	}
	
	protected ITemplateType[] getDefaultAndCustomTemplates(ICharacterGenerics generics) {
		List<ITemplateType> types = new ArrayList<ITemplateType>();
		ICharacterTemplate defaultTemplate = generics.getTemplateRegistry().getDefaultTemplate(getType());
		types.add(defaultTemplate.getTemplateType());
		ICharacterExternalsTemplate[] templatesForType = generics.getTemplateRegistry().getAllSupportedTemplates(getType());
		for (ICharacterExternalsTemplate template : templatesForType) {
			if (generics.getTemplateRegistry().getTemplate(template).isCustomTemplate()) {
				types.add(template.getTemplateType());
			}
		}
		return types.toArray(new ITemplateType[0]);
	}
	
	protected abstract ICharacterType getType();
}
