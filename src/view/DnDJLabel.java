package view;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class DnDJLabel extends JLabel implements Transferable,
	DragSourceListener, DragGestureListener, DropTargetListener {

	private DragSource dragSource;
	private DropTarget dropTarget;

	public DnDJLabel(){
		this("");
	}

	public DnDJLabel(String text){
		super(text);
		
		setIcon(new ImageIcon());
		
		setTransferHandler(new LabelTransferHandler());

		dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
		
		dropTarget = new DropTarget(this, this);
		this.setDropTarget(dropTarget);
	}

	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[]{new DataFlavor(ImageIcon.class, "DnDJLabel Icon")};
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return true;
	}

	public Object getTransferData(DataFlavor flavor) {
		return this;
	}

	//DRAG SOURCE LISTENER
	public void dragEnter(DragSourceDragEvent dsde) {
		//System.out.println("(DnDJLabel) DRAG_ENTER (DragSource): "  + this.getName());
	}
	public void dragOver(DragSourceDragEvent dsde) {
		//System.out.println("(DnDJLabel) DRAG_OVER (DragSource): " );
	}
	public void dropActionchanged(DragSourceDragEvent dsde) {
		//System.out.println("(DnDJLabel) DROP_ACTION_CHANGED (DragSource): "  + this.getName());
	}
	public void dragExit(DragSourceEvent dse) {
		//System.out.println("(DnDJLabel) DRAG_EXIT (DragSource): "  + this.getName());
	}
	public void dragDropEnd(DragSourceDropEvent dsde) {
		//System.out.println("(DnDJLabel) DRAG_DROP_END (DragSource): at " + this.getName() );
		repaint();
	}

	
	//DRAG GESTURE LISTENER
	public void dragGestureRecognized(DragGestureEvent dge) {
		//System.out.println("(DnDJLabel) DRAG_GESTURE_RECOGNISED (DragGesture): at "  + this.getName());
		
		//dragSource.startDrag(dge, DragSource.DefaultMoveDrop, new DnDJLabel("BLERK"), this);       
		dragSource.startDrag(dge, DragSource.DefaultMoveDrop,
				((ImageIcon) this.getIcon()).getImage(),
				new Point(this.getWidth() / 2, this.getHeight() / 2),
				this, this);
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
		//System.out.println("(DnDJLabel) DROP_ACTION_CHANGED (DragGesture): "  + this.getName());
	}

	//DROP TARGET LISTENER
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		//System.out.println("(DnDJLabel) DRAG_ENTER (DropTarget): "  + this.getName());
	}
	@Override
	public void dragExit(DropTargetEvent dte) {
		//System.out.println("(DnDJLabel) DRAG_EXIT (DropTarget): "  + this.getName());
	}
	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		//System.out.println("(DnDJLabel) DRAG_OVER (DropTarget): " );
	}
	@Override
	public void drop(DropTargetDropEvent dtde) {
		//System.out.println("(DnDJLabel) DROP (DropTarget): " + this.getName());
		try {
			Point point = dtde.getLocation();

			Transferable transferable = dtde.getTransferable();

			DataFlavor[] flavour = transferable.getTransferDataFlavors();

			LabelTransferHandler transferHandler = (LabelTransferHandler)getTransferHandler();
			
			if (!this.isEnabled()){
				dtde.rejectDrop();
				return;
			}
			
			if(transferHandler.canImport((JComponent)this, flavour)){
				transferHandler.importData((JComponent)this, (DnDJLabel)transferable.getTransferData(flavour[0]), point);
				repaint();
				
				//Make like a click for the controller.
				BoardView.getInstance().getControllerEventHandler().setEvent(this.getName());
			}
			else {
				return;
			}
		} catch (UnsupportedFlavorException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			dtde.dropComplete(true);
		}
	}
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		//System.out.println("(DnDJLabel) DROP_ACTION_CHANGED (DropTarget): "  + this.getName());
	}
	
	class LabelTransferHandler extends TransferHandler{

		public boolean canImport(JComponent component, DataFlavor[] flavour){
			DataFlavor tempFlavour = new DataFlavor(ImageIcon.class, component.getName());
			for (int i = 0; i < flavour.length; i++){
				if (flavour[i].equals(tempFlavour)){
					return true;
				}
			}
			return false;
		}

		public boolean importData(JComponent component, Transferable transferable, Point point){
			try {
				DnDJLabel label = (DnDJLabel)transferable.getTransferData(new DataFlavor(ImageIcon.class, "DnDJLabel Icon"));
				((DnDJLabel)component).setIcon(label.getIcon());

			} catch (UnsupportedFlavorException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return true;
		}
	}
}
