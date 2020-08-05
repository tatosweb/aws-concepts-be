package co.com.aws.training.awstrainingbe.component.poll.io.gateway;

import co.com.aws.training.awstrainingbe.component.poll.model.Poll;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PollGateWayImpl implements PollGateWay{

    private final PollRepository pollRepository;

    @Override
    public List<Poll> getPolls() {
        return pollRepository.findAll();
    }

    @Override
    public Optional<Poll> getPoll(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(pollRepository.getOne(id));
    }

    @Override
    public void savePoll(Poll poll) {
        pollRepository.save(poll);
    }
}
