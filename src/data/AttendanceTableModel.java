package data;

import data.types.Attendee;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AttendanceTableModel extends AbstractTableModel {

    private List<Attendee> attendeeList;
    private List<Attendee> backupCopy = null;
    private String[] parameters;
    private Class[] parameterTypes;
    private boolean editable;

    public AttendanceTableModel(List<Attendee> attendeeList) {
        this.attendeeList = attendeeList;
        this.parameters = attendeeList.get(0).getParameterList();
        this.parameterTypes = attendeeList.get(0).getParameterTypes();
    }

    public void backup() {backupCopy = attendeeList;}
    public void revertToBackup() {attendeeList = backupCopy; backupCopy = null;}
    public void removeBackup() {backupCopy = null;}

    public void setAttendeeList(List<Attendee> attendeeList) { this.attendeeList = attendeeList; }
    public List<Attendee> getAttendeeList() {return this.attendeeList;}


    private boolean isSaved = false;
    public boolean isSaved() {return isSaved;}
    public void setSaved(boolean e) {this.isSaved = e;}

    public void setEditable(boolean editable) { this.editable = editable; }

    public Attendee getAttendeeAt(int rowIndex) {return attendeeList.get(rowIndex);}

    @Override
    public int getRowCount() { return attendeeList.size(); }

    @Override
    public int getColumnCount() { return parameters.length; }

    @Override
    public Class<?> getColumnClass(int columnIndex) { return parameterTypes[columnIndex]; }

    @Override
    public String getColumnName(int column) {return parameters[column];}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { return attendeeList.get(rowIndex).getData(columnIndex); }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {return editable;}

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        try {
            attendeeList.get(rowIndex).setData(columnIndex, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearData() {

        this.attendeeList.clear();
        this.attendeeList.add(new Attendee(""));


    }
}
