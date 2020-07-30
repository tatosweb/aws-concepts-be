package co.com.aws.training.awstrainingbe.component.poll.io.gateway;

import co.com.aws.training.awstrainingbe.component.poll.model.Poll;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {

    List<Poll> findById(Long id, Pageable pageable);
    List<Poll> findAll();

}
