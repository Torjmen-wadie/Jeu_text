import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("5913f634-8105-4325-a644-2aaad2ee56da")
public class Place {
    @objid ("b09a4212-3f94-45a1-b217-bec96b541e71")
    private String Nom;

    @objid ("836945dd-7f95-427b-869f-b842147e5826")
    private List<Object> object = new ArrayList<Object> ();

    @objid ("2b370f19-a34a-4cdc-b123-c43b2fb60726")
    private List<Exit> exits = new ArrayList<Exit> ();

    @objid ("f644ae9e-2fe3-44a6-9ea2-b887daf7d22c")
    public Personnage personnage;

}
