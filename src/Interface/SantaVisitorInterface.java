package Interface;

import data.Baby;
import data.Child;
import data.Kid;
import data.Teen;

public interface SantaVisitorInterface {
    public void visit (Baby child);
    public void visit (Kid child);
    public void visit (Teen child);

}
