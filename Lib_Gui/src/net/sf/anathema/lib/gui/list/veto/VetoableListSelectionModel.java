package net.sf.anathema.lib.gui.list.veto;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;

import net.disy.commons.core.util.SimpleBlock;
import net.sf.anathema.lib.gui.list.ListSelectionMode;

public class VetoableListSelectionModel extends DefaultListSelectionModel {

    private static final long serialVersionUID = -4520422891121213115L;
	private final List<IVetor> vetors = new ArrayList<IVetor>();
	private boolean alreadyAsked;
	private final ListSelectionMode mode;

	public VetoableListSelectionModel() {
		this(ListSelectionMode.SingleSelection);
	}

	public VetoableListSelectionModel(ListSelectionMode mode) {
		this.mode = mode;
		setSelectionMode(mode.getMode());
	}

	@Override
	public void addSelectionInterval(final int index0, final int index1) {
		executeVetoable(new SimpleBlock() {
			public void execute() {
				VetoableListSelectionModel.super.addSelectionInterval(index0,
						index1);
			}
		});
	}

	private synchronized void executeVetoable(SimpleBlock block) {
		if (alreadyAsked) {
			block.execute();
			return;
		}
		if (vetos()) {
			return;
		}
		alreadyAsked = true;
		block.execute();
		alreadyAsked = false;
	}

	public synchronized void addVetor(IVetor vetor) {
		vetors.add(vetor);
	}

	@Override
	public void removeSelectionInterval(final int index0, final int index1) {
		if (getMaxSelectionIndex() == -1) {
			return;
		}
		executeVetoable(new SimpleBlock() {
			public void execute() {
				VetoableListSelectionModel.super.removeSelectionInterval(
						index0, index1);
			}
		});
	}

	public synchronized void removeVetor(IVetor vetor) {
		vetors.remove(vetor);
	}

	@Override
	public void setSelectionInterval(final int index0, final int index1) {
		executeVetoable(new SimpleBlock() {
			public void execute() {
				VetoableListSelectionModel.super.setSelectionInterval(index0,
						index1);
			}
		});
	}

	@Override
	public void setSelectionMode(int selectionMode) {
		super.setSelectionMode(mode.getMode());
	}

	private synchronized boolean vetos() {
		List<IVetor> cloneList = new ArrayList<IVetor>(vetors);
        for (IVetor vetor : cloneList) {
            if (vetor.vetos()) {
                return true;
            }
        }
		return false;
	}
}