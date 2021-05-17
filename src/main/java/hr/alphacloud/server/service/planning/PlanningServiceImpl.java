package hr.alphacloud.server.service.planning;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.planning.PlanDeleteCommand;
import hr.alphacloud.server.model.command.planning.PlanFilterCommand;
import hr.alphacloud.server.model.command.planning.PlanSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.dto.planning.PlanDTO;
import hr.alphacloud.server.model.dto.settings.position_template.PositionPropertiesDTO;
import hr.alphacloud.server.model.entity.reporting.BruttoBalanceProperties;
import hr.alphacloud.server.model.entity.reporting.planning.Plan;
import hr.alphacloud.server.repository.planning.PlanningRepository;
import hr.alphacloud.server.specification.PlanningSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PlanningServiceImpl implements PlanningService {

    private final PlanningRepository planningRepository;


    public PlanningServiceImpl(PlanningRepository planningRepository){

        this.planningRepository = planningRepository;
    }

    @Override
    @Transactional
    public ApiBaseDTO<PlanDTO> findPlan(ApiBaseCommand<PlanFilterCommand> command) {

/*        Date date = new Date();
        int year = Year.now().getValue();

        Positions positions = this.positionsRepository.findByCompanyIdAndSheetType(
                command.getCommand().getCompanyId(),"profitLoss");
        // properties
        List<BruttoBalanceDTO> bruttoBalanceList = BruttoBalanceDTO.of(
                this.bruttoBalanceRepository.findBruttoBooks(command.getCommand().getCompanyId(), "MONTHLY",
                        command.getCommand().getDateFrom(), command.getCommand().getDateTo()));
        if (bruttoBalanceList.isEmpty()) {
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.NO_DATA);
        }
       // List<PlanPeriod> planPeriodList = new ArrayList<>();
        List<String> reportDate = new ArrayList<>();

        bruttoBalanceList.forEach(report -> reportDate.add(report.getReport().getReportDateFrom().toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        PositionDTO positionDTO = objectMapper.convertValue(positions,
                new TypeReference<PositionDTO>() {
                });

        for (int i = 0; i < bruttoBalanceList.size(); i++) {
            List<BruttoBalanceProperties> properties = objectMapper.convertValue(bruttoBalanceList.get(i).getBruttoBalancePropertiesList(), new TypeReference<>() {
            });
            for (BruttoBalanceProperties property : properties) {
                if (!property.getKonto().equals(0)) {
                    if (property.getFinishedBalance() != null) {
                        int finalI = i;
                        positionDTO.getPositionProperties().forEach(item -> fillKontos(property, item, finalI));
                    }
                }
            }
        }
        sumKontos(positionDTO.getPositionProperties(), bruttoBalanceList.size() - 1);
        positionDTO.setForDate(reportDate);

        List<String> planPeriodList = new ArrayList<>();
        List<String> operatingIncomes = new ArrayList<>();

        operatingIncomes.add(positionDTO.getPositionProperties().get(0).toString());*/

/*        planPeriodList.addAll(positionDTO.getPositionProperties().forEach(item -> item.getPositionProperties()));
        planPeriodList.forEach(item -> positionDTO.getPositionProperties().forEach(x -> x.getTotalValue().addAll());

        planPeriodList.add(positionDTO.getPositionProperties())*/
       // return ApiBaseDTO.generateSuccessResponse(PositionDTO.of(positionDTO));

        final Optional<Plan> planOptional = this.planningRepository.findById(command.getCommand().getId());
        return planOptional.map(item -> ApiBaseDTO.generateSuccessResponse(PlanDTO.fromEntity(item))).orElse(
                ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

    @Override
    @Transactional
    public ApiBasePageDTO<PlanDTO> filterPlan(ApiBasePageCommand<PlanFilterCommand> command) {
        final Page<Plan> planPage = this.planningRepository.findAll(PlanningSpecification.getPlanFilterSpecification(command.getCommand()), command.getPaginationAndSorting().generatePagingAndSortingRequest());
        return ApiBasePageDTO.generateSuccessResponse(PlanDTO.of(planPage.getContent()),
                PageDTO.ofPage(planPage));
    }

    @Override
    @Transactional
    public ApiBaseDTO<PlanDTO> savePlan(ApiBaseCommand<PlanSaveCommand> command) {
        Plan plan = command.getCommand().convertToEntity();

        if (plan.getPlanPeriodList() != null) {
            plan.getPlanPeriodList().forEach(item -> item.setPlan(plan));
        }
        return ApiBaseDTO.generateSuccessResponse(PlanDTO.fromEntity(this.planningRepository.save(plan)));
    }

    @Override
    public ApiBaseDTO<Boolean> deletePlan(ApiBaseCommand<PlanDeleteCommand> command) {
        this.planningRepository.deleteById(command.getCommand().getId());
        return ApiBaseDTO.generateSuccessResponse(true);
    }

    /**
     * fill last child with kontos from bruttoBalanceList
     */
    private void fillKontos(BruttoBalanceProperties property, PositionPropertiesDTO positionProperties, int index) {
        if (positionProperties.getPositionProperties() != null) {
            positionProperties.getPositionProperties().forEach(item -> {
                if (item.getKonto() != null && item.getKonto().equals(property.getKonto())) {
                    item.getTotalValue().add(property.getFinishedBalance());
                } else if (item.getTotalValue().size() < index) {
                    item.getTotalValue().add(BigDecimal.ZERO);
                } else if (positionProperties.getTotalValue().size() <= index) {
                    positionProperties.getTotalValue().add(index, BigDecimal.ZERO);
                }
                fillKontos(property, item, index);
            });
        }
    }

    /**
     * sum final kontos and add values to nested parents
     */
    private void sumKontos(List<PositionPropertiesDTO> array, int index) {
        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).getPositionProperties().size() > 0) {
                    sumKontos(array.get(i).getPositionProperties(), index);
                    for (int j = array.get(i).getTotalValue().size(); j <= index; j++) {
                        array.get(i).getTotalValue().add(BigDecimal.ZERO);
                    }
                    for (int k = 0; k <= index; k++) {
                        array.get(i).getTotalValue().set(k, sumValues(array.get(i), k).stream().reduce(BigDecimal.ZERO, BigDecimal::add));
                    }
                }
            }
        }
    }
    /**
     * sum total value of child
     */
    private List<BigDecimal> sumValues(PositionPropertiesDTO parent, int index) {
        List<BigDecimal> finalList = new ArrayList<>();
        List<BigDecimal> fillData = new ArrayList<>();
        if (parent.getPositionProperties() != null) {
            for (PositionPropertiesDTO child : parent.getPositionProperties()) {
                if (child.getTotalValue().size() <= index) {
                    child.getTotalValue().add(BigDecimal.ZERO);
                }
                fillData.add(child.getTotalValue().get(index));
            }
        }
        finalList.add(fillData.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
        return finalList;
    }
}
