package net.sf.anathema.character.library.virtueflaw.persistence;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.framework.persistence.AbstractPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class VirtueFlawPersister extends AbstractPersister implements IAdditionalPersister {

  public static final String TAG_ROOT_VIRTUE = "RootVirtue"; //$NON-NLS-1$
  public static final String TAG_VIRTUE_FLAW = "VirtueFlaw"; //$NON-NLS-1$
  public static final String TAG_NAME = "Name"; //$NON-NLS-1$
  public static final String ATTRIB_NAME = "name"; //$NON-NLS-1$

  public void save(Element parent, IAdditionalModel model) {
    IVirtueFlawModel virtueFlawModel = (IVirtueFlawModel) model;
    saveVirtueFlaw(parent, virtueFlawModel.getVirtueFlaw());
  }

  private void saveVirtueFlaw(Element parent, IVirtueFlaw virtueFlaw) {
    Element flawElement = createFlawElement(parent);
    saveRootVirtue(flawElement, virtueFlaw);
    saveTextualDescription(flawElement, TAG_NAME, virtueFlaw.getName());
    saveAdditionalData(flawElement, virtueFlaw);
  }

  protected void saveAdditionalData(Element flawElement, IVirtueFlaw virtueFlaw) {
    // Nothing to do
  }

  private final Element createFlawElement(Element parent) {
    return parent.addElement(TAG_VIRTUE_FLAW);
  }

  private final void saveRootVirtue(Element flawElement, IVirtueFlaw virtueFlaw) {
    ITraitType rootVirtue = virtueFlaw.getRoot();
    if (rootVirtue != null) {
      Element rootElement = flawElement.addElement(TAG_ROOT_VIRTUE);
      rootElement.addAttribute(ATTRIB_NAME, rootVirtue.getId());
    }
  }

  public void load(Element parent, IAdditionalModel model) throws PersistenceException {
    IVirtueFlawModel virtueFlawModel = (IVirtueFlawModel) model;
    loadVirtueFlaw(parent, virtueFlawModel.getVirtueFlaw());

  }

  private void loadVirtueFlaw(Element parent, IVirtueFlaw virtueFlaw) {
    Element flawElement = parent.element(TAG_VIRTUE_FLAW);
    if (flawElement == null) {
      return;
    }
    Element rootElement = flawElement.element(TAG_ROOT_VIRTUE);
    if (rootElement != null) {
      virtueFlaw.setRoot(VirtueType.valueOf(rootElement.attributeValue(ATTRIB_NAME)));
    }
    restoreTextualDescription(flawElement, TAG_NAME, virtueFlaw.getName());
    loadAdditionalData(flawElement, virtueFlaw);
  }

  protected void loadAdditionalData(Element flawElement, IVirtueFlaw virtueFlaw) {
    // Nothing to do
  }
}