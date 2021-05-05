package com.nobita.demo.service;

import com.nobita.demo.model.ProductExport;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductExportService extends BaseService<ProductExport> {
    public List<ProductExport> findAllByDateExport(String dateIn, String dateOut);
}
