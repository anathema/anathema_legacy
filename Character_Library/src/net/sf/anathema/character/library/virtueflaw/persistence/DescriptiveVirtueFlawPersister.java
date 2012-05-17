package net.sf.anathema.character.library.virtueflaw.persistence;

import net.sf.anathema.character.library.virtueflaw.model.IDescriptiveVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.framework.persistence.TextPersister;
import org.dom4j.Element;

public class DescriptiveVirtueFlawPersister extends VirtueFlawPersister {

  public static final String TAG_LIMIT_BREAK = "LimitBreak";
  public static final String TAG_DESCRIPTION = "Description";
  private final TextPersister textPersister = new TextPersister();

  @Override
  protected void saveVirtueFlaw(Element flawElement, IVirtueFlaw virtueFlaw) {
    super.saveVirtueFlaw(flawElement, virtueFlaw);
    IDescriptiveVirtueFlaw descriptiveVirtueFlaw = (IDescriptiveVirtueFlaw) virtueFlaw;
    textPersister.saveTextualDescription(flawElement, TAG_LIMIT_BREAK, descriptiveVirtueFlaw.getLimitBreak());
    textPersister.saveTextualDescription(flawElement, TAG_DESCRIPTION, descriptiveVirtueFlaw.getDescription());
  }

  @Override
  protected void loadVirtueFlaw(Element flawElement, IVirtueFlaw virtueFlaw) {
    super.loadVirtueFlaw(flawElement, virtueFlaw);
    IDescriptiveVirtueFlaw solarFlaw = (IDescriptiveVirtueFlaw) virtueFlaw;
    textPersister.restoreTextualDescription(flawElement, TAG_LIMIT_BREAK, solarFlaw.getLimitBreak());
    textPersister.restoreTextualDescription(flawElement, TAG_DESCRIPTION, solarFlaw.getDescription());
  }
}