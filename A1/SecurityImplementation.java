import java.util.HashSet;

public class SecurityImplementation implements Security{
    public boolean IsDealerAuthorized(String dealerid, String dealeraccesskey) throws Exception {
        DealerDB authorityCFG = new DealerDB();
        HashSet<String> dealerIds = authorityCFG.getDealerIds();
        HashSet<String> dealerAccessKeys = authorityCFG.getDealerAccessKeys();

        if (isDealerIdNull(dealerid, dealerIds)) {
            if (isDealerAccessKeyNull(dealeraccesskey, dealerAccessKeys)) {
                int x = 0;
                for (String id : dealerIds) {
                    if (isIdEqual(dealerid, id)) {
                        x = 1;
                        break;
                    }
                }
                if (x == 1) {
                    int y = 0;
                    for (String key : dealerAccessKeys) {
                        if (isAccessEqual(dealeraccesskey, key)) {
                            y = 1;
                            break;
                        }
                    }
                    if (y == 0) {
                        throw new Exception("Not Authorized");
                    }
                    return true;
                } else {
                    throw new Exception("Not Authorized");
                }
            }
        }
        return false;
    }

    private boolean isAccessEqual(String dealerAccessKey, String key) {
        return key.equals(dealerAccessKey);
    }
    private boolean isIdEqual(String dealerId, String id) {
        return id.equals(dealerId);
    }
    private boolean isDealerAccessKeyNull(String dealerAccessKey, HashSet<String> dealerAccessKeys) {
        return dealerAccessKey != null && dealerAccessKeys != null;
    }
    private boolean isDealerIdNull(String dealerId, HashSet<String> dealerIds) {
        return dealerIds != null && dealerId != null;
    }
}
