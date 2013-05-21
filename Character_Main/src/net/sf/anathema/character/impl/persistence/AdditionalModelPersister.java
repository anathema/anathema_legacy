package net.sf.anathema.character.impl.persistence;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.Arrays;
import java.util.Comparator;

public class AdditionalModelPersister {

  private static final String TAG_MODEL = "Model";
  private static final String ATTRIB_TEMPLATE_ID = "templateId";
  private static final String TAG_ADDITIONAL_MODELS = "AdditionalModels";
  private static final String TAG_CONTENT = "Content";

  private final IRegistry<String, IAdditionalPersisterFactory> registry;
  private final IMessaging messaging;

  public AdditionalModelPersister(IRegistry<String, IAdditionalPersisterFactory> registry, IMessaging messaging) {
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
        throw new PersistenceException("No model for template id " + templateId);
      }
      IAdditionalPersisterFactory factory = registry.get(templateId);
      factory.createPersister(messaging).load(modelElement.element(TAG_CONTENT), model);
    }
  }

  private IAdditionalModel findModel(IAdditionalModel[] additionalModels, final String templateId) {
    return ArrayUtilities.find(new Predicate<IAdditionalModel>() {
      @Override
      public boolean apply(IAdditionalModel input) {
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
    Arrays.sort(additionalModels, new Comparator<IAdditionalModel>() {
      @Override
      public int compare(IAdditionalModel o1, IAdditionalModel o2) {
        return o1.getTemplateId().compareTo(o2.getTemplateId());
      }
    });
    for (IAdditionalModel model : additionalModels) {
      Element contentElement = createModelContentElement(overallElement, model);
      IAdditionalPersisterFactory factory = registry.get(model.getTemplateId());
      factory.createPersister(messaging).save(contentElement, model);
    }
  }
}