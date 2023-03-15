public interface PartManager {
    enum PartResponse {
        SUCCESS,
        OUT_OF_STOCK,
        NO_LONGER_MANUFACTURED,
        INVALID_PART
    }
    // Submit part for manufacture and delivery.
    PartResponse SubmitPartForManufactureAndDelivery(int partNumber, int quantity, DeliveryAddress deliveryAddress);
}
