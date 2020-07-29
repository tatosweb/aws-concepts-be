package co.com.aws.training.awstrainingbe.component.poll.io.gateway;

import co.com.aws.training.awstrainingbe.component.poll.model.Poll;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PollGateWayImpl implements PollGateWay{

    private final PollRepository pollRepository;

    @Override
    public List<Poll> getPolls(Pageable pageable) {
        return pollRepository.find(pageable);
    }

    @Override
    public Poll getPoll(Long id) {
        return pollRepository.getOne(id);
    }

    @Override
    public void savePoll(Poll poll) {
        pollRepository.save(poll);
    }
}
