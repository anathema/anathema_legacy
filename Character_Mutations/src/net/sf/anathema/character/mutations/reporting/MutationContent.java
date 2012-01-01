package net.sf.anathema.character.mutations.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.character.mutations.model.MutationsAdditionalModel;
import net.sf.anathema.character.mutations.template.MutationsTemplate;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.ArrayList;
import java.util.List;

public class MutationContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private IGenericCharacter character;

  public MutationContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Mutations"; //$NON-NLS-1$
  }

  @Override
  public boolean hasContent() {
    IMutationsModel model = getMutationModel();
    return model.getSelectedQualities().length > 0;
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printMutations = new ArrayList<String>();
    IMutationsModel model = getMutationModel();
    IQualitySelection<IMutation>[] mutations = model.getSelectedQualities();
    for (int index = 0; index < mutations.length; index++) {
      IIdentificate mutation = mutations[index].getQuality();
      printMutations.add(getResources().getString("Mutations.Mutation." + mutation.getId())); //$NON-NLS-1$
    }
    return printMutations;
  }

  private IMutationsModel getMutationModel() {
    MutationsAdditionalModel additionalModel = (MutationsAdditionalModel) character.getAdditionalModel(MutationsTemplate.ID);
    return additionalModel.getModel();
  }
}
