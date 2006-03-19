package net.sf.anathema.lib.gui.layout;

import javax.swing.JLabel;
import javax.swing.JSplitPane;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;

public class AnathemaLayoutUtilities {
  public static JLabel[] createLabels(String[] labelStrings) {
    JLabel[] labels = new JLabel[labelStrings.length];
    for (int i = 0; i < labelStrings.length; i++) {
      labels[i] = new JLabel(labelStrings[i]);
    }

    return labels;
  }

  public static GridDialogLayoutData createAlignedGridDialogData(GridAlignment horizontalAlignment) {
    GridDialogLayoutData dialogData = new GridDialogLayoutData();
    dialogData.setHorizontalAlignment(horizontalAlignment);
    return dialogData;
  }

  public static JSplitPane createSplitPane(double dividerLocation) {
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(dividerLocation);
    splitPane.setDividerSize(7);
    return splitPane;
  }
}