package com.change_vision.astah.extension.plugin.internal.util;

import javax.swing.JFrame;

import com.change_vision.astah.extension.plugin.internal.exception.APIException;
import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.BasicModelEditor;
import com.change_vision.jude.api.inf.editor.ClassDiagramEditor;
import com.change_vision.jude.api.inf.editor.IDiagramEditorFactory;
import com.change_vision.jude.api.inf.editor.IModelEditorFactory;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IClassDiagram;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.view.IDiagramViewManager;
import com.change_vision.jude.api.inf.view.IViewManager;

public class AstahAPIUtils {
	public IClassDiagram getClassDiagram() {
		IDiagramViewManager diagramViewManager = getDiagramViewManager();
		return (IClassDiagram)diagramViewManager.getCurrentDiagram();
	}
	
	public IDiagramViewManager getDiagramViewManager() {
		IViewManager viewManager = getViewManager();
	    IDiagramViewManager diagramViewManager = viewManager.getDiagramViewManager();
		return diagramViewManager;
	}

	public ClassDiagramEditor getClassDiagramEditor() {
		try {
			return getDiagramEditorFactory().getClassDiagramEditor();
		} catch (InvalidUsingException e) {
			throw new APIException(e);
		}
	}

	public BasicModelEditor getBasicModelEditor() {
		try {
			return getModelEditorFactory().getBasicModelEditor();
		} catch (InvalidEditingException e) {
			throw new APIException(e);
		}
	}

	public ProjectAccessor getProjectAccessor() {
		ProjectAccessor projectAccessor = null;
		try {
			AstahAPI api = AstahAPI.getAstahAPI();
			projectAccessor = api.getProjectAccessor();
		} catch (ClassNotFoundException e) {
	        throw new IllegalStateException(e);
		}
		if(projectAccessor == null) throw new IllegalStateException("projectAccessor is null.");
		return projectAccessor;
	}

	public JFrame getMainFrame() {
		return getViewManager().getMainFrame();
	}
	
	public String getEdition() {
		return getProjectAccessor().getAstahEdition();
	}

	private IViewManager getViewManager() {
		ProjectAccessor projectAccessor = getProjectAccessor();
		IViewManager viewManager;
		try {
			viewManager = projectAccessor.getViewManager();
		} catch (InvalidUsingException e) {
			throw new APIException(e);
		}
		if(viewManager == null) throw new IllegalStateException("ViewManager is null.");
		return viewManager;
	}

	private IModelEditorFactory getModelEditorFactory() {
		ProjectAccessor projectAccessor = getProjectAccessor();
		IModelEditorFactory modelEditorFactory = projectAccessor.getModelEditorFactory();
		if(modelEditorFactory == null) throw new IllegalStateException("modelEditorFactory is null.");
		return modelEditorFactory;
	}

	private IDiagramEditorFactory getDiagramEditorFactory() {
		ProjectAccessor projectAccessor = getProjectAccessor();
		IDiagramEditorFactory diagramEditorFactory = projectAccessor.getDiagramEditorFactory();
		if(diagramEditorFactory == null) throw new IllegalStateException("diagramEditorFactory is null.");
		return diagramEditorFactory;
	}

}
