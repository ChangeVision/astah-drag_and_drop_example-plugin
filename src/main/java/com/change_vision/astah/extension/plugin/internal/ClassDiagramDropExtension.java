package com.change_vision.astah.extension.plugin.internal;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.change_vision.jude.api.inf.model.IClassDiagram;
import com.change_vision.jude.api.inf.view.DiagramDropTargetListener;

public final class ClassDiagramDropExtension extends DiagramDropTargetListener {

	public ClassDiagramDropExtension() {
		super(IClassDiagram.class);
	}

	@Override
	public void dropExternalData(DropTargetDropEvent dtde) {
		if(dtde.isLocalTransfer()) return;
		
		if(dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			System.out.println("ClassDiagramDropExtension.stringFlavor" + getURLStringFromDropContent(dtde));
			dtde.dropComplete(true);
		} else if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
			List<File> files = getFilesFromDropContent(dtde);
			System.out.println("javaFileListFravor" + files);
			dtde.dropComplete(true);
		}
	}

	private String getURLStringFromDropContent(DropTargetDropEvent dtde) {
		dtde.acceptDrop(DnDConstants.ACTION_LINK);
		Transferable target = dtde.getTransferable();

		String urlString;
		try {
			urlString = (String)target.getTransferData(DataFlavor.stringFlavor);
		} catch (Exception e) {
			urlString = "";
		}
		return urlString;
	}
		
	@SuppressWarnings("unchecked")
	private List<File> getFilesFromDropContent(DropTargetDropEvent dtde) {
		dtde.acceptDrop(DnDConstants.ACTION_COPY);
		List<File> list;
		try {
			list = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		} catch (Exception e) {
			list = new ArrayList<File>();
		}
		return list;
	}
	
	@Override
	public void dropModels(DropTargetDropEvent dtde, Set<?> models) {
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
	}
}