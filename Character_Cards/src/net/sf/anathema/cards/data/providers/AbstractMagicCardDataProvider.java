package net.sf.anathema.cards.data.providers;

import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.description.MagicDescription;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractMagicCardDataProvider implements ICardDataProvider {
	
	private final IAnathemaModel model;
	private final IResources resources;
	
	protected AbstractMagicCardDataProvider(IAnathemaModel model, IResources resources) {
		this.model = model;
		this.resources = resources;
	}
	
	protected IResources getResources() {
		return resources;
	}
	
	protected MagicDescription getMagicDescription(IMagic magic) {
	    return CharmDescriptionProviderExtractor.CreateFor(model, resources).getCharmDescription(magic);
	}
}
