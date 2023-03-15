public interface Security {

    // Returns whether the dealer is authorized to order parts on behalf of their customers.
    boolean IsDealerAuthorized(String dealerid, String dealeraccesskey) throws Exception;
}
