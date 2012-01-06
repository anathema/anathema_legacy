package net.sf.anathema.character.mutations.persistence;

import net.sf.anathema.character.library.quality.model.QualitySelection;
import net.sf.anathema.character.library.quality.persistence.AbstractQualityPersister;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_CREATION_ACTIVE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_EXPERIENCE_ACTIVE;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_ID;
import static net.sf.anathema.character.library.quality.persistence.IQualityPersistenceXmlConstants.ATTRIB_VALUE;

public class MutationPersister extends AbstractQualityPersister<IMutation> {
  private static final String TAG_MUTATIONS = "Mutations"; //$NON-NLS-1$
  private static final String TAG_MUTATION = "Mutation"; //$NON-NLS-1$
  private static final String TAG_GIFTS = "Gifts"; //$NON-NLS-1$
  private static final String TAG_GIFT = "Gift"; //$NON-NLS-1$

  public void load(Element parent, IQualityModel<IMutation> model) throws PersistenceException {
    Element qualityGroupElement = parent.element(TAG_MUTATIONS);
    if (qualityGroupElement != null) {
      for (Object element : qualityGroupElement.elements(TAG_MUTATION)) {
        loadMutation(model, (Element) element);
      }
    }
    qualityGroupElement = parent.element(TAG_GIFTS);
    if (qualityGroupElement != null) {
      for (Object element : qualityGroupElement.elements(TAG_GIFT)) {
        loadMutation(model, (Element) element);
      }
    }
  }

  private void loadMutation(IQualityModel<IMutation> model, Element element) throws PersistenceException {
    String giftId = element.attributeValue(ATTRIB_ID);
    //backward compatability
    giftId = giftId.replace("DeadlyBeastmanTransformation.Gift", "Mutations.Mutation");
    IMutationsModel mutationModel = (IMutationsModel) model;
    final int value = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_VALUE);
    final boolean creationActive = ElementUtilities.getBooleanAttribute(element, ATTRIB_CREATION_ACTIVE, true);
    final boolean experienceActive = ElementUtilities.getBooleanAttribute(element, ATTRIB_EXPERIENCE_ACTIVE, false);
    model.addQualitySelection(new QualitySelection<IMutation>(mutationModel.getMutationById(giftId), value, creationActive, experienceActive));
  }

  @Override
  protected String getGroupElementName() {
    return TAG_MUTATIONS;
  }

  @Override
  protected String getSingleElementName() {
    return TAG_MUTATION;
  }

  @Override
  protected void saveSpecials(Element element, IMutation quality) {
    // nothing to do
  }
}
