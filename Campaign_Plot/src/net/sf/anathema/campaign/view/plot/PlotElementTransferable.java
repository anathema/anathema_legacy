package net.sf.anathema.campaign.view.plot;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.tree.DefaultMutableTreeNode;

import net.disy.commons.core.exception.UnreachableCodeReachedException;

public final class PlotElementTransferable implements Transferable {

  private final DefaultMutableTreeNode transferNode;
  public static final DataFlavor FLAVOR = createFlavorConstant();

  private static DataFlavor createFlavorConstant() {
    try {
      return new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=" + PlotElementTransferable.class.getName()); //$NON-NLS-1$
    }
    catch (ClassNotFoundException e) {
      throw new UnreachableCodeReachedException();
    }
  }

  public PlotElementTransferable(DefaultMutableTreeNode selectedNode) {
    this.transferNode = selectedNode;
  }

  public DataFlavor[] getTransferDataFlavors() {
    return new DataFlavor[] { FLAVOR };
  }

  public boolean isDataFlavorSupported(DataFlavor demandedFlavor) {
    return demandedFlavor.equals(FLAVOR);
  }

  public Object getTransferData(DataFlavor flavor) {
    return transferNode;
  }
}