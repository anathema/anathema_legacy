package net.sf.anathema.character.reporting.text;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.resources.BackgroundInternationalizer;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.framework.reporting.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.MultiColumnText;

public class BackgroundsTextEncoder extends AbstractTextEncoder {

  private final BackgroundInternationalizer internationalizer;

  public BackgroundsTextEncoder(ITextReportUtils utils, IResources resources) {
    super(utils, resources);
    this.internationalizer = new BackgroundInternationalizer(resources);
  }

  public void createParagraphs(MultiColumnText columnText, IGenericCharacter genericCharacter) throws DocumentException {
    IGenericTrait[] backgrounds = genericCharacter.getBackgrounds();
    if (backgrounds.length == 0) {
      return;
    }
    Phrase traitPhrase = createTextParagraph(createBoldTitle(getString(getLabelKey()) + ": ")); //$NON-NLS-1$
    for (int index = 0; index < backgrounds.length; index++) {
      if (index > 0) {
        traitPhrase.add(createTextChunk(", ")); //$NON-NLS-1$
      }
      IGenericTrait trait = backgrounds[index];
      traitPhrase.add(createTextChunk(internationalizer.getDisplayName((IBackgroundTemplate) trait.getType())));
      traitPhrase.add(createTextChunk(" " + String.valueOf(trait.getCurrentValue()))); //$NON-NLS-1$
    }
    columnText.addElement(traitPhrase);
  }

  private String getLabelKey() {
    return "TextDescription.Label.Backgrounds"; //$NON-NLS-1$
  }

}
