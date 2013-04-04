package net.sf.anathema.character.linguistics.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.linguistics.ILinguisticsAdditionalModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.character.linguistics.template.LinguisticsTemplate;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.List;

public class LinguisticsContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private IGenericCharacter character;

  public LinguisticsContent(Resources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Languages";
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printLanguages = new ArrayList<>();
    ILinguisticsModel model = getModel();
    for (Identified language : model.getEntries()) {
      String text = language.getId();
      if (model.isPredefinedLanguage(language)) {
        text = getString("Language." + text);
      }
      printLanguages.add(text);
    }
    return printLanguages;
  }

  private ILinguisticsModel getModel() {
    ILinguisticsAdditionalModel additionalModel = (ILinguisticsAdditionalModel) character.getAdditionalModel(LinguisticsTemplate.ID);
    return additionalModel.getLinguisticsModel();
  }
}
