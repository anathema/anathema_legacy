package net.sf.anathema.character.impl.persistence;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AdditionalModelPersister {

  private static final String TAG_MODEL = "Model"; //$NON-NLS-1$
  private static final String ATTRIB_TEMPLATE_ID = "templateId"; //$NON-NLS-1$
  private static final String TAG_ADDITIONAL_MODELS = "AdditionalModels"; //$NON-NLS-1$
  private static final String TAG_CONTENT = "Content"; //$NON-NLS-1$

  private final IRegistry<String, IAdditionalPersisterFactory> registry;
  private final IAnathemaMessaging messaging;

  public AdditionalModelPersister(IRegistry<String, IAdditionalPersisterFactory> registry, IAnathemaMessaging messaging) {
    this.registry = registry;
    this.messaging = messaging;
  }

  public void load(Element parent, IAdditionalModel[] additionalModels) throws PersistenceException {
    Element overallElement = parent.element(TAG_ADDITIONAL_MODELS);
    if (overallElement == null) {
      return;
    }
    for (Element modelElement : ElementUtilities.elements(overallElement, TAG_MODEL)) {
      String templateId = modelElement.attributeValue(ATTRIB_TEMPLATE_ID);
      IAdditionalModel model = findModel(additionalModels, templateId);
      if (model == null) {
        throw new PersistenceException("No model for template id " + templateId); //$NON-NLS-1$
      }
      IAdditionalPersisterFactory factory = registry.get(templateId);
      factory.createPersister(messaging).load(modelElement.element(TAG_CONTENT), model);
    }
  }

  private IAdditionalModel findModel(IAdditionalModel[] additionalModels, final String templateId) {
    return ArrayUtilities.find(new IPredicate<IAdditionalModel>() {
      public boolean evaluate(IAdditionalModel input) {
        return input.getTemplateId().equals(templateId);
      }
    }, additionalModels);
  }

  private Element createModelContentElement(Element overallElement, IAdditionalModel model) {
    Element modelElement = overallElement.addElement(TAG_MODEL);
    modelElement.addAttribute(ATTRIB_TEMPLATE_ID, model.getTemplateId());
    return modelElement.addElement(TAG_CONTENT);
  }

  public void save(Element parent, IAdditionalModel[] additionalModels) {
    Element overallElement = parent.addElement(TAG_ADDITIONAL_MODELS);
    for (IAdditionalModel model : additionalModels) {
      Element contentElement = createModelContentElement(overallElement, model);
      IAdditionalPersisterFactory factory = registry.get(model.getTemplateId());
      factory.createPersister(messaging).save(contentElement, model);
    }
  }
}