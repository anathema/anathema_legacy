package net.sf.anathema.character.infernal.urge.persistence;

import net.sf.anathema.character.infernal.urge.model.InfernalUrge;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.persistence.VirtueFlawPersister;
import net.sf.anathema.framework.persistence.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class InfernalUrgePersister extends VirtueFlawPersister {

  public static final String TAG_URGE = "urge"; //$NON-NLS-1$
  public static final String TAG_DESCRIPTION = "description";
  private final TextPersister textPersister = new TextPersister();

  @Override
  protected void saveVirtueFlaw(Element flawElement, IVirtueFlaw virtueFlaw) {
    super.saveVirtueFlaw(flawElement, virtueFlaw);
    InfernalUrge infernalUrge = (InfernalUrge) virtueFlaw;
    textPersister.saveTextualDescription(flawElement, TAG_DESCRIPTION, infernalUrge.getDescription());
  }

  @Override
  protected void loadVirtueFlaw(Element flawElement, IVirtueFlaw virtueFlaw) {
	try
	{
	    super.loadVirtueFlaw(flawElement, virtueFlaw);
	    InfernalUrge infernalUrge = (InfernalUrge) virtueFlaw;
	    textPersister.restoreTextualDescription(flawElement, TAG_DESCRIPTION, infernalUrge.getDescription());
	}
	catch (PersistenceException e)
	{
		e.printStackTrace();
	}
  }
}