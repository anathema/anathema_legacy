package net.sf.anathema.character.solar.virtueflaw.persistence;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.persistence.VirtueFlawPersister;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;
import net.sf.anathema.framework.persistence.TextPersister;

import org.dom4j.Element;

public class SolarVirtueFlawPersister extends VirtueFlawPersister {

  public static final String TAG_LIMIT_BREAK = "LimitBreak"; //$NON-NLS-1$    
  public static final String TAG_DESCRIPTION = "Description"; //$NON-NLS-1$
  private final TextPersister textPersister = new TextPersister();

  @Override
  protected void saveAdditionalData(Element flawElement, IVirtueFlaw virtueFlaw) {
    ISolarVirtueFlaw solarFlaw = (ISolarVirtueFlaw) virtueFlaw;
    textPersister.saveTextualDescription(flawElement, TAG_LIMIT_BREAK, solarFlaw.getLimitBreak());
    textPersister.saveTextualDescription(flawElement, TAG_DESCRIPTION, solarFlaw.getDescription());
  }

  @Override
  protected void loadAdditionalData(Element flawElement, IVirtueFlaw virtueFlaw) {
    ISolarVirtueFlaw solarFlaw = (ISolarVirtueFlaw) virtueFlaw;
    textPersister.restoreTextualDescription(flawElement, TAG_LIMIT_BREAK, solarFlaw.getLimitBreak());
    textPersister.restoreTextualDescription(flawElement, TAG_DESCRIPTION, solarFlaw.getDescription());
  }
}