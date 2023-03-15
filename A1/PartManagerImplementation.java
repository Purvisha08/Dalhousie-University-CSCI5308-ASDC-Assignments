import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PartManagerImplementation implements PartManager {
    public PartResponse SubmitPartForManufactureAndDelivery(int partNumber, int quantity, DeliveryAddress deliveryAddress) {
        HashMap<Integer,Integer> mokePartDetail = new HashMap<>();
        HashSet<Integer> upcomingNumber= new HashSet<>();

        getMokePartDetails(mokePartDetail);

        PartResponse success = getPartResponse(partNumber, quantity, mokePartDetail);
        if (success != null) return success;
        return PartResponse.NO_LONGER_MANUFACTURED;
    }

    private PartResponse getPartResponse(int partNumber, int quantity, HashMap<Integer, Integer> mokePartDetail) {
        for (Map.Entry<Integer,Integer> mapEntry : mokePartDetail.entrySet()){
            PartResponse success = getPartResponse(partNumber, quantity, mapEntry);
            if (success != null) return success;
        }
        return null;
    }

    private void getMokePartDetails(HashMap<Integer, Integer> mokePartDetail) {
        mokePartDetail.put(1234,100);
        mokePartDetail.put(5678,10);
        mokePartDetail.put(3003,11);
        mokePartDetail.put(9898,90);
        mokePartDetail.put(6567,87);
        mokePartDetail.put(8888,11);

    }
    private PartResponse getPartResponse(int partNumber, int quantity, Map.Entry<Integer, Integer> mapEntry) {
        if (comparePartNumber(partNumber, mapEntry)){
            if (mapEntry.getValue() >= quantity){
                return PartResponse.SUCCESS;
            }else {
                return PartResponse.OUT_OF_STOCK;
            }
        }
        return null;
    }
    private boolean comparePartNumber(int partNumber, Map.Entry<Integer, Integer> mapEntry) {
        return mapEntry.getKey() == partNumber;
    }

    private boolean isaBoolean(HashMap<Integer, Integer> mokePartDetail, HashSet<Integer> futureNumbers) {
        return mokePartDetail != null || futureNumbers != null;
    }
}
