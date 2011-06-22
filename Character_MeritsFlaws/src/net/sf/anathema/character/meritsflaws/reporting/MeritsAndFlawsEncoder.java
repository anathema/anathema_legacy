package net.sf.anathema.character.meritsflaws.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.MeritsFlawsAdditionalModel;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;
import net.sf.anathema.character.meritsflaws.template.MeritsFlawsTemplate;
import net.sf.anathema.character.reporting.sheet.util.AbstractLineTextEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class MeritsAndFlawsEncoder extends AbstractLineTextEncoder {

  private final IResources resources;

  public MeritsAndFlawsEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  public String getHeaderKey() {
    return "MeritsAndFlaws"; //$NON-NLS-1$
  }

  @Override
  protected void addToPhrase(IGenericCharacter character, Font font, Phrase phrase) {
    IMeritsFlawsModel model = (IMeritsFlawsModel)(((MeritsFlawsAdditionalModel) (character.getAdditionalModel(MeritsFlawsTemplate.ID))).getMeritsFlawsModel());
    IQualitySelection<IPerk>[] perks = model.getSelectedQualities();
	for (int index = 0; index < perks.length; index++)
	{
	  IPerk perk = perks[index].getQuality();
	  String text = getPerkString(perk); //$NON-NLS-1$
	  text += index + 1 < perks.length ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
	  phrase.add(new Chunk(text, font));
	}
  }
  
  private String getPerkString(IPerk perk)
  {
	  String string = perk.getType().getId() + ".";
	  string += perk.getCategory().getId() + ".";
	  string += perk.getId();
	  return resources.getString(string);
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  IMeritsFlawsModel model = (IMeritsFlawsModel)(((MeritsFlawsAdditionalModel) (character.getAdditionalModel(MeritsFlawsTemplate.ID))).getMeritsFlawsModel());
	  return model.getSelectedQualities().length > 0;
  }
}