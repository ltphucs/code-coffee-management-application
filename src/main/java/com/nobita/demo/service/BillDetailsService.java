package com.nobita.demo.service;

import com.nobita.demo.model.BillDetail;
import com.nobita.demo.model.ProductExport;
import com.nobita.demo.model.QuantitativeExport;

import java.util.List;

public interface BillDetailsService extends BaseService<BillDetail> {
    List<BillDetail> findByIdOrder(Long idOrder);

    boolean deleteByIdOrder(Long idOrder);

    List<QuantitativeExport> getQuantitativeExport(Long idOrder);

    List<ProductExport> getProductExport(Long idOrder);
}
