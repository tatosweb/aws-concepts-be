package co.com.aws.training.awstrainingbe.component.poll.io.web;

import co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto.PollDto;
import co.com.aws.training.awstrainingbe.component.poll.service.PollService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1", produces = "application/json;charset=UTF-8")
public class PollController {

    private final String API_V1 = "V1";
    private final PollService service;

    public PollController(PollService service) {
        this.service = service;
    }

    @ApiOperation("")
    @GetMapping("/polls")
    public PollDto getPolls(@RequestParam(name = "size", defaultValue = "10") final int size) {
        return null;
    }

    @ApiOperation("")
    @PutMapping("/poll")
    @Validated
    public HttpServletResponse savePoll(@RequestBody @Valid PollDto preferences) {

        //TODO: do it
        return null;
    }

}
