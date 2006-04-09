package net.sf.anathema.character.solar.virtueflaw.persistence;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.persistence.VirtueFlawPersister;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;

import org.dom4j.Element;

public class SolarVirtueFlawPersister extends VirtueFlawPersister {

  public static final String TAG_LIMIT_BREAK = "LimitBreak"; //$NON-NLS-1$    
  public static final String TAG_DESCRIPTION = "Description"; //$NON-NLS-1$

  @Override
  protected void saveAdditionalData(Element flawElement, IVirtueFlaw virtueFlaw) {
    ISolarVirtueFlaw solarFlaw = (ISolarVirtueFlaw) virtueFlaw;
    saveTextualDescription(flawElement, TAG_LIMIT_BREAK, solarFlaw.getLimitBreak());
    saveTextualDescription(flawElement, TAG_DESCRIPTION, solarFlaw.getDescription());
  }

  @Override
  protected void loadAdditionalData(Element flawElement, IVirtueFlaw virtueFlaw) {
    ISolarVirtueFlaw solarFlaw = (ISolarVirtueFlaw) virtueFlaw;
    restoreTextualDescription(flawElement, TAG_LIMIT_BREAK, solarFlaw.getLimitBreak());
    restoreTextualDescription(flawElement, TAG_DESCRIPTION, solarFlaw.getDescription());
  }
}