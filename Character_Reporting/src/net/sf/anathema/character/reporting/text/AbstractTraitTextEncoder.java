package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.framework.reporting.itext.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.MultiColumnText;

public abstract class AbstractTraitTextEncoder<T extends ITraitType> extends AbstractTextEncoder {

  public AbstractTraitTextEncoder(ITextReportUtils utils, IResources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericCharacter genericCharacter) throws DocumentException {
    Phrase traitPhrase = createTextParagraph(createBoldTitle(getString(getLabelKey()) + ": ")); //$NON-NLS-1$
    for (T type : getTypes(genericCharacter)) {
      if (addSeparator(type)) {
        traitPhrase.add(createTextChunk(", ")); //$NON-NLS-1$
      }
      IFavorableGenericTrait trait = genericCharacter.getFavorableTrait(type);
      if (trait.isCasteOrFavored()) {
        traitPhrase.add(createTextChunk("*")); //$NON-NLS-1$
      }
      traitPhrase.add(createTextChunk(getString(trait.getType().getId())));
      traitPhrase.add(createTextChunk(" " + String.valueOf(trait.getCurrentValue()))); //$NON-NLS-1$
    }
    columnText.addElement(traitPhrase);
  }

  protected abstract boolean addSeparator(T type);

  protected abstract T[] getTypes(IGenericCharacter genericCharacter);

  protected abstract String getLabelKey();
}