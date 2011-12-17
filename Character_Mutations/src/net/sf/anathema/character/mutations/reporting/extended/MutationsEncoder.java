package net.sf.anathema.character.mutations.reporting.extended;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.character.mutations.model.MutationsAdditionalModel;
import net.sf.anathema.character.mutations.template.MutationsTemplate;
import net.sf.anathema.character.reporting.extended.util.AbstractLineTextEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class MutationsEncoder extends AbstractLineTextEncoder {

  private final IResources resources;

  public MutationsEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Mutations"; //$NON-NLS-1$
  }

  @Override
  protected void addToPhrase(IGenericCharacter character, Font font, Phrase phrase) {
    IMutationsModel model = ((MutationsAdditionalModel) (character.getAdditionalModel(MutationsTemplate.ID))).getModel();
    IQualitySelection<IMutation>[] mutations = model.getSelectedQualities();
	for (int index = 0; index < mutations.length; index++)
	{
	  IIdentificate mutation = mutations[index].getQuality();
	  String text = resources.getString("Mutations.Mutation." + mutation.getId()); //$NON-NLS-1$
	  text += index + 1 < mutations.length ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
	  phrase.add(new Chunk(text, font));
	}
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  IMutationsModel model = ((MutationsAdditionalModel) (character.getAdditionalModel(MutationsTemplate.ID))).getModel();
	  return model.getSelectedQualities().length > 0;
  }
}