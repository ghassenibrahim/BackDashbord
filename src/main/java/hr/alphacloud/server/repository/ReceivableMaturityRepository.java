package hr.alphacloud.server.repository;

import hr.alphacloud.server.model.entity.reporting.ReceivableMaturity;
import hr.alphacloud.server.repository.base.BaseReportRepository;

import java.util.Date;
import java.util.List;

public interface ReceivableMaturityRepository extends BaseReportRepository<ReceivableMaturity> {

    List<ReceivableMaturity> findAllByReportCompanyIdAndImportTypeAndDateFromAfterAndDateToBefore(Long companyId,
                                                                                                  String sheetType,
                                                                                                  Date dateFrom,
                                                                                                  Date dateTo);
}
