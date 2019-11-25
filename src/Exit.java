import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("402ac537-442a-4b46-974b-f6beecd9ba42")
public class Exit implements Openable {
    @objid ("c19899a0-a014-4561-9100-5a84429c624a")
    private boolean opened;

    @objid ("3694a77a-e7c8-4cf4-a5b3-5ee501bad4a0")
    private List<Place> places = new ArrayList<Place> ();

}
