package net.sf.anathema.character.lunar.virtueflaw.persistence;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.persistence.VirtueFlawPersister;
import net.sf.anathema.character.lunar.virtueflaw.model.ILunarVirtueFlaw;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class LunarVirtueFlawPersister extends VirtueFlawPersister {

  public static final String TAG_LIMIT_BREAK = "LimitBreak"; //$NON-NLS-1$    
  public static final String TAG_DESCRIPTION = "Description"; //$NON-NLS-1$
  private final TextPersister textPersister = new TextPersister();

  @Override
  protected void saveVirtueFlaw(Element flawElement, IVirtueFlaw virtueFlaw) {
    super.saveVirtueFlaw(flawElement, virtueFlaw);
    if (virtueFlaw instanceof ILunarVirtueFlaw)
    {
	    ILunarVirtueFlaw lunarFlaw = (ILunarVirtueFlaw) virtueFlaw;
	    textPersister.saveTextualDescription(flawElement, TAG_LIMIT_BREAK, lunarFlaw.getLimitBreak());
	    textPersister.saveTextualDescription(flawElement, TAG_DESCRIPTION, lunarFlaw.getDescription());
    }
  }

  @Override
  protected void loadVirtueFlaw(Element flawElement, IVirtueFlaw virtueFlaw) {
	try
	{
	    super.loadVirtueFlaw(flawElement, virtueFlaw);
	    if (virtueFlaw instanceof ILunarVirtueFlaw)
	    {
		    ILunarVirtueFlaw lunarFlaw = (ILunarVirtueFlaw) virtueFlaw;
		    textPersister.restoreTextualDescription(flawElement, TAG_LIMIT_BREAK, lunarFlaw.getLimitBreak());
		    textPersister.restoreTextualDescription(flawElement, TAG_DESCRIPTION, lunarFlaw.getDescription());
	    }
	}
	catch (PersistenceException e)
	{
		e.printStackTrace();
	}
  }
}