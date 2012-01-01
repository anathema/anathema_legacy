package net.sf.anathema.character.meritsflaws.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.MeritsFlawsAdditionalModel;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;
import net.sf.anathema.character.meritsflaws.template.MeritsFlawsTemplate;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class MeritsAndFlawsContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private IGenericCharacter character;

  public MeritsAndFlawsContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "MeritsAndFlaws"; //$NON-NLS-1$
  }

  @Override
  public boolean hasContent() {
    IMeritsFlawsModel model = getModel();
    return model.getSelectedQualities().length > 0;
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printPerks = new ArrayList<String>();
    IQualitySelection<IPerk>[] perks = getModel().getSelectedQualities();
    for (int index = 0; index < perks.length; index++) {
      IPerk perk = perks[index].getQuality();
      printPerks.add(getPerkString(perk));
    }
    return printPerks;
  }

  private String getPerkString(IPerk perk) {
    String string = perk.getType().getId() + ".";  //$NON-NLS-1$
    string += perk.getCategory().getId() + ".";  //$NON-NLS-1$
    string += perk.getId();
    return getResources().getString(string);
  }

  private IMeritsFlawsModel getModel() {
    MeritsFlawsAdditionalModel additionalModel = (MeritsFlawsAdditionalModel) character.getAdditionalModel(MeritsFlawsTemplate.ID);
    return additionalModel.getMeritsFlawsModel();
  }
}
