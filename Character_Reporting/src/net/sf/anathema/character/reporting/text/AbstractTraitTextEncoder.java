package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractTraitTextEncoder extends AbstractTextEncoder {

  public AbstractTraitTextEncoder(PdfReportUtils utils, Resources resources) {
    super(utils, resources);
  }

  public void createParagraphs(MultiColumnText columnText, Hero hero) throws DocumentException {
    Phrase traitPhrase = createTextParagraph(createBoldTitle(getString(getLabelKey()) + ": "));
    boolean firstPrinted = true;
    for (TraitType type : getTypes(hero)) {
      Trait trait = TraitModelFetcher.fetch(hero).getTrait(type);
      if (trait.getCurrentValue() == 0) {
        continue;
      }
      if (!firstPrinted) {
        traitPhrase.add(createTextChunk(", "));
      }
      firstPrinted = false;
      if (trait.isCasteOrFavored()) {
        traitPhrase.add(createTextChunk("*"));
      }
      traitPhrase.add(createTextChunk(getString(trait.getType().getId())));
      traitPhrase.add(createTextChunk(" " + String.valueOf(trait.getCurrentValue())));
    }
    columnText.addElement(traitPhrase);
  }

  protected abstract TraitType[] getTypes(Hero hero);

  protected abstract String getLabelKey();
}
