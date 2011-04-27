package net.sf.anathema.character.library.virtueflaw.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.persistence.TraitPersister;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class VirtueFlawPersister implements IAdditionalPersister {

  public static final String TAG_ROOT_VIRTUE = "RootVirtue"; //$NON-NLS-1$
  public static final String TAG_VIRTUE_FLAW = "VirtueFlaw"; //$NON-NLS-1$
  public static final String TAG_NAME = "Name"; //$NON-NLS-1$
  public static final String TAG_LIMIT = "Limit";
  public static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private final TextPersister textPersister = new TextPersister();
  private final TraitPersister traitPersister = new TraitPersister();

  public void save(Element parent, IAdditionalModel model) {
    IVirtueFlawModel virtueFlawModel = (IVirtueFlawModel) model;
    saveVirtueFlaw(createFlawElement(parent), virtueFlawModel.getVirtueFlaw());
  }

  protected void saveVirtueFlaw(Element flawElement, IVirtueFlaw virtueFlaw) {
    saveRootVirtue(flawElement, virtueFlaw);
    textPersister.saveTextualDescription(flawElement, TAG_NAME, virtueFlaw.getName());
    traitPersister.saveTrait(flawElement, TAG_LIMIT, virtueFlaw.getLimitTrait());
  }

  private Element createFlawElement(Element parent) {
    return parent.addElement(TAG_VIRTUE_FLAW);
  }

  private void saveRootVirtue(Element flawElement, IVirtueFlaw virtueFlaw) {
    ITraitType rootVirtue = virtueFlaw.getRoot();
    if (rootVirtue != null) {
      Element rootElement = flawElement.addElement(TAG_ROOT_VIRTUE);
      rootElement.addAttribute(ATTRIB_NAME, rootVirtue.getId());
    }
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    IVirtueFlawModel virtueFlawModel = (IVirtueFlawModel) model;
    loadVirtueFlaw(parent.element(TAG_VIRTUE_FLAW), virtueFlawModel.getVirtueFlaw());
  }

  protected void loadVirtueFlaw(Element flawElement, IVirtueFlaw virtueFlaw) throws PersistenceException {
    if (flawElement == null) {
      return;
    }
    Element rootElement = flawElement.element(TAG_ROOT_VIRTUE);
    if (rootElement != null) {
      virtueFlaw.setRoot(VirtueType.valueOf(rootElement.attributeValue(ATTRIB_NAME)));
    }
    textPersister.restoreTextualDescription(flawElement, TAG_NAME, virtueFlaw.getName());
    Element limitElement = flawElement.element(TAG_LIMIT);
    if (limitElement != null)
    	traitPersister.restoreTrait(limitElement, virtueFlaw.getLimitTrait());
  }
}