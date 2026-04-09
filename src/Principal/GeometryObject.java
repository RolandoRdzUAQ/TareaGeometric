package Principal;
import java.util.Date;

public class GeometryObject {
    private String color = "white";
    private boolean filled;
    private Date dateCreated;

    public GeometryObject() {
        dateCreated = new Date();
    }

    public GeometryObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
        this.dateCreated = new Date();
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public boolean isFilled() { return filled; }
    public void setFilled(boolean filled) { this.filled = filled; }
    public Date getDateCreated() { return dateCreated; }
    
    @Override
    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color + " and filled: " + filled;
    }
}
