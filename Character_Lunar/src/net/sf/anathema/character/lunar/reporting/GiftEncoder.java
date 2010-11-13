package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.sheet.util.AbstractLineTextEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class GiftEncoder extends AbstractLineTextEncoder {

  private final IResources resources;

  public GiftEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  public String getHeaderKey() {
    return "Lunar.Gifts"; //$NON-NLS-1$
  }

  @Override
  protected void addToPhrase(IGenericCharacter character, Font font, Phrase phrase) {
    IBeastformModel model = (IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IQualitySelection<IGift>[] gifts = model.getGiftModel().getSelectedQualities();
    for (int index = 0; index < gifts.length; index++) {
      IIdentificate gift = gifts[index].getQuality();
      String text = resources.getString("DeadlyBeastmanTransformation.Gift." + gift.getId()); //$NON-NLS-1$
      text += index + 1 < gifts.length ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
      phrase.add(new Chunk(text, font));
    }
  }
}