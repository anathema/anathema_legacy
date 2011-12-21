package net.sf.anathema.character.mutations.reporting;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.character.mutations.model.MutationsAdditionalModel;
import net.sf.anathema.character.mutations.template.MutationsTemplate;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.LineFillingBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class MutationsEncoder extends LineFillingBoxContentEncoder {

  private final IResources resources;

  public MutationsEncoder(BaseFont baseFont, IResources resources) {
    super();
    this.resources = resources;
  }

  public String getHeaderKey(ReportContent content) {
    return "Mutations"; //$NON-NLS-1$
  }

  @Override
  protected void addToPhrase(IGenericCharacter character, Font font, Phrase phrase) {
    IMutationsModel model = getMutationModel(character);
    IQualitySelection<IMutation>[] mutations = model.getSelectedQualities();
    for (int index = 0; index < mutations.length; index++) {
      IIdentificate mutation = mutations[index].getQuality();
      String text = resources.getString("Mutations.Mutation." + mutation.getId()); //$NON-NLS-1$
      text += index + 1 < mutations.length ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
      phrase.add(new Chunk(text, font));
    }
  }

  public boolean hasContent(ReportContent content) {
    IMutationsModel model = getMutationModel(content.getCharacter());
    return model.getSelectedQualities().length > 0;
  }

  private IMutationsModel getMutationModel(IGenericCharacter character) {
    MutationsAdditionalModel additionalModel = (MutationsAdditionalModel) character.getAdditionalModel(MutationsTemplate.ID);
    return additionalModel.getModel();
  }
}
