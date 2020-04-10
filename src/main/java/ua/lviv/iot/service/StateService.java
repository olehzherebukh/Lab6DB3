package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.ArtistEntity;
import ua.lviv.iot.domain.StateEntity;
import ua.lviv.iot.exceptions.NoSuchArtistException;
import ua.lviv.iot.exceptions.NoSuchStateException;
import ua.lviv.iot.repository.ArtistRepository;
import ua.lviv.iot.repository.StateRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {
    @Autowired
    StateRepository stateRepository;

    public StateEntity getState(Integer state_id) throws NoSuchStateException {
        StateEntity state = stateRepository.findById(state_id).get();
        if (state == null) throw new NoSuchStateException();
        return state;
    }

    public List<StateEntity> getAllState() {
        return stateRepository.findAll();
    }

    @Transactional
    public void createState(StateEntity state) {
        stateRepository.save(state);
    }

    @Transactional
    public void updateState(StateEntity uState, Integer state_id) throws NoSuchStateException {
        StateEntity state = stateRepository.findById(state_id).get();
        if (state == null) throw new NoSuchStateException();
        state.setStateId(uState.getStateId());
        state.setNameOfState(uState.getNameOfState());
    }

    @Transactional
    public void deleteState(Integer state_id) throws NoSuchStateException {
        Optional state = stateRepository.findById(state_id);

        if (!state.isPresent()) throw new NoSuchStateException();
        stateRepository.delete((StateEntity) state.get());
    }
}
