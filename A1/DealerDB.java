import java.util.HashSet;
import java.util.Set;

public class DealerDB {
    public DealerDB() {
    }
    HashSet<String> dealerIds = new HashSet<>();
    HashSet<String> deelerAccessKeys = new HashSet<>();
    public HashSet<String> getDealerIds() {
        getIds(dealerIds);
        return dealerIds;
    }
    public HashSet<String> getDealerAccessKeys() {
        getAccessKeys(deelerAccessKeys);
        return deelerAccessKeys;
    }
    private void getAccessKeys(Set<String> dealerAccessKeys) {
        dealerAccessKeys.add("kkklas8882kk23nllfjj88290");
        dealerAccessKeys.add("yyumxe6660uu09jrhgbregrej");
        dealerAccessKeys.add("qwrewq87546kjn4545nk4545k");
        dealerAccessKeys.add("kjnbhj232jknb12323jb324hj");
        dealerAccessKeys.add("eiwfhbew87we6kjwenbfw98ew");
    }
    private void getIds(Set<String> dealerIds) {
        dealerIds.add("XXX-1234-ABCD-1234");
        dealerIds.add("XXX-5678-EFGH-5678");
        dealerIds.add("XXX-6574-IMFH-8765");
        dealerIds.add("XXX-8721-UNGH-2134");
        dealerIds.add("XXX-9867-QWER-0098");
    }

}
