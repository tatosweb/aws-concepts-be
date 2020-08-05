package co.com.aws.training.awstrainingbe.component.poll.io.gateway;

import co.com.aws.training.awstrainingbe.component.poll.model.Poll;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public interface PollGateWay {

    List<Poll> getPolls();

    Optional<Poll> getPoll(Long id) throws EntityNotFoundException;

    void savePoll(Poll poll);
}
