package co.com.aws.training.awstrainingbe.component.poll.io.gateway;

import co.com.aws.training.awstrainingbe.component.poll.model.Poll;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PollGateWay {

    List<Poll> getPolls();

    Poll getPoll(Long id);

    void savePoll(Poll poll);
}
