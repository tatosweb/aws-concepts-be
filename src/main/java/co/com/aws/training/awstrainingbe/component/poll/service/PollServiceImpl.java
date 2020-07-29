package co.com.aws.training.awstrainingbe.component.poll.service;

import co.com.aws.training.awstrainingbe.component.poll.io.gateway.PollGateWay;
import co.com.aws.training.awstrainingbe.component.poll.model.Poll;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PollServiceImpl implements PollService{

    private final PollGateWay pollGateWay;

    public PollServiceImpl(PollGateWay pollGateWay) {
        this.pollGateWay = pollGateWay;
    }

    @Override
    public List<Poll> getPolls(Pageable pageable) {
        return pollGateWay.getPolls(pageable);
    }

    @Override
    public Poll getPoll(Long id) {
        return pollGateWay.getPoll(id);
    }

    @Override
    public void savePoll(Poll poll) {
        pollGateWay.savePoll(poll);
    }
}
