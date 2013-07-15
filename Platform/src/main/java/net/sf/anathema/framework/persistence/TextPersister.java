package net.sf.anathema.framework.persistence;

import com.google.common.base.Strings;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import org.dom4j.Element;

public class TextPersister {

  public void saveNonEmptyText(Element parent, String tagName, String text) {
    if (Strings.isNullOrEmpty(text)) {
      return;
    }
    parent.addElement(tagName).addCDATA(text);
  }

  public void saveTextualDescription(Element parent, String tagName, ITextualDescription textualDescription) {
    saveNonEmptyText(parent, tagName, textualDescription.getText());
  }

  public void restoreTextualDescription(Element parent, String tagName, ITextualDescription description) {
    Element textualElement = parent.element(tagName);
    if (textualElement != null) {
      description.setText(textualElement.getText());
    }
    description.setDirty(false);
  }
}