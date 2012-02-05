package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractTraitTextEncoder extends AbstractTextEncoder {

  public AbstractTraitTextEncoder(PdfReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericCharacter genericCharacter) throws DocumentException {
    Phrase traitPhrase = createTextParagraph(createBoldTitle(getString(getLabelKey()) + ": ")); //$NON-NLS-1$
    boolean firstPrinted = true;
    for (ITraitType type : getTypes(genericCharacter)) {
      IFavorableGenericTrait trait = genericCharacter.getTraitCollection().getFavorableTrait(type);
      if (trait.getCurrentValue() == 0) {
        continue;
      }
      if (!firstPrinted) {
        traitPhrase.add(createTextChunk(", ")); //$NON-NLS-1$
      }
      firstPrinted = false;
      if (trait.isCasteOrFavored()) {
        traitPhrase.add(createTextChunk("*")); //$NON-NLS-1$
      }
      traitPhrase.add(createTextChunk(getString(trait.getType().getId())));
      traitPhrase.add(createTextChunk(" " + String.valueOf(trait.getCurrentValue()))); //$NON-NLS-1$
    }
    columnText.addElement(traitPhrase);
  }

  protected abstract ITraitType[] getTypes(IGenericCharacter genericCharacter);

  protected abstract String getLabelKey();
}
