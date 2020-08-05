package co.com.aws.training.awstrainingbe.component.poll.io.web;

import co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto.PollDto;
import co.com.aws.training.awstrainingbe.component.poll.model.Poll;
import co.com.aws.training.awstrainingbe.component.poll.model.PollNotFoundException;
import co.com.aws.training.awstrainingbe.component.poll.service.PollService;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1", produces = "application/json;charset=UTF-8")
public class PollController {

    private final PollService service;

    @ApiOperation("")
    @GetMapping("/polls")
    public List<PollDto> getPolls(@RequestParam(name = "size", defaultValue = "10") final int size) {
        List<Poll> dataPolls = service.getPolls();
        List<PollDto> returnPolls = Lists.newArrayList();
        dataPolls.stream().forEach(poll ->
                returnPolls.add(PollDto.builder()
                        .age(poll.getAge())
                        .id(poll.getId())
                        .lastName(poll.getLastName())
                        .name(poll.getName())
                        .preferredLanguage(poll.getPreferredLanguage())
                        .profession(poll.getProfession())
                        .registrationDate(poll.getRegistrationDate())
                        .workPlace(poll.getWorkPlace()).build()));
        return returnPolls;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("")
    @PostMapping("/poll")
    @Validated
    public void savePoll(@RequestBody @Valid PollDto poll) {
        Poll dataPoll = new Poll();
        dataPoll.setAge(poll.getAge());
        dataPoll.setLastName(poll.getLastName());
        dataPoll.setName(poll.getName());
        dataPoll.setPreferredLanguage(poll.getPreferredLanguage());
        dataPoll.setProfession(poll.getProfession());
        dataPoll.setWorkPlace(poll.getWorkPlace());
        dataPoll.setRegistrationDate(OffsetDateTime.now());
        service.savePoll(dataPoll);
    }

    @ApiOperation("")
    @GetMapping("/poll/{id}")
    public PollDto getPoll(@PathVariable final Long id) {
        Optional<Poll> poll = null;
        try {
            poll = service.getPoll(id);
            return PollDto.builder()
                    .age(poll.get().getAge())
                    .id(poll.get().getId())
                    .lastName(poll.get().getLastName())
                    .name(poll.get().getName())
                    .preferredLanguage(poll.get().getPreferredLanguage())
                    .profession(poll.get().getProfession())
                    .registrationDate(poll.get().getRegistrationDate())
                    .workPlace(poll.get().getWorkPlace())
                    .build();
        } catch (EntityNotFoundException e) {
            //TODO: use log4j
            e.printStackTrace();
            throw new PollNotFoundException("id: " + id);
        }
    }
}
