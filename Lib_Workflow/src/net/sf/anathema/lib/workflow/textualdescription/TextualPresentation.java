package net.sf.anathema.lib.workflow.textualdescription;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class TextualPresentation {

  public void initView(final ITextView textView, final ISimpleTextualDescription textualDescription) {
    textView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        textualDescription.setText(newValue);
      }
    });
    textView.setText(textualDescription.getText());
    textualDescription.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        textView.setText(newValue);
      }
    });
  }
}