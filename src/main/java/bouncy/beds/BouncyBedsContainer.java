package bouncy.beds;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.Collections;

public class BouncyBedsContainer  extends DummyModContainer {
    public BouncyBedsContainer() {
        super(createMetadata());
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        return true;
    }

    private static ModMetadata createMetadata() {
        ModMetadata metadata = new ModMetadata();

        metadata.modId = "bouncybeds";
        metadata.name = "Bouncy Beds";
        metadata.version = "1.0";
        metadata.description = "Make Beds Bouncy Again!";
        metadata.authorList = Collections.singletonList("EdgarAllen");
        metadata.credits = "Blame u/aslak123; it was their idea";

        return metadata;
    }
}