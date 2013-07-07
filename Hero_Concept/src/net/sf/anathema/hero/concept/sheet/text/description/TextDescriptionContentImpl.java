package net.sf.anathema.hero.concept.sheet.text.description;

import com.google.common.base.Strings;
import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

import java.util.ArrayList;
import java.util.List;

public class TextDescriptionContentImpl implements TextDescriptionContent {

  private final HeroDescription description;

  public TextDescriptionContentImpl(HeroDescription description) {
    this.description = description;
  }

  @Override
  public String getName() {
    return description.getName().getText();
  }

  @Override
  public List<String> getDescription() {
    List<String> descriptionParts = new ArrayList<>();
    addNonEmptyText(descriptionParts, description.getCharacterization());
    addNonEmptyText(descriptionParts, description.getPhysicalDescription());
    addNonEmptyText(descriptionParts, description.getNotes());
    return descriptionParts;
  }

  private void addNonEmptyText(List<String> descriptionParts, ITextualDescription textualDescription) {
    if (!isEmpty(textualDescription)) {
      descriptionParts.add(textualDescription.getText());
    }
  }

  private boolean isEmpty(ITextualDescription textualDescription) {
    return Strings.isNullOrEmpty(textualDescription.getText());
  }
}