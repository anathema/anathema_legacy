package net.sf.anathema.character.mutations.persistence;

import org.dom4j.Element;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.mutations.model.MutationsAdditionalModel;
import net.sf.anathema.lib.exception.PersistenceException;

public class MutationModelPersister implements IAdditionalPersister
{
	MutationPersister persister = new MutationPersister();

	@Override
	public void load(Element parent, IAdditionalModel model)
			throws PersistenceException {
		persister.load(parent, ((MutationsAdditionalModel)model).getModel());
	}

	@Override
	public void save(Element parent, IAdditionalModel model) {
		persister.save(parent, ((MutationsAdditionalModel)model).getModel());
	}

}
