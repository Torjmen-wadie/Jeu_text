import com.sun.istack.internal.Nullable;
import exceptions.NotRightKey;

public interface Usable {
    void use(@Nullable Object obj) throws NotRightKey;
}
