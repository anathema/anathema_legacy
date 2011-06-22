package net.sf.anathema.character.thaumaturgy.persistence;

import org.dom4j.Element;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyAdditionalModel;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyMagic;
import net.sf.anathema.character.thaumaturgy.model.IThaumaturgyModel;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyMagicType;
import net.sf.anathema.lib.exception.PersistenceException;

public class ThaumaturgyModelPersister implements IAdditionalPersister
{
	private final String TAG_ART = "Art";
	private final String TAG_PROCEDURE = "Procedure";
	private final String TAG_RANK = "Rank";
	private final String TAG_ART_NAME = "ArtName";
	private final String TAG_PROCEDURE_NAME = "ProcedureName";
	
	TraitPersister persister = new TraitPersister();
	
	@Override
	public void load(Element parent, IAdditionalModel model)
			throws PersistenceException {
		IThaumaturgyModel realModel = ((IThaumaturgyAdditionalModel) model).getThaumaturgyModel();
		realModel.setCurrentType(ThaumaturgyMagicType.Degree);
		for (Object element : parent.elements(TAG_ART))
		{
			Element artElement = (Element)element;
			Element artNameElement = artElement.element(TAG_ART_NAME);
			String artName = artNameElement.getText();
			realModel.setCurrentArt(artName);
			IThaumaturgyMagic magic = realModel.commitSelection();
			persister.restoreTrait(artElement, TAG_RANK, magic);
		}
		realModel.setCurrentType(ThaumaturgyMagicType.Procedure);
		for (Object element : parent.elements(TAG_PROCEDURE))
		{
			Element procedureElement = (Element)element;
			Element artNameElement = procedureElement.element(TAG_ART_NAME);
			Element procedureNameElement = procedureElement.element(TAG_PROCEDURE_NAME);
			String artName = artNameElement.getText();
			String procedureName = procedureNameElement.getText();
			realModel.setCurrentArt(artName);
			realModel.setCurrentProcedure(procedureName);
			IThaumaturgyMagic magic = realModel.commitSelection();
			persister.restoreTrait(procedureElement, TAG_RANK, magic);
		}
	}

	@Override
	public void save(Element parent, IAdditionalModel model) {
		IThaumaturgyModel realModel = ((IThaumaturgyAdditionalModel) model).getThaumaturgyModel();
		for (IThaumaturgyMagic degree : realModel.getLearnedDegrees())
		{
			Element artElement = parent.addElement(TAG_ART);
			Element artNameElement = artElement.addElement(TAG_ART_NAME);
			artNameElement.addCDATA(degree.getArt());
			persister.saveTrait(artElement, TAG_RANK, degree);
		}
		for (IThaumaturgyMagic procedure : realModel.getLearnedProcedures())
		{
			Element procedureElement = parent.addElement(TAG_PROCEDURE);
			Element artNameElement = procedureElement.addElement(TAG_ART_NAME);
			Element procedureNameElement = procedureElement.addElement(TAG_PROCEDURE_NAME);
			artNameElement.addCDATA(procedure.getArt());
			procedureNameElement.addCDATA(procedure.getProcedure());
			persister.saveTrait(procedureElement, TAG_RANK, procedure);
		}		
	}

}
