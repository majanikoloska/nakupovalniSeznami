package interceptori;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;
@ApplicationScoped

public class BelezenjeKlicevZrno {
    private int cnt = 0;
    private Logger log = Logger.getLogger(BelezenjeKlicevZrno.class.getName());
    public void sobiraj() {
        cnt++;
        log.info("Stevilo klicev je: "+cnt);
    }
}
