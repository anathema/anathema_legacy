package net.sf.anathema.campaign.view.plot;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public final class PlotElementTransferable implements Transferable {

  private final DefaultMutableTreeNode transferNode;
  public static final DataFlavor FLAVOR = createFlavorConstant();

  private static DataFlavor createFlavorConstant() {
    try {
      return new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=" + PlotElementTransferable.class.getName(), null, PlotElementTransferable.class.getClassLoader());
    }
    catch (ClassNotFoundException e) {
      throw new RuntimeException("Did you specify the correct classloader?", e);
    }
  }

  public PlotElementTransferable(DefaultMutableTreeNode selectedNode) {
    this.transferNode = selectedNode;
  }

  @Override
  public DataFlavor[] getTransferDataFlavors() {
    return new DataFlavor[] { FLAVOR };
  }

  @Override
  public boolean isDataFlavorSupported(DataFlavor demandedFlavor) {
    return demandedFlavor.equals(FLAVOR);
  }

  @Override
  public Object getTransferData(DataFlavor flavor) {
    return transferNode;
  }
}