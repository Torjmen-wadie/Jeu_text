import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("06f90622-7054-440e-9ae2-1ebb476a85d7")
public class Personnage {
    @objid ("e18a9c09-9ed9-4839-bcab-f06aa734a82e")
    private String Nom;

    @objid ("8e4ec9c0-cc08-464b-9262-52b46675593e")
    private int hopeless;

    @objid ("010c79fc-ce55-4705-93ab-af8382c45b7d")
    private int MAX_OBJ = 5;

    @objid ("bef605a4-b995-4fd9-9642-4e9d0dbadcd7")
    private List<Portable> objects = new ArrayList<Portable> ();

    @objid ("61e1c647-f34b-457a-8aca-e6cbd223b1e2")
    public void status() {
    }

    @objid ("410777b7-26d3-492d-a58f-8fd6f360a7ea")
    public void showObjects() {
    }

    @objid ("b75fe820-5156-4ab8-8657-9fbeb9ccddce")
    public void use(Object obj, Object obj) {
    }

    @objid ("20def20a-5035-480d-a601-6066dbdf6370")
    public void use(Object obj) {
    }

}
