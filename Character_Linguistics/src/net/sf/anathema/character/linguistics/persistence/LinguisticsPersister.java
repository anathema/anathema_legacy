package net.sf.anathema.character.linguistics.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.linguistics.ILinguisticsAdditionalModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class LinguisticsPersister implements IAdditionalPersister {

  private static final String TAG_LANGUAGES = "linguistics"; //$NON-NLS-1$
  private static final String TAG_LANGUAGE = "language"; //$NON-NLS-1$

  public void load(Element parent, IAdditionalModel additionalModel) throws PersistenceException {
    Element element = parent.element(TAG_LANGUAGES);
    ILinguisticsModel model = ((ILinguisticsAdditionalModel) additionalModel).getLinguisticsModel();
    for (Element languageElement : ElementUtilities.elements(element, TAG_LANGUAGE)) {
      String name = languageElement.getText();
      IIdentificate language = model.getPredefinedLanguageById(name);
      if (language != null) {
        model.selectLanguage(language);
      }
      else {
        model.selectBarbarianLanguage(name);
      }
      model.commitSelection();
    }
  }

  public void save(Element parent, IAdditionalModel additionalModel) {
    Element element = parent.addElement(TAG_LANGUAGES);
    ILinguisticsModel model = ((ILinguisticsAdditionalModel) additionalModel).getLinguisticsModel();
    for (IIdentificate language : model.getEntries()) {
      Element languageElement = element.addElement(TAG_LANGUAGE);
      languageElement.addCDATA(language.getId());
    }
  }
}