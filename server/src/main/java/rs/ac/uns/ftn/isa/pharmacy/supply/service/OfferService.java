package rs.ac.uns.ftn.isa.pharmacy.supply.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.supply.dto.OfferMapper;
import rs.ac.uns.ftn.isa.pharmacy.supply.dto.OfferRequestDto;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.*;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.Offer;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.OrderedProduct;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.SupplierStock;
import rs.ac.uns.ftn.isa.pharmacy.supply.repository.OfferRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.repository.OrderedProductRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.repository.SupplierStockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final SupplierStockRepository supplierStockRepository;
    private final OfferMapper offerMapper;

    public OfferService(
            OfferRepository offerRepository,
            OrderedProductRepository orderedProductRepository,
            SupplierStockRepository supplierStockRepository,
            OfferMapper offerMapper
    ) {
        this.offerRepository = offerRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.supplierStockRepository = supplierStockRepository;
        this.offerMapper = offerMapper;
    }

    public void create(OfferRequestDto dto)
            throws InvalidEntityException, LateDeadlineException, InvalidForeignKeyException,
            EntityExistsException, InsufficientProductAmountException, ExpiredException
    {
        Offer offer = offerMapper.dtoToObject(dto);
        offer.validateBeforeChange();
        if (offerExists(offer.getPurchaseOrder().getId(), offer.getSupplier().getPersonId()))
            throw new EntityExistsException("Offer");
        if (isSupplierStockedUp(offer.getPurchaseOrder().getId(), offer.getSupplier().getPersonId())) {
            offer.setStatus(Offer.Status.PENDING);
            offerRepository.save(offer);
        }
        else throw new InsufficientProductAmountException();
    }

    public void update(OfferRequestDto dto)
            throws EntityNotFoundException, LateDeadlineException, InvalidEntityException, ExpiredException
    {
        Offer offer = getOffer(dto.getPurchaseOrderId(), dto.getSupplierId());
        if (offer == null) throw new EntityNotFoundException("Offer");
        offer.setPrice(dto.getPrice());
        offer.setDeliveryDeadline(dto.getDeliveryDeadline());
        offer.validateBeforeChange();
        offerRepository.save(offer);
    }

    public List<Offer> getByStatus(Offer.Status status, long supplierId) {
        return offerRepository.getByStatusAndSupplier(status, supplierId);
    }

    private boolean offerExists(long purchaseOrderId, long supplierId) {
        return getOffer(purchaseOrderId, supplierId) != null;
    }

    private Offer getOffer(long purchaseOrderId, long supplierId) {
        return offerRepository.getByPurchaseOrderAndSupplier(purchaseOrderId, supplierId);
    }

    private boolean isSupplierStockedUp(long purchaseOrderId, long supplierId) {
        List<OrderedProduct> orderedProducts = orderedProductRepository.getByPurchaseOrderId(purchaseOrderId);
        List<SupplierStock> supplierStocks = supplierStockRepository.getBySupplierId(supplierId);

        for (OrderedProduct orderedProduct : orderedProducts) {
            Optional<SupplierStock> productInStock = supplierStocks.stream().findAny();
            if (productInStock.isEmpty() || productInStock.get().getAmount() < orderedProduct.getAmount())
                return false;
        }

        return true;
    }

    public List<Offer> getBySupplierId(long supplierId) {
        return offerRepository.getBySupplier(supplierId);
    }

    public List<Offer> getByPurchaseOrderId(long purchaseId) {
        return offerRepository.getByPurchaseOrder(purchaseId);
    }

    public Boolean checkSupplies(long purchaseOrderId, long supplierId) {
        return isSupplierStockedUp(purchaseOrderId, supplierId);
    }
}
